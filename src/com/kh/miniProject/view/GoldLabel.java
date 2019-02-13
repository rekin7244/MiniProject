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
	private JLabel label;
	private int x,y;
	
	public GoldLabel(JLabel label,int gold) {
		this.label = label;
		this.x = label.getX();
		this.y = label.getY();
		this.labelTimer = new Timer(10,new LabelTimer());
		
		this.setText("+ "+gold+" G");
		this.setForeground(Color.yellow);
		this.setFont(new Font("Elephant",Font.ITALIC,15));
		labelTimer.start();
	}
	
	class LabelTimer implements ActionListener{
		private int count = 20;
		@Override
		public void actionPerformed(ActionEvent e) {
			Point point = label.getLocation();
			
		}
	}
}
