package com.ivanch.TicTacClock.TicTacClockMutexVer2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ThreadTicV2 implements Runnable {

	private final Object monitor0;
	private final Thread self;
	private final FileOutputStream outputStream;
	
	public ThreadTicV2(Object monitor0, FileOutputStream outputStream) {
		this.monitor0 = monitor0;
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
			echoTic();
			synchronized (monitor0) {
				monitor0.notify();
				try {
					monitor0.wait();
				} catch (InterruptedException e) { 
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	private void echoTic() {
		try {
			outputStream.write("Tic - ".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}

}

