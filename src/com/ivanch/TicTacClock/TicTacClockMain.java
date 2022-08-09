package com.ivanch.TicTacClock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.ivanch.TicTacClock.TicTacClockMutexVer1.*;

public class TicTacClockMain {
	

	public static void main(String[] args) throws InterruptedException {
		
		TicTacClockMutexVersionOne();
		
	}
	
	public static void TicTacClockMutexVersionOne() throws InterruptedException {
		Object[] monitors = new Object[3];
		fillArrayMonitors(monitors);
		int numberOfIterations = 11;
		String FileNameForResult = "ResultMutexVer1.txt";
		FileOutputStream outputStream = createOutputStream(FileNameForResult);
		
		try {
			outputStream.write("Start TicTacClockMutexVersionOne()\n".getBytes());
		} catch (IOException e) { }
		
		//create synchronized threads
		ThreadTicV1 th1 = new ThreadTicV1(monitors, numberOfIterations, outputStream);
		ThreadTacV1 th2 = new ThreadTacV1(monitors, numberOfIterations, outputStream);
		ThreadClockV1 th3 = new ThreadClockV1(monitors, numberOfIterations, outputStream);
		
		//run threads in turn with an intermediate timer to run sequentially
		th1.start();
		TimeUnit.MILLISECONDS.sleep(10);
		th2.start();
		TimeUnit.MILLISECONDS.sleep(10);
		th3.start();
		
		th1.join();
		th2.join();
		th3.join();
		
		try {
			outputStream.write("End TicTacClockMutexVersionOne()\n".getBytes());
		} catch (IOException e) { }
		
		closeOutputStream(outputStream);
	}
	
	public static FileOutputStream createOutputStream(String fileName)
	{
		try {
			return new FileOutputStream(fileName);
		} catch (FileNotFoundException e) { e.printStackTrace(); }
		return null;
	}
	
	public static void closeOutputStream(FileOutputStream fos) {
		try {
			fos.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private static void fillArrayMonitors(Object[] monitors) {
		for (int i = 0; i < monitors.length; i++) {
			monitors[i] = new Object();
		}
	}
	
}


















