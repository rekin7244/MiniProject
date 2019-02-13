package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	JButton save_Ranking = new JButton("����A�� Aa��A ���� A����a");
	JButton toMarket = new JButton("��oA��A����I");
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
		this.equipsLv = m.getEquipsLv();

		this.setLayout(null);
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);
		
		music = new Music("inGameMusic.mp3",false);
		music.start();
		
		//��������1~5
		for(int i=0;i<5;i++) {
			stageBtn[i] = new JButton("STAGE "+ (i+1));
			stageBtn[i].setBounds(50+stagexSize*i,50,stagexSize,stageySize);
			stageBtn[i].setIcon(new ImageIcon(btnImg[i]));
			stageBtn[i].setContentAreaFilled(false);
			stageBtn[i].setBorderPainted(false);
			this.add(stageBtn[i]);
		}
		//��������6~10
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
			stageBtn[i].addMouseListener(new MyAction());
		}
		//�������� ��ư
		toMarket.addMouseListener(new MyAction());
		//���� �� ��ŷ ��ư
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
