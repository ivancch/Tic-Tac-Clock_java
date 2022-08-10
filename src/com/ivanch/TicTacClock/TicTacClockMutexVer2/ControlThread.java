package com.ivanch.TicTacClock.TicTacClockMutexVer2;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

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
		
		
	}
	
	
}
