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
import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.run.Run;

public class GameView extends JPanel{
	//�г� ���� ����� ���� ���� ����
	public MainFrame mf;
	public GuestPanel gP;
	public MenuPanel mP;
	public EquipmentPanel eP;
	public CustomerManager cm;
	//Ÿ�̸� Ŭ���� (�׽�Ʈ) & Back ��ư
	private CookingTime cookTimer;
	private JPanel gView;
	private TimerTest gameTimer;
	private JButton backButton;
	private Image backButtonImage;
	//���� �� ����� ����
	private int drinksNo;	//���ᰳ��
	private int friedNo;	//Ƣ�谳��
	private int tbkNo;		//�����̰���
	private int odengNo;	//��������
	private int ramenNo;	//��鰳��
	//Member ���� ���������
	private Member m;
	//stage ���� ����
	private int stageLv;
	private int stageGold;
	private JButton gold;
	//�ֹ� ���� ����
	private OrderDao orderDao;
	
	//�޴��� ���� ����
	private int[] tableLv;

	//cons
	public GameView(MainFrame mf,Member m,int stageLv) {
		orderDao = new OrderDao(this);			//�������� �� orderDao ���� ���ѹ���!!
		this.gView = this;
		this.mf = mf;
		this.m = m;
		this.stageLv = stageLv;
		this.tableLv = m.getTableLv();
		this.setLayout(null);
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);

		//�� �г� �߰�
		gP = new GuestPanel(new ImageIcon("images/��ũ����-2017-09-24-����-6.00.47.png")
				.getImage().getScaledInstance(1024, 318, 0),orderDao);
		gP.setLayout(null);
		gP.setSize(Run.SCREEN_WIDTH,318);
		this.add(gP);

		//���Ŵ��� ����
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
		mP.setting(mP,drinksNo,tbkNo,friedNo);
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
		String[] command = {"�������","���������� �̵�"};
		int result;
		
		Dialog dialog = new Dialog(mf);
		dialog.setBounds(150, 150, 200, 200);
		m.setStageGold(stageGold);
		m.setGold(m.getGold()+stageGold);
		m.setMaxStage(stageLv+1);
		
		result = JOptionPane.showOptionDialog(null,
				"STAGE "+stageLv+" CLEAR!!\n Earned Gold : "+stageGold,
				"�αۺαۺн�",JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, command, command[0]);

		if(result==0) {
			ChangePanel.changePanel(mf, gView, new ResultPanel(mf,m));
		}else {
			ChangePanel.changePanel(mf, gView, new StageView(mf,m));
		}
	}
	//btn Action
	class Event_Cook implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Equips Panel ActionListener
			JButton[] equips = eP.getEquips();
			if(e.getActionCommand().equals("�����̱��")) {
				System.out.println("�����̱��");
				if(tbkNo<4) {
					
					if(tableLv[0]==1 && tbkNo<1) {
						judgeLv("������",equips);					
					}else if(tableLv[0]==2 && tbkNo<2) {
						judgeLv("������",equips);			
					}else if(tableLv[0]==3 && tbkNo<3) {
						judgeLv("������",equips);	
					}else if(tableLv[0]==4 && tbkNo<4) {
						judgeLv("������",equips);	
					}
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
					if(tableLv[1]==1 && drinksNo<1) {
						judgeLv("�����",equips);					
					}else if(tableLv[1]==2 && drinksNo<2) {
						judgeLv("�����",equips);			
					}else if(tableLv[1]==3 && drinksNo<3) {
						judgeLv("�����",equips);	
					}else if(tableLv[1]==4 && drinksNo<4) {
						judgeLv("�����",equips);	
					}
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
					if(tableLv[2]==1 && friedNo<1) {
						judgeLv("Ƣ��",equips);					
					}else if(tableLv[2]==2 && friedNo<2) {
						judgeLv("Ƣ��",equips);			
					}else if(tableLv[2]==3 && friedNo<3) {
						judgeLv("Ƣ��",equips);	
					}else if(tableLv[2]==4 && friedNo<4) {
						judgeLv("Ƣ��",equips);	
					}
				}else {
					System.out.println("Ƣ���� �ִ� �����Դϴ�.");
				}
				refreshMenuTable();
				System.out.println("Ƣ�� ���� �ܿ� ����"+friedNo);
			}

			//MenuPanel ActionListener
			int temp;
			if(e.getActionCommand().equals("������")) {
				if(tbkNo>0) {
					if((temp=orderDao.searchOrder(new MenuOrder("������")))>=0) {
						cm.deleteLabel(temp);
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
					if((temp=orderDao.searchOrder(new MenuOrder("Ƣ��")))>=0) {
						cm.deleteLabel(temp);
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
					if((temp=orderDao.searchOrder(new MenuOrder("�����")))>=0) {
						System.out.println("temp:"+temp);
						cm.deleteLabel(temp);
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
	
	public void judgeLv(String menuName,JButton[] equips) {
		if(menuName.equals("������")) {
			equips[1].setEnabled(false);
			cookTimer = new CookingTime(equips[1],m,6,"������");
			gView.add(cookTimer);
			tbkNo++;		
		}else if(menuName.equals("�����")) {
			equips[0].setEnabled(false);
			cookTimer = new CookingTime(equips[0],m,5,"�����");
			gView.add(cookTimer);
			drinksNo++;
		}else if(menuName.equals("Ƣ��")) {
			equips[2].setEnabled(false);
			cookTimer = new CookingTime(equips[2],m,10,"Ƣ��");
			gView.add(cookTimer);
			friedNo++;
		}
	}
}