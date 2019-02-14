package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class GoldLabel extends JLabel{
	private Timer labelTimer;
	private Point point;
	private GuestPanel gP;
	private GoldLabel gLabel;
	
	public GoldLabel(GuestPanel gP,Point point,int gold) {
		this.gP = gP;
		this.point = point;
		this.labelTimer = new Timer(10,new LabelTimer());
		
		this.setText("+ "+gold+" G");
		this.setLocation(point);
		this.setSize(100,20);
		this.setForeground(Color.yellow);
		this.setFont(new Font("Elephant",Font.BOLD,20));
		this.setBackground(null);
		
		gLabel = this;
		gP.add(gLabel);
		
		labelTimer.start();
	}
	
	class LabelTimer implements ActionListener{
		private int count = 40;
		@Override
		public void actionPerformed(ActionEvent e) {
			gLabel.setLocation(point.x, point.y+2);
			gP.repaint();
			count--;
			
			if(count<0) {
				labelTimer.stop();
				gP.remove(gLabel);
			}
			
		}
	}
}
