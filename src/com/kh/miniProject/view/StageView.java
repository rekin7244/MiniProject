package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.Music;
import com.kh.miniProject.run.Run;

public class StageView extends JPanel{
	private int stagexSize = 180;		//stage��ư ����ũ��
	private int stageySize = 150;		//stage��ư ����ũ��
	private JPanel sView;
	private MarketPanel mPanel;
	private MainFrame mf;
	private JButton[] stageBtn = new JButton[10];	//1~10�������� ��ư
	private Member m;
	private int[] equipsLv;
	//Music
	private Music titleMusic;
	private boolean firstPlay = true;
	
	public StageView(MainFrame mf,Member m) {
		this.m = m;
		this.mf = mf;
		this.equipsLv = m.getEquipsLv();

		this.setLayout(null);
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);

		//��������1~5
		for(int i=0;i<5;i++) {
			stageBtn[i] = new JButton("STAGE "+ (i+1));
			stageBtn[i].setBounds(50+stagexSize*i,50,stagexSize,stageySize);
			this.add(stageBtn[i]);
		}
		//��������6~10
		for(int i=5;i<10;i++) {
			stageBtn[i] = new JButton("STAGE "+ (i+1));
			stageBtn[i].setBounds(50+stagexSize*(i-5),80+stageySize,stagexSize,stageySize);
			this.add(stageBtn[i]);
		}
		for (int i = m.getMaxStage(); i < 10; i++) {
			stageBtn[i].setEnabled(false);
		}

		//��������
		JButton toMarket = new JButton("��������");
		toMarket.setBounds(700,450,200,70);
		this.add(toMarket);

		//���� �� (ȸ���̸�)��ŷ ���
		/*if(Member)*/	//ȸ������ �Ǻ�
		JButton save_Ranking = new JButton("��ŷ ��� �� ����");
		save_Ranking.setBounds(700,550,200,70);
		this.add(save_Ranking);

		//������ ���
		JButton gold = new JButton("������  "+m.getGold()+"  G");
		gold.setBounds(80,500,200,70);
		gold.setEnabled(false);
		gold.setBackground(Color.YELLOW);
		this.add(gold);

		this.sView = this;


		//��������1 �� ��ư ���� �� GameView�� changePanel ����
		for (int i = 0; i < stageBtn.length; i++) {
			stageBtn[i].addActionListener(new MyAction());
		}
		//�������� ��ư
		toMarket.addActionListener(new MyAction());
		//���� �� ��ŷ ��ư
		save_Ranking.addActionListener(new MyAction());
	}	
	public void musicOn() {
		firstPlay=false;
		titleMusic = new Music("TitleMusic.mp3",false);
		titleMusic.start();
	}
	class MyAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("STAGE 1")) {
				new ChangePanel().changePanel(mf,sView,new GameView(mf,m,1));
				if(!firstPlay) {
					titleMusic.close();
				}
			}
			if(e.getActionCommand().equals("STAGE 2")) {
				if(equipsLv[1]>0) {
					new ChangePanel().changePanel(mf,sView,new GameView(mf,m,2));
					if(!firstPlay) {
						titleMusic.close();
					}
				}else {
					System.out.println("Ƣ��Ⱑ �ʿ��մϴ�.");
				}
			}
			if(e.getActionCommand().equals("STAGE 3")) {
				if(equipsLv[2]>0) {
					new ChangePanel().changePanel(mf,sView,new GameView(mf,m,3));
					if(!firstPlay) {
						titleMusic.close();
					}
				}else {
					System.out.println("������谡 �ʿ��մϴ�.");
				}
			}
			if(e.getActionCommand().equals("STAGE 4")) {
				if(equipsLv[3]>0) {
					new ChangePanel().changePanel(mf,sView,new GameView(mf,m,4));
					if(!firstPlay) {
						titleMusic.close();
					}
				}else {
					System.out.println("����谡 �ʿ��մϴ�.");
				}
			}
			
			if(e.getActionCommand().equals("��������")) {
				new ChangePanel().changePanel(mf, sView, mPanel=new MarketPanel(mf,m));
				mPanel.setting(mPanel);
			}
			if(e.getActionCommand().equals("��ŷ ��� �� ����")) {
				new ChangePanel().changePanel(mf, sView, new RankingPanel(mf));
			}
		}
	}
}
