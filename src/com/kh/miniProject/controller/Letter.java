package com.kh.miniProject.controller;

import java.awt.Font;

import javax.swing.JLabel;

import com.kh.miniProject.view.StoryPanel;

public class Letter extends JLabel implements Runnable{

	private String temp = "";
	private StoryPanel sp;
	private JLabel []  la= new JLabel[14] ;
	private String story = "�ҿ���.....\n" 
						+"���� �¾�� ���� �������� �����̳İ� \n"
						+"����� 20���� �ʸ� ���� ���̶���.\n" 
						+"�ʴ� �� �λ��� ���� ū �����̰�, �ʸ� Ű��� �������� \n"
						+ "�־����� ���� �ູ�߾��ܴ�.\n" 
						+"�׷��� ���� �����غ���.....\n "
						+ "�� ������ ���ο� �ູ�� ã�ƺ��� �ʹٴ� ������ �������.\n" 
						+ "�ʵ� ���� �����̴� �������� ���� �λ��� ������ ��ȹ�ؼ� \n "
						+ "����� ���� ���� �ʰڴ�?\n"
						+"�ʿ��� �����ٰ� �ٸ��� ���� ���� ���� �Ҿƹ��������� 2��° \n"
						+ "�̾� �� �� ������ ���Ի��̱���....\n" 
						+"�Ҿƹ����� �ƹ������� ���� �̾� �� ������ ���� ���Ը� \n "
						+ "�ٽ� �� ��ϱ� �� ������ �������� �ٶ��ܴ�.\n"
						+"�׷��� ������ ������������ ������ ��ư��� �ٶ���. \n" ;   
	
	
	
	public Letter(StoryPanel sp)  {
		this.sp = sp;
		int y = 100;
		
		for(int i = 0; i < 14; i++) {
			la[i] = new JLabel();
			la[i].setBounds(50, y, 1024, 30);
			y += 30;
			sp.add(la[i]);
		}
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stop() {
		for (int i = 0; i < 14; i++) {
			sp.remove(la[i]);
		}
	}
	
	
	@Override
	public void run() {
		int count = 0;
		for(int i = 0; i <story.length(); i++) {
			temp += story.charAt(i);
			la[count].setText(temp);
			la[count].setFont(new Font("����", Font.ITALIC, 25));
			repaint();
			
			if(story.charAt(i) == '\n') {
				count++;
				temp = "";
			}
			
			try {
				Thread.sleep(95);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
