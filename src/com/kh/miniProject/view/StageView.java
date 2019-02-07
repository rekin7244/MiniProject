package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.Run;

public class StageView extends JPanel{
	private int stagexSize = 180;		//stage��ư ����ũ��
	private int stageySize = 150;		//stage��ư ����ũ��
	private JPanel sView;
	private JButton[] stageBtn = new JButton[10];
	private Member m;
	
	public StageView(MainFrame mf,Member m) {
		this.m = m;
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
		
		//��������
		JButton toMarket = new JButton("��������");
		toMarket.setBounds(700,450,200,70);
		this.add(toMarket);
		
		//���� �� (ȸ���̸�)��ŷ ���
		/*if(Member)*/	//ȸ������ �Ǻ�
		JButton save_Ranking = new JButton("���� �� ��ŷ ���");
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
		stageBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePanel().changePanel(mf,sView,new GameView(mf,m));
			}
		});
		
		//�������� ��ư
		toMarket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePanel().changePanel(mf, sView, new MarketPanel(m));
			}
		});
	}	
}
