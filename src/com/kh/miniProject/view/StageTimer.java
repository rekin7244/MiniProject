package com.kh.miniProject.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kh.miniProject.controller.CustomerManager;

public class StageTimer extends JPanel {
	private JLabel timerLabel;
	private Timer timer;
	private GuestPanel gP;
	private CustomerManager cm;
	private GameView gView;
	private int stageLv;
	
	public StageTimer(GuestPanel gP,CustomerManager cm,GameView gView,int stageLv) {
		this.gP = gP;
		this.cm = cm;
		this.gView = gView;
		this.stageLv = stageLv;
		
		timerLabel = new JLabel("60��");
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
				
				//guest����
				if(stageLv<3) {
					if(gameTime%5==0&&gameTime!=0) {cm.guest();}
				}else if(stageLv<6) {
					if(gameTime%4==0&&gameTime!=0) {cm.guest();}
				}else {
					if(gameTime%3==0&&gameTime!=0) {cm.guest();}
				}
				
				//����
				if(gameTime<=0) {
					timer.stop();
					gView.endStage();
				}
				timerLabel.setText(gameTime+"��");
				gP.repaint();
			}
	}
}
