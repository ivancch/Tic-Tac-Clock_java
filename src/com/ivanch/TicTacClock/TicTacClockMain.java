package com.ivanch.TicTacClock;

import java.io.FileWriter;
import java.io.IOException;

import com.ivanch.TicTacClock.TicTacClockMutex.ThreadTic;

public class TicTacClockMain {

	public static void main(String[] args) {
		
		System.out.println("Program start");
		
		TicTacClockMutex();
		
		
	}
	
	public static void TicTacClockMutex() {
		Object[] monitors = new Object[3];
		int numberOfIterations = 10;
		
		
		try {
			FileWriter writer = new FileWriter("resultForMutex.txt", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ThreadTic th1 = new ThreadTic(monitors, numberOfIterations);
		
		
	}


}
