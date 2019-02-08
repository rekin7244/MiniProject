package com.kh.miniProject.controller;

import java.util.Random;
import java.util.Scanner;

import com.kh.miniProject.model.dao.OrderDao;
import com.kh.miniProject.model.vo.menu.Menu;
import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.view.GuestPanel;


public class OrderManager {
	private int menuNo = 0;
	private OrderDao od;
	private GuestPanel gP;
	

	public OrderManager(OrderDao od,GuestPanel gP) {
		this.od = od;
		this.gP = gP;
		
		addGuest();
	}

	public void addGuest() {
		
	}
	//¸Þ´º »ý¼º
	public int createOrder(int level) {

		int random = new Random().nextInt(level);
		MenuOrder menu = null;
		if (random == 0) {
			od.addOrder(new MenuOrder("¶±ººÀÌ", 1000, menuNo++));
		} else if (random == 1) {
			od.addOrder(new MenuOrder("¶ó¸é", 1500, menuNo++));
		} else if (random == 2) {
			od.addOrder(new MenuOrder("À½·á¼ö", 2000, menuNo++));
		} else if (random == 3) {
			od.addOrder(new MenuOrder("Æ¢±è", 2500, menuNo++));
		}
		return random;
	}

}
