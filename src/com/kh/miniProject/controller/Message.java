package com.kh.miniProject.controller;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.kh.miniProject.view.GuestPanel;

public class Message extends JLabel implements Runnable {
	private CustomerManager cm;
	private GuestPanel gP;
	private JLabel message;

	public Message(CustomerManager cm, GuestPanel gP) {
		this.cm = cm;
		this.gP = gP;

		Image icon = new ImageIcon("images/message.png").getImage().getScaledInstance(250, 150, 0);
		message = new JLabel(new ImageIcon(icon));

		message.setSize(250, 150);
		this.add(message);
		this.setVisible(true);
		this.setBounds(0, 100, 250, 150);

		Thread t = new Thread(this);
		System.out.println("스레드 생성");
		t.start();
	}

	@Override
	public void run() {
		for (int x = message.getX(); x <= 1000; x += 50) {
			message.setBounds(x, 10, 250, 150);
			gP.add(message);
			repaint();
			try {
				Thread.sleep(100);
				if (message.getX() == 400) {
					Thread.sleep(1500);
				} else if (message.getX() == 1000) {
					gP.remove(message);
					repaint();
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
