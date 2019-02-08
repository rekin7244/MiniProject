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
	private int x = 864; //주문 이미지 위치
	private int y = 15; //주문 이미지 위치
	
	private GuestPanel gP;
	private OrderDao orderDao;
	private CustomerTimer[] cTimer = new CustomerTimer[3];

	private int maxOrderNo = 3;		//주문하는 메뉴의 최대 수
	private int orderNo;			//주문 번호
	private int customerNo=0;			//손님 번호
	private OrderLabel[] orderLabel;				//주문 이미지
	private JLabel[] customer = new JLabel[3];		//customer수
	private int[] customerOrderNo = new int[3];		//customer남은 주문수


	public CustomerManager(GuestPanel gP,OrderDao orderDao) {
		orderLabel = new OrderLabel[100];	//초기화
		this.gP = gP;
		this.orderDao = orderDao;
	}

	public void guest(int maxOrderNo) {
		cTimer[customerNo] = new CustomerTimer(this,3,customerNo);
		gP.add(cTimer[customerNo]);
		Image icon = new ImageIcon("images/guest.PNG")
				.getImage().getScaledInstance(120, 200, 0); // 손님 이미지
		customer[customerNo] = new JLabel(new ImageIcon(icon)); // 손님라벨

		addOrder(maxOrderNo); 			//음식 주문
		customerOrderNo[0]=maxOrderNo;
		
		//손님 객체 존재시 (1,2,3번 자리 지정)
		if(customerNo==0) {
			customer[customerNo].setBounds(744, 0, 120, 200); // 손님 위치 설정
		}else if(customerNo==1) {
			customer[customerNo].setBounds(444, 0, 120, 200);
		}else {
			customer[customerNo].setBounds(144, 0, 120, 200); 
		}
		gP.add(customer[customerNo]); // 패널에 손님라벨 추가
		int temp = customerNo;
		
		if(customerNo!=maxOrderNo-1) {
			customerNo++;
		}else {
			customerNo=0;
		}
	}

	public void addOrder(int menuNo) {
		y = 15;	//y축 초기화

		for (int i = 0; i < menuNo; i++) {				//메뉴 개수에 따라 반복
			int random = new Random().nextInt(2);		//1스테이지 한정 2가지
			if (random == 0) {
				orderDao.addOrder(new MenuOrder("떡볶이", 2000, orderNo));
			} else if (random == 1) {
				orderDao.addOrder(new MenuOrder("음료수", 1000, orderNo));
			}
			orderLabel[orderNo] = new OrderLabel(orderNo);
			//랜덤값 0일 경우 떡볶이
			Image food = null;
			if (random == 0) {
				food = new ImageIcon("images/떡볶이순대.jpg")
						.getImage().getScaledInstance(50, 40, 0);
				//랜덤값 1일경우 라면
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
	
	public void deleteLabel(int orderNo) {		//주문내역 삭제 및 모든 주문 전달 완료시 손님(+타이머) 삭제
		gP.remove(orderLabel[orderNo]);
		//손님에 따라 손님 주문수 감소
		if(orderNo<maxOrderNo*1) {
			customerOrderNo[0]-=1;
		}else if(orderNo<maxOrderNo*2) {
			customerOrderNo[1]-=1;
		}else if(orderNo<maxOrderNo*3) {
			customerOrderNo[2]-=1;
		}
		//손님 주문 종료시
		if(customerOrderNo[0]==0) {
			cTimer[0].timerStop();
			gP.remove(cTimer[0]);
			gP.remove(customer[0]);
		}
		gP.repaint();
	}
	public void deleteCustomer(int customerNo) {		//시간 만료시 주문내역과 손님(+타이머) 삭제
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
