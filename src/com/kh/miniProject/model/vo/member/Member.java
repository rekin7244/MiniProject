package com.kh.miniProject.model.vo.member;

import java.io.Serializable;
import java.util.Arrays;

public class Member implements Serializable{
	private String memberId;
	private String memberPwd;
	private String memberEmail;
	private int maxStage=1;		//초기 세팅 1스테이지
	private int gold;			//초기 금액 0
	
	//조리기구 레벨 초기 세팅 {자판기/떡볶이/라면기계/오뎅기계/튀김기}
	private int[] equipsLv = {1,1,0,0,0};
	//메뉴테이블 레벨 초기 세팅 {자판기/떡볶이/라면/오뎅/튀김}
	private int[] tableLv = {1,1,1,1,1};
	
	public int[] getTableLv() {
		return tableLv;
	}


	public void setTableLv(int[] tableLv) {
		this.tableLv = tableLv;
	}


	//cons
	public Member(String memberId, String memberPwd, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberEmail = memberEmail;
		if(memberId.equals("test")) {
			maxStage=2;
			gold=10000;
		};
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


	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberEmail=" + memberEmail
				+ ", maxStage=" + maxStage + ", gold=" + gold + ", equipsLv=" + Arrays.toString(equipsLv) + ", tableLv="
				+ Arrays.toString(tableLv) + "]";
	}
	
}
