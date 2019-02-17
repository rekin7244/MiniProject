package com.kh.miniProject.controller;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.kh.miniProject.view.StoryPanel;

public class Story extends JLabel implements Runnable {
	private String temp = "";
	private String story = "아빠는 내가 태어나기도 전에 돌아가시고... \n" 
	         + "엄마 혼자서 지금까지 나를 키워주셨다.\n" + 
	         "형제도 다른 친척도 없던 나에게 유일한 가족은  \n"
	         + "엄마였고,  풍족하진 않았지만 \n" 
			 + "엄마와 단 둘이 살기에는 모든게 평탄했다.\n"

			+ "그렇게 나의 그리고 우리의 행복은 계속 될줄 \n"

			+ "알았는데.... 어느날 엄마가 이런 쪽지한장만 \n"
			+ "남긴채 나를 두고 떠나버렸다.\n";
	private StoryPanel sp;
	private JLabel[] la = new JLabel[8];
	private Thread t;

	public Story(StoryPanel sp/* , String[] story */) {
		this.sp = sp;
		int y = 100; 

		for(int i = 0 ; i<8 ; i++) {
			la[i]=new JLabel();
			la[i].setBounds(50, y, 1024,50);
			y += 50;
			sp.add(la[i]);
		}
		/*la[0] = new JLabel(); // 첫줄
		la[1] = new JLabel(); // 두번째줄
		// la2 = new JLabel()
		la[0].setBounds(0, 0, 1024, 50);
		la[1].setBounds(0, 50, 1024, 50);
		sp.add(la[1]);
		sp.add(la[0]);*/
		t = new Thread(this);
		t.start();
	}
	public void stop() {
		for(int i = 0 ; i<8 ; i++) {
			sp.remove(la[i]);
		}
	}
	
	@Override
	public void run() {
		int count =0;
		for (int i = 0; i < story.length(); i++) {
			temp += story.charAt(i);
			la[count].setText(temp);
			la[count].setFont(new Font("굴림", Font.ITALIC, 40));
			repaint();
			if(story.charAt(i) == '\n') {
				count++; 
				temp = "" ;
			}
				try {
					Thread.sleep(95);
				} catch (InterruptedException e) {
				}
			}
			
		}

	}
