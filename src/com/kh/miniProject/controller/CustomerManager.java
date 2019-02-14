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
	private int maxOrderNo; 					// 주문하는 메뉴의 최대 수
	private int orderNo; 						// 주문 번호
	private int customerNo = 0; 				// 손님 번호
	private int count = 0; 						// 히든번호
	private boolean notHidden = true;			// 히든손님 판별(메뉴가격 2배 결정)
	private OrderLabel[] orderLabel; 			// 주문 이미지
	private JLabel[] customer; 					// customer수
	private int[] customerOrderNo; 				// customer남은 주문수
	private int[] customerX;					// customer x 좌표
	private Timer[] guestTimer;					// 손님 들어오는 타이머
	private int combo;							// 콤보	(손님 나가면 리셋)

	// cons
	public CustomerManager(GameView gView, GuestPanel gP, OrderDao orderDao, int maxOrderNo, int stageLv) {
		orderLabel = new OrderLabel[100]; // 초기화
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

	// 손님 생성
	public void guest() {
		Random rand = new Random();
		if(stageLv<=4) {	//1~4스테이지 히든 없이 진행
			Image[] icon = {new ImageIcon("images/손놈2.png").getImage().getScaledInstance(190, 250, 0),
					new ImageIcon("images/손놈1.png").getImage().getScaledInstance(190, 250, 0),
					new ImageIcon("images/손놈3.png").getImage().getScaledInstance(190, 250, 0)};	 // 손님 이미지
			customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); // 손님라벨
			notHidden = true;
		}else if(stageLv==5){	//5stage부터 히든 출현
			if (count == 5) {	//count0부터 시작해서 5에 히든손님 출현 
				notHidden = false;
				m = new Message(this, gP);	//경고메세지 출력
				cTimer[customerNo] = new CustomerTimer(this,(11-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // 각 손님별 타이머 설정
				gP.add(cTimer[customerNo]);
				Image icon = new ImageIcon("images/Inked히든손님2.png").getImage().getScaledInstance(190, 250, 0);		// 히든손님 이미지
				addOrder(maxOrderNo, customerX[customerNo], notHidden);
				customer[customerNo] = new JLabel(new ImageIcon(icon)); 											// 히든손님 라벨
				count = 0;
			} else {
				Image[] icon = {new ImageIcon("images/손놈2.png").getImage().getScaledInstance(190, 250, 0),
						new ImageIcon("images/손놈1.png").getImage().getScaledInstance(190, 250, 0),
						new ImageIcon("images/손놈3.png").getImage().getScaledInstance(190, 250, 0)}; 	// 손님 이미지
				customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); 	// 손님라벨
				count++;
				notHidden = true;
			}
		}else if(stageLv<10) {
			if (count == 5) {	//count0부터 시작해서 5에 히든손님 출현
				notHidden = false;
				m = new Message(this, gP);
				cTimer[customerNo] = new CustomerTimer(this,(12-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // 각 손님별 타이머 설정
				gP.add(cTimer[customerNo]);
				Image icon = new ImageIcon("images/Inked히든손님2.png").getImage().getScaledInstance(140, 200, 0); // 손님 이미지
				customer[customerNo] = new JLabel(new ImageIcon(icon)); // 손님라벨
				addOrder(maxOrderNo, customerX[customerNo], notHidden);
				count = 0;
			} else {
				Image[] icon = {new ImageIcon("images/손놈2.png").getImage().getScaledInstance(140, 200, 0),
						new ImageIcon("images/손놈1.png").getImage().getScaledInstance(140,200,0),
						new ImageIcon("images/손놈3.png").getImage().getScaledInstance(140,200,0)
				}; // 손님 이미지
				customer[customerNo] = new JLabel(new ImageIcon(icon[rand.nextInt(icon.length-1)])); // 손님라벨
				count++;
				notHidden = true;
			}
		}else {	//10stage는 히든만 출현!!
			notHidden = false;
			cTimer[customerNo] = new CustomerTimer(this,(12-(0.3*stageLv))/1.5,customerNo,customerX[customerNo]); // 각 손님별 타이머 설정
			gP.add(cTimer[customerNo]);
			Image icon = new ImageIcon("images/Inked히든손님2.png").getImage().getScaledInstance(120, 200, 0); // 손님 이미지
			customer[customerNo] = new JLabel(new ImageIcon(icon)); // 손님라벨
			addOrder(maxOrderNo, customerX[customerNo], notHidden);
		}

		customerOrderNo[customerNo] = maxOrderNo;
		// 음식 주문
		// 손님 객체 존재시 (1,2,3번 자리 지정)
		if(stageLv<6) {								//손님라벨 사이즈
			customer[customerNo].setSize(150,220);	
		}else {
			customer[customerNo].setSize(130,200);
		}

		if(notHidden) {									//손님라벨 위치
			customer[customerNo].setLocation(0, 105);
		}else {
			customer[customerNo].setLocation(customerX[customerNo], 105);
		}

		gP.add(customer[customerNo]); 		// 패널에 손님라벨 추가


		if(notHidden) {time();}		//일반손님 출현시 이동하는 메소드 실행

		if(stageLv<6) {
			// 손님 No 설정 (0~2)
			if (customerNo != 2) {
				customerNo++;
			} else {
				customerNo = 0;
			}
		}else {
			// 손님 No 설정(0~3)
			if (customerNo != 3) {
				customerNo++;
			} else {
				customerNo = 0;
			}}
	}

	public void time() {
		guestTimer[customerNo] = new Timer(20,new GuestTimer());
		guestTimer[customerNo].start();
	}

	public void addOrder(int menuNo, int x, boolean notHidden) {
		int y = 15; // y축 초기화

		for (int i = 0; i < menuNo; i++) { // 메뉴 개수에 따라 반복
			int random = 0;
			if (stageLv == 1) {
				random = new Random().nextInt(2); // 1스테이지 한정 2가지
			} else if (stageLv == 2) {
				random = new Random().nextInt(3); // 2스테이지 한정 3가지
			} else if (stageLv == 3) {
				random = new Random().nextInt(4); // 3스테이지 한정 4가지
			} else {
				random = new Random().nextInt(5); // 4스테이지 이후 5가지
			}

			if (notHidden == false) { //히든손님 지불 가격
				if (random == 0) {
					orderDao.addOrder(new MenuOrder("떡볶이", 4600, orderNo));
				} else if (random == 1) {
					orderDao.addOrder(new MenuOrder("음료수", 2000, orderNo));
				} else if (random == 2) {
					orderDao.addOrder(new MenuOrder("튀김", 3600, orderNo));
				} else if (random == 3) {
					orderDao.addOrder(new MenuOrder("오뎅", 4000, orderNo));
				} else if (random == 4) {
					orderDao.addOrder(new MenuOrder("라면", 7000, orderNo));
				}
			} else { //일반손님 지불 가격
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
			}
			orderLabel[orderNo] = new OrderLabel(orderNo); // order Label 추가

			// 이미지 추가
			// 랜덤값에 따라 떡볶이,음료수,튀김,오뎅,라면
			Image food = null;
			if (random == 0) {
				food = new ImageIcon("images/tbk1.png").getImage().getScaledInstance(50, 50, 0);
			} else if (random == 1) {
				food = new ImageIcon("images/drink1.png").getImage().getScaledInstance(50, 40, 0);
			} else if (random == 2) {
				food = new ImageIcon("images/fried1.png").getImage().getScaledInstance(50, 50, 0);
			} else if (random == 3) {
				food = new ImageIcon("images/오뎅.jpg").getImage().getScaledInstance(50, 50, 0);
			} else if (random == 4) {
				food = new ImageIcon("images/ramen.png").getImage().getScaledInstance(50, 50, 0);
			}
			if(stageLv<6) {
				// 위치 설정
				orderLabel[orderNo].setIcon(new ImageIcon(food));
				orderLabel[orderNo].setBounds(x + 150, y + 120, 70, 50);
				y += 50;
				gP.add(orderLabel[orderNo]);

				// orderNo설정
				if (orderNo != maxOrderNo * 3 - 1) {
					orderNo++;
				} else {
					orderNo = 0;
				}
			}else {
				// 위치 설정
				orderLabel[orderNo].setIcon(new ImageIcon(food));
				orderLabel[orderNo].setBounds(x + 130, y + 120, 100, 40);
				y += 40;
				gP.add(orderLabel[orderNo]);

				// orderNo설정
				if (orderNo != maxOrderNo * 4 - 1) {
					orderNo++;
				} else {
					orderNo = 0;
				}
			}
		}

	}

	public void deleteLabel(int orderNo,int gold) { // 주문내역 삭제 및 모든 주문 전달 완료시 손님(+타이머) 삭제
		Point point = orderLabel[orderNo].getLocation();
		new GoldLabel(gP,point,gold);
		gP.remove(orderLabel[orderNo]);

		// 손님에 따라 손님 주문수 감소
		if (orderNo < maxOrderNo * 1) {
			customerOrderNo[0] -= 1;
		} else if (orderNo < maxOrderNo * 2) {
			customerOrderNo[1] -= 1;
		} else if (orderNo < maxOrderNo * 3) {
			customerOrderNo[2] -= 1;
		} else if (stageLv>5 && orderNo < maxOrderNo * 4) {
			customerOrderNo[3] -= 1;
		}
		// 손님 주문 종료시
		for (int i = 0; i < customerOrderNo.length; i++) {
			if (cTimer[i] != null) {
				if (customerOrderNo[i] == 0) {
					cTimer[i].timerStop();
					gP.remove(cTimer[i]);
					gP.remove(customer[i]);
					Music buttonEnteredMusic = new Music("coins_5.mp3", false);
					buttonEnteredMusic.start();
				}
			}
		}
		gP.repaint();
	}


	public void deleteCustomer(int customerNo) { // 시간 만료시 주문내역과 손님(+타이머) 삭제
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
				cTimer[cNo] = new CustomerTimer(cm,11-(0.3*stageLv),cNo,customerX[cNo]); // 각 손님별 타이머 설정
				gP.add(cTimer[cNo]);
			}
		}
	}
}
