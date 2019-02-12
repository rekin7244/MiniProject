package com.kh.miniProject.view;

import java.awt.*;


import javax.swing.*;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.*;

public class MenuPanel extends JPanel{
	private static final int PNANEL_HIGHT = 200;

	private Image[] drinksImage = {
			new ImageIcon("Images/drink1.jpg").getImage().getScaledInstance(150, PNANEL_HIGHT, 0),
			new ImageIcon("Images/drink2.jpg").getImage().getScaledInstance(150, PNANEL_HIGHT, 0),
			new ImageIcon("Images/drink3.jpg").getImage().getScaledInstance(150, PNANEL_HIGHT, 0)	};
	private Image[] tbkImage = {
			new ImageIcon("Images/tbk1.png").getImage().getScaledInstance(250, PNANEL_HIGHT, 0),
			new ImageIcon("Images/tbk2.png").getImage().getScaledInstance(250, PNANEL_HIGHT, 0),
			new ImageIcon("Images/tbk3.png").getImage().getScaledInstance(250, PNANEL_HIGHT, 0),	
			new ImageIcon("Images/tbk4.png").getImage().getScaledInstance(250, PNANEL_HIGHT, 0)	};
	private Image[] friedImage = {
			new ImageIcon("Images/fried1.png").getImage().getScaledInstance(180, PNANEL_HIGHT, 0),
			new ImageIcon("Images/fried2.png").getImage().getScaledInstance(180, PNANEL_HIGHT, 0),
			new ImageIcon("Images/fried3.png").getImage().getScaledInstance(180, PNANEL_HIGHT, 0),	
			new ImageIcon("Images/fried4.png").getImage().getScaledInstance(180, PNANEL_HIGHT, 0)	};
	private Image[] odengImage = {
			new ImageIcon("Images/¿Àµ­1.png").getImage().getScaledInstance(200, PNANEL_HIGHT, 0),
			new ImageIcon("Images/¿Àµ­2.png").getImage().getScaledInstance(200, PNANEL_HIGHT, 0),
			new ImageIcon("Images/¿Àµ­3.png").getImage().getScaledInstance(200, PNANEL_HIGHT, 0),
			new ImageIcon("Images/¿Àµ­4.png").getImage().getScaledInstance(200, PNANEL_HIGHT, 0)};
	private Image[] ramenImage = {
			new ImageIcon("Images/¶ó¸é1.png").getImage().getScaledInstance(195, PNANEL_HIGHT, 0),
			new ImageIcon("Images/¶ó¸é2.png").getImage().getScaledInstance(195, PNANEL_HIGHT, 0),
			new ImageIcon("Images/¶ó¸é3.png").getImage().getScaledInstance(195, PNANEL_HIGHT, 0),
			new ImageIcon("Images/¶ó¸é4.png").getImage().getScaledInstance(195, PNANEL_HIGHT, 0)};
	private String[] menu = {"À½·á¼ö","¶±ººÀÌ","Æ¢±è","¿Àµ­","¶ó¸é"};

	private int[] imageLocation = {Run.SCREEN_WIDTH-150,200,500,0,Run.SCREEN_WIDTH-345};
	private int[] buttonSize = {150,250,180,200,195};
	private JButton menuButton[];



	public MenuPanel(Member m) {
		menuButton = new JButton[menu.length];
		this.setLayout(null);
		this.setBounds(0,318,Run.SCREEN_WIDTH,PNANEL_HIGHT);
		this.setBackground(Color.DARK_GRAY);

		for(int i=0; i<menuButton.length;i++) {
			menuButton[i] = new JButton(menu[i]);
			menuButton[i].setIcon(null);
			menuButton[i].setBounds(imageLocation[i],0, buttonSize[i], PNANEL_HIGHT);
			menuButton[i].setContentAreaFilled(false);
			this.add(menuButton[i]);
		}
	}

	public void setting(JPanel mp, int drinksNo,int tbkNo,int friedNo,int odengNo,int ramenNo) {
		
		if(drinksNo==3) {
			menuButton[0].setIcon(new ImageIcon(drinksImage[2]));
		}else if(drinksNo==2) {
			menuButton[0].setIcon(new ImageIcon(drinksImage[1]));
		}else if(drinksNo==1) {
			menuButton[0].setIcon(new ImageIcon(drinksImage[0]));
		}else {
			menuButton[0].setIcon(null);
		}

		if(tbkNo==4) {
			menuButton[1].setIcon(new ImageIcon(tbkImage[3]));
		}else if(tbkNo==3) {
			menuButton[1].setIcon(new ImageIcon(tbkImage[2]));
		}else if(tbkNo==2) {
			menuButton[1].setIcon(new ImageIcon(tbkImage[1]));
		}else if(tbkNo==1) {
			menuButton[1].setIcon(new ImageIcon(tbkImage[0]));
		}else{
			menuButton[1].setIcon(null);
		}

		if(friedNo==4) {
			menuButton[2].setIcon(new ImageIcon(friedImage[3]));
		}else if(friedNo==3) {
			menuButton[2].setIcon(new ImageIcon(friedImage[2]));
		}else if(friedNo==2) {
			menuButton[2].setIcon(new ImageIcon(friedImage[1]));
		}else if(friedNo==1) {
			menuButton[2].setIcon(new ImageIcon(friedImage[0]));
		}else{
			menuButton[2].setIcon(null);
		}
		
		if(odengNo==4) {
			menuButton[3].setIcon(new ImageIcon(odengImage[3]));
		}else if(odengNo==3) {
			menuButton[3].setIcon(new ImageIcon(odengImage[2]));
		}else if(odengNo==2) {
			menuButton[3].setIcon(new ImageIcon(odengImage[1]));
		}else if(odengNo==1) {
			menuButton[3].setIcon(new ImageIcon(odengImage[0]));
		}else{
			menuButton[3].setIcon(null);
		}
		
		if(ramenNo==4) {
			menuButton[4].setIcon(new ImageIcon(ramenImage[3]));
		}else if(ramenNo==3) {
			menuButton[4].setIcon(new ImageIcon(ramenImage[2]));
		}else if(ramenNo==2) {
			menuButton[4].setIcon(new ImageIcon(ramenImage[1]));
		}else if(ramenNo==1) {
			menuButton[4].setIcon(new ImageIcon(ramenImage[0]));
		}else{
			menuButton[4].setIcon(null);
		}
	}


	public String[] getMenu() {
		return menu;
	}
	public void setMenu(String[] menu) {
		this.menu = menu;
	}
	public JButton[] getMenuButton() {
		return menuButton;
	}
	public void setMenuButton(JButton[] menuButton) {
		this.menuButton = menuButton;
	}
}
