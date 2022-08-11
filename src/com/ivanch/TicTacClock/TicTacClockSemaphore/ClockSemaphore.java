package com.ivanch.TicTacClock.TicTacClockSemaphore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ClockSemaphore implements Runnable{
	
	private ClockStatus status;
	private final Semaphore semaphore;
	private final Thread self;
	private final FileOutputStream outputStream;
	private final int iterations;
	
	public ClockSemaphore(Semaphore semaphore, 
						  ClockStatus status, 
						  int iterations, 
						  FileOutputStream outputStream) {
		self = new Thread(this);
		this.semaphore = semaphore;
		this.status = status;
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
		
	}
	
	private void echoClock() {
		try {
			outputStream.write("clock!\n".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}
	
}
