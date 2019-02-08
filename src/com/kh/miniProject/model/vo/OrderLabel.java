package com.kh.miniProject.model.vo;

import javax.swing.JLabel;

public class OrderLabel extends JLabel {
	private int orderNo;
	
	public OrderLabel(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
}
