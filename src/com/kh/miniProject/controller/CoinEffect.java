package com.kh.miniProject.controller;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.kh.miniProject.view.GuestPanel;

public class CoinEffect extends JLabel implements Runnable{
	private CustomerManager cm;
	private GuestPanel gP;
	private int no;	//손님 수
	private JLabel[] coin= new JLabel[3];
	private double time = 0.7;


	public CoinEffect(CustomerManager cm, GuestPanel gP, int no, int x, int y) {
		this.cm = cm;
		this.gP = gP;
		this.no = no;

		Image money = new ImageIcon("images/coin1.gif").getImage().getScaledInstance(120, 225, 0);
		coin[no] = new JLabel(new ImageIcon(money));
		coin[no].setBounds(x+ 30, y+150, 120, 225);

		Thread t = new Thread(this);
		t.start();
	}


	@Override
	public void run() {
		int x = coin[no].getX() - 20;
		int y = coin[no].getY() - 200;
		for(double i = 0; i <= 0.7; i+=0.1) {
			//System.out.println("스레드 시작");
			coin[no].setBounds(x, y, 120, 225);
			gP.add(coin[no]);
			repaint();
			try {
				Thread.sleep(100);
				if(i == time) {
					gP.remove(coin[no]);
					repaint();
					//System.out.println("스레드 끝");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}