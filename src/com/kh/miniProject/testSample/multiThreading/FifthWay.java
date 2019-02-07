package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

public class FifthWay {
	public static void main(String argc[]) {
		System.out.println("Main thread starts here...");

		Runnable myThreadTask5 = new Runnable(){	//Runnable °´Ã¼
			@Override
			public void run(){
				for(int i = 0; i<5; i++) {
					System.out.println("TICK TICK " + i);
					try {
						TimeUnit.MICROSECONDS.sleep((long)Math.random()*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(myThreadTask5);
		thread.start();

		System.out.println("Main thread ends here...");
	}
}