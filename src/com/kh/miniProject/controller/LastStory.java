package com.kh.miniProject.controller;

import java.awt.Font;

import javax.swing.JLabel;

import com.kh.miniProject.view.GuestPanel;
import com.kh.miniProject.view.StoryPanel;

public class LastStory extends JLabel implements Runnable {

	private String temp = "";
	private StoryPanel sp;
	private JLabel[] la = new JLabel[4];
	private String story = "엄마와 다시 만나기 위해선  \n" + "이 무너져 가는 떡볶이 가게를 \n"
			+ "다시 일으켜 세워야해!!\n" + "좋아!! 이제부터 시작이야.\n";

	public LastStory(StoryPanel sp) {
		this.sp = sp;
		int y = 100;

		for (int i = 0; i < 4; i++) {
			la[i] = new JLabel();
			la[i].setBounds(200, y, 1024, 80);
			y += 80;
			sp.add(la[i]);
		}

		Thread t = new Thread(this);
		t.start();
	}

	public void stop() {
		for (int i = 0; i < 4; i++) {
			sp.remove(la[i]);
		}
	}
	
	
	
	
	

	@Override
	public void run() {
		int count = 0;
		boolean b = true;
		for (int i = 0; i < story.length(); i++) {
			temp += story.charAt(i);
			la[count].setText(temp);
			la[count].setFont(new Font("굴림", Font.ITALIC, 40));
			repaint();

			if (story.charAt(i) == '\n') {
				count++;
				temp = "";
			}
			if(count == 3) {
				if(b) {
				new Walkking(sp);
				b = false;
				} 
			}

			try {
				Thread.sleep(95);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
