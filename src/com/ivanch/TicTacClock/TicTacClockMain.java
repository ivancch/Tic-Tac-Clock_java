package com.ivanch.TicTacClock;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.ivanch.TicTacClock.TicTacClockMutex.*;

public class TicTacClockMain {
	

	public static void main(String[] args) {
		
		TicTacClockMutex();
		
	}
	
	public static void TicTacClockMutex() {
		Object[] monitors = new Object[3];
		int numberOfIterations = 10;
		String FileNameForResult = "ResultMutex.txt";
		FileOutputStream outputStream = createOutputStream(FileNameForResult);
		
		ThreadTic th1 = new ThreadTic(monitors, numberOfIterations, outputStream);
		ThreadTac th2 = new ThreadTac(monitors, numberOfIterations, outputStream);
		ThreadClock th3 = new ThreadClock(monitors, numberOfIterations, outputStream);
		
//		th1.start();
//		th2.start();
//		th3.start();
//		
//		th1.join();
//		th2.join();
//		th3.join();
		
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
	
}
