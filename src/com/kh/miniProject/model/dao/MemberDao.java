package com.kh.miniProject.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.kh.miniProject.model.vo.member.Member;

public class MemberDao {
	private ArrayList<Member> memberList;
	
	public MemberDao() {		//�������̸鼭 ����� ��� �ҷ���
		memberList = new ArrayList<Member>();
		try {
			ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("members.dat"));
			while(true) {
				memberList.add((Member)oIn.readObject());
			}
		}catch (FileNotFoundException e) {
			System.out.println("����� ȸ���� �����ϴ�.");
		} catch (EOFException e) {
			System.out.println("����� ������ �о�Խ��ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addMember(Member member) {
		memberList.add(member);
		saveMemberList();
	}
	
	//����� : ����� ȸ�� ���
	public void printMember() {
		for (Member member : memberList) {
			System.out.println(member);
		}
	}
	
	public Member loginMember(String memberId,String memberPwd) {
		for (Member member : memberList) {
			if(member.getMemberId().equals(memberId) && member.getMemberPwd().equals(memberPwd)) {
				return member;
			}			//�α��� ����
		}
		return null;	//�α��� ����
	}
	

	public Member searchMember(String memberEmail) {
		for (Member member : memberList) {
			if(member.getMemberEmail().equals(memberEmail)) {
				return member;
			}			//��ȸ ����
		}
		return null;	//��ȸ ����
	}
	
	//�ӽ� ������ ���� (���ص� ��)
	public void removeMember(String memberId,String memberPwd) {
		for (Member member : memberList) {
			if(member.getMemberId().equals(memberId) && member.getMemberPwd().equals(memberPwd)) {
				memberList.remove(member);
				return;
			}			//��� ����
		}
	}
	
	public int getMemberGold(Member m) {
		for (Member member : memberList) {
			if(member.getMemberId().equals(m.getMemberId())) {
				return member.getGold();
			}
		}
		return 0;
	}
	
	public void saveMemberList() {							//ArrayList ��ü ���Ͽ� ����
		try {
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream("members.dat"));
			for (Member member : memberList) {
				oOut.writeObject(member);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
