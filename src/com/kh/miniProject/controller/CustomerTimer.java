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
	private int customerNo;
	private CustomerManager cm;
	private Timer timer;
	private double waitingTime;
	
	public CustomerTimer(CustomerManager cm,double waitingTime,int customerNo) {
		this.cm = cm;
		this.waitingTime = waitingTime;
		this.customerNo = customerNo;
		
		//게스트 타이머 시작
		timerLabel = new JLabel();
		timer = new Timer(100, new TimerStart());

		timerLabel.setSize(70, 40);
		timerLabel.setFont(new Font("Elephant", Font.BOLD, 20));
		this.add(timerLabel);
		this.setVisible(true);
		this.setBounds((3-customerNo)*300-56,150,70,40);

		timer.start();
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
				cm.deleteCustomer(customerNo);
				timer.stop();
			}
			//시간 표시
			if(((int)(gameTime*10))%10==0) {
				timerLabel.setText(((int)(gameTime*10))/10+"초");
			}

		}
	}
}
