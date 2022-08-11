package com.ivanch.TicTacClock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.ivanch.TicTacClock.TicTacClockMutexVer1.*;
import com.ivanch.TicTacClock.TicTacClockMutexVer2.*;
import com.ivanch.TicTacClock.TicTacClockSemaphore.*;

public class TicTacClockMain {
	
	private final static int numberOfIterationsAllTreads = 10;
	
	public static void main(String[] args) throws InterruptedException {
		
		System.out.print("Start of main\n");
		
		
		long time1 = System.currentTimeMillis();//for estimate execution time
		TicTacClockMutexVersionOne();
		time1 = System.currentTimeMillis() - time1;
		
		
		long time2 = System.currentTimeMillis();		
		TicTacClockMutexVersionTwo();
		time2 = System.currentTimeMillis() - time2;
		
		
		long time3 = System.currentTimeMillis();		
		TicTacClockSemaphore();
		time3 = System.currentTimeMillis() - time3;
		
		System.out.print(String.format("First synchronization method using "
				+ "mutex for %d iterations ran %d milliseconds\n",
				numberOfIterationsAllTreads,
				time1));
		
		System.out.print(String.format("Second synchronization method using "
				+ "mutex for %d iterations ran %d milliseconds\n",
				numberOfIterationsAllTreads,
				time2));
		
		System.out.print(String.format("Third synchronization method using "
				+ "semaphore for %d iterations ran %d milliseconds\n",
				numberOfIterationsAllTreads,
				time3));
		
		System.out.println("End of main");
		
	}

	private static void TicTacClockMutexVersionOne() throws InterruptedException {
		Object[] monitors = new Object[3];
		fillArrayMonitors(monitors);
		int numberOfIterations = numberOfIterationsAllTreads;
		String fileNameForResult = "ResultMutexVer1.txt";
		FileOutputStream outputStream = createOutputStream(fileNameForResult);
		
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
	
	private static void TicTacClockMutexVersionTwo() {
		Object[] monitors = new Object[3];
		fillArrayMonitors(monitors);
		int numberOfIterations = numberOfIterationsAllTreads;
		String fileNameForResult = "ResultMutexVer2.txt";
		FileOutputStream outputStream = createOutputStream(fileNameForResult);
		
		try {
			outputStream.write("Start TicTacClockMutexVersionTwo()\n".getBytes());
		} catch (IOException e) { }
		
		//creating and adding worker threads to List
		List<Thread> workingThreads = new ArrayList<Thread>();
		workingThreads.add(new ThreadTicV2(monitors[0], outputStream).getThread());
		workingThreads.add(new ThreadTacV2(monitors[1], outputStream).getThread());
		workingThreads.add(new ThreadClockV2(monitors[2], outputStream).getThread());
		
		//create a control flow to manage and synchronize worker threads
		ControlThread controlThread 
			= new ControlThread(monitors, numberOfIterations, workingThreads);
		controlThread.start();
		controlThread.join();
		
		try {
			outputStream.write("End TicTacClockMutexVersionTwo()\n".getBytes());
		} catch (IOException e) { }
		
	}
	
	//global variable to allocate hours when the semaphore is running
	public static ClockStatus statusSem = ClockStatus.TIC;	
	
	private static void TicTacClockSemaphore() throws InterruptedException {
		Semaphore semaphore = new Semaphore(1);
		int iterations = numberOfIterationsAllTreads;
		String fileNameForResult = "ResultSemaphore.txt";
		FileOutputStream outputStream = createOutputStream(fileNameForResult);
		
		try {
			outputStream.write("Start TicTacClockSemaphore()\n".getBytes());
		} catch (IOException e) { }
		
		TicSemaphore th1 = new TicSemaphore(semaphore, iterations, outputStream);
		TacSemaphore th2 = new TacSemaphore(semaphore, iterations, outputStream);
		ClockSemaphore th3 = new ClockSemaphore(semaphore, iterations, outputStream);
		th1.start();
		th2.start();
		th3.start();
		
		th1.join();
		th2.join();
		th3.join();
		
		try {
			outputStream.write("End TicTacClockSemaphore()\n".getBytes());
		} catch (IOException e) { }
		
	}
	
	private static FileOutputStream createOutputStream(String fileName)
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
