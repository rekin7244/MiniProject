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
import com.kh.miniProject.view.GoldLabel;
import com.kh.miniProject.view.GuestPanel;

public class CustomerManager {
	private GuestPanel gP;
	private OrderDao orderDao;
	private CustomerTimer[] cTimer;
	private GameView gView;
	private CustomerManager cm;
	private Message m;


	private int stageLv; 						// stageLv
	private int maxOrderNo; 					// �ֹ��ϴ� �޴��� �ִ� ��
	private int orderNo; 						// �ֹ� ��ȣ
	private int customerNo = 0; 				// �մ� ��ȣ
	private int count = 0; 						// �����ȣ
	private boolean notHidden = true;			// ����մ� �Ǻ�(�޴����� 2�� ����)
	private OrderLabel[] orderLabel; 			// �ֹ� �̹���
	private JLabel[] customer; 					// customer��
	private int[] customerOrderNo; 				// customer���� �ֹ���
	private int[] customerX;					// customer x ��ǥ
	private Timer[] guestTimer;					// �մ� ������ Ÿ�̸�
	private int combo;							// �޺�	(�մ� ������ ����)
	private CoinEffect coin;
	
	// cons
	public CustomerManager(GameView gView, GuestPanel gP, OrderDao orderDao, int maxOrderNo, int stageLv) {
		orderLabel = new OrderLabel[100]; // �ʱ�ȭ
		this.gView = gView;
		this.gP = gP;
		this.orderDao = orderDao;
		this.maxOrderNo = maxOrderNo;
		this.stageLv = stageLv;
		if(stageLv<6) {
			this.cTimer = new CustomerTimer[3];
			this.customer = new JLabel[3];
			this.customerOrderNo = new int[3];
			this.customerX = new int[]{744,444,144};
			this.guestTimer = new Timer[3];
		}else{
			this.cTimer = new CustomerTimer[4];
			this.customer = new JLabel[4];
			this.customerOrderNo = new int[4];
			this.customerX = new int[]{798,594,396,198};
			this.guestTimer = new Timer[4];
		}
		this.cm = this;
	}

