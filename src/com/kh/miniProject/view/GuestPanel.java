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
	//음식 이미지 삭제 메소드
	public void deleteImage(int menuNo) {
		
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 1024, 350, null);
	}
}
