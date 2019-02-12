package com.kh.miniProject.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.kh.miniProject.controller.CustomerManager;
import com.kh.miniProject.model.dao.OrderDao;


public class GuestPanel extends JPanel {
	private Image img;
	private CustomerManager cm;
	
	public GuestPanel(Image img,OrderDao od) {
		this.img = img;
		
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 1024, 350, null);
	}
}
