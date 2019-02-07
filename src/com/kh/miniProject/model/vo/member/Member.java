package com.kh.miniProject.model.vo.member;

public class Member {
	private String memberId;
	private String memberPwd;
	private String memberEmail;
	private int maxStage;
	private int gold;
	
	private int[] equipsLv = {1,1,1,1,1};
	
	//cons
	public Member(String memberId, String memberPwd, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberEmail = memberEmail;
	}
	
	
	//get,set
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public int getMaxStage() {
		return maxStage;
	}
	public void setMaxStage(int maxStage) {
		this.maxStage = maxStage;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int[] getEquipsLv() {
		return equipsLv;
	}
	public void setEquipsLv(int[] equipsLv) {
		this.equipsLv = equipsLv;
	}
}
