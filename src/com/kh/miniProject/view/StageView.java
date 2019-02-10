package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.Run;

public class StageView extends JPanel{
	private int stagexSize = 180;		//stage버튼 가로크기
	private int stageySize = 150;		//stage버튼 세로크기
	private JPanel sView;
	private MarketPanel mPanel;
	private MainFrame mf;
	private JButton[] stageBtn = new JButton[10];	//1~10스테이지 버튼
	private Member m;
	private int[] equipsLv;

	public StageView(MainFrame mf,Member m) {
		this.m = m;
		this.mf = mf;
		this.equipsLv = m.getEquipsLv();

		this.setLayout(null);
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);

		//스테이지1~5
		for(int i=0;i<5;i++) {
			stageBtn[i] = new JButton("STAGE "+ (i+1));
			stageBtn[i].setBounds(50+stagexSize*i,50,stagexSize,stageySize);
			this.add(stageBtn[i]);
		}
		//스테이지6~10
		for(int i=5;i<10;i++) {
			stageBtn[i] = new JButton("STAGE "+ (i+1));
			stageBtn[i].setBounds(50+stagexSize*(i-5),80+stageySize,stagexSize,stageySize);
			this.add(stageBtn[i]);
		}
		for (int i = m.getMaxStage(); i < 10; i++) {
			stageBtn[i].setEnabled(false);
		}

		//상점으로
		JButton toMarket = new JButton("상점으로");
		toMarket.setBounds(700,450,200,70);
		this.add(toMarket);

		//저장 후 (회원이면)랭킹 출력
		/*if(Member)*/	//회원인지 판별
		JButton save_Ranking = new JButton("저장 및 랭킹 출력");
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
			stageBtn[i].addActionListener(new MyAction());
		}
		//상점으로 버튼
		toMarket.addActionListener(new MyAction());
		//저장 및 랭킹 버튼
		save_Ranking.addActionListener(new MyAction());
	}	
	class MyAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("STAGE 1")) {
				new ChangePanel().changePanel(mf,sView,new GameView(mf,m,1));
			}
			if(e.getActionCommand().equals("STAGE 2")) {
				if(equipsLv[1]>0) {
					new ChangePanel().changePanel(mf,sView,new GameView(mf,m,2));
				}else {
					System.out.println("튀김기가 필요합니다.");
				}
			}
			if(e.getActionCommand().equals("STAGE 3")) {
				if(equipsLv[2]>0) {
					new ChangePanel().changePanel(mf,sView,new GameView(mf,m,3));
				}else {
					System.out.println("오뎅기계가 필요합니다.");
				}
			}
			if(e.getActionCommand().equals("STAGE 4")) {
				if(equipsLv[3]>0) {
					new ChangePanel().changePanel(mf,sView,new GameView(mf,m,4));
				}else {
					System.out.println("라면기계가 필요합니다.");
				}
			}
			if(e.getActionCommand().equals("상점으로")) {
				new ChangePanel().changePanel(mf, sView, mPanel=new MarketPanel(mf,m));
				mPanel.setting(mPanel);
			}
			if(e.getActionCommand().equals("저장 및 랭킹 출력")) {
				new ChangePanel().changePanel(mf, sView, new RankingPanel(mf));
			}
		}
	}
}
