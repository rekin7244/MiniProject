package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.Run;

public class EquipmentPanel extends JPanel{

	private int panelSize = 250;			//¿Â∫Ò ∆–≥Œ ºº∑Œ≈©±‚

	private String[] name = {"¿⁄∆«±‚","∂±∫∫¿Ã±‚∞Ë","∆¢±Ë±‚","ø¿µ≠±‚∞Ë","∂Û∏È±‚∞Ë"};	//¿Â∫Ò∏Ì
	//equipsLv πËø≠ : 			{∂±∫∫¿Ã/∆¢±Ë±‚/ø¿µ≠±‚∞Ë/∂Û∏È±‚∞Ë}
	//¿ÃπÃ¡ˆµÈ πËø≠
	private Image[] images = {new ImageIcon("images/¿⁄∆«±‚.png")
			.getImage().getScaledInstance(150,panelSize,0),
			new ImageIcon("images/equip1.jpg")
			.getImage().getScaledInstance(250,panelSize,0),
			new ImageIcon("images/equip2.png")
			.getImage().getScaledInstance(200,panelSize,0),
			new ImageIcon("images/equip3.jpg")
			.getImage().getScaledInstance(200,panelSize,0),
			new ImageIcon("images/equip4.png")
			.getImage().getScaledInstance(180,panelSize,0)};
	private Image[] tbkImage = {new ImageIcon("images/∂±∫∫¿Ã∆«1.jpg")
			.getImage().getScaledInstance(250,panelSize,0),
			new ImageIcon("images/∂±∫∫¿Ã∆«2.jpg")
			.getImage().getScaledInstance(250,panelSize,0),
			new ImageIcon("images/∂±∫∫¿Ã∆«3.jpg")
			.getImage().getScaledInstance(250,panelSize,0)};
	private Image[] friedImage = {new ImageIcon("images/∆¢±Ë±‚1.jpg")
			.getImage().getScaledInstance(180,panelSize,0),
			new ImageIcon("images/∆¢±Ë±‚2.jpg")
			.getImage().getScaledInstance(180,panelSize,0),
			new ImageIcon("images/∆¢±Ë±‚3.jpg")
			.getImage().getScaledInstance(180,panelSize,0)};
	private JButton[] equips = new JButton[name.length];
	//πˆ∆∞ x¡¬«•
	private int[] xlocation = {Run.SCREEN_WIDTH-150,200,500,0,Run.SCREEN_WIDTH-345};
	//πˆ∆∞ x≈©±‚
	private int[] xSize = {150,250,180,200,195};

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
			equips[i] = new JButton(name[i]);
			equips[i].setBounds(xlocation[i],0,xSize[i],panelSize);
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
	//btn getter
	public JButton[] getEquips() {
		return equips;
	}
}
