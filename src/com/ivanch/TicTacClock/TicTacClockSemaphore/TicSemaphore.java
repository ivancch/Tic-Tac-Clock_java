package com.ivanch.TicTacClock.TicTacClockSemaphore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class TicSemaphore implements Runnable {
	
	private ClockStatus status;
	private final Semaphore semaphore;
	private final Thread self;
	private final FileOutputStream outputStream;
	private final int iterations;
	
	public TicSemaphore(Semaphore semaphore, 
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
	
	private void echoTic() {
		try {
			outputStream.write("Tic - ".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}
}
