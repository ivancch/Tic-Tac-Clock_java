package com.ivanch.TicTacClock.TicTacClockSemaphore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import com.ivanch.TicTacClock.TicTacClockMain;

public class TacSemaphore implements Runnable{

	private final Semaphore semaphore;
	private final Thread self;
	private final FileOutputStream outputStream;
	private final int iterations;
	
	public TacSemaphore(Semaphore semaphore, 
						  int iterations, 
						  FileOutputStream outputStream) {
		self = new Thread(this);
		this.semaphore = semaphore;
		this.iterations = iterations;
		this.outputStream = outputStream; 
	}
	
	public Thread getThread() {
		return self;
	}
	
	public void start() {
		self.start();
	}
	
	public void join() {
		try {
			self.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		for (int i = 0; i < iterations; i++) {
			
			while (TicTacClockMain.statusSem != ClockStatus.TAC)
				semaphore.release();
			
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			echoTac();//write in file
			TicTacClockMain.statusSem = ClockStatus.CLOCK;
			semaphore.release();
		}
	}
	
	private void echoTac() {
		try {
			outputStream.write("tac - ".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}
}
