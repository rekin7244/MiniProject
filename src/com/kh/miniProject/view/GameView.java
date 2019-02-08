package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.run.Run;

public class GameView extends JPanel{
	private int equipPanelySize = 250;			//��� �г� ����ũ��

	//�г� ���� ����� ���� ���� ����
	public MainFrame mf;
	public GuestPanel gP;
	public MenuPanel mP;
	public EquipmentPanel eP;
	//Ÿ�̸� Ŭ���� (�׽�Ʈ) & Back ��ư
	private JPanel gView;
	private TimerTest gameTimer;
	private JButton backButton;
	private Image backButtonImage;
	//���� �� ����� ����
	private int drinksNo;
	private int friedNo;
	private int tbkNo;
	private int odengNo;
	private int sundeNo;
	private Member m;
	//���������� ��� ����
	private int stageGold=0;
	JButton gold;
	OrderDao orderDao;
	//cons
	public GameView(MainFrame mf,Member m) {
		orderDao = new OrderDao();			//�������� �� orderDao ���� ���ѹ���!!
		this.gView = this;
		this.mf = mf;
		this.m = m;
		this.setLayout(null);
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);

		//Timer
		gameTimer = new TimerTest();
		this.add(gameTimer);


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
				}
			}		
		});
		backButton.setBounds(950,0,50,50);
		backButton.setContentAreaFilled(false);
		this.add(backButton);

		//�Խ�Ʈ �г� �߰�
		gP = new GuestPanel(new ImageIcon("images/��ũ����-2017-09-24-����-6.00.47.png")
				.getImage().getScaledInstance(1024, 318, 0),orderDao);
		gP.setLayout(null);
		gP.setSize(Run.SCREEN_WIDTH,318);
		this.add(gP);

		//��� ���
		JButton gold = new JButton("���");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0,0,200,30);
		gold.setText("GOLD : "+m.getGold()+"G");
		this.add(gold);

		//�޴� �г� �߰�
		mP = new MenuPanel();
		mP.setting(mP,drinksNo,tbkNo,friedNo);
		this.add(mP);

		//��� �г� �߰�
		eP = new EquipmentPanel();
		eP.equipsSetting(eP);
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

	//btn Action
	class Event_Cook implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton[] equips = eP.getEquips();
			if(e.getActionCommand().equals("�����̱��")) {
				System.out.println("�����̱��");
				if(tbkNo<4) {
					equips[1].setEnabled(false);
					tbkNo++;
					equips[1].setEnabled(true);
				}else {
					System.out.println("�����̰� �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("������ ���� �ܿ� ����"+tbkNo);
			}		
			if(e.getActionCommand().equals("������")) {
				System.out.println("������");
			}
			if(e.getActionCommand().equals("���Ǳ�")) {
				System.out.println("���Ǳ�");
				if(drinksNo<3) {
					drinksNo++;
				}else {
					System.out.println("������� �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("����� ���� �ܿ� ����"+drinksNo);
			}
			if(e.getActionCommand().equals("�������")) {
				System.out.println("�������");
			}
			if(e.getActionCommand().equals("Ƣ���")) {
				System.out.println("Ƣ���");
				if(friedNo<4) {
					friedNo++;
				}else {
					System.out.println("Ƣ���� �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("Ƣ�� ���� �ܿ� ����"+friedNo);
			}
			//System.out.println("MenuListener actionPerformed() -> " + orderDao.getOrderList().size());
			if(e.getActionCommand().equals("������")) {
				if(tbkNo>0) {
					if(orderDao.searchOrder(new MenuOrder("������"))) {
						tbkNo--;
						System.out.println("������ �ܿ� ���� : " + tbkNo);
						mP.setting(mP,drinksNo,tbkNo,friedNo);
					}else {
						System.out.println("�ֹ��� �����̰� �����ϴ�.");
					}
				}else {
					System.out.println("�����̰� �����ϴ�.");
				}
			}
			if(e.getActionCommand().equals("Ƣ��")) {
				if(friedNo>0) {
					if(orderDao.searchOrder(new MenuOrder("Ƣ��"))) {
						friedNo--;
						System.out.println("Ƣ�� �ܿ� ���� : " + friedNo);
						mP.setting(mP,drinksNo,tbkNo,friedNo);
					}else {
						System.out.println("�ֹ��� Ƣ���� �����ϴ�.");
					}
				}else {
					System.out.println("Ƣ���� �����ϴ�.");
				}
			}
			if(e.getActionCommand().equals("�����")) {
				if(drinksNo>0) {
					if(orderDao.searchOrder(new MenuOrder("�����"))) {
						drinksNo--;
						System.out.println("����� �ܿ� ���� : " + drinksNo);
						mP.setting(mP,drinksNo,tbkNo,friedNo);
					}else {
						System.out.println("�ֹ��� ������� �����ϴ�.");
					}
				}else {
					System.out.println("������� �����ϴ�.");
				}
			}
		}
	}
	public void refreshMenuTable() {
		//���Ǳ�, ������, Ƣ��
		mP.setting(mP,drinksNo,tbkNo,friedNo);
	}
}
