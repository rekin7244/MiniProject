package com.kh.miniProject.view;

import javax.swing.*;

public class ChangePanel {
	
	public static void changePanel(JFrame mf, JPanel op, JPanel np) {
		mf.remove(op);
		mf.add(np);
		
		//������Ʈ�� �ٽ� �׸��� �޼ҵ�
		mf.repaint();
	}
	
}
