package com.ivanch.TicTacClock.TicTacClockMutex;

public class ThreadTic implements Runnable{

	private final Object[] monitors;
	private final Thread self;
	private final int iterations;
	
	
	public ThreadTic(Object[] monitors, int numberOfIterations) {
		this.monitors = monitors;
		self = new Thread (this);
		iterations = numberOfIterations;
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
			
			
		}
		
	}

}
