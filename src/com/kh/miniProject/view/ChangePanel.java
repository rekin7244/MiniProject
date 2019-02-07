package com.kh.miniProject.view;

import javax.swing.*;

public class ChangePanel {
	
	public static void changePanel(JFrame mf, JPanel op, JPanel np) {
		mf.remove(op);
		mf.add(np);
		
		//컴포넌트를 다시 그리는 메소드
		mf.repaint();
	}
	
}
