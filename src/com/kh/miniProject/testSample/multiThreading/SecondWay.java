package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

//Thread ��ü�� ���ϴ� ������ �����ϴ� ���
public class SecondWay {
	public static void main(String argc[]) throws InterruptedException {
		System.out.println("Main thread starts here...");

		new MyThreadTask2().start();          //start() ȣ��
		Thread t = new MyThreadTask2();
		t.start();					//start() ȣ��

		System.out.println("Main thread ends here...");
	}
}


class MyThreadTask2 extends Thread {
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

	public MyThreadTask2() {
		this.id = ++count;
		//this.start();   	//���� 
	}
}