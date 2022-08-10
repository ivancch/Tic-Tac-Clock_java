package com.ivanch.TicTacClock.TicTacClockMutexVer2;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ControlThread implements Runnable {
	
	private final Object[] monitors;
	private final Thread self;
	private final List<Thread> workingThreads;
	private final int iterations;
	
	public ControlThread(Object[] monitors, int numberOfIterations, List<Thread> workingThreads) {
		this.monitors = monitors;
		self = new Thread(this);
		this.workingThreads = workingThreads;
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
		
		try {
			workingThreads.get(0).start();
			TimeUnit.MILLISECONDS.sleep(10);
			workingThreads.get(1).start();
			TimeUnit.MILLISECONDS.sleep(10);
			workingThreads.get(2).start();
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		try {
			for (int i = 0; i < iterations - 1; i++) {
				synchronized(monitors[0]) {
					monitors[0].notify();
					monitors[0].wait();
				}
				synchronized(monitors[1]) {
					monitors[1].notify();
					monitors[1].wait();
				}
				synchronized(monitors[2]) {
					monitors[2].notify();
					monitors[2].wait();
				}
			}
			workingThreads.get(0).interrupt();
			workingThreads.get(1).interrupt();
			workingThreads.get(2).interrupt();
			
			workingThreads.get(0).join();
			workingThreads.get(1).join();
			workingThreads.get(2).join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
