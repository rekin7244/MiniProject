package com.kh.miniProject.controller;

import java.awt.Font;

import javax.swing.JLabel;

import com.kh.miniProject.view.StoryPanel;

public class Letter extends JLabel implements Runnable{

	private String temp = "";
	private StoryPanel sp;
	private JLabel []  la= new JLabel[14] ;
	private String story = "소연아.....\n" 
						+"내가 태어나서 가장 잘한일이 무엇이냐고 \n"
						+"물어보면 20년전 너를 낳은 것이란다.\n" 
						+"너는 내 인생에 가장 큰 보석이고, 너를 키우며 부족함은 \n"
						+ "있었지만 정말 행복했었단다.\n" 
						+"그런데 문득 생각해보니.....\n "
						+ "이 엄마의 새로운 행복을 찾아보고 싶다는 생각이 들더구나.\n" 
						+ "너도 이제 성인이니 앞으로의 너의 인생을 스스로 계획해서 \n "
						+ "만들어 가야 하지 않겠니?\n"
						+"너에게 물려줄건 다른건 없고 오직 너희 할아버지때부터 2대째 \n"
						+ "이어 온 이 떡볶이 가게뿐이구나....\n" 
						+"할아버지와 아버지때의 명성을 이어 이 쓰러져 가는 가게를 \n "
						+ "다시 잘 운영하길 이 엄마는 진심으로 바란단다.\n"
						+"그러면 언제나 포기하지말고 성실히 살아가길 바란다. \n" ;   
	
	
	
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
			la[count].setFont(new Font("굴림", Font.ITALIC, 25));
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
