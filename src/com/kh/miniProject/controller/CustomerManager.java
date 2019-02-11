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
	public CustomerTimer[] cTimer = new CustomerTimer[3];

	private int stageLv;		//stageLv
	private int maxOrderNo;		//주문하는 메뉴의 최대 수
	private int orderNo;			//주문 번호
	private int customerNo=0;			//손님 번호
	private OrderLabel[] orderLabel;				//주문 이미지
	private JLabel[] customer = new JLabel[3];		//customer수
	private int[] customerOrderNo = new int[3];		//customer남은 주문수

	//cons
	public CustomerManager(GuestPanel gP,OrderDao orderDao,int maxOrderNo,int stageLv) {
		orderLabel = new OrderLabel[100];	//초기화
		this.gP = gP;
		this.orderDao = orderDao;
		this.maxOrderNo = maxOrderNo;
		this.stageLv = stageLv;
	}

	//손님 생성
	public void guest() {
		cTimer[customerNo] = new CustomerTimer(this,12-(0.5*stageLv),customerNo);	//각 손님별 타이머 설정
		gP.add(cTimer[customerNo]);
		Image icon = new ImageIcon("images/guest.PNG")
				.getImage().getScaledInstance(120, 200, 0); // 손님 이미지
		customer[customerNo] = new JLabel(new ImageIcon(icon)); // 손님라벨


		customerOrderNo[customerNo]=maxOrderNo;
		//음식 주문
		//손님 객체 존재시 (1,2,3번 자리 지정)
		if(customerNo==0) {
			customer[customerNo].setBounds(744, 0, 120, 200); // 손님 위치 설정
			addOrder(maxOrderNo,744); 
		}else if(customerNo==1) {
			customer[customerNo].setBounds(444, 0, 120, 200);
			addOrder(maxOrderNo,444); 
		}else {
			customer[customerNo].setBounds(144, 0, 120, 200); 
			addOrder(maxOrderNo,144); 
		}
		gP.add(customer[customerNo]); // 패널에 손님라벨 추가
		int temp = customerNo;

		//손님 No 설정
		if(customerNo!=2) {
			customerNo++;
		}else {
			customerNo=0;
		}
	}

	public void addOrder(int menuNo,int x) {
		int y = 15;	//y축 초기화

		for (int i = 0; i < menuNo; i++) {				//메뉴 개수에 따라 반복
			int random=0;
			if(stageLv==1) {
				random = new Random().nextInt(2);		//1스테이지 한정 2가지
			}else if(stageLv==2) {
				random = new Random().nextInt(3);		//2스테이지 한정 3가지
			}else if(stageLv==3) {
				random = new Random().nextInt(4);		//3스테이지 한정 4가지
			}else {
				random = new Random().nextInt(5);		//4스테이지 이후 5가지
			}
			if (random == 0) {
				orderDao.addOrder(new MenuOrder("떡볶이", 2300, orderNo));
			} else if (random == 1) {
				orderDao.addOrder(new MenuOrder("음료수", 1000, orderNo));
			} else if (random == 2) {
				orderDao.addOrder(new MenuOrder("튀김", 1800, orderNo));
			} else if (random == 3) {
				orderDao.addOrder(new MenuOrder("오뎅", 2000, orderNo));
			} else if (random == 4) {
				orderDao.addOrder(new MenuOrder("라면", 3500, orderNo));
			}
			orderLabel[orderNo] = new OrderLabel(orderNo);	//order Label 추가

			//이미지 추가
			//랜덤값에 따라 떡볶이,음료수,튀김,오뎅,라면
			Image food = null;
			if (random == 0) {
				food = new ImageIcon("images/떡볶이순대.jpg")
						.getImage().getScaledInstance(50, 40, 0);
			} else if (random == 1) {
				food = new ImageIcon("images/drinkImage.jpg")
						.getImage().getScaledInstance(50, 40, 0);
			} else if (random == 2) {
				food = new ImageIcon("images/friedImage.jpeg")
						.getImage().getScaledInstance(50, 40, 0);
			} else if (random == 3) {
				food = new ImageIcon("images/오뎅Lv1.jpg")
						.getImage().getScaledInstance(50, 40, 0);
			} else if (random == 4) {
				food = new ImageIcon("images/ramen.png")
						.getImage().getScaledInstance(50, 40, 0);
			}

			//위치 설정
			orderLabel[orderNo].setIcon(new ImageIcon(food));
			orderLabel[orderNo].setBounds(x+120, y, 100, 30);
			y += 40;
			gP.add(orderLabel[orderNo]);

			//orderNo설정
			if(orderNo!=maxOrderNo*3-1) {
				orderNo++;
			}else {
				orderNo=0;
			}
		}
	}

	public void deleteLabel(int orderNo) {			//주문내역 삭제 및 모든 주문 전달 완료시 손님(+타이머) 삭제
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
	public void deleteCustomer(int customerNo) {		//시간 만료시 주문내역과 손님(+타이머) 삭제
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
	public void endCustomer() {
		for (int i = 0; i < cTimer.length; i++) {
			if(cTimer[i]!=null) {
				cTimer[i].timerStop();
			}
		}
	}
}
