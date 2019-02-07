package com.kh.miniProject.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.miniProject.controller.OrderManager;
import com.kh.miniProject.model.dao.OrderDao;


public class GuestPanel extends JPanel {
	private int x = 864; //주문 이미지 위치
	private int y = 15; //주문 이미지 위치
	private int level = 5; //스테이지 레벨
	private Image img;
	private JLabel[] label = new JLabel[level];
	private OrderManager om;

	public GuestPanel(Image img,OrderDao od) {
		this.img = img;
		om = new OrderManager(od,this);
		guest();
	}

	public void guest() {
		Image icon = new ImageIcon("images/guest.PNG")
				.getImage().getScaledInstance(120, 200, 0); // 손님 이미지
		JLabel guest = new JLabel(new ImageIcon(icon)); // 손님라벨

		addImage(level); //음식 이미지 생성

		guest.setBounds(744, 0, 120, 200); // 손님 위치
		this.add(guest); // 패널에 손님라벨 추가
		//mf.add(this); //메인프레임에 패널 등록

	}

	//음식 이미지 등록 메소드
	public void addImage(int level) {
		if(level >= 5) { //스테이지 5부턴
			level = 4; //메뉴주문 4개씩
		}

		//스테이지 수만큼 메뉴 이미지 등록
		for (int i = 0; i < level; i++) {
			int result = om.createOrder(level);
			//랜덤값 0일 경우 떡볶이
			if (result == 0) {
				Image food = new ImageIcon("images/떡볶이순대.jpg")
						.getImage().getScaledInstance(50, 40, 0);
				label[i] = new JLabel(new ImageIcon(food));
				label[i].setBounds(x, y, 100, 30);
				y += 40;
				this.add(label[i]);
				//랜덤값 1일경우 라면
			} else if (result == 1) {
				Image food = new ImageIcon("images/ramen.png")
						.getImage().getScaledInstance(50, 40, 0);
				label[i] = new JLabel(new ImageIcon(food));
				label[i].setBounds(x, y, 100, 30);
				y += 40;
				this.add(label[i]);
				//랜덤값 2일 경우 콜라	
			} else if(result == 2){
				Image food = new ImageIcon("images/drinkImage.jpg")
						.getImage().getScaledInstance(50, 40, 0);
				label[i] = new JLabel(new ImageIcon(food));
				label[i].setBounds(x, y, 100, 30);
				y += 40;
				this.add(label[i]);
				//랜덤값 3일경우 튀김
			} else if(result == 3) {
				Image food = new ImageIcon("images/friedImage.jpeg")
						.getImage().getScaledInstance(50, 40, 0);
				label[i] = new JLabel(new ImageIcon(food));
				label[i].setBounds(x, y, 100, 30);
				y += 40;
				this.add(label[i]);
			}
		}
	}
	//음식 이미지 삭제 메소드
	public void deleteImage(int menuNo) {
		
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 1024, 350, null);
	}
}
