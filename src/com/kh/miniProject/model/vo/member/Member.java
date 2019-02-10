package com.kh.miniProject.model.vo.member;

import java.io.Serializable;
import java.util.Arrays;

public class Member implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8292407382870924813L;
	private String memberId;
	private String memberPwd;
	private String memberEmail;
	private int maxStage=1;				//�ʱ� ���� 1��������
	private int gold=9999999;			//�ʱ� �ݾ� 0
	private int stageGold;

	//�����ⱸ ���� �ʱ� ���� {������/Ƣ���/�������/�����}
	private int[] equipsLv = {1,0,0,0};
	//�޴����̺� ���� �ʱ� ���� {������/Ƣ��/����/���}
	private int[] tableLv = {1,1,1,1};


	//cons
	public Member(String memberId, String memberPwd, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberEmail = memberEmail;
		if(memberId.equals("test")) {	//test��
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
	
	


	public int getStageGold() {
		return stageGold;
	}

	public void setStageGold(int stageGold) {
		this.stageGold = stageGold;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberEmail=" + memberEmail
				+ ", maxStage=" + maxStage + ", gold=" + gold + ", equipsLv=" + Arrays.toString(equipsLv) + ", tableLv="
				+ Arrays.toString(tableLv) + "]";
	}
}
