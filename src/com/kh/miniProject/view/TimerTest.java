package com.kh.miniProject.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TimerTest extends JPanel {
	private JLabel timerLabel;
	private Timer timer;
	private boolean isTimer;

	
	public TimerTest() {
		timerLabel = new JLabel("10��");
		timer = new Timer(1000, new TimerStart());
		isTimer = false;
		
		this.add(timerLabel);
		this.setVisible(true);
		this.setSize(50, 30);
		
		
		timer.start();
	}
	
	class TimerStart implements ActionListener {
		private int gameTime = 10;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	
				System.out.println(gameTime);
				
				gameTime--;
					
				if(gameTime<=0) {
					timer.stop();
					System.out.println("Ÿ�̸� �׽�Ʈ ����");
					/*return;*/
				}
				timerLabel.setText(gameTime+"��");
				
			}
	}
	
	public static void main(String[] args) {
		TimerTest tt = new TimerTest();
	}
	
}
