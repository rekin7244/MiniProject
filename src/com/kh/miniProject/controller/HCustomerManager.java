package com.kh.miniProject.controller;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.kh.miniProject.controller.CustomerManager.GuestTimer;
import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.OrderLabel;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.music.Music;
import com.kh.miniProject.view.GameView;
import com.kh.miniProject.view.GuestPanel;

public class HCustomerManager extends CustomerManager{
	private GuestPanel gP;
	private OrderDao orderDao;
	public CustomerTimer[] cTimer = new CustomerTimer[4];
	private GameView gView;
	private CustomerManager cm;
	private Message m;

	private int stageLv; 						// stageLv
	private int maxOrderNo; 					// �ֹ��ϴ� �޴��� �ִ� ��
	private int orderNo; 						// �ֹ� ��ȣ
	private int customerNo = 0; 				// �մ� ��ȣ
	private int count = 0; 						// �����ȣ
	private boolean guest = true;				// ����մ� �Ǻ�(�޴����� 2�� ����)
	private OrderLabel[] orderLabel; 			// �ֹ� �̹���
	private JLabel[] customer = new JLabel[4]; 	// customer��
	private int[] customerOrderNo = new int[4]; // customer���� �ֹ���
	private int[] customerX = {798,594,396,198};// customer x ��ǥ
	private Timer guestTimer;					// �մ� ������ Ÿ�̸�

	// cons
	public HCustomerManager(GameView gView, GuestPanel gP, OrderDao orderDao, int maxOrderNo, int stageLv) {
		super(gView,gP,orderDao,maxOrderNo,stageLv);
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
		if(stageLv!=10) {	//6~9stage ����
			if (count == 5) {	//count0���� �����ؼ� 5�� ����մ� ����
				m = new Message(this, gP);
				cTimer[customerNo] = new CustomerTimer(this,(12-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // �� �մԺ� Ÿ�̸� ����
				gP.add(cTimer[customerNo]);
				Image icon = new ImageIcon("images/Inked����մ�2.png").getImage().getScaledInstance(140, 200, 0); // �մ� �̹���
				customer[customerNo] = new JLabel(new ImageIcon(icon)); // �մԶ�
				addOrder(maxOrderNo, customerX[customerNo], guest);
				count = 0;
				guest = false;
			} else {
				
				Image[] icon = {new ImageIcon("images/�ճ�2.png").getImage().getScaledInstance(140, 200, 0),
						new ImageIcon("images/�ճ�1.png").getImage().getScaledInstance(140,200,0),
						new ImageIcon("images/�ճ�3.png").getImage().getScaledInstance(140,200,0)
				}; // �մ� �̹���
				customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); // �մԶ�
				count++;
				guest = true;
			}
		}else {	//10stage�� ���縸 ����!!!
			cTimer[customerNo] = new CustomerTimer(this,(12-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // �� �մԺ� Ÿ�̸� ����
			gP.add(cTimer[customerNo]);
			Image icon = new ImageIcon("images/Inked����մ�2.png").getImage().getScaledInstance(120, 200, 0); // �մ� �̹���
			customer[customerNo] = new JLabel(new ImageIcon(icon)); // �մԶ�
			guest = false;
		}

		customerOrderNo[customerNo] = maxOrderNo;
		// ���� �ֹ�
		// �մ� ��ü ����� (1,2,3,4�� �ڸ� ����)
		customer[customerNo].setSize(130,200);					//�մԶ� ������
		
		if(guest){customer[customerNo].setLocation(0, 125);}	//�մԶ� ��ġ
		else {customer[customerNo].setLocation(customerX[customerNo],125);}
		
		gP.add(customer[customerNo]); // �гο� �մԶ� �߰�
		
		
		if(guest) {time();}		//�Ϲݼմ��̸� �̵��ϴ� �޼ҵ� ����
		
		
		
		// �մ� No ����(0~3)
		if (customerNo != 3) {
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
				food = new ImageIcon("images/tbk1.png").getImage().getScaledInstance(60, 50, 0);
			} else if (random == 1) {
				food = new ImageIcon("images/drink1.png").getImage().getScaledInstance(60, 50, 0);
			} else if (random == 2) {
				food = new ImageIcon("images/fried1.png").getImage().getScaledInstance(60, 50, 0);
			} else if (random == 3) {
				food = new ImageIcon("images/����.jpg").getImage().getScaledInstance(60, 50, 0);
			} else if (random == 4) {
				food = new ImageIcon("images/ramen.png").getImage().getScaledInstance(60, 50, 0);
			}

			// ��ġ ����
			orderLabel[orderNo].setIcon(new ImageIcon(food));
			orderLabel[orderNo].setBounds(x + 130, y + 120, 100, 40);
			y += 40;
			gP.add(orderLabel[orderNo]);

			// orderNo����
			if (orderNo != maxOrderNo * 4 - 1) {
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
		} else if (orderNo < maxOrderNo * 4) {
			customerOrderNo[3] -= 1;
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

	/*
	 * public void deleteMessage() { gP.repaint(); }
	 */

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
			if((point.x)==customerX[cNo]) {
				guestTimer.stop();
				addOrder(maxOrderNo, customerX[cNo], notHidden);
				cTimer[cNo] = new CustomerTimer(cm,11-(0.3*stageLv),cNo,customerX[cNo]); // �� �մԺ� Ÿ�̸� ����
				gP.add(cTimer[cNo]);
			}
			
		}
	}
}
