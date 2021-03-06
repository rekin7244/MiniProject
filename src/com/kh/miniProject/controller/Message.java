package com.kh.miniProject.controller;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.kh.miniProject.music.Music;
import com.kh.miniProject.view.GuestPanel;

public class Message extends JLabel implements Runnable {
	private GuestPanel gP;
	private JLabel message;
	private CustomerManager cm;
	
	private int no;

	public Message(CustomerManager cm, GuestPanel gP, int no) {
		this.gP = gP;
		this.cm = cm;
		this.no = no;

		Image icon = new ImageIcon("images/message.png").getImage().getScaledInstance(250, 150, 0);
		message = new JLabel(new ImageIcon(icon));
		message.setBounds(0, 100, 250, 150);

		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		for (int x = message.getX(); x <= 1000; x += 50) {
			message.setBounds(x, 10, 250, 150);
			gP.add(message);
			Music buttonEnteredMusic = new Music("[ȿ����]�����,���̷�,���̷�,��� (online-audio-converter.com).mp3",false);
			buttonEnteredMusic.start();
			repaint();
			try {
				Thread.sleep(100);
				if (message.getX() == 400) {
					Thread.sleep(1500);
				} else if (message.getX() == 1000) {
					gP.remove(message);
					repaint();
					System.out.println("������ ��");
					cm.hidden(no);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
