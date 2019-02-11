package com.kh.miniProject.controller;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.OrderLabel;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.music.Music;
import com.kh.miniProject.view.GuestPanel;

public class CustomerManager {
	private GuestPanel gP;
	private OrderDao orderDao;
	private CustomerTimer[] cTimer = new CustomerTimer[3];

	private int stageLv;		//stageLv
	private int maxOrderNo;		//�ֹ��ϴ� �޴��� �ִ� ��
	private int orderNo;			//�ֹ� ��ȣ
	private int customerNo=0;			//�մ� ��ȣ
	private int count=0 ; 				//���� �մ� ����   
	
	
	private OrderLabel[] orderLabel;				//�ֹ� �̹���
	private JLabel[] customer = new JLabel[3];		//customer��
	private int[] customerOrderNo = new int[3];		//customer���� �ֹ���
	
	//cons
	public CustomerManager(GuestPanel gP,OrderDao orderDao,int maxOrderNo,int stageLv) {
		orderLabel = new OrderLabel[100];	//�ʱ�ȭ
		this.gP = gP;
		this.orderDao = orderDao;
		this.maxOrderNo = maxOrderNo;
		this.stageLv = stageLv;
	}
	
	//�մ� ����
	public void guest() {
		cTimer[customerNo] = new CustomerTimer(this,12-(0.5*stageLv),customerNo);	//�� �մԺ� Ÿ�̸� ����
		gP.add(cTimer[customerNo]);
		Image icon = new ImageIcon("images/guest.PNG")
				.getImage().getScaledInstance(120, 200, 0); // �մ� �̹���
		customer[customerNo] = new JLabel(new ImageIcon(icon)); // �մԶ�


		customerOrderNo[customerNo]=maxOrderNo;
		//���� �ֹ�
		//�մ� ��ü ����� (1,2,3�� �ڸ� ����)
		if(customerNo==0) {
			customer[customerNo].setBounds(744, 0, 120, 200); // �մ� ��ġ ����
			
			addOrder(maxOrderNo,744); 
		}else if(customerNo==1) {
			customer[customerNo].setBounds(444, 0, 120, 200);
			
			addOrder(maxOrderNo,444); 
		}else {
			customer[customerNo].setBounds(144, 0, 120, 200); 
			
			addOrder(maxOrderNo,144 ); 
		}
		
		
		
		gP.add(customer[customerNo]); // �гο� �մԶ� �߰�
		int temp = customerNo;
		
		//�մ� No ����
		if(customerNo!=2) {
			customerNo++;
		}else {
			customerNo=0;
		}
	}

	public void addOrder(int menuNo,int x) {
		int y = 15;	//y�� �ʱ�ȭ

		for (int i = 0; i < menuNo; i++) {				//�޴� ������ ���� �ݺ�
			int random=0;
			if(stageLv==1) {
				random = new Random().nextInt(2);		//1�������� ���� 2����
			}else if(stageLv==2) {
				random = new Random().nextInt(3);		//2�������� ���� 3����
			}else if(stageLv==3) {
				random = new Random().nextInt(4);		//3�������� ���� 4����
			}else {
				random = new Random().nextInt(5);		//4�������� ���� 5����
			}
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
			orderLabel[orderNo] = new OrderLabel(orderNo);	//order Label �߰�
			
			//�̹��� �߰�
			//�������� ���� ������,�����,Ƣ��,����,���
			Image food = null;
			if (random == 0) {
				food = new ImageIcon("images/�����̼���.jpg")
						.getImage().getScaledInstance(50, 40, 0);
			} else if (random == 1) {
				food = new ImageIcon("images/drinkImage.jpg")
						.getImage().getScaledInstance(50, 40, 0);
			} else if (random == 2) {
				food = new ImageIcon("images/friedImage.jpeg")
						.getImage().getScaledInstance(50, 40, 0);
			} else if (random == 3) {
				food = new ImageIcon("images/����Lv1.jpg")
						.getImage().getScaledInstance(50, 40, 0);
			} else if (random == 4) {
				food = new ImageIcon("images/ramen.png")
						.getImage().getScaledInstance(50, 40, 0);
			}
			
			//��ġ ����
			orderLabel[orderNo].setIcon(new ImageIcon(food));
			orderLabel[orderNo].setBounds(x+120, y, 100, 30);
			y += 40;
			gP.add(orderLabel[orderNo]);
			
			//orderNo����
			if(orderNo!=maxOrderNo*3-1) {
				orderNo++;
			}else {
				orderNo=0;
			}
		}
	}

	public void deleteLabel(int orderNo) {			//�ֹ����� ���� �� ��� �ֹ� ���� �Ϸ�� �մ�(+Ÿ�̸�) ����
		gP.remove(orderLabel[orderNo]);
	
		//�մԿ� ���� �մ� �ֹ��� ����
		if(orderNo<maxOrderNo*1) {
			customerOrderNo[0]-=1;
		}else if(orderNo<maxOrderNo*2) {
			customerOrderNo[1]-=1;
		}else if(orderNo<maxOrderNo*3) {
			customerOrderNo[2]-=1;
		}
		//�մ� �ֹ� �����
		for (int i = 0; i < customerOrderNo.length; i++) {
			if(cTimer[i]!=null) {
				if(customerOrderNo[i]==0) {
					cTimer[i].timerStop();
					gP.remove(cTimer[i]);
					gP.remove(customer[i]);
				}
			}
		}
		gP.repaint();
		Music buttonEnteredMusic = new Music("coins_5.mp3",false);
		buttonEnteredMusic.start();
	}
	public void deleteCustomer(int customerNo) {		//�ð� ����� �ֹ������� �մ�(+Ÿ�̸�) ����
		for (int i = maxOrderNo*customerNo; i < maxOrderNo*customerNo+maxOrderNo; i++) {
			if(orderLabel[i]!=null) {
				gP.remove(orderLabel[i]);
				orderDao.removeOrder(i);
			}
		}
		cTimer[customerNo].timerStop();
		gP.remove(cTimer[customerNo]);
		gP.remove(customer[customerNo]);
		gP.repaint();
	}
}
