package com.kh.miniProject.controller;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.kh.miniProject.view.GuestPanel;

public class MessageTimer extends JLabel{
	private CustomerManager cm;
	private Timer timer;
	double time;
	JLabel message;
	
	public MessageTimer(CustomerManager cm, double time, int x) {
		this.cm = cm;
		this.time = time;
		
		//Message = new JLabel("Hidden");
		Image icon = new ImageIcon("images/hiddenmessage.png")
				.getImage().getScaledInstance(250, 150, 0);
		message = new JLabel(new ImageIcon(icon));
		timer = new Timer(100, new TimerStart());
		
		message.setSize(250,150);
		message.setFont(new Font("Elephant", Font.BOLD, 40));
		this.add(message);
		this.setVisible(true);
		this.setBounds(500, 100, 250, 150);
		
		timer.start();
	}
	
	/*public void timerStop() {
		timer.stop();
	}*/
	
	class TimerStart implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			time -= 0.1;
			
			if(time <= 0) {
				cm.deleteMessage();
				timer.stop();
			}
		}
		
	}

}
