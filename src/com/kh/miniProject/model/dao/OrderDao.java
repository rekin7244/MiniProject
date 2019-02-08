package com.kh.miniProject.model.dao;

import java.util.ArrayList;

import com.kh.miniProject.model.vo.menu.MenuOrder;
import com.kh.miniProject.view.GameView;

public class OrderDao {
	private ArrayList<MenuOrder> orderList;	//LinkedList로 바꾸자 (속도 개선의 효과를 보기위해)
	private int orderCount;
	private int stageGold;
	private GameView gameView;
	
	//cons
	public OrderDao(GameView gameView) {
		orderList = new ArrayList();
		this.gameView = gameView;
	}
	

	public void addOrder(MenuOrder menu) {
		orderList.add(menu);
	}
	
	public boolean searchOrder(MenuOrder menu) {
		for (MenuOrder menuOrder : orderList) {
			if(menuOrder.getMenuName().equals(menu.getMenuName())){
				removeOrder(menuOrder.getOrderNo());
				return true;
			}
		}
		return false;
	}
	
	public void removeOrder(int no) {
		for (MenuOrder menuOrder : orderList) {
			if(menuOrder.getOrderNo()==no) {
				orderList.remove(menuOrder);
				stageGold += menuOrder.getMenuPrice();
				gameView.updateGold(stageGold);
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
