package com.ivanch.TicTacClock.TicTacClockMutexVer1;

import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadTicV1 implements Runnable {

	private final Object[] monitors;
	private final Thread self;
	private final int iterations;
	private final FileOutputStream outputStream;
	
	public ThreadTicV1(Object[] monitors, int numberOfIterations, FileOutputStream outputStream) {
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
			
			echoTic();
			
			synchronized (monitors[0]) {
				monitors[0].notify();					
			}				
			
			if (i == iterations - 1)
				break; //loop exit condition
			i++;
			
			try {
				synchronized (monitors[2]) {
					monitors[2].wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void echoTic() {
		try {
			outputStream.write("Tic - ".getBytes());
		} catch (IOException e) { e.printStackTrace(); }
	}

}

















