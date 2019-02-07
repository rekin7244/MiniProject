package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

//Thread 객체를 만들자 마자 실행하는 경우
public class FirstWay {
	public static void main(String argc[]) throws InterruptedException {
		System.out.println("Main thread starts here...");

		new MyThreadTask();
		Thread t = new MyThreadTask();
		
		System.out.println("Main thread ends here...");
	}
}

//Thread class를 상속 후 run() 메소드 오버라이딩
class MyThreadTask extends Thread {
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
	
	//start()로 run()메소드를 호출
	//Thread 객체의 cons에서 run()메소드를 호출
	public MyThreadTask() {
		this.id = ++count;
		this.start();
	}
}
