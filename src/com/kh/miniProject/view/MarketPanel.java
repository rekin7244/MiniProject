package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.kh.miniProject.model.vo.member.Member;

public class MarketPanel extends JPanel {
	//¹Þ¾Æ¿Ã ÇÊµå
	MainFrame mf;
	Member m;
	//»ç¿ë ÇÊµå
	private int tbkLv = 1;
	JButton mo = new JButton("¶±ººÀÌ±â±¸");
	private int tkLv = 1;
	JButton mo1 = new JButton("Æ¢±è±â±¸");
	JPanel mPanel;
	private int[] equipsLv;
	private EquipSetting e;

	private Image[] equipsImages = { new ImageIcon("images/1Lvtbk.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/2Lvtbk.jpg").getImage().getScaledInstance(200, 200, 0) };

	private Image[] equipsImages1 = { new ImageIcon("images/1Lvfried.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/2Lvfried.jpg").getImage().getScaledInstance(200, 200, 0) };

	public MarketPanel(MainFrame mf, Member m) {
		e = new EquipSetting();
		this.mf = mf;
		this.m = m;
		this.equipsLv = m.getEquipsLv();
		this.setLayout(null);
		this.setBounds(110, 50, 800, 650);
		this.setBackground(Color.orange);

		// °ñµåÃâ·Â
		JButton gold = new JButton("°ñµå");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0, 0, 200, 30);
		gold.setText("¼ÒÁö±Ý: " + 0 + "G");
		this.add(gold);

		// µ¹¾Æ°¡±â
		JButton returnBtn = new JButton("µ¹¾Æ°¡±â");
		returnBtn.setBounds(660, 620, 140, 30);

		// »óÁ¡ ¹öÆ° ¹× ±â´É ±¸Çö
		setting(this);

		// Action
		mPanel = this;
		returnBtn.addActionListener(new EquipSetting());
	}
	public void setting(JPanel panel) {
		//¶±ººÀÌ
		if (equipsLv[0] == 1) {
			mo.setIcon(new ImageIcon(equipsImages[0]));
		} else if (equipsLv[0] == 2) {
			mo.setIcon(new ImageIcon(equipsImages[1]));
		}
		mo.setBounds(200, 200, 200, 200);
		mo.removeActionListener(e);
		mo.addActionListener(e);

		//Æ¢±è
		if (equipsLv[1] == 1) {
			mo1.setIcon(new ImageIcon(equipsImages1[0]));
		} else if (equipsLv[1] == 2) {
			mo1.setIcon(new ImageIcon(equipsImages1[1]));
		}
		mo1.setBounds(500, 200, 200, 200);
		mo1.removeActionListener(e);
		mo1.addActionListener(e);

		panel.add(mo);
		panel.add(mo1);
	}

	class EquipSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("¶±ººÀÌ±â±¸")) {
				System.out.println("tbk");
				if (equipsLv[0] == 1) {
					equipsLv[0] += 1;
				} else {
					System.out.println("lv max error");
				}
				refresh();
			}
			if (e.getActionCommand().equals("Æ¢±è±â±¸")) {
				System.out.println("tk");
				if (equipsLv[1] == 1) {
					equipsLv[1] += 1;
				} else {
					System.out.println("lv max error");
				}
				refresh();
			}
			if (e.getActionCommand().equals("µ¹¾Æ°¡±â")) {
				new ChangePanel().changePanel(mf,mPanel,new StageView(mf,m));
			};
		}
		public void refresh() {
			setting(mPanel);
		}
	}
}//class
