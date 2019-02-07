package com.kh.miniProject.model.vo.menu;

public class MenuOrder extends Menu{
	private int orderNo;
	
	public MenuOrder(String menuName) {
		super(menuName);
	}
	public MenuOrder(String menuName, int menuPrice, int orderNo) {
		super(menuName,menuPrice);
		this.orderNo = orderNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
