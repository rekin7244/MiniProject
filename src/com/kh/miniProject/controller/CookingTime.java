package com.kh.miniProject.controller;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CookingTime extends JPanel{
	private JLabel loadingLabel;
	private Image loadingImage 
	= new ImageIcon("images/loading.gif").getImage().getScaledInstance(180, 180, 0); // º’¥‘ ¿ÃπÃ¡ˆ
	private JLabel timerLabel;
	private Timer timer;
	private int cooktime;
	private JButton btn;

	public CookingTime(JButton btn,int cooktime,String menuName) {
		this.btn = btn;
		this.cooktime = cooktime;
		
		timerLabel = new JLabel();
		timer = new Timer(100, new TimerStart());
		timerLabel.setFont(new Font("Elephant", Font.BOLD, 16));
		timerLabel.setBounds(btn.getWidth()/2,0,220,220);

		loadingLabel = new JLabel(new ImageIcon(loadingImage));
		loadingLabel.setBounds(btn.getX()+10,0,180,180);
		
		btn.add(timerLabel);
		btn.add(loadingLabel,btn.CENTER);
		
		this.setVisible(true);
		this.setOpaque(false);
		this.setBounds(btn.CENTER,0,220,220);
		timer.start();
	}
	public void timerStop() {
		timer.stop();
	}
	
	class TimerStart implements ActionListener {
		private double gameTime = cooktime;
		@Override
		public void actionPerformed(ActionEvent e) {
			gameTime-=0.1;

			if(gameTime<=0) {
				timer.stop();
				btn.setEnabled(true);
				btn.remove(timerLabel);
				btn.remove(loadingLabel);
			}
			double temp=0;
			temp = (int)(gameTime*10);
			temp/=10;
			timerLabel.setText(""+temp);
		}
	}
}
