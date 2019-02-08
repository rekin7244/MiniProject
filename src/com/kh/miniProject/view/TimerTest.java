package com.kh.miniProject.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TimerTest extends JPanel {
	private JLabel timerLabel;
	private Timer timer;
	private boolean isTimer;

	
	public TimerTest() {
		timerLabel = new JLabel("60��");
		timer = new Timer(1000, new TimerStart());
		isTimer = false;
		
		timerLabel.setFont(new Font("Elephant", Font.BOLD, 25));
		this.add(timerLabel);
		this.setVisible(true);
		this.setLocation(0,30);
		this.setSize(80, 50);
		
		
		timer.start();
	}
	public void timerStop() {
		timer.stop();
	}
	
	class TimerStart implements ActionListener {
		private int gameTime = 60;
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
	/*
	public static void main(String[] args) {
		TimerTest tt = new TimerTest();
	}
	*/
}
