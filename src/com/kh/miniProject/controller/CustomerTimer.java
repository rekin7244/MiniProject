package com.kh.miniProject.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.kh.miniProject.model.vo.Customer;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.view.GuestPanel;

public class CustomerTimer extends JPanel{
	private JLabel timerLabel;
	private GuestPanel gP;
	private CustomerManager cm;
	private Timer timer;
	private Customer customer;
	private double waitingTime;
	private int orderNo;
	
	public CustomerTimer(GuestPanel gP,CustomerManager cm,Customer c,int orderNo) {
		this.gP = gP;
		this.cm = cm;
		this.customer = c;
		this.waitingTime = c.getWaitingTime();
		this.orderNo = orderNo;

		timerLabel = new JLabel();
		timer = new Timer(100, new TimerStart());

		timerLabel.setSize(70, 40);
		timerLabel.setFont(new Font("Elephant", Font.BOLD, 20));
		this.add(timerLabel);
		this.setVisible(true);
		this.setBounds(864,150,70, 40);

		timer.start();
		cm.guest();
	}
	public void timerStop() {
		timer.stop();
	}

	class TimerStart implements ActionListener {
		private double gameTime = waitingTime;
		@Override
		public void actionPerformed(ActionEvent e) {
			gameTime-=0.1;
			
			if(gameTime<=0) {
				cm.deleteCustomer(0);
				timer.stop();
			}
			//시간 표시
			if(((int)(gameTime*10))%10==0) {
				timerLabel.setText(((int)(gameTime*10))/10+"초");
			}

		}
	}
}
