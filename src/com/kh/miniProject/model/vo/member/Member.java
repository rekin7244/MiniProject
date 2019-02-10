package com.kh.miniProject.model.vo.member;

import java.io.Serializable;
import java.util.Arrays;

public class Member implements Serializable{
	private String memberId;
	private String memberPwd;
	private String memberEmail;
	private int maxStage=1;		//초기 세팅 1스테이지
	private int gold=999999999;			//초기 금액 0
	
	//조리기구 레벨 초기 세팅 {떡볶이/튀김기/오뎅기계/라면기계}
	private int[] equipsLv = {1,0,0,0};
	//메뉴테이블 레벨 초기 세팅 {떡볶이/튀김/오뎅/라면}
	private int[] tableLv = {1,1,1,1};


	//cons
	public Member(String memberId, String memberPwd, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberEmail = memberEmail;
		if(memberId.equals("test")) {	//test용
			maxStage=3;
			gold=30000;
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
	public int[] getTableLv() {
		return tableLv;
	}
	public void setTableLv(int[] tableLv) {
		this.tableLv = tableLv;
	}


	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberEmail=" + memberEmail
				+ ", maxStage=" + maxStage + ", gold=" + gold + ", equipsLv=" + Arrays.toString(equipsLv) + ", tableLv="
				+ Arrays.toString(tableLv) + "]";
	}
	
}
