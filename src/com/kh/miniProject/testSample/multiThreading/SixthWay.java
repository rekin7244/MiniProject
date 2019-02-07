package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

public class SixthWay {
	public static void main(String argc[]) {
		System.out.println("Main thread starts here...");

		new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i = 0; i<5; i++) {
					System.out.println("TICK TICK " + i);
					try {
						TimeUnit.MICROSECONDS.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		System.out.println("Main thread ends here...");
	}
}