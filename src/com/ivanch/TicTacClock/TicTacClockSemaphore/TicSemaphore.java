package com.ivanch.TicTacClock.TicTacClockSemaphore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import com.ivanch.TicTacClock.TicTacClockMain;

public class TicSemaphore implements Runnable {
	
	private final Semaphore semaphore;
	private final Thread self;
	private final FileOutputStream outputStream;
	private final int iterations;
	
	public TicSemaphore(Semaphore semaphore, 
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
			
			while (TicTacClockMain.statusSem != ClockStatus.TIC)
				semaphore.release();
			
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			echoTic();//write in file
			TicTacClockMain.statusSem = ClockStatus.TAC;
			semaphore.release();
		}
	}
	
	private void echoTic() {
		try {
			outputStream.write("Tic - ".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}
}
