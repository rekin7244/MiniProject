package com.kh.miniProject.controller;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MessageTimer extends JPanel{
	private JLabel Message;
	private CustomerManager cm;
	private Timer timer;
	private double time;
	
	public MessageTimer(CustomerManager cm, double time) {
		this.cm = cm;
		this.time = time;
		
		//Message = new JLabel("Hidden");
		Image icon = new ImageIcon("images/hidden1.png")
				.getImage().getScaledInstance(100, 70, 0);
		Message = new JLabel(new ImageIcon(icon));
		timer = new Timer(100, new TimerStart());
		
		Message.setSize(100,50);
		Message.setFont(new Font("Elephant", Font.BOLD, 40));
		this.add(Message);
		this.setVisible(true);
		this.setBounds(450, 100, 100, 70);
		
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