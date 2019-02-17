package com.kh.miniProject.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import com.kh.miniProject.music.Music;

public class CustomerTimer extends JLabel{
	private JLabel timerLabel;
	private int customerNo;
	private CustomerManager cm;
	private Timer timer;
	private double waitingTime;

	public CustomerTimer(CustomerManager cm,double waitingTime,int customerNo,int x) {
		this.cm = cm;
		this.waitingTime = waitingTime;
		this.customerNo = customerNo;

		//게스트 타이머 시작
		timerLabel = new JLabel();
		timer = new Timer(100, new TimerStart());

		timerLabel.setSize(70, 40);
		timerLabel.setFont(new Font("Elephant", Font.BOLD, 20));
		timerLabel.setBackground(null);
		this.add(timerLabel);
		this.setVisible(true);
		this.setBounds(x-16,120,70,40);

		timer.start();
	}
	public void timerStop() {
		timer.stop();
	}

	class TimerStart implements ActionListener {
		private double gameTime = waitingTime;
		private boolean color = true;
		@Override
		public void actionPerformed(ActionEvent e) {
			gameTime-=0.1;
			
			if(gameTime<4&&color) {
				timerLabel.setForeground(Color.RED);
				color=false;
			}
			if(gameTime<=0) {
				cm.deleteCustomer(customerNo);
				timer.stop();
				Music buttonEnteredMusic = new Music("손님도망.mp3",false);
				buttonEnteredMusic.start();
			}
			//시간 표시
			double temp=0;
			temp = (int)(gameTime*10);
			temp/=10;
			timerLabel.setText(temp+"초");

		}
	}
}
