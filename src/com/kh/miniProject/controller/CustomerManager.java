package com.kh.miniProject.controller;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.OrderLabel;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.music.Music;
import com.kh.miniProject.view.GameView;
import com.kh.miniProject.view.GuestPanel;

public class CustomerManager {
	private GuestPanel gP;
	private OrderDao orderDao;
	private CustomerTimer[] cTimer = new CustomerTimer[3];
	private GameView gView;
	private MessageTimer mt;
	private CustomerManager cm;


	private int stageLv; 						// stageLv
	private int maxOrderNo; 					// �ֹ��ϴ� �޴��� �ִ� ��
	private int orderNo; 						// �ֹ� ��ȣ
	private int customerNo = 0; 				// �մ� ��ȣ
	private int count = 0; 						// �����ȣ
	private boolean guest = true;				// ����մ� �Ǻ�(�޴����� 2�� ����)
	private OrderLabel[] orderLabel; 			// �ֹ� �̹���
	private JLabel[] customer = new JLabel[3]; 	// customer��
	private int[] customerOrderNo = new int[3]; // customer���� �ֹ���
	private int[] customerX = {744,444,144};	// customer x ��ǥ
	private Timer guestTimer;					// �մ� ������ Ÿ�̸�
	private int combo;							// �޺�	(�մ� ������ ����)
	
	// cons
	public CustomerManager(GameView gView, GuestPanel gP, OrderDao orderDao, int maxOrderNo, int stageLv) {
		orderLabel = new OrderLabel[100]; // �ʱ�ȭ
		this.gView = gView;
		this.gP = gP;
		this.orderDao = orderDao;
		this.maxOrderNo = maxOrderNo;
		this.stageLv = stageLv;
		this.cm = this;
	}

	// �մ� ����
	public void guest() {
		Random rand = new Random();
		if(stageLv>4) {	//5stage���� ���� ����
			if (count == 5) {	//count0���� �����ؼ� 5�� ����մ� ���� 
				cTimer[customerNo] = new CustomerTimer(this,(11-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // �� �մԺ� Ÿ�̸� ����
				gP.add(cTimer[customerNo]);
				mt = new MessageTimer(this, 1.5, customerX[customerNo]);
				gP.add(mt);
				Image icon = new ImageIcon("images/Inked����մ�2.png").getImage().getScaledInstance(190, 250, 0);		// ����մ� �̹���
				customer[customerNo] = new JLabel(new ImageIcon(icon)); 											// ����մ� ��
				addOrder(maxOrderNo, customerX[customerNo], guest);
				count = 0;
				guest = false;
			} else {
				Image[] icon = {new ImageIcon("images/�ճ�2.png").getImage().getScaledInstance(190, 250, 0),
						new ImageIcon("images/�ճ�1.png").getImage().getScaledInstance(190, 250, 0),
						new ImageIcon("images/�ճ�3.png").getImage().getScaledInstance(190, 250, 0)}; 	// �մ� �̹���
				customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); 	// �մԶ�
				count++;
				guest = true;
			}
		}else {	//1~4�������� ���� ���� ����
			Image[] icon = {new ImageIcon("images/�ճ�2.png").getImage().getScaledInstance(190, 250, 0),
					new ImageIcon("images/�ճ�1.png").getImage().getScaledInstance(190, 250, 0),
					new ImageIcon("images/�ճ�3.png").getImage().getScaledInstance(190, 250, 0)};	 // �մ� �̹���
			customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); // �մԶ�
			guest = true;
		}

		customerOrderNo[customerNo] = maxOrderNo;
		// ���� �ֹ�
		// �մ� ��ü ����� (1,2,3�� �ڸ� ����)
		customer[customerNo].setSize(150,220);					//�մԶ� ������
		
		if(guest) {customer[customerNo].setLocation(0, 105);	//�մԶ� ��ġ
		}else {customer[customerNo].setLocation(customerX[customerNo], 105);}
		
		gP.add(customer[customerNo]); 		// �гο� �մԶ� �߰�
		
		
		if(guest) {time();}		//�Ϲݼմ� ������ �̵��ϴ� �޼ҵ� ����

		// �մ� No ���� (0~2)
		if (customerNo != 2) {
			customerNo++;
		} else {
			customerNo = 0;
		}
	}
	
	public void time() {
		guestTimer = new Timer(20,new GuestTimer());
		guestTimer.start();
	}

