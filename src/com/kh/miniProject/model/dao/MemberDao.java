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
	
	public MemberDao() {		//생성자이면서 저장된 멤버 불러옴
		memberList = new ArrayList<Member>();
		try {
			ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("members.dat"));
			while(true) {
				memberList.add((Member)oIn.readObject());
			}
		}catch (FileNotFoundException e) {
			System.out.println("저장된 회원이 없습니다.");
		} catch (EOFException e) {
			System.out.println("저장된 정보를 읽어왔습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		printMember();
	}
	
	public void addMember(Member member) {
		memberList.add(member);
		saveMemberList();
	}
	
	public void saveMember(Member member) {
		for (Member m : memberList) {
			if(m.getMemberId().equals(member.getMemberId())) {
				m.setGold(member.getGold());
				m.setMaxStage(member.getMaxStage());
				m.setEquipsLv(member.getEquipsLv());
				m.setTableLv(member.getTableLv());
				saveMemberList();
				System.out.println("저장됨");
			}
		}
	}
	
	public boolean checkMember(String memberId, String memberEmail) {
		for(Member m : memberList) {
			if(m.getMemberId().equals(memberId) || m.getMemberEmail().equals(memberEmail)) {
				return false;
			}
		}
		return true;
	}
	
	//아이디 중복확인
	public boolean checkID(String memberId) {
		for(Member m : memberList) {
			if(m.getMemberId().equals(memberId)) {
				return false;
			}
		}
		return true;
	}
	
	//이메일 중복확인
	public boolean checkEmail(String memberEmail) {
		for(Member m : memberList) {
			if(m.getMemberEmail().equals(memberEmail)) {
				return false;
			}
		}
		return true;
	}
	
	//실험용 : 저장된 회원 출력
	public void printMember() {
		for (Member member : memberList) {
			System.out.println(member);
		}
	}
	
	public Member loginMember(String memberId,String memberPwd) {
		for (Member member : memberList) {
			if(member.getMemberId().equals(memberId) && member.getMemberPwd().equals(memberPwd)) {
				return member;
			}			//로그인 성공
		}
		return null;	//로그인 실패
	}
	

	public Member searchMember(String memberEmail) {
		for (Member member : memberList) {
			if(member.getMemberEmail().equals(memberEmail)) {
				return member;
			}			//조회 성공
		}
		return null;	//조회 실패
	}
	
	//임시 구현한 내용 (안해도 됨)
	public void removeMember(String memberId,String memberPwd) {
		for (Member member : memberList) {
			if(member.getMemberId().equals(memberId) && member.getMemberPwd().equals(memberPwd)) {
				memberList.remove(member);
				saveMemberList();
				return;
			}			//멤버 삭제
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
	
	public void saveMemberList() {							//ArrayList 전체 파일에 저장
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
