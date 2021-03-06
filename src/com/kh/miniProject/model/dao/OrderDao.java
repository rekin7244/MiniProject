package com.kh.miniProject.model.dao;

import java.util.ArrayList;

import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.view.GameView;

public class OrderDao {
	private ArrayList<MenuOrder> orderList;
	private int stageGold;
	private GameView gameView;
	
	//cons
	public OrderDao(GameView gameView) {
		orderList = new ArrayList<MenuOrder>();
		this.gameView = gameView;
	}
	
	public void addOrder(MenuOrder menu) {
		orderList.add(menu);
	}
	public int searchPrice(MenuOrder menu) {
		for (MenuOrder menuOrder : orderList) {
			if(menuOrder.getMenuName().equals(menu.getMenuName())){
				return menuOrder.getMenuPrice();
			}
		}
		return -1;
	}
	public int searchOrder(MenuOrder menu) {
		for (MenuOrder menuOrder : orderList) {
			if(menuOrder.getMenuName().equals(menu.getMenuName())){
				removeOrder(menuOrder.getOrderNo());
				stageGold += menuOrder.getMenuPrice();
				gameView.updateGold(stageGold);
				return menuOrder.getOrderNo();
			}
		}
		return -1;
	}
	
	public void removeOrder(int no) {
		for (MenuOrder menuOrder : orderList) {
			if(menuOrder.getOrderNo()==no) {
				orderList.remove(menuOrder);
				return;
			}
		}
	}
	
	public ArrayList<MenuOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<MenuOrder> orderList) {
		this.orderList = orderList;
	}
	
}
