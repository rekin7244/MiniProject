package com.kh.miniProject.run;

import javax.swing.JFrame;

import com.kh.miniProject.view.MainFrame;

public class Run {
	public static final int SCREEN_WIDTH = 1024;
	public static final int SCREEN_HEIGHT = 768;

	public static JFrame mainFrame;
	
	public static void main(String[] args) {
		mainFrame = new MainFrame();
	}
}