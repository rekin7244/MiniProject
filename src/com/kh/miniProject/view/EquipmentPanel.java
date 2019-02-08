package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.Run;

public class EquipmentPanel extends JPanel{

	private int panelSize = 250;			//장비 패널 세로크기

	private String[] name = {"자판기","떡볶이기계","순대기계","오뎅기계","튀김기"};	//장비명

	//이미지들 배열
	private Image[] images = {new ImageIcon("images/equip0.png")
			.getImage().getScaledInstance(250,panelSize,0),
			new ImageIcon("images/equip1.jpg")
			.getImage().getScaledInstance(250,panelSize,0),
			new ImageIcon("images/equip2.jpg")
			.getImage().getScaledInstance(200,panelSize,0),
			new ImageIcon("images/equip3.jpg")
			.getImage().getScaledInstance(200,panelSize,0),
			new ImageIcon("images/equip4.png")
			.getImage().getScaledInstance(180,panelSize,0)};
	private JButton[] equips = new JButton[name.length];
	//버튼 x좌표
	private int[] xlocation = {Run.SCREEN_WIDTH-150,200,Run.SCREEN_WIDTH-345,0,500};
	//버튼 x크기
	private int[] xSize = {150,250,195,200,180};

	//cons
	public EquipmentPanel() {
		this.setLayout(null);
		this.setBounds(0,Run.SCREEN_HEIGHT-panelSize,Run.SCREEN_WIDTH,panelSize);
		this.setBackground(Color.GRAY);
	}

	public void equipsSetting(JPanel panel,Member m) {
		int[] equipsLv = m.getEquipsLv();
		//Equipments setting
		for (int i = 0; i < images.length; i++) {
			if(equipsLv[i]>0) {
				equips[i] = new JButton(name[i]);
				equips[i].setBounds(xlocation[i],0,xSize[i],panelSize);
				equips[i].setIcon(new ImageIcon(images[i]));
				panel.add(equips[i]);
			}
		}
	}
	//btn getter
	public JButton[] getEquips() {
		return equips;
	}


}
