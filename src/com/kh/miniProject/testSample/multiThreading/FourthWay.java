package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

public class FourthWay {
	public static void main(String argc[]) throws InterruptedException {
		System.out.println("Main thread starts here...");

		new Thread(new MyThreadTask4()).start(); 	//start()호출
		Thread t = new Thread(new MyThreadTask4());
		t.start();	//start()호출
		
		System.out.println("Main thread ends here...");
	}
}


class MyThreadTask4 implements Runnable {	
	private static int count = 0;
	private int id;
	@Override
	public void run(){
		for(int i = 0; i<5; i++) {
			System.out.println("<" + id + ">TICK TICK " + i);
			try {
				TimeUnit.MICROSECONDS.sleep((long)Math.random()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public MyThreadTask4() {
		this.id = ++count;
		//new Thread(this).start();		//제거
	}
}