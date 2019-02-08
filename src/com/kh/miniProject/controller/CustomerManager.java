package com.kh.miniProject.controller;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.OrderLabel;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.view.GuestPanel;

public class CustomerManager {
	private int x = 864; //�ֹ� �̹��� ��ġ
	private int y = 15; //�ֹ� �̹��� ��ġ
	
	private GuestPanel gP;
	private OrderDao orderDao;
	private CustomerTimer[] cTimer = new CustomerTimer[3];

	private int maxOrderNo = 3;		//�ֹ��ϴ� �޴��� �ִ� ��
	private int orderNo;			//�ֹ� ��ȣ
	private int customerNo=0;			//�մ� ��ȣ
	private OrderLabel[] orderLabel;				//�ֹ� �̹���
	private JLabel[] customer = new JLabel[3];		//customer��
	private int[] customerOrderNo = new int[3];		//customer���� �ֹ���


	public CustomerManager(GuestPanel gP,OrderDao orderDao) {
		orderLabel = new OrderLabel[100];	//�ʱ�ȭ
		this.gP = gP;
		this.orderDao = orderDao;
	}

	public void guest(int maxOrderNo) {
		cTimer[customerNo] = new CustomerTimer(this,3,customerNo);
		gP.add(cTimer[customerNo]);
		Image icon = new ImageIcon("images/guest.PNG")
				.getImage().getScaledInstance(120, 200, 0); // �մ� �̹���
		customer[customerNo] = new JLabel(new ImageIcon(icon)); // �մԶ�

		addOrder(maxOrderNo); 			//���� �ֹ�
		customerOrderNo[0]=maxOrderNo;
		
		//�մ� ��ü ����� (1,2,3�� �ڸ� ����)
		if(customerNo==0) {
			customer[customerNo].setBounds(744, 0, 120, 200); // �մ� ��ġ ����
		}else if(customerNo==1) {
			customer[customerNo].setBounds(444, 0, 120, 200);
		}else {
			customer[customerNo].setBounds(144, 0, 120, 200); 
		}
		gP.add(customer[customerNo]); // �гο� �մԶ� �߰�
		int temp = customerNo;
		
		if(customerNo!=maxOrderNo-1) {
			customerNo++;
		}else {
			customerNo=0;
		}
	}

	public void addOrder(int menuNo) {
		y = 15;	//y�� �ʱ�ȭ

		for (int i = 0; i < menuNo; i++) {				//�޴� ������ ���� �ݺ�
			int random = new Random().nextInt(2);		//1�������� ���� 2����
			if (random == 0) {
				orderDao.addOrder(new MenuOrder("������", 2000, orderNo));
			} else if (random == 1) {
				orderDao.addOrder(new MenuOrder("�����", 1000, orderNo));
			}
			orderLabel[orderNo] = new OrderLabel(orderNo);
			//������ 0�� ��� ������
			Image food = null;
			if (random == 0) {
				food = new ImageIcon("images/�����̼���.jpg")
						.getImage().getScaledInstance(50, 40, 0);
				//������ 1�ϰ�� ���
			} else if (random == 1) {
				food = new ImageIcon("images/drinkImage.jpg")
						.getImage().getScaledInstance(50, 40, 0);
			}
			orderLabel[orderNo].setIcon(new ImageIcon(food));
			orderLabel[orderNo].setBounds(x, y, 100, 30);
			y += 40;
			gP.add(orderLabel[orderNo]);
			if(orderNo!=maxOrderNo*3-1) {
				orderNo++;
			}else {
				orderNo=0;
			}
		}
	}
	
	public void deleteLabel(int orderNo) {		//�ֹ����� ���� �� ��� �ֹ� ���� �Ϸ�� �մ�(+Ÿ�̸�) ����
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
		if(customerOrderNo[0]==0) {
			cTimer[0].timerStop();
			gP.remove(cTimer[0]);
			gP.remove(customer[0]);
		}
		gP.repaint();
	}
	public void deleteCustomer(int customerNo) {		//�ð� ����� �ֹ������� �մ�(+Ÿ�̸�) ����
		for (int i = 0; i < orderLabel.length; i++) {
			if(orderLabel[i]!=null) {
				gP.remove(orderLabel[i]);
			}
		}
		cTimer[customerNo].timerStop();
		gP.remove(cTimer[customerNo]);
		gP.remove(customer[customerNo]);
		gP.repaint();
	}
}
