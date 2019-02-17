package com.kh.miniProject.controller;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.kh.miniProject.view.StoryPanel;

public class Story extends JLabel implements Runnable {
	private String temp = "";
	private String story = "�ƺ��� ���� �¾�⵵ ���� ���ư��ð�... \n" 
	         + "���� ȥ�ڼ� ���ݱ��� ���� Ű���̴ּ�.\n" + 
	         "������ �ٸ� ģô�� ���� ������ ������ ������  \n"
	         + "��������,  ǳ������ �ʾ����� \n" 
			 + "������ �� ���� ��⿡�� ���� ��ź�ߴ�.\n"

			+ "�׷��� ���� �׸��� �츮�� �ູ�� ��� ���� \n"

			+ "�˾Ҵµ�.... ����� ������ �̷� �������常 \n"
			+ "����ä ���� �ΰ� �������ȴ�.\n";
	private StoryPanel sp;
	private JLabel[] la = new JLabel[8];
	private Thread t;

	public Story(StoryPanel sp/* , String[] story */) {
		this.sp = sp;
		int y = 100; 

		for(int i = 0 ; i<8 ; i++) {
			la[i]=new JLabel();
			la[i].setBounds(50, y, 1024,50);
			y += 50;
			sp.add(la[i]);
		}
		/*la[0] = new JLabel(); // ù��
		la[1] = new JLabel(); // �ι�°��
		// la2 = new JLabel()
		la[0].setBounds(0, 0, 1024, 50);
		la[1].setBounds(0, 50, 1024, 50);
		sp.add(la[1]);
		sp.add(la[0]);*/
		t = new Thread(this);
		t.start();
	}
	public void stop() {
		for(int i = 0 ; i<8 ; i++) {
			sp.remove(la[i]);
		}
	}
	
	@Override
	public void run() {
		int count =0;
		for (int i = 0; i < story.length(); i++) {
			temp += story.charAt(i);
			la[count].setText(temp);
			la[count].setFont(new Font("����", Font.ITALIC, 40));
			repaint();
			if(story.charAt(i) == '\n') {
				count++; 
				temp = "" ;
			}
				try {
					Thread.sleep(95);
				} catch (InterruptedException e) {
				}
			}
			
		}

	}
