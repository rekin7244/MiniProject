package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kh.miniProject.controller.CookingTime;
import com.kh.miniProject.controller.CustomerManager;
import com.kh.miniProject.model.dao.MemberDao;
import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.music.Music;
import com.kh.miniProject.run.Run;

public class GameView extends JPanel{
	//�г� ���� ����� ���� ���� ����
	private MainFrame mf;
	private GuestPanel gP;
	private MenuPanel mP;
	private EquipmentPanel eP;
	private CustomerManager cm;
	//Ÿ�̸� Ŭ���� & Back ��ư
	private CookingTime cookTimer;
	private JPanel gView;
	private StageTimer gameTimer;
	private JButton backButton;
	private Image backButtonImage;
	//Music
	private Music music;
	//���� �� ����� ����
	private int drinksNo;	//���ᰳ��
	private int friedNo;	//Ƣ�谳��
	private int tbkNo;		//�����̰���
	private int odengNo;	//��������
	private int ramenNo;	//��鰳��
	//stage ���� ����
	private int stageLv;
	private int stageGold[];
	private JButton gold;
	private int credit=3;		//���
	private JLabel[] heart=new JLabel[3];
	
	//�޺�&���ʽ� ���� �� �ʱ�ȭ (�մ� ������ ����)
	private int combo;
	private FontMetrics fontMetrics;
	private JLabel comboLabel;
	private int bonus;
	  
	private JLabel goldLabel;
	//�ֹ� ���� ����
	private OrderDao orderDao;
	//Member ���� ���������
	private Member m;
	//��� ����
	private int[] equipLv;
	private int[] tableLv;

	//cons
	public GameView(MainFrame mf,Member m,int stageLv) {
		orderDao = new OrderDao(this);			//�������� �� orderDao ���� ���ѹ���!!
		this.gView = this;
		this.mf = mf;
		this.m = m;
		this.stageLv = stageLv;
		this.equipLv = m.getEquipsLv();
		this.tableLv = m.getTableLv();
		this.setLayout(null);
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);
		this.combo=0;
		stageGold = new int[3];

		//����
		music = new Music("inGameMusic.mp3",false);
		music.start();


		//�� �г� �߰�
		gP = new GuestPanel(new ImageIcon("images/��ũ����-2017-09-24-����-6.00.47.png")
				.getImage().getScaledInstance(1024, 318, 0),orderDao);
		gP.setLayout(null);
		gP.setSize(Run.SCREEN_WIDTH,318);
		this.add(gP);
		
		comboLabel = new JLabel("Combo");
		comboLabel.setBounds(70, 230, 170, 30);
		comboLabel.setFont(new Font("Dialog",Font.BOLD,30));
		gP.add(comboLabel);

		//��Ʈ ����
		if(stageLv!=10) {
			for (int i = 0; i < heart.length; i++) {
				heart[i]=new JLabel(new ImageIcon(new ImageIcon("images/heart.png")
						.getImage().getScaledInstance(40, 40, 0)));
				heart[i].setBounds(40+i*45,270,40,40);
				gP.add(heart[i]);
			}
		}else {
			heart[0]=new JLabel(new ImageIcon(new ImageIcon("images/heart.png").getImage().getScaledInstance(80, 80, 0)));
			heart[0].setBounds(50,220,80,80);
			gP.add(heart[0]);
		}

		//���Ŵ��� ����
		if(stageLv<3) {
			cm = new CustomerManager(this,gP,orderDao,2,stageLv);
		}else if(stageLv<8) {
			cm = new CustomerManager(this,gP,orderDao,3,stageLv);
		}else {
			cm = new CustomerManager(this,gP,orderDao,4,stageLv);
		}
		//�������� Timer
		gameTimer = new StageTimer(gP,cm,this,stageLv);
		gP.add(gameTimer);

