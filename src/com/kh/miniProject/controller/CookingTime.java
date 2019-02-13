package com.kh.miniProject.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.kh.miniProject.run.Run;

public class CookingTime extends JPanel{
	private JLabel timerLabel;
	private Timer timer;
	private int cooktime;
	private JButton btn;
	
	public CookingTime(JButton btn,int cooktime,String menuName) {
		this.btn = btn;
		this.cooktime = cooktime;
		
		timerLabel = new JLabel();
		timer = new Timer(1000, new TimerStart());
		
		timerLabel.setSize(70, 40);
		timerLabel.setFont(new Font("Elephant", Font.BOLD, 20));
		this.add(timerLabel);
		this.setVisible(true);
		if(menuName.equals("������")) {
			this.setBounds(200,Run.SCREEN_HEIGHT-250,70,40);
		}else if(menuName.equals("�����")) {
			this.setBounds(Run.SCREEN_WIDTH-150,Run.SCREEN_HEIGHT-250,70,40);
		}else if(menuName.equals("Ƣ��")) {
			this.setBounds(500,Run.SCREEN_HEIGHT-250,70,40);
		}else if(menuName.equals("����")) {
			this.setBounds(0,Run.SCREEN_HEIGHT-250,70,40);
		}else if(menuName.equals("���")) {
			this.setBounds(Run.SCREEN_WIDTH-345,Run.SCREEN_HEIGHT-250,70,40);
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
				gameTime--;
					
				if(gameTime<=0) {
					timer.stop();

					btn.setEnabled(true);
					/*return;*/
				}
				timerLabel.setText(gameTime+"��");
				
			}
	}
}
