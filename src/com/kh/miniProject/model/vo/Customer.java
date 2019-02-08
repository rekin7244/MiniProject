package com.kh.miniProject.model.vo;

public class Customer {
	private double waitingTime;
	
	
	//cons
	public Customer(int lv) {
		this.waitingTime = 10-(0.5*lv);
	}

	//get,set
	public double getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}
}
