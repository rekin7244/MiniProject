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
	private JLabel endingLabel;
	private JLabel credit;
	private String story = "[...��Ĭ]\r\n" + 
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
	
	private String name = "--------  ������  ---------\r\n" + 
			"\r\n" + 
			"���� : ��ȸ��\r\n" + 
			"\r\n" + 
			"������ : �żҿ�\r\n" + 
			"\r\n" + 
			"������ : �̼���\r\n" + 
			"\r\n" + 
			"�۰� : ���Ҷ�\r\n" + 
			"\r\n" + 
			"���ΰ��̸� : ������\r\n" + 
			"\r\n" + 
			"�׸��� ������....\r\n" + 
			"�ڡ������� : ���� \r\n" + 
			"";
	
	private int x=30;
	private int y=Run.SCREEN_HEIGHT-300;
	private int nameY = Run.SCREEN_HEIGHT-300;
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
		Font creditFont = new Font("�޸տ�����",Font.BOLD,30);
		
		endingLabel = new JLabel();
		endingLabel.setText("<html>" + story.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		endingLabel.setBounds(x,y,1000,1000);
		endingLabel.setFont(font);
		endingLabel.setForeground(Color.WHITE);
		
		credit = new JLabel();
		credit.setText("<html>" + name.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		credit.setBounds(110, nameY, 1000, 1000);
		credit.setFont(creditFont);
		credit.setForeground(Color.WHITE);
		credit.setVisible(false);
		
		endingTimer = new Timer(500,new MyTimer());
		endingTimer.start();
		
		myLb = new JLabel[img.length];
		for(int i=0; i<myLb.length;i++) {
			myLb[i] = new JLabel();
			myLb[i].setBounds(100, 200, 800, 300);
		}

		this.add(endingLabel);
		this.add(credit);
		
		
		
		
		mf.add(this);
	}
	
	class MyTimer implements ActionListener,Runnable{
		private int time = 30;
		private Thread t;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		/*	time --;*/
			
			if(y<=-300) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				t = new Thread(this);
				endingLabel.setVisible(false);
				t.start();
				System.out.println("������ ȣ����");
				endingTimer.stop();
				
			}else {
				y-=30;
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
					Thread.sleep(6000);
					myLb[i].setVisible(false);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
			Timer nameTimer = new Timer(500,new NameTimer());
			nameTimer.start();
			}
			
		
	}
	
	class NameTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			credit.setVisible(true);
			// TODO Auto-generated method stub
			//	credit.setBounds(x,nameY,1000,1000);
			System.out.println("NameTimer : " + nameY);
			if(nameY<=-200) {
				try {
					Thread.sleep(10000);
					System.exit(0);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {			
				credit.setBounds(110, nameY,1000,1000);
				System.out.println();
				nameY-=30;
				endingPanel.repaint();
			}
		}
		
	}
	
	
}
