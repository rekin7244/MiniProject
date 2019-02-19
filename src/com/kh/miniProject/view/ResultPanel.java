package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.Music;
import com.kh.miniProject.run.Run;

public class ResultPanel extends JPanel{
	private JButton mainButton;
	private JButton storeButton;
	private int stageGold[];
	private JPanel resultPanel;
	private Image flareImage = new ImageIcon("images/ResultPanelImage1.png").getImage();
	private Image bgrImage = new ImageIcon("images/rank.jpg").getImage();
	private Timer resultTimer;
	private int tempGold;
	private JPanel rPage;
	private MarketPanel mP;
	
	public ResultPanel(MainFrame mf, Member m) {
		this.stageGold = m.getStageGold();
		this.resultPanel = this;
		this.setLayout(null);
		this.setBounds(0,0, Run.SCREEN_WIDTH, Run.SCREEN_HEIGHT);
		this.rPage = this;
		
		
		resultTimer = new Timer(100,new ResultTimerListener());
		resultTimer.start();
		
		mainButton = new JButton("스테이지로 가기");
		mainButton.setBounds(200, 500, 300, 100);
		
		storeButton = new JButton("상점으로 가기");
		storeButton.setBounds(550, 500, 300, 100);
		
		Music music = new Music("드럼.mp3",false);
		music.start();
		
		mainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<stageGold.length;i++) {
					stageGold[i] = 0;
				}
				m.setStageGold(stageGold);
				music.close();
				ChangePanel.changePanel(mf, resultPanel, new StageView(mf, m));
			}	
		});
		
		storeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<stageGold.length;i++) {
					stageGold[i] = 0;
				}
				m.setStageGold(stageGold);
				music.close();
				ChangePanel.changePanel(mf, rPage, mP=new MarketPanel(mf, m));
				mP.marketMusic();
				mP.setting(mP);
				music.close();
			}	
		});
		
		
		
		this.add(mainButton);	
		this.add(storeButton);
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(bgrImage, 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 70));
		g.setColor(Color.white);
		g.drawString("GAME RESULT", 300, 100);
		
		g.setFont(new Font("Elephant",Font.ITALIC,50));
		g.setColor(Color.CYAN);
		g.drawString(tempGold+" Score ", 370, 300);
		
		g.setFont(new Font("Dialog",Font.BOLD,25));
		g.setColor(Color.WHITE);
		g.drawString(stageGold[2] + "Bonus Money | ", 350, 360);
		
		g.setFont(new Font("Dialog",Font.BOLD,25));
		g.setColor(Color.WHITE);
		g.drawString(stageGold[1] + "Combo", 580, 360);
		
		
		g.drawImage(flareImage, 340, 120,null);		
		
	}
	
	class ResultTimerListener implements ActionListener{
		private int timer = 20;
		private Random rand = new Random();
		@Override
		public void actionPerformed(ActionEvent e) {
			timer--;
			if(timer>0) {
				tempGold = rand.nextInt(stageGold[0]);
				rPage.repaint();
			}else if(timer==0){
				tempGold = stageGold[0];
				resultTimer.stop();
				rPage.repaint();
			}
		}
		
	}
}
