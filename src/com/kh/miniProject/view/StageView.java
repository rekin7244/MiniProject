package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.Music;
import com.kh.miniProject.run.Run;

public class StageView extends JPanel{
	private int stagexSize = 180;		//stage버튼 가로크기
	private int stageySize = 150;		//stage버튼 세로크기
	private JPanel sView;
	private MarketPanel mPanel;
	private MainFrame mf;
	private JButton[] stageBtn = new JButton[10];	//1~10스테이지 버튼
	private Member m;
	private JButton save_Ranking;
	private JButton toMarket;
	//Music
	private Music music;
	
	//Image
	private Image[] btnImg = { new ImageIcon("images/one (1).png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/two.png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/three.png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/four.png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/five.png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/six.png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/seven.png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/eight.png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/nine.png").getImage().getScaledInstance(165, 150, 0),
			new ImageIcon("images/zero.png").getImage().getScaledInstance(165, 150, 0)
			
	};
	
	public StageView(MainFrame mf,Member m) {
		this.m = m;
		this.mf = mf;

		this.setLayout(null);
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);
		
		music = new Music("inGameMusic.mp3",false);
		music.start();
		
		//스테이지1~5
		for(int i=0;i<5;i++) {
			stageBtn[i] = new JButton("STAGE "+ (i+1));
			stageBtn[i].setBounds(50+stagexSize*i,50,stagexSize,stageySize);
			stageBtn[i].setIcon(new ImageIcon(btnImg[i]));
			stageBtn[i].setContentAreaFilled(false);
			stageBtn[i].setBorderPainted(false);
			this.add(stageBtn[i]);
		}
		//스테이지6~10
		for(int i=5;i<10;i++) {
			stageBtn[i] = new JButton("STAGE "+ (i+1));
			stageBtn[i].setBounds(50+stagexSize*(i-5),80+stageySize,stagexSize,stageySize);
			stageBtn[i].setIcon(new ImageIcon(btnImg[i]));
			stageBtn[i].setContentAreaFilled(false);
			stageBtn[i].setBorderPainted(false);
			this.add(stageBtn[i]);
		}
		for (int i = m.getMaxStage(); i < 10; i++) {
			stageBtn[i].setEnabled(false);
		}

		//상점으로
		toMarket = new JButton("상점으로");
		toMarket.setBounds(700,450,200,70);
		this.add(toMarket);

		//저장 후 (회원이면)랭킹 출력
		/*if(Member)*/	//회원인지 판별
		save_Ranking = new JButton("랭킹 출력 및 종료");
		save_Ranking.setBounds(700,550,200,70);
		this.add(save_Ranking);

		//소지금 출력
		JButton gold = new JButton("소지금  "+m.getGold()+"  G");
		gold.setBounds(80,500,200,70);
		gold.setEnabled(false);
		gold.setBackground(Color.YELLOW);
		this.add(gold);

		this.sView = this;


		//스테이지1 번 버튼 선택 시 GameView로 changePanel 실행
		for (int i = 0; i < stageBtn.length; i++) {
			stageBtn[i].addMouseListener(new MyAction());
		}
		//상점으로 버튼
		toMarket.addMouseListener(new MyAction());
		//저장 및 랭킹 버튼
		save_Ranking.addMouseListener(new MyAction());
	}	
	class MyAction implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Music buttonEnteredMusic = new Music("decision8.mp3",false);
			buttonEnteredMusic.start();
				for(int i=1;i<=10;i++) {
					if(e.getSource()==stageBtn[(i-1)]) {
						ChangePanel.changePanel(mf,sView,new GameView(mf,m,i));
						music.close();						
					}
				}	
				
				if(e.getSource()==toMarket) {
					new ChangePanel().changePanel(mf, sView, mPanel=new MarketPanel(mf,m));
					mPanel.marketMusic();
					mPanel.setting(mPanel);
					music.close();
				}else if(e.getSource()==save_Ranking) {
					new ChangePanel().changePanel(mf, sView, new RankingPanel(mf));
					music.close();
				}
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			Music buttonEnteredMusic = new Music("cursor7.mp3",false);
			buttonEnteredMusic.start();
		}

		@Override
		public void mouseExited(MouseEvent e) {}
	}
}