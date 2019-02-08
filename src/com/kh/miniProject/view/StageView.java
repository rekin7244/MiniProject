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
	private JButton[] stageBtn = new JButton[10];
	private Member m;
	
	public StageView(MainFrame mf,Member m) {
		this.m = m;
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
		stageBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePanel().changePanel(mf,sView,new GameView(mf,m));
			}
		});
		
		//상점으로 버튼
		toMarket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePanel().changePanel(mf, sView, new MarketPanel(mf,m));
			}
		});
	}	
}
