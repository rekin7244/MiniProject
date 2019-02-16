package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kh.miniProject.music.Music;
import com.kh.miniProject.run.Run;

public class EndingPanel extends JPanel {
	JLabel endingLabel;
	JLabel credit;
	String story = "[...��Ĭ]\r\n" + 
			"\r\n" + 
			"�� ���� �̷��� ���� ������ ������. \r\n" + 
			"�׵��� �츮 ���Ը� �ٽ� �����Ѿ߰ڴٴ� ���� �ϳ���,\r\n" + 
			"�޿��� �ѹ����̶� ����;��� �츮������ ���� ���� ��ٸ��� ������ �� �������̿��µ�\r\n" + 
			"�׷��Ե� ����;��� �츮 ������ �� ���տ� ���־���.\r\n" + 
			"\r\n" + 
			"������ ���� �ٸ� ���̻��� ���� �ʿ������ ���� �� �������� ������ �����.\r\n" + 
			"\r\n" + 
			"���� ������ ���� �׵��ȿ� ���������� , ������Ƽ�� �Ͻúҷ� �����ϰ� ��Ż���� �θ��� 2ȣ�� ������ ���Ը� �����ϰ� �Ǿ���  \r\n" + 
			"�� ���Ŀ��� 3ȣ���� ���� ������ �����ϴµ� ��� ��簡 ��â�ϸ� ��� CEO�� �Ǿ���.\r\n" + 
			"\r\n" + 
			"��ȭ����.\r\n" + 
			"� ������ ���̶� ���Ӿ��� ��°� ������ ������ ������ ������ �ູ���� �ٲ� ������ �ִٴ� ���̴�.\r\n" + 
			"\r\n" + 
			"�̰� �ϻ��� ����� ��� �����ε� ���� ������ ��ư��� ������ �ູ�ϰ� ��� �ʹ�.";
	
	private int x=30;
	private int y=Run.SCREEN_HEIGHT-100;
	private Timer endingTimer;
	private JLabel[] myLb;
	private EndingPanel endingPanel;
	private int reviewIndex=0;
	
	private Image[] img = {new ImageIcon("images/sy.gif").getImage().getScaledInstance(800, 300, 0),
			new ImageIcon("images/SW.gif").getImage().getScaledInstance(800, 300, 0),
			new ImageIcon("images/SM.gif").getImage().getScaledInstance(800, 300, 0),
			new ImageIcon("images/SR.gif").getImage().getScaledInstance(800, 300, 0),
			new ImageIcon("images/HS.gif").getImage().getScaledInstance(800, 300, 0),
			new ImageIcon("images/HR.gif").getImage().getScaledInstance(800, 300, 0)
	
	};
	
	//b6abab
	
	public EndingPanel(MainFrame mf) {
		this.setLayout(null);
		this.setBounds(0, 0, Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);
		this.setBackground(Color.black);
		this.endingPanel = this;
		
		Music music = new Music("����.mp3",false);
		music.start();
		
		Font font = new Font("�޸տ�����",Font.BOLD,16);
		
		
		
		endingLabel = new JLabel();
		endingLabel.setText("<html>" + story.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		endingLabel.setBounds(x,y,1000,1000);
		endingLabel.setFont(font);
		endingLabel.setForeground(Color.WHITE);
		
		endingTimer = new Timer(500,new MyTimer());
		endingTimer.start();
		
		myLb = new JLabel[img.length];
		for(int i=0; i<myLb.length;i++) {
			myLb[i] = new JLabel();
			myLb[i].setBounds(100, 200, 800, 300);
		}

		/*syLb.setIcon(new ImageIcon(img));*/
		this.add(endingLabel);
		/*this.add(syLb);*/
		mf.add(this);
		
		
		
	
	}
	
	/*public void paint(Graphics g) {
		g.setFont(new Font("�޸տ�����",Font.BOLD,30));
		g.setColor(Color.WHITE);
		
		g.drawString(story, x, y);
		
	}*/
	
	class MyTimer implements ActionListener,Runnable{
		private int time = 30;
		private Thread t;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		/*	time --;*/
			
			if(y<=100) {
				t = new Thread(this);
				endingLabel.setVisible(false);
				t.start();
				System.out.println("������ ȣ����");
				y=0;
			}else {
				y-=40;
				endingLabel.setBounds(x, y,1000,1000);
				time--;
				System.out.println(y);
				
			}
				
		//	repaint();
			
			
		}
		
		@Override
		public void run() {
			System.out.println("������ ȣ���");
			
			try {
				for(int i=0;i<myLb.length;i++) {
					myLb[i].setIcon(new ImageIcon(img[i]));
					
					endingPanel.add(myLb[i]);
					endingPanel.repaint();
					t.sleep(6000);
					myLb[i].setVisible(false);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		/*}*/
		
	}
	
	
}
