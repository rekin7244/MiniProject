package com.kh.miniProject.testSample;

import java.awt.Panel;

import javax.swing.*;

public class Cooking_JProgressBar {
	private JProgressBar p;
	private JLabel label;
	private int cookingTime=4;		//test 4��

	public Cooking_JProgressBar() {}
	public Cooking_JProgressBar(JPanel panel) {
		//JProgressBar
		panel = new JPanel();
		p = new JProgressBar();
		p.setMinimum(0);
		p.setMaximum(100);
		p.setValue(0);
		p.setBounds(200,0,250,10);
		
		label = new JLabel("");
		label.setBounds(200,10,250,10);

		panel.add(p);
		panel.add(label);
	}

	public void go() {
		try {
			for (int i = 0; i <= 10; i++) {
				p.setValue(i);
				Thread.sleep(100*cookingTime);
				System.out.println(i+"0% ���� ��...");
				label.setText(i+"% ������...");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//btn.setEnabled(true);
	}
}