	public void addOrder(int menuNo, int x, boolean guest) {
		int y = 15; // y�� �ʱ�ȭ

		for (int i = 0; i < menuNo; i++) { // �޴� ������ ���� �ݺ�
			int random = 0;
			if (stageLv == 1) {
				random = new Random().nextInt(2); // 1�������� ���� 2����
			} else if (stageLv == 2) {
				random = new Random().nextInt(3); // 2�������� ���� 3����
			} else if (stageLv == 3) {
				random = new Random().nextInt(4); // 3�������� ���� 4����
			} else {
				random = new Random().nextInt(5); // 4�������� ���� 5����
			}

			if (guest == false) { //����մ� ���� ����
				if (random == 0) {
					orderDao.addOrder(new MenuOrder("������", 4600, orderNo));
				} else if (random == 1) {
					orderDao.addOrder(new MenuOrder("�����", 2000, orderNo));
				} else if (random == 2) {
					orderDao.addOrder(new MenuOrder("Ƣ��", 3600, orderNo));
				} else if (random == 3) {
					orderDao.addOrder(new MenuOrder("����", 4000, orderNo));
				} else if (random == 4) {
					orderDao.addOrder(new MenuOrder("���", 7000, orderNo));
				}
			} else { //�Ϲݼմ� ���� ����
				if (random == 0) {
					orderDao.addOrder(new MenuOrder("������", 2300, orderNo));
				} else if (random == 1) {
					orderDao.addOrder(new MenuOrder("�����", 1000, orderNo));
				} else if (random == 2) {
					orderDao.addOrder(new MenuOrder("Ƣ��", 1800, orderNo));
				} else if (random == 3) {
					orderDao.addOrder(new MenuOrder("����", 2000, orderNo));
				} else if (random == 4) {
					orderDao.addOrder(new MenuOrder("���", 3500, orderNo));
				}
			}
			orderLabel[orderNo] = new OrderLabel(orderNo); // order Label �߰�

			// �̹��� �߰�
			// �������� ���� ������,�����,Ƣ��,����,���
			Image food = null;
			if (random == 0) {
				food = new ImageIcon("images/tbk1.png").getImage().getScaledInstance(50, 50, 0);
			} else if (random == 1) {
				food = new ImageIcon("images/drink1.png").getImage().getScaledInstance(50, 40, 0);
			} else if (random == 2) {
				food = new ImageIcon("images/fried1.png").getImage().getScaledInstance(50, 50, 0);
			} else if (random == 3) {
				food = new ImageIcon("images/����.jpg").getImage().getScaledInstance(50, 50, 0);
			} else if (random == 4) {
				food = new ImageIcon("images/ramen.png").getImage().getScaledInstance(50, 50, 0);
			}

			// ��ġ ����
			orderLabel[orderNo].setIcon(new ImageIcon(food));
			orderLabel[orderNo].setBounds(x + 150, y + 120, 70, 50);
			y += 50;
			gP.add(orderLabel[orderNo]);

			// orderNo����
			if (orderNo != maxOrderNo * 3 - 1) {
				orderNo++;
			} else {
				orderNo = 0;
			}
		}

	}

	public void deleteLabel(int orderNo) { // �ֹ����� ���� �� ��� �ֹ� ���� �Ϸ�� �մ�(+Ÿ�̸�) ����
		gP.remove(orderLabel[orderNo]);

		// �մԿ� ���� �մ� �ֹ��� ����
		if (orderNo < maxOrderNo * 1) {
			customerOrderNo[0] -= 1;
		} else if (orderNo < maxOrderNo * 2) {
			customerOrderNo[1] -= 1;
		} else if (orderNo < maxOrderNo * 3) {
			customerOrderNo[2] -= 1;
		}
		// �մ� �ֹ� �����
		for (int i = 0; i < customerOrderNo.length; i++) {
			if (cTimer[i] != null) {
				if (customerOrderNo[i] == 0) {
					cTimer[i].timerStop();
					gP.remove(cTimer[i]);
					gP.remove(customer[i]);
				}
			}
		}
		gP.repaint();
		Music buttonEnteredMusic = new Music("coins_5.mp3", false);
		buttonEnteredMusic.start();
	}
	
	public void deleteMessage() {
		gP.remove(mt);
		gP.repaint();
	}

	public void deleteCustomer(int customerNo) { // �ð� ����� �ֹ������� �մ�(+Ÿ�̸�) ����
		for (int i = maxOrderNo * customerNo; i < maxOrderNo * customerNo + maxOrderNo; i++) {
			if (orderLabel[i] != null) {
				gP.remove(orderLabel[i]);
				orderDao.removeOrder(i);
			}
		}
		gView.gameOver();
		cTimer[customerNo].timerStop();
		gP.remove(cTimer[customerNo]);
		gP.remove(customer[customerNo]);
		gP.repaint();
	}

	public void endCustomer() {
		for (int i = 0; i < cTimer.length; i++) {
			if (cTimer[i] != null) {
				cTimer[i].timerStop();
			}
		}
	}
	
	class GuestTimer implements ActionListener{
		private int cNo = customerNo;
		private boolean notHidden = guest;
		@Override
		public void actionPerformed(ActionEvent e) {
			Point point = customer[cNo].getLocation();
			customer[cNo].setLocation((point.x+6),(point.y));
			gP.repaint();
			if((point.x)>=customerX[cNo]) {
				guestTimer.stop();
				addOrder(maxOrderNo, customerX[cNo], notHidden);
				cTimer[cNo] = new CustomerTimer(cm,11-(0.3*stageLv),cNo,customerX[cNo]); // �� �մԺ� Ÿ�̸� ����
				gP.add(cTimer[cNo]);
			}
		}
	}
}
