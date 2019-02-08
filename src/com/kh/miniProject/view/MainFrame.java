package com.kh.miniProject.view;

import java.awt.Color;

import javax.swing.JFrame;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.Run;

public class MainFrame extends JFrame{

	public MainFrame() {
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);
		this.setTitle("부글부글 분식");
		this.setResizable(false);
		this.setBackground(Color.pink);
		
		
		//test 용으로 guestLogin 처리
		Member guest = new Member("guest","p","e");
		guest.setGold(2000);
		//test용이므로 StageView를 초기화면으로 해둠
		//this.add(new StageView(this,guest));
		//this.add(new GameView());
		this.add(new LoginPanel(this,guest));
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
