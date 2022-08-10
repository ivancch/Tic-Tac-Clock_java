package com.ivanch.TicTacClock.TicTacClockMutexVer2;

import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadTacV2 implements Runnable {

	private final Object monitor;
	private final Thread self;
	private final FileOutputStream outputStream;
	
	public ThreadTacV2(Object monitor, FileOutputStream outputStream) {
		this.monitor = monitor;
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
		
	}
	
	private void echoTac() {
		try {
			outputStream.write("tac - ".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}

}

