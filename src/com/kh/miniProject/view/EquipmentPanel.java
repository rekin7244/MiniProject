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
	private Image drinkImage = new ImageIcon("images/equipImage/¿⁄∆«±‚.png")
			.getImage().getScaledInstance(150,panelSize,0);
	private Image[] tbkImage = {new ImageIcon("images/equipImage/∂±∫∫¿Ã∆«1.jpg")
			.getImage().getScaledInstance(205,equipSize,0),
			new ImageIcon("images/equipImage/∂±∫∫¿Ã∆«2.jpg")
			.getImage().getScaledInstance(205,equipSize,0),
			new ImageIcon("images/equipImage/∂±∫∫¿Ã∆«3.jpg")
			.getImage().getScaledInstance(205,equipSize,0)};
	private Image[] friedImage = {new ImageIcon("images/equipImage/∆¢±Ë±‚1.jpg")
			.getImage().getScaledInstance(120,equipSize,0),
			new ImageIcon("images/equipImage/∆¢±Ë±‚2.jpg")
			.getImage().getScaledInstance(180,equipSize,0),
			new ImageIcon("images/equipImage/∆¢±Ë±‚3.jpg")
			.getImage().getScaledInstance(180,equipSize,0)};
	private Image[] odengImage = {new ImageIcon("images/equipImage/ø¿µ≠1.jpg")
			.getImage().getScaledInstance(200,equipSize,0),
			new ImageIcon("images/equipImage/ø¿µ≠2.jpg")
			.getImage().getScaledInstance(200,equipSize,0),
			new ImageIcon("images/equipImage/ø¿µ≠2.jpg")
			.getImage().getScaledInstance(200,equipSize,0)};
	private Image[] ramenImage = {new ImageIcon("images/equipImage/∂Û∏È±‚∞Ë1.png")
			.getImage().getScaledInstance(260,230,0),
			new ImageIcon("images/equipImage/∂Û∏È±‚∞Ë1.png")
			.getImage().getScaledInstance(260,230,0),
			new ImageIcon("images/equipImage/∂Û∏È±‚∞Ë4.png")
			.getImage().getScaledInstance(260,230,0)};
	private JButton[] equips = new JButton[name.length];
	private Image tableImage = new ImageIcon("images/equiptable2.png").getImage().getScaledInstance(Run.SCREEN_WIDTH, panelSize, 0);
	//πˆ∆∞ x¡¬«•
	private int[] xlocation = {Run.SCREEN_WIDTH-150,205,415,0,Run.SCREEN_WIDTH-445};
	//πˆ∆∞ x≈©±‚
	private int[] xSize = {150,210,180,200,295};

	//cons
	public EquipmentPanel() {
		this.setLayout(null);
		this.setBounds(0,Run.SCREEN_HEIGHT-panelSize,Run.SCREEN_WIDTH,panelSize);
		this.setBackground(Color.GRAY);
	}

	public void equipsSetting(JPanel panel,Member m) {
		int[] equipsLv = m.getEquipsLv();
		//Equipments setting
		for (int i = 0; i < name.length; i++) {
			equips[i] = new JButton();
			if(i!=0) {
				if(i==2) {
					if(equipsLv[1]==1) {	//∆¢±Ë±‚
						equips[i].setBounds(xlocation[i],0,xSize[i]-60,equipSize);
					}else {
						equips[i].setBounds(xlocation[i],0,xSize[i],equipSize);
					}
				}else if(i==4){				//∂Û∏È±‚∞Ë
					equips[i].setBounds(xlocation[i],0,xSize[i],equipSize+90);
				}else						//∂±∫∫¿Ã,ø¿µ≠
					equips[i].setBounds(xlocation[i],0,xSize[i],equipSize);
			}else {							//¿⁄∆«±‚
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
			}else if(i==3){
				if(equipsLv[2]>0)
				equips[i].setIcon(new ImageIcon(odengImage[equipsLv[2]-1]));				
			}else if(i==4) {
				if(equipsLv[3]>0)
				equips[i].setIcon(new ImageIcon(ramenImage[equipsLv[3]-1]));			
			}else {	//¿⁄∆«±‚
				equips[i].setIcon(new ImageIcon(drinkImage));
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
