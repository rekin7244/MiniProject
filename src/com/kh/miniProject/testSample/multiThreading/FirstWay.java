package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

//Thread ��ü�� ������ ���� �����ϴ� ���
public class FirstWay {
	public static void main(String argc[]) throws InterruptedException {
		System.out.println("Main thread starts here...");

		new MyThreadTask();
		Thread t = new MyThreadTask();
		
		System.out.println("Main thread ends here...");
	}
}

//Thread class�� ��� �� run() �޼ҵ� �������̵�
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
	
	//start()�� run()�޼ҵ带 ȣ��
	//Thread ��ü�� cons���� run()�޼ҵ带 ȣ��
	public MyThreadTask() {
		this.id = ++count;
		this.start();
	}
}
