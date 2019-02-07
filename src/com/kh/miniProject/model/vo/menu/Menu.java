package com.kh.miniProject.model.vo.menu;

public class Menu {
	private String menuName;
	private int menuPrice;
	
	//cons
	public Menu(String menuName) {
		this.menuName = menuName;
	}
	public Menu(String menuName, int menuPrice) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}
	
	//get,set
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
}
