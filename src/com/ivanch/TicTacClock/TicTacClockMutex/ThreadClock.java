package com.ivanch.TicTacClock.TicTacClockMutex;

import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadClock implements Runnable{

	private final Object[] monitors;
	private final Thread self;
	private final int iterations;
	private final FileOutputStream outputStream;
	
	public ThreadClock(Object[] monitors, int numberOfIterations, FileOutputStream outputStream) {
		this.monitors = monitors;
		self = new Thread (this);
		iterations = numberOfIterations;
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
		int i = 0;
		while (true) {

			echoClock();
			
			synchronized (monitors[2]) {
				monitors[2].notify();					
			}				
			
			if (i == iterations - 1)
				break; //loop exit condition
			i++;
			
			try {
				synchronized (monitors[1]) {
					monitors[1].wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	
	private void echoClock() {
		try {
			outputStream.write("clock!\n".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}
	
}












