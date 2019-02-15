package com.kh.miniProject.controller;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.kh.miniProject.view.StoryPanel;

public class Story extends JLabel implements Runnable {
	private String temp = "";
	private String temp1 = "";
	private String[] story = { "��", "��", "��", " ", "��", "��", "��", "��", "��", "��", "��", " ", "��", "��", " ", "��", "��", "��",
			"��", "��", ".", ".", ".", "\n", "��", "��", " ", "ȥ", "��", "��", " ", "��", "��", "��", "��", " ", "��", "��", " ",
			"Ű", "��", "��", "��", "��", ".", ".", ".", };
	private StoryPanel sp;
	private JLabel[] la = new JLabel[3];

	public Story(StoryPanel sp/* , String[] story */) {
		this.sp = sp;

		la[0] = new JLabel(); // ù��
		la[1] = new JLabel(); // �ι�°��
		// la2 = new JLabel()
		la[0].setBounds(0, 0, 1024, 50);
		la[1].setBounds(0, 50, 1024, 50);
		sp.add(la[1]);
		sp.add(la[0]);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		boolean linech = false;
		for (int i = 0; i < story.length; i++) { // 24
			if (!linech) {
				temp += story[i];
				la[0].setText(temp);
				la[0].setFont(new Font("Elephant", Font.ITALIC, 40));
				repaint();
			} else {
				temp1 += story[i];
				la[1].setText(temp1);
				la[1].setFont(new Font("Elephant", Font.ITALIC, 40));
				repaint();
			}
			if (story[i].equals("\n")) {
				linech = true;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
