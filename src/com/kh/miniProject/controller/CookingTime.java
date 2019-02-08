package com.kh.miniProject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.Run;

public class CookingTime extends JPanel{
	private JLabel timerLabel;
	private Timer timer;
	private boolean isTimer;
	private Member m;
	private int cooktime;
	private JButton btn;
	
	public CookingTime(JButton btn, Member m,int cooktime,String menuName) {
		this.btn = btn;
		this.m = m;
		this.cooktime = cooktime;
		
		timerLabel = new JLabel();
		timer = new Timer(1000, new TimerStart());
		isTimer = false;
		
		this.add(timerLabel);
		this.setVisible(true);
		if(menuName.equals("¶±ººÀÌ")) {
			this.setBounds(200,Run.SCREEN_HEIGHT-250,50, 30);
		}else if(menuName.equals("À½·á¼ö")) {
			this.setBounds(Run.SCREEN_WIDTH-150,Run.SCREEN_HEIGHT-250,50, 30);
		}else if(menuName.equals("Æ¢±è")) {
			this.setBounds(500,Run.SCREEN_HEIGHT-250,50, 30);
		}
		
		
		timer.start();
	}
	public void timerStop() {
		timer.stop();
	}
	
	class TimerStart implements ActionListener {
		private int gameTime = cooktime;
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(gameTime);
				
				gameTime--;
					
				if(gameTime<=0) {
					timer.stop();

					btn.setEnabled(true);
					/*return;*/
				}
				timerLabel.setText(gameTime+"ÃÊ");
				
			}
	}
}