	// �մ� ����
	public void guest() {
		Random rand = new Random();
		Image money = new ImageIcon("images/coin1.gif").getImage().getScaledInstance(50, 50, 0);
		if(stageLv<=4) {	//1~4�������� ���� ���� ����
			Image[] icon = {new ImageIcon("images/�ճ�2.png").getImage().getScaledInstance(190, 250, 0),
					new ImageIcon("images/�ճ�1.png").getImage().getScaledInstance(190, 250, 0),
					new ImageIcon("images/�ճ�3.png").getImage().getScaledInstance(190, 250, 0)};	 // �մ� �̹���
			customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); // �մԶ�
			notHidden = true;
		}else if(stageLv==5){	//5stage���� ���� ����
			if (count == 1) {	//count0���� �����ؼ� 5�� ����մ� ����
				notHidden = false;
				m = new Message(this, gP);	//���޼��� ���
				System.out.println("���� ����");
				cTimer[customerNo] = new CustomerTimer(this,(11-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // �� �մԺ� Ÿ�̸� ����
				gP.add(cTimer[customerNo]);
				Image icon = new ImageIcon("images/Inked����մ�2.png").getImage().getScaledInstance(190, 250, 0);		// ����մ� �̹���
				addOrder(maxOrderNo, customerX[customerNo], notHidden);
				customer[customerNo] = new JLabel(new ImageIcon(icon)); 											// ����մ� ��
				count = 0;
			} else {
				Image[] icon = {new ImageIcon("images/�ճ�2.png").getImage().getScaledInstance(190, 250, 0),
						new ImageIcon("images/�ճ�1.png").getImage().getScaledInstance(190, 250, 0),
						new ImageIcon("images/�ճ�3.png").getImage().getScaledInstance(190, 250, 0)}; 	// �մ� �̹���
				customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); 	// �մԶ�
				count++;
				notHidden = true;
			}
		}else if(stageLv<10) {
			if (count == 5) {	//count0���� �����ؼ� 5�� ����մ� ����
				notHidden = false;
				m = new Message(this, gP);
				cTimer[customerNo] = new CustomerTimer(this,(12-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // �� �մԺ� Ÿ�̸� ����
				//gP.add(cTimer[customerNo]);
				Image icon = new ImageIcon("images/Inked����մ�2.png").getImage().getScaledInstance(140, 200, 0); // �մ� �̹���
				customer[customerNo] = new JLabel(new ImageIcon(icon)); // �մԶ�
				addOrder(maxOrderNo, customerX[customerNo], notHidden);
				count = 0;
			} else {
				Image[] icon = {new ImageIcon("images/�ճ�2.png").getImage().getScaledInstance(140, 200, 0),
						new ImageIcon("images/�ճ�1.png").getImage().getScaledInstance(140,200,0),
						new ImageIcon("images/�ճ�3.png").getImage().getScaledInstance(140,200,0)
				}; // �մ� �̹���
				customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); // �մԶ�
				count++;
				notHidden = true;
			}
		}else {	//10stage�� ���縸 ����!!
			notHidden = false;
			cTimer[customerNo] = new CustomerTimer(this,(12-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // �� �մԺ� Ÿ�̸� ����
			gP.add(cTimer[customerNo]);
			Image icon = new ImageIcon("images/Inked����մ�2.png").getImage().getScaledInstance(120, 200, 0); // �մ� �̹���
			customer[customerNo] = new JLabel(new ImageIcon(icon)); // �մԶ�
			addOrder(maxOrderNo, customerX[customerNo], notHidden);
		}

		customerOrderNo[customerNo] = maxOrderNo;
		// ���� �ֹ�
		// �մ� ��ü ����� (1,2,3�� �ڸ� ����)
		if(stageLv<6) {								//�մԶ� ������
			customer[customerNo].setSize(150,220);	
		}else {
			customer[customerNo].setSize(130,200);
		}

		if(notHidden) {									//�մԶ� ��ġ
			customer[customerNo].setLocation(0, 105);
		}else {
			customer[customerNo].setLocation(customerX[customerNo], 105);
		}

		gP.add(customer[customerNo]); 		// �гο� �մԶ� �߰�


		if(notHidden) {time();}		//�Ϲݼմ� ������ �̵��ϴ� �޼ҵ� ����

		if(stageLv<6) {
			// �մ� No ���� (0~2)
			if (customerNo != 2) {
				customerNo++;
			} else {
				customerNo = 0;
			}
		}else {
			// �մ� No ����(0~3)
			if (customerNo != 3) {
				customerNo++;
			} else {
				customerNo = 0;
			}}
	}
	
	public synchronized void hidden() {
		cTimer[customerNo] = new CustomerTimer(this,(11-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // �� �մԺ� Ÿ�̸� ����
		gP.add(cTimer[customerNo]);
		Image icon = new ImageIcon("images/Inked����մ�2.png").getImage().getScaledInstance(190, 250, 0);		// ����մ� �̹���
		addOrder(maxOrderNo, customerX[customerNo], notHidden);
		customer[customerNo] = new JLabel(new ImageIcon(icon)); 											// ����մ� ��
		count = 0;
	}

	public void time() {
		guestTimer[customerNo] = new Timer(20,new GuestTimer());
		guestTimer[customerNo].start();
	}

	public void addOrder(int menuNo, int x, boolean notHidden) {
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

			if (notHidden == false) { //����մ� ���� ����
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
				food = new ImageIcon("images/��1.png").getImage().getScaledInstance(50, 50, 0);
			} else if (random == 4) {
				food = new ImageIcon("images/���1.png").getImage().getScaledInstance(50, 50, 0);
			}
			if(stageLv<6) {
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
			}else {
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

	}

	public void deleteLabel(int orderNo,int gold) { // �ֹ����� ���� �� ��� �ֹ� ���� �Ϸ�� �մ�(+Ÿ�̸�) ����
		Point point = orderLabel[orderNo].getLocation();
		new GoldLabel(gP,point,gold);
		gP.remove(orderLabel[orderNo]);

		// �մԿ� ���� �մ� �ֹ��� ����
		if (orderNo < maxOrderNo * 1) {
			customerOrderNo[0] -= 1;
			if(customerOrderNo[0]==0) {
				coin = new CoinEffect(cm, gP, 0,customer[0].getX(),customer[0].getY());
				Music buttonEnteredMusic = new Music("���� �Ҹ� - ���� ȿ���� (1) (1).mp3", false);
				buttonEnteredMusic.start();
			}
		} else if (orderNo < maxOrderNo * 2) {
			customerOrderNo[1] -= 1;
			if(customerOrderNo[1]==0) {
				coin = new CoinEffect(cm, gP, 0,customer[1].getX(),customer[1].getY());
				Music buttonEnteredMusic = new Music("���� �Ҹ� - ���� ȿ���� (1) (1).mp3", false);
				buttonEnteredMusic.start();
			}
		} else if (orderNo < maxOrderNo * 3) {
			customerOrderNo[2] -= 1;
			if(customerOrderNo[2]==0) {
				coin = new CoinEffect(cm, gP, 0,customer[2].getX(),customer[2].getY());
				Music buttonEnteredMusic = new Music("���� �Ҹ� - ���� ȿ���� (1) (1).mp3", false);
				buttonEnteredMusic.start();
			}
		} else if (stageLv>5 && orderNo < maxOrderNo * 4) {		//��������6���� 4��° �մ� �ֹ�����
			customerOrderNo[3] -= 1;
			if(customerOrderNo[3]==0) {
				coin = new CoinEffect(cm, gP, 0,customer[3].getX(),customer[3].getY());
				Music buttonEnteredMusic = new Music("���� �Ҹ� - ���� ȿ���� (1) (1).mp3", false);
				buttonEnteredMusic.start();
			}
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
		private boolean ishidden = notHidden;
		@Override
		public void actionPerformed(ActionEvent e) {
			Point point = customer[cNo].getLocation();
			customer[cNo].setLocation((point.x+(customerX[cNo]/45)),(point.y));
			gP.repaint();
			if((point.x)>=customerX[cNo]) {
				guestTimer[cNo].stop();
				addOrder(maxOrderNo, customerX[cNo], ishidden);
				cTimer[cNo] = new CustomerTimer(cm,11-(0.3*stageLv),cNo,customerX[cNo]); // �� �մԺ� Ÿ�̸� ����
				gP.add(cTimer[cNo]);
			}
		}
	}
}
