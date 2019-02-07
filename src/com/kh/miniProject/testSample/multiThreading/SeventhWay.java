package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

//SixthWay를 Lambda로 구현
public class SeventhWay {
	public static void main(String argc[]) {
		System.out.println("Main thread starts here...");

		new Thread(()->{
				for(int i = 0; i<5; i++) {
					System.out.println("TICK TICK " + i);
					try {
						TimeUnit.MICROSECONDS.sleep((long)Math.random()*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		).start();

		System.out.println("Main thread ends here...");
	}
}