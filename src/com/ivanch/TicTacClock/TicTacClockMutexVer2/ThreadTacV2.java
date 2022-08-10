package com.ivanch.TicTacClock.TicTacClockMutexVer2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ThreadTacV2 implements Runnable {

	private final Object monitor1;
	private final Thread self;
	private final FileOutputStream outputStream;
	
	public ThreadTacV2(Object monitor1, FileOutputStream outputStream) {
		this.monitor1 = monitor1;
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
			echoTac();
			synchronized (monitor1) {
				monitor1.notify();
				try {
					monitor1.wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	private void echoTac() {
		try {
			outputStream.write("tac - ".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}

}

