package com.kh.miniProject.view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.*;
import com.kh.miniProject.run.Run;

public class MainFrame extends JFrame{
	
	private Music titleMusic;

	public MainFrame() {
		/*trackList.add(new Track("cursor7.mp3","cursor7"));*/
		
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);
		this.setTitle("�αۺα� �н�");
		this.setResizable(false);
		this.setBackground(Color.pink);
		
		
		//loginPanel�� �̵�
		this.add(new LoginPanel(this));
		
		/*test���̹Ƿ� StageView�� �ʱ�ȭ������ �ص�
		this.add(new StageView(this,guest));
		this.add(new GameView());*/
		titleMusic = new Music("TitleMusic.mp3",false);
		titleMusic.start();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Thread getTitleMusic() {
		return titleMusic;
	}

	public void setTitleMusic(String string, boolean b) {
		// TODO Auto-generated method stub
		if(b==true) {
			new Music(string,b).start();			
		}else {
			
		}
	}


	
	
	
}
