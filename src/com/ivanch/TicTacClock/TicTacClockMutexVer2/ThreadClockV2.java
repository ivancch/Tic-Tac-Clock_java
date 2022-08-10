package com.ivanch.TicTacClock.TicTacClockMutexVer2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ThreadClockV2 implements Runnable{

	private final Object monitor2;
	private final Thread self;
	private final FileOutputStream outputStream;
	
	public ThreadClockV2(Object monitor2, FileOutputStream outputStream) {
		this.monitor2 = monitor2;
		self = new Thread (this);
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
		
		while(!self.isInterrupted()) {
			echoClock();
			synchronized (monitor2) {
				monitor2.notify();
				try {
					monitor2.wait();
				} catch (InterruptedException e) { 
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	private void echoClock() {
		try {
			outputStream.write("clock!\n".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}
	
}

