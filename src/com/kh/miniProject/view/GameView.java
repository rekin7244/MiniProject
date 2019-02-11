package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private StageView sView;
	//Ÿ�̸� Ŭ���� & Back ��ư
	private CookingTime cookTimer;
	private JPanel gView;
	private TimerTest gameTimer;
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
	private int stageGold;
	private JButton gold;
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

		music = new Music("inGameMusic.mp3",false);
		music.start();



		//���� �г� �߰�
		gP = new GuestPanel(new ImageIcon("images/��ũ����-2017-09-24-����-6.00.47.png")
				.getImage().getScaledInstance(1024, 318, 0),orderDao);
		gP.setLayout(null);
		gP.setSize(Run.SCREEN_WIDTH,318);
		this.add(gP);

		//�����Ŵ��� ����
		if(stageLv<3) {
			cm = new CustomerManager(gP,orderDao,2,stageLv);
		}else {
			cm = new CustomerManager(gP,orderDao,3,stageLv);
		}
		//�������� Timer
		gameTimer = new TimerTest(gP,cm,this);
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

				result = JOptionPane.showOptionDialog(null,
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
	public void updateGold(int stageGold) {
		this.stageGold = stageGold;
		gold.setText(stageGold + "��");
	}
	public void gameOver() {
		//��Ʈ 3�� ������ gameover
	}
	public void endStage() {
		//����
		m.setStageGold(stageGold);
		m.setGold(m.getGold()+stageGold);
		if(m.getMaxStage()==stageLv) {
			m.setMaxStage(stageLv+1);
		}
		//���� ����
		cm.endCustomer();
		//���� ����
		music.close();

		String[] command = {"�������","���������� �̵�"};
		int result;
		Dialog dialog = new Dialog(mf);
		dialog.setBounds(150, 150, 200, 200);
		result = JOptionPane.showOptionDialog(null,
				"STAGE "+stageLv+" CLEAR!!\n Earned Gold : "+stageGold,
				"�αۺαۺн�",JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, command, command[0]);

		if(result==0) {
			ChangePanel.changePanel(mf, gView, new ResultPanel(mf,m));
			MemberDao mDao = new MemberDao();
			mDao.saveMember(m);
		}else {
			ChangePanel.changePanel(mf, gView, sView=new StageView(mf,m));
			MemberDao mDao = new MemberDao();
			mDao.saveMember(m);
		}
	}

	//btn Action
	class Event_Cook implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Equips Panel ActionListener
			JButton[] equips = eP.getEquips();
			if(e.getActionCommand().equals("���Ǳ�")) {
				System.out.println("���Ǳ�");
				Music buttonEnteredMusic = new Music("decision9.mp3",false);
				buttonEnteredMusic.start();
				if(drinksNo<3) {
					judgeLv("�����",equips);	
				}else {
					System.out.println("������� �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("����� ���� �ܿ� ����"+drinksNo);
			}
			if(e.getActionCommand().equals("�����̱��")) {
				System.out.println("�����̱��");
				if(tbkNo<4) {
					Music buttonEnteredMusic = new Music("cook1.mp3",false);
					buttonEnteredMusic.start();
					if(tbkNo<tableLv[0]) {
						judgeLv("������",equips);	
					}
				}else {
					System.out.println("�����̰� �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("������ ���� �ܿ� ����"+tbkNo);
			}
			if(e.getActionCommand().equals("Ƣ���")) {
				System.out.println("Ƣ���");
				if(friedNo<4) {
					Music buttonEnteredMusic = new Music("fried.mp3",false);
					buttonEnteredMusic.start();
					if(friedNo<tableLv[1]) {
						judgeLv("Ƣ��",equips);
					}
				}else {
					System.out.println("Ƣ���� �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("Ƣ�� ���� �ܿ� ����"+friedNo);
			}
			if(e.getActionCommand().equals("�������")) {
				System.out.println("�������");
				if(odengNo<4) {
					Music buttonEnteredMusic = new Music("cook1.mp3",false);
					buttonEnteredMusic.start();
					if(odengNo<tableLv[2]) {
						judgeLv("����",equips);
					}
				}else {
					System.out.println("������ �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("���� ���� �ܿ� ����"+tbkNo);
			}
			if(e.getActionCommand().equals("�����")) {
				System.out.println("�����");
				if(ramenNo<4) {
					Music buttonEnteredMusic = new Music("cook1.mp3",false);
					buttonEnteredMusic.start();
					if(ramenNo<tableLv[3]) {
						judgeLv("���",equips);
					}
				}else {
					System.out.println("����� �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("��� ���� �ܿ� ����"+tbkNo);
			}


			//MenuPanel ActionListener
			int temp;
			if(e.getActionCommand().equals("�����")) {
				if(drinksNo>0) {
					if((temp=orderDao.searchOrder(new MenuOrder("�����")))>=0) {
						Music buttonEnteredMusic = new Music("decision11.mp3",false);
						buttonEnteredMusic.start();
						System.out.println("temp:"+temp);
						cm.deleteLabel(temp);
						drinksNo--;
						System.out.println("����� �ܿ� ���� : " + drinksNo);
						refreshMenuTable();
					}else {
						System.out.println("�ֹ��� ������� �����ϴ�.");
					}
				}else {
					System.out.println("������� �����ϴ�.");
				}
			}
			if(e.getActionCommand().equals("������")) {
				if(tbkNo>0) {
					if((temp=orderDao.searchOrder(new MenuOrder("������")))>=0) {
						Music buttonEnteredMusic = new Music("cancel4.mp3",false);
						buttonEnteredMusic.start();
						cm.deleteLabel(temp);
						tbkNo--;
						System.out.println("������ �ܿ� ���� : " + tbkNo);
						refreshMenuTable();
					}else {
						System.out.println("�ֹ��� �����̰� �����ϴ�.");
					}
				}else {
					System.out.println("�����̰� �����ϴ�.");
				}
			}
			if(e.getActionCommand().equals("Ƣ��")) {
				if(friedNo>0) {
					if((temp=orderDao.searchOrder(new MenuOrder("Ƣ��")))>=0) {
						Music buttonEnteredMusic = new Music("cancel4.mp3",false);
						buttonEnteredMusic.start();
						cm.deleteLabel(temp);
						friedNo--;
						System.out.println("Ƣ�� �ܿ� ���� : " + friedNo);
						refreshMenuTable();
					}else {
						System.out.println("�ֹ��� Ƣ���� �����ϴ�.");
					}
				}else {
					System.out.println("Ƣ���� �����ϴ�.");
				}
			}
			if(e.getActionCommand().equals("����")) {
				if(odengNo>0) {
					if((temp=orderDao.searchOrder(new MenuOrder("����")))>=0) {
						Music buttonEnteredMusic = new Music("decision11.mp3",false);
						buttonEnteredMusic.start();
						System.out.println("temp:"+temp);
						cm.deleteLabel(temp);
						odengNo--;
						System.out.println("���� �ܿ� ���� : " + odengNo);
						refreshMenuTable();
					}else {
						System.out.println("�ֹ��� ������ �����ϴ�.");
					}
				}else {
					System.out.println("������ �����ϴ�.");
				}
			}
		}
	}

	public void refreshMenuTable() {
		//���Ǳ�, ������, Ƣ��
		mP.setting(mP,drinksNo,tbkNo,friedNo,odengNo,ramenNo);
	}

	public void judgeLv(String menuName,JButton[] equips) {
		if(menuName.equals("�����")) {
			equips[0].setEnabled(false);
			cookTimer = new CookingTime(equips[0],5,"�����");
			gView.add(cookTimer);
			drinksNo++;
		}else if(menuName.equals("������")) {
			equips[1].setEnabled(false);
			cookTimer = new CookingTime(equips[1],7-(int)(equipLv[0]*1.5),"������");
			gView.add(cookTimer);
			tbkNo++;	
		}else if(menuName.equals("Ƣ��")) {
			equips[2].setEnabled(false);
			cookTimer = new CookingTime(equips[2],10-(int)(equipLv[1]*1.5),"Ƣ��");
			gView.add(cookTimer);
			friedNo++;
		}else if(menuName.equals("����")) {
			equips[3].setEnabled(false);
			cookTimer = new CookingTime(equips[3],8-(int)(equipLv[2]*1.5),"����");
			gView.add(cookTimer);
			odengNo++;
		}else if(menuName.equals("���")) {
			equips[4].setEnabled(false);
			cookTimer = new CookingTime(equips[4],15-equipLv[3]*2,"���");
			gView.add(cookTimer);
			ramenNo++;
		}
	}
}