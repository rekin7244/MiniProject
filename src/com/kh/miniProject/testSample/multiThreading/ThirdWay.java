package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

//Runnable 객체를 만들자 마자 실행하는 경우
public class ThirdWay {
	public static void main(String argc[]) throws InterruptedException {
		System.out.println("Main thread starts here...");

		new MyThreadTask3();
		//Thread t = new MyThreadTask(); 	//Thread 타입 변로 받을 수 없음 
		new MyThreadTask3();

		System.out.println("Main thread ends here...");
	}
}

//Runnable 인터페이스 구현
class MyThreadTask3 implements Runnable {	//extends Thread --> implements Runnable
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

	public MyThreadTask3() {
		this.id = ++count;
		//this.start();
		
		//Thread(Runnable).start()로 run()메소드 실행시킴
		new Thread(this).start();		
	}
}