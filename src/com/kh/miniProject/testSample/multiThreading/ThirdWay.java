package com.kh.miniProject.testSample.multiThreading;

import java.util.concurrent.TimeUnit;

//Runnable ��ü�� ������ ���� �����ϴ� ���
public class ThirdWay {
	public static void main(String argc[]) throws InterruptedException {
		System.out.println("Main thread starts here...");

		new MyThreadTask3();
		//Thread t = new MyThreadTask(); 	//Thread Ÿ�� ���� ���� �� ���� 
		new MyThreadTask3();

		System.out.println("Main thread ends here...");
	}
}

//Runnable �������̽� ����
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
		
		//Thread(Runnable).start()�� run()�޼ҵ� �����Ŵ
		new Thread(this).start();		
	}
}