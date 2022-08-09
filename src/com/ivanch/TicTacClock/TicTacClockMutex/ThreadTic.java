package com.ivanch.TicTacClock.TicTacClockMutex;

import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadTic implements Runnable {

	private final Object[] monitors;
	private final Thread self;
	private final int iterations;
	private final FileOutputStream outputStream;
	
	
	public ThreadTic(Object[] monitors, int numberOfIterations, FileOutputStream outputStream) {
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
		for (int i = 0; i < iterations; i++) {
			System.out.println("1-");
			echoTic();
		}
	}
	
	private void echoTic() {
		try {
			outputStream.write("Tic-".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}

}

















