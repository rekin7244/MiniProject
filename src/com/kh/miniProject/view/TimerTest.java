package com.kh.miniProject.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kh.miniProject.controller.CustomerManager;
import com.kh.miniProject.controller.CustomerTimer;

public class TimerTest extends JPanel {
	private JLabel timerLabel;
	private Timer timer;
	private GuestPanel gP;
	private CustomerTimer cTimer;
	private CustomerManager cm;
	
	public TimerTest(GuestPanel gP,CustomerManager cm) {
		this.gP = gP;
		this.cm = cm;
		timerLabel = new JLabel("60段");
		timer = new Timer(1000, new TimerStart());
		
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
				System.out.println(gameTime);
				
				gameTime--;
				
				//guest持失
				if(gameTime%6==0) {
				cm.guest(3);
				}
				
				if(gameTime<=0) {
					timer.stop();
				}
				timerLabel.setText(gameTime+"段");
				gP.repaint();
			}
	}
}
