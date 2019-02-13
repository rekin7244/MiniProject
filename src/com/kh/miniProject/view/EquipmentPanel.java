package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.Run;

public class EquipmentPanel extends JPanel{

	private int panelSize = 250;			//¿Â∫Ò ∆–≥Œ ºº∑Œ≈©±‚
	private int equipSize = 120;

	private String[] name = {"¿⁄∆«±‚","∂±∫∫¿Ã±‚∞Ë","∆¢±Ë±‚","ø¿µ≠±‚∞Ë","∂Û∏È±‚∞Ë"};	//¿Â∫Ò∏Ì
	//equipsLv πËø≠ : 			{∂±∫∫¿Ã/∆¢±Ë±‚/ø¿µ≠±‚∞Ë/∂Û∏È±‚∞Ë}
	//¿ÃπÃ¡ˆµÈ πËø≠
	private Image[] images = {new ImageIcon("images/¿⁄∆«±‚.png")
			.getImage().getScaledInstance(150,panelSize,0),
			new ImageIcon("images/equip1.jpg")
			.getImage().getScaledInstance(220,equipSize,0),
			new ImageIcon("images/equip2.png")
			.getImage().getScaledInstance(200,equipSize,0),
			new ImageIcon("images/equip3.jpg")
			.getImage().getScaledInstance(200,equipSize,0),
			new ImageIcon("images/equip4.png")
			.getImage().getScaledInstance(180,equipSize,0)};
	private Image[] tbkImage = {new ImageIcon("images/∂±∫∫¿Ã∆«1.jpg")
			.getImage().getScaledInstance(205,equipSize,0),
			new ImageIcon("images/∂±∫∫¿Ã∆«2.jpg")
			.getImage().getScaledInstance(205,equipSize,0),
			new ImageIcon("images/∂±∫∫¿Ã∆«3.jpg")
			.getImage().getScaledInstance(205,equipSize,0)};
	private Image[] friedImage = {new ImageIcon("images/∆¢±Ë±‚1.jpg")
			.getImage().getScaledInstance(120,equipSize,0),
			new ImageIcon("images/∆¢±Ë±‚2.jpg")
			.getImage().getScaledInstance(180,equipSize,0),
			new ImageIcon("images/∆¢±Ë±‚3.jpg")
			.getImage().getScaledInstance(180,equipSize,0)};
	private JButton[] equips = new JButton[name.length];
	private Image tableImage = new ImageIcon("images/equiptable2.png").getImage().getScaledInstance(Run.SCREEN_WIDTH, panelSize, 0);
	//πˆ∆∞ x¡¬«•
	private int[] xlocation = {Run.SCREEN_WIDTH-150,205,415,0,Run.SCREEN_WIDTH-345};
	//πˆ∆∞ x≈©±‚
	private int[] xSize = {150,210,180,200,195};

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
			equips[i] = new JButton();
			if(i!=0) {
				if(i==2) {
					if(equipsLv[1]==1) {
						equips[i].setBounds(xlocation[i],0,xSize[i]-60,equipSize);
					}else {
						equips[i].setBounds(xlocation[i],0,xSize[i],equipSize);
					}
				}else {
					equips[i].setBounds(xlocation[i],0,xSize[i],equipSize);
				}
			}else {
				equips[i].setBounds(xlocation[i],0,xSize[i],panelSize);
			}
			equips[i].setContentAreaFilled(false);
			equips[i].setBorderPainted(false);
			if(i==1) {
				if(equipsLv[0]>0)
				equips[i].setIcon(new ImageIcon(tbkImage[equipsLv[0]-1]));
			}else if(i==2) {
				if(equipsLv[1]>0)
				equips[i].setIcon(new ImageIcon(friedImage[equipsLv[1]-1]));
			}else {
				equips[i].setIcon(new ImageIcon(images[i]));				
			}
			if(i>0) {
				if(equipsLv[i-1]!=0) {panel.add(equips[i]);}
				if(equipsLv[i-1]==0) {
					equips[i].setEnabled(false);
				}
			}else {panel.add(equips[i]);}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(tableImage, 0, 0, null);
		repaint();
	}
	
	//btn getter
	public JButton[] getEquips() {
		return equips;
	}
}
