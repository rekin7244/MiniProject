package com.kh.miniProject.view;

import java.awt.Color;

import javax.swing.JFrame;

import com.kh.miniProject.music.*;
import com.kh.miniProject.run.Run;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		/*trackList.add(new Track("cursor7.mp3","cursor7"));*/
		
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);
		this.setTitle("�αۺα� �н�");
		this.setResizable(false);
		this.setBackground(Color.pink);
		
		
		//loginPanel�� �̵�
		this.add(new LoginPanel(this));
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}
