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
	String story = "[...찰칵]\r\n" + 
			"\r\n" + 
			"이 날이 이렇게 빨리 올줄은 몰랐다. \r\n" + 
			"그동안 우리 가게를 다시 일으켜야겠다는 생각 하나로,\r\n" + 
			"꿈에서 한번만이라도 보고싶었던 우리엄마를 만날 날만 기다리며 열심히 일 했을뿐이였는데\r\n" + 
			"그렇게도 보고싶었던 우리 엄마가 내 눈앞에 서있었다.\r\n" + 
			"\r\n" + 
			"엄마와 나는 다른 더이상의 말이 필요없었고 서로 꼭 껴안으며 한참을 울었다.\r\n" + 
			"\r\n" + 
			"이후 엄마와 나는 그동안에 모은돈으로 , 마세라티를 일시불로 구입하고 이탈리아 로마에 2호점 떡볶이 가게를 오픈하게 되었고  \r\n" + 
			"그 이후에도 3호점을 영국 런던에 오픈하는등 계속 장사가 번창하며 어엿한 CEO가 되었다.\r\n" + 
			"\r\n" + 
			"전화위복.\r\n" + 
			"어떤 불행한 일이라도 끊임없는 노력과 강인한 의지로 힘쓰면 불행을 행복으로 바꿔 놓을수 있다는 말이다.\r\n" + 
			"\r\n" + 
			"이걸 일생의 모토로 삼아 앞으로도 더욱 열심히 살아가며 엄마와 행복하게 살고 싶다.";
	
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
		
		Music music = new Music("엔딩.mp3",false);
		music.start();
		
		Font font = new Font("휴먼엑스포",Font.BOLD,16);
		
		
		
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
		g.setFont(new Font("휴먼엑스포",Font.BOLD,30));
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
				System.out.println("쓰레드 호출함");
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
			System.out.println("쓰레드 호출됨");
			
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