		//backButton
		backButton = new JButton();
		backButtonImage = new ImageIcon("images/backButton.png").getImage().getScaledInstance(50,50,0);
		backButton.setIcon(new ImageIcon(backButtonImage));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] command = {"Ȯ��","���"};
				int result;

				result = JOptionPane.showOptionDialog(mf,
						"���� �׸��νðڽ��ϱ� ? ",
						"�αۺαۺн�",JOptionPane.YES_NO_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, command, command[0]);

				if(result==0) {
					gameTimer.timerStop();
					ChangePanel.changePanel(mf, gView, new StageView(mf,m));
					music.close();
					cm.endCustomer();
				}
			}		
		});
		backButton.setBounds(950,0,50,50);
		backButton.setContentAreaFilled(false);
		gP.add(backButton);


		//��� ���
		gold = new JButton("���");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0,0,200,30);
		gold.setText(0 + "��");
		gP.add(gold);

		//�޴� �г� �߰�
		mP = new MenuPanel(m);
		mP.setting(mP,drinksNo,tbkNo,friedNo,odengNo,ramenNo);
		this.add(mP);

		//��� �г� �߰�
		eP = new EquipmentPanel();
		eP.equipsSetting(eP,m);
		this.add(eP);


		//ActionListener setting
		JButton[] equips = eP.getEquips();
		for (int i = 0; i < equips.length; i++) {
			equips[i].addActionListener(new Event_Cook());
		}		
		JButton[] menuButton = mP.getMenuButton();
		for(int i=0; i<menuButton.length;i++) {
			menuButton[i].addActionListener(new Event_Cook());
		}
	}

	//��� ����
	public void updateGold(int stageGold) {
		this.stageGold[0] = stageGold;
		gold.setText(stageGold + "��");
	}

	public void gameOver() {	//����ɶ����� ��Ʈ ����
		if(stageLv!=10) {		//stage10����
			credit--;
			gP.remove(heart[credit]);
			comboUpdate(true);		//(�մ� ������ �޺�����)
			//��Ʈ 3�� ������ gameover
			if(credit==0) {
				//��� ���� ����
				m.setStageGold(stageGold);
				m.setGold(m.getGold()+stageGold[0]);
				//���� ����
				gameTimer.timerStop();
				cm.endCustomer();
				//���� ����
				music.close();

				JOptionPane.showMessageDialog(mf, "GAME OVER!!");
				ChangePanel.changePanel(mf, gView, new StageView(mf,m));
				return;
			}
		}
	}

	//Ÿ�̸� ����� �������� ����
	public void endStage() {
		//��� ���� ����
		stageGold[1] = combo;
		stageGold[2] = bonus;
		m.setStageGold(stageGold);
		m.setGold(m.getGold()+stageGold[0]);
		if(m.getMaxStage()==stageLv&&stageLv!=10) {
			m.setMaxStage(stageLv+1);
		}
		//���� ����
		cm.endCustomer();
		//���� ����
		music.close();

		String[] command = {"�������","���������� �̵�"};
		int result;
		result = JOptionPane.showOptionDialog(mf,
				"STAGE "+stageLv+" CLEAR!!\n Earned Gold : "+stageGold[0],
				"�αۺαۺн�",JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, command, command[0]);

		if(result==0) {
			ChangePanel.changePanel(mf, gView, new ResultPanel(mf,m));
			MemberDao mDao = new MemberDao();
			mDao.saveMember(m);
		}else {
			ChangePanel.changePanel(mf, gView, new StageView(mf,m));
			MemberDao mDao = new MemberDao();
			mDao.saveMember(m);
		}
	}

	//btn Action
	class Event_Cook implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Music buttonEnteredMusic=null;

			//Equips Panel ActionListener
			JButton[] equips = eP.getEquips();
			if(e.getSource()==equips[0]) {
				buttonEnteredMusic = new Music("decision9.mp3",false);
				if(drinksNo<4) {judgeLv("�����",equips);}
			}
			if(e.getSource()==equips[1]) {
				if(tbkNo<4) {
					buttonEnteredMusic = new Music("cook1.mp3",false);
					if(tbkNo<tableLv[0]) {judgeLv("������",equips);}
				}
			}
			if(e.getSource()==equips[2]) {
				if(friedNo<4) {
					buttonEnteredMusic = new Music("fried.mp3",false);
					if(friedNo<tableLv[1]) {judgeLv("Ƣ��",equips);}
				}
			}
			if(e.getSource()==equips[3]) {
				if(odengNo<4) {
					buttonEnteredMusic = new Music("cook1.mp3",false);
					if(odengNo<tableLv[2]) {judgeLv("����",equips);}
				}
			}
			if(e.getSource()==equips[4]) {
				if(ramenNo<4) {
					buttonEnteredMusic = new Music("cook1.mp3",false);
					if(ramenNo<tableLv[3]) {judgeLv("���",equips);}
				}
			}


			//MenuPanel ActionListener
			int ordertemp = -1;
			int pricetemp = -1;
			if(e.getActionCommand().equals("�����")) {
				MenuOrder menu = new MenuOrder("�����");
				if(drinksNo>0) {
					if((pricetemp=orderDao.searchPrice(menu))>=0&&((ordertemp=orderDao.searchOrder(menu))>=0)) {
						buttonEnteredMusic = new Music("decision11.mp3",false);
						drinksNo--;
						comboUpdate(false);
					}
				}
			}
			if(e.getActionCommand().equals("������")) {
				MenuOrder menu = new MenuOrder("������");
				if(tbkNo>0) {
					if((pricetemp=orderDao.searchPrice(menu))>=0&&((ordertemp=orderDao.searchOrder(menu))>=0)) {
						buttonEnteredMusic = new Music("cancel4.mp3",false);
						tbkNo--;
						comboUpdate(false);
					}
				}
			}
			if(e.getActionCommand().equals("Ƣ��")) {
				MenuOrder menu = new MenuOrder("Ƣ��");
				if(friedNo>0) {
					if((pricetemp=orderDao.searchPrice(menu))>=0&&((ordertemp=orderDao.searchOrder(menu))>=0)) {
						buttonEnteredMusic = new Music("cancel4.mp3",false);
						friedNo--;
						comboUpdate(false);
					}
				}
			}
			if(e.getActionCommand().equals("����")) {
				MenuOrder menu = new MenuOrder("����");
				if(odengNo>0) {
					if((pricetemp=orderDao.searchPrice(menu))>=0&&((ordertemp=orderDao.searchOrder(menu))>=0)) {
						buttonEnteredMusic = new Music("decision11.mp3",false);
						odengNo--;
						comboUpdate(false);
					}
				}
			}
			if(e.getActionCommand().equals("���")) {
				MenuOrder menu = new MenuOrder("���");
				if(ramenNo>0) {
					if((pricetemp=orderDao.searchPrice(menu))>=0&&((ordertemp=orderDao.searchOrder(menu))>=0)) {
						buttonEnteredMusic = new Music("decision11.mp3",false);
						ramenNo--;
						comboUpdate(false);
					}
				}
			}
			if(ordertemp!=-1) {cm.deleteLabel(ordertemp,pricetemp);}
			if(buttonEnteredMusic!=null) {buttonEnteredMusic.start();}
			refreshMenuTable();
		}
	}

	public void refreshMenuTable() {
		//�޴� ���̺� ���� ����
		mP.setting(mP,drinksNo,tbkNo,friedNo,odengNo,ramenNo);
	}

	public void judgeLv(String menuName,JButton[] equips) {		//���� ���� ����
		if(menuName.equals("�����")) {
			equips[0].setEnabled(false);
			cookTimer = new CookingTime(equips[0],4,"�����");	//���� Ÿ�̸�
			drinksNo++;
		}else if(menuName.equals("������")) {
			equips[1].setEnabled(false);
			int temp=7;
			if(equipLv[0]==2) { 	temp=7;
			}else if(equipLv[0]==3) {temp=6;}
			cookTimer = new CookingTime(equips[1],temp,"������");
			if(tbkNo+equipLv[0]<tableLv[0]) {		//���� ����	
				tbkNo+=equipLv[0];
			}else {tbkNo=tableLv[0];}
		}else if(menuName.equals("Ƣ��")) {
			equips[2].setEnabled(false);
			int temp=7;
			if(equipLv[1]==2) { 	temp=8;
			}else if(equipLv[1]==3) {temp=8;}
			cookTimer = new CookingTime(equips[2],temp,"Ƣ��");
			if(friedNo+equipLv[1]<tableLv[1]) {		//���� ����
				friedNo+=equipLv[1];
			}else {friedNo=tableLv[1];}
		}else if(menuName.equals("����")) {
			equips[3].setEnabled(false);
			int temp=8;
			if(equipLv[2]==2) { 	temp=9;
			}else if(equipLv[2]==3) {temp=8;}
			cookTimer = new CookingTime(equips[3],temp,"����");
			if(odengNo+equipLv[2]<tableLv[2]) {		//���� ����
				odengNo+=equipLv[2];
			}else {odengNo=tableLv[2];}
		}else if(menuName.equals("���")) {
			equips[4].setEnabled(false);
			int temp=9;
			if(equipLv[3]==2) { 	temp=10;
			}else if(equipLv[3]==3) {temp=10;}
			cookTimer = new CookingTime(equips[4],temp,"���");
			if(ramenNo+equipLv[3]<tableLv[3]) {		//���� ����
				ramenNo+=equipLv[3];
			}else {ramenNo=tableLv[3];}
		}
	}
	
	
	public void comboUpdate(boolean isFail) {
		if(!isFail) {
			combo++;
			
			if(combo==10) {
				bonus += 5000;
				stageGold[0]+=bonus;
				Music comboMusic = new Music("�޺�����.mp3",false);
				comboMusic.start();
				
			}else if(combo==20) {
				bonus += 7000;
				stageGold[0]+=bonus;
				Music comboMusic = new Music("�޺�����.mp3",false);
				comboMusic.start();
				
			}else if(combo==40) {
				bonus += 10000;
				stageGold[0]+=bonus;
				Music comboMusic = new Music("�޺�����.mp3",false);
				comboMusic.start();
			}
			
			comboLabel.setText(combo + " Combo");
		}else {
			combo =0;
			comboLabel.setText("Combo");
		}
	}

	public int getCombo() {
		return combo;
	}

	public void setCombo(int combo) {
		this.combo = combo;
	}
	
	
	
}