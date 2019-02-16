package com.kh.miniProject.controller;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.kh.miniProject.view.GuestPanel;
import com.kh.miniProject.view.StoryPanel;

public class Walkking extends JLabel implements Runnable {

	private JLabel walk;
	private StoryPanel st;   
	
	public  Walkking(StoryPanel st) {
		this.st=st;
		
		Image icon = new ImageIcon("images/walkinggif2.gif")
				.getImage().getScaledInstance(100, 150, 0);
		walk = new JLabel(new ImageIcon(icon));
		walk.setBounds(1024, 600, 100, 150);
		st.add(walk);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		for(int x = walk.getX(); x >= -80; x -=10) {
			walk.setBounds(x, 600, 100, 150);
			repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		st.remove(walk);
		repaint();
		
	}

}
