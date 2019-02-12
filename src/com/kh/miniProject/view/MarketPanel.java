package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kh.miniProject.model.dao.MemberDao;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.Music;

public class MarketPanel extends JPanel {

	// ¹Þ¾Æ¿Ã ÇÊµå
	MainFrame mf;
	Member m;

	// »ç¿ë ÇÊµå
  private Music marketMusic;
	// ±â±¸ ¾÷±Ûºñ¿ë
	private int level1 = 25000;
	private int level2 = 50000;
	private int level3 = 100000;
	// ÆÇ ¾÷±Ûºñ¿ë
	private int plevel2 = 30000;
	private int plevel3 = 60000;
	private int plevel4 = 100000;

	// ±â±¸
	JButton mo = new JButton("¶±ººÀÌ±â±¸");
	JButton mo1 = new JButton("Æ¢±è±â±¸");
	JButton mo2 = new JButton("¿Àµ­±â±¸");
	JButton mo3 = new JButton("¶ó¸é±â±¸");

	// ÆÇ
	JButton mo4 = new JButton("¶±ººÀÌÆÇ ¾÷±Û");
	JButton mo5 = new JButton("Æ¢±èÆÇ ¾÷±Û");
	JButton mo6 = new JButton("¿Àµ­ÆÇ ¾÷±Û");
	JButton mo7 = new JButton("¶ó¸éÆÇ ¾÷±Û");

	MarketPanel mPanel;
	JButton gold;
	private int[] equipsLv;
	private int[] tableLv;
	private EquipSetting e;

	// ¶±ººÀÌ ±â±¸
	private Image[] equipsImages = { new ImageIcon("images/¶±2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¶±3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// Æ¢±è ±â±¸
	private Image[] equipsImages1 = { new ImageIcon("images/Æ¢1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Æ¢2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Æ¢3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// ¿Àµ­ ±â±¸
	private Image[] equipsImages2 = { new ImageIcon("images/¿À1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¿À2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¿À3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// ¶ó¸é ±â±¸
	private Image[] equipsImages3 = { new ImageIcon("images/¶ó1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¶ó2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¶ó3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// ¶±ººÀÌ ÆÇ
	private Image[] tableImages = { new ImageIcon("images/¶±2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¶±3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¶±4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0), };

	// Æ¢±è ÆÇ
	private Image[] tableImages1 = { new ImageIcon("images/Æ¢2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Æ¢3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Æ¢4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// ¿Àµ­ ÆÇ
	private Image[] tableImages2 = { new ImageIcon("images/¿À2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¿À3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¿À4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// ¶ó¸é ÆÇ
	private Image[] tableImages3 = { new ImageIcon("images/¶ó2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¶ó3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¶ó4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	
	//cons
	public MarketPanel(MainFrame mf, Member m) {
		e = new EquipSetting();
		this.mf = mf;
		this.m = m;
		this.equipsLv = m.getEquipsLv();
		this.tableLv = m.getTableLv();

		this.setLayout(null);
		this.setBounds(110, 50, 800, 650);
		this.setBackground(Color.orange);

		marketMusic = new Music("intro3.mp3",false);
		marketMusic.start();

		// °ñµå Ãâ·Â
		gold = new JButton("°ñµå");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0, 0, 200, 30);

		// µ¹¾Æ°¡±â
		JButton returnBtn = new JButton("µ¹¾Æ°¡±â");
		returnBtn.setBounds(660, 620, 140, 30);
		this.add(returnBtn);
		returnBtn.addActionListener(e);

		mPanel = this;
	}// marketpanel

	// »óÁ¡ ¹öÆ° ¹× ±â´É ±¸Çö
	public void setting(MarketPanel mPanel) {
		// °ñµå Ãâ·Â
		gold.setText("¼ÒÁö±Ý: " + m.getGold() + "G");
		mPanel.add(gold);

		// ¶±ººÀÌ ±â±¸
		if (equipsLv[0] == 1) {
			mo.setIcon(new ImageIcon(equipsImages[0]));
		} else if (equipsLv[0] == 2) {
			mo.setIcon(new ImageIcon(equipsImages[1]));
		} else {
			mo.setIcon(new ImageIcon(equipsImages[2]));
			mo.setEnabled(false);
		}
		mo.setBounds(20, 60, 180, 180);
		mo.removeActionListener(e);
		mo.addActionListener(e);

		
		if (equipsLv[1] == 0) {
			mo1.setIcon(new ImageIcon(equipsImages1[0]));
		} else if (equipsLv[1] == 1) {
			mo1.setIcon(new ImageIcon(equipsImages1[1]));
		} else if (equipsLv[1] == 2) {
			mo1.setIcon(new ImageIcon(equipsImages1[2]));
		} else {
			mo1.setIcon(new ImageIcon(equipsImages1[3]));
			mo1.setEnabled(false);
		}
		mo1.setBounds(210, 60, 180, 180);
		mo1.removeActionListener(e);
		mo1.addActionListener(e);

		// ¿Àµ­ ±â±¸
		mo2.setVisible(false);
		if (equipsLv[1] >= 1) {
			mo2.setVisible(true);
		}
		if (equipsLv[2] == 0) {
			mo2.setIcon(new ImageIcon(equipsImages2[0]));
		} else if (equipsLv[2] == 1) {
			mo2.setIcon(new ImageIcon(equipsImages2[1]));
		} else if (equipsLv[2] == 2) {
			mo2.setIcon(new ImageIcon(equipsImages2[2]));
		} else {
			mo2.setIcon(new ImageIcon(equipsImages2[3]));
			mo2.setEnabled(false);
		}
		mo2.setBounds(400, 60, 180, 180);
		mo2.removeActionListener(e);
		mo2.addActionListener(e);

		// ¶ó¸é ±â±¸
		mo3.setVisible(false);
		if (equipsLv[2] >= 1) {
			mo3.setVisible(true);
		}
		if (equipsLv[3] == 0) {
			mo3.setIcon(new ImageIcon(equipsImages3[0]));
		} else if (equipsLv[3] == 1) {
			mo3.setIcon(new ImageIcon(equipsImages3[1]));
		} else if (equipsLv[3] == 2) {
			mo3.setIcon(new ImageIcon(equipsImages3[2]));
		} else {
			mo3.setIcon(new ImageIcon(equipsImages3[3]));
			mo3.setEnabled(false);
		}
		mo3.setBounds(590, 60, 180, 180);
		mo3.removeActionListener(e);
		mo3.addActionListener(e);

		// ¶±ººÀÌÆÇ
		if (tableLv[0] == 1) {
			mo4.setIcon(new ImageIcon(tableImages[0])); // ÆÇ·¾2 ·¾2ÀÌ¹ÌÁö
		} else if (tableLv[0] == 2) {
			mo4.setIcon(new ImageIcon(tableImages[1])); // ÆÇ·¾3 ·¾3ÀÌ¹ÌÁö
		} else if (tableLv[0] == 3) {
			mo4.setIcon(new ImageIcon(tableImages[2])); // ÆÇ·¾4 ·¾4ÀÌ¹ÌÁö
		} else {
			mo4.setIcon(new ImageIcon(tableImages[3])); // ¸¸·¾ÀÌ¹ÌÁö
			mo4.setEnabled(false);
		}

		mo4.setBounds(20, 360, 180, 180);
		mo4.removeActionListener(e);
		mo4.addActionListener(e);

		// Æ¢±è ÆÇ
		mo5.setVisible(false);
		if (equipsLv[1] > 0) {
			mo5.setVisible(true);
		}
		if (tableLv[1] == 1) {
			mo5.setIcon(new ImageIcon(tableImages1[0])); // ÆÇ·¾2 ·¾2ÀÌ¹ÌÁö
		} else if (tableLv[1] == 2) {
			mo5.setIcon(new ImageIcon(tableImages1[1])); // ÆÇ·¾3 ·¾3ÀÌ¹ÌÁö
		} else if (tableLv[1] == 3) {
			mo5.setIcon(new ImageIcon(tableImages1[2])); // ÆÇ·¾4 ·¾4ÀÌ¹ÌÁö
		} else {
			mo5.setIcon(new ImageIcon(tableImages1[3])); // ¸¸·¾ÀÌ¹ÌÁö
			mo5.setEnabled(false);
		}

		mo5.setBounds(210, 360, 180, 180);
		mo5.removeActionListener(e);
		mo5.addActionListener(e);

		// ¿Àµ­ ÆÇ
		mo6.setVisible(false);
		if (equipsLv[2] > 0) {
			mo6.setVisible(true);
		}
		if (tableLv[2] == 1) {
			mo6.setIcon(new ImageIcon(tableImages2[0])); // ÆÇ·¾2 ·¾2ÀÌ¹ÌÁö
		} else if (tableLv[2] == 2) {
			mo6.setIcon(new ImageIcon(tableImages2[1])); // ÆÇ·¾3 ·¾3ÀÌ¹ÌÁö
		} else if (tableLv[2] == 3) {
			mo6.setIcon(new ImageIcon(tableImages2[2])); // ÆÇ·¾4 ·¾4ÀÌ¹ÌÁö
		} else {
			mo6.setIcon(new ImageIcon(tableImages2[3])); // ¸¸·¾ÀÌ¹ÌÁö
			mo6.setEnabled(false);
		}

		mo6.setBounds(400, 360, 180, 180);
		mo6.removeActionListener(e);
		mo6.addActionListener(e);

		// ¶ó¸é ÆÇ
		mo7.setVisible(false);
		if (equipsLv[3] > 0) {
			mo7.setVisible(true);
		}
		if (tableLv[3] == 1) {
			mo7.setIcon(new ImageIcon(tableImages3[0])); // ÆÇ·¾2 ·¾2ÀÌ¹ÌÁö
		} else if (tableLv[3] == 2) {
			mo7.setIcon(new ImageIcon(tableImages3[1])); // ÆÇ·¾3 ·¾3ÀÌ¹ÌÁö
		} else if (tableLv[3] == 3) {
			mo7.setIcon(new ImageIcon(tableImages3[2])); // ÆÇ·¾4 ·¾4ÀÌ¹ÌÁö
		} else {
			mo7.setIcon(new ImageIcon(tableImages3[3])); // ¸¸·¾ÀÌ¹ÌÁö
			mo7.setEnabled(false);
		}

		mo7.setBounds(590, 360, 180, 180);
		mo7.removeActionListener(e);
		mo7.addActionListener(e);

		mPanel.add(mo);
		mPanel.add(mo1);
		mPanel.add(mo2);
		mPanel.add(mo3);
		mPanel.add(mo4);
		mPanel.add(mo5);
		mPanel.add(mo6);
		mPanel.add(mo7);

		// ¶óº§
		// ¶±ººÀÌ ¶óº§
		JLabel label = new JLabel();
		label.setText("¶±ººÀÌ ¾÷±×·¹ÀÌµå");
		label.setBounds(50, 160, 200, 200);

		JLabel tbkwon1 = new JLabel();
		if (equipsLv[0] == 1) {
			tbkwon1.setText("50,000¿ø");
		} else {
			tbkwon1.setVisible(false);
		}
		tbkwon1.setBounds(80, 180, 200, 200);

		JLabel tbkwon2 = new JLabel();
		if (equipsLv[0] == 2) {
			tbkwon2.setText("100,000¿ø");
		} else {
			tbkwon2.setVisible(false);
		}
		tbkwon2.setBounds(80, 180, 200, 200);

		mPanel.add(label);
		mPanel.add(tbkwon1);
		mPanel.add(tbkwon2);

		// Æ¢±è ¶óº§
		JLabel label1 = new JLabel();
		label1.setVisible(false);
		if (equipsLv[0] >= 1) {
			label1.setVisible(true);

			label1.setText("Æ¢±è ¾÷±×·¹ÀÌµå");
			label1.setBounds(250, 160, 200, 200);

			JLabel tkwon = new JLabel();
			if (equipsLv[1] == 0) {
				tkwon.setText("25,000¿ø");
			} else {
				tkwon.setVisible(false);
			}
			tkwon.setBounds(270, 180, 200, 200);

			JLabel tkwon1 = new JLabel();
			if (equipsLv[1] == 1) {
				tkwon1.setText("50,000¿ø");
			} else {
				tkwon1.setVisible(false);
			}
			tkwon1.setBounds(270, 180, 200, 200);

			JLabel tkwon2 = new JLabel();
			if (equipsLv[1] == 2) {
				tkwon2.setText("100,000¿ø");
			} else {
				tkwon2.setVisible(false);
			}
			tkwon2.setBounds(270, 180, 200, 200);

			mPanel.add(label1);
			mPanel.add(tkwon);
			mPanel.add(tkwon1);
			mPanel.add(tkwon2);
		}

		// ¿Àµ­ ¶óº§
		JLabel label2 = new JLabel();
		label2.setVisible(false);
		if (equipsLv[1] > 0) {
			label2.setVisible(true);

			label2.setText("¿Àµ­ ¾÷±×·¹ÀÌµå");
			label2.setBounds(450, 160, 200, 200);

			JLabel odwon = new JLabel();
			if (equipsLv[2] == 0) {
				odwon.setText("25,000¿ø");
			} else {
				odwon.setVisible(false);
			}
			odwon.setBounds(470, 180, 200, 200);

			JLabel odwon1 = new JLabel();
			if (equipsLv[2] == 1) {
				odwon1.setText("50,000¿ø");
			} else {
				odwon1.setVisible(false);
			}
			odwon1.setBounds(470, 180, 200, 200);

			JLabel odwon2 = new JLabel();
			if (equipsLv[2] == 2) {
				odwon2.setText("100,000¿ø");
			} else {
				odwon2.setVisible(false);
			}
			odwon2.setBounds(470, 180, 200, 200);

			mPanel.add(label2);
			mPanel.add(odwon);
			mPanel.add(odwon1);
			mPanel.add(odwon2);
		}

		// ¶ó¸é ¶óº§
		JLabel label3 = new JLabel();
		label3.setVisible(false);
		if (equipsLv[2] > 0) {
			label3.setVisible(true);

			label3.setText("¶ó¸é ¾÷±×·¹ÀÌµå");
			label3.setBounds(630, 160, 200, 200);

			JLabel rmwon = new JLabel();
			if (equipsLv[3] == 0) {
				rmwon.setText("25,000¿ø");
			} else {
				rmwon.setVisible(false);
			}
			rmwon.setBounds(650, 180, 200, 200);

			JLabel rmwon1 = new JLabel();
			if (equipsLv[3] == 1) {
				rmwon1.setText("50,000¿ø");
			} else {
				rmwon1.setVisible(false);
			}
			rmwon1.setBounds(650, 180, 200, 200);

			JLabel rmwon2 = new JLabel();
			if (equipsLv[3] == 2) {
				rmwon2.setText("100,000¿ø");
			} else {
				rmwon2.setVisible(false);
			}
			rmwon2.setBounds(650, 180, 200, 200);

			mPanel.add(label3);
			mPanel.add(rmwon);
			mPanel.add(rmwon1);
			mPanel.add(rmwon2);
		}

		// ¶±ººÀÌÆÇ ¶óº§
		JLabel label4 = new JLabel();
		label4.setText("¶±ººÀÌÆÇ ¾÷±×·¹ÀÌµå");
		label4.setBounds(50, 460, 200, 200);

		JLabel tbkupwon1 = new JLabel();
		if (tableLv[0] == 1) {
			tbkupwon1.setText("30,000¿ø");
		} else {
			tbkupwon1.setVisible(false);
		}
		tbkupwon1.setBounds(80, 480, 200, 200);

		JLabel tbkupwon2 = new JLabel();
		if (tableLv[0] == 2) {
			tbkupwon2.setText("60,000¿ø");
		} else {
			tbkupwon2.setVisible(false);
		}
		tbkupwon2.setBounds(80, 480, 200, 200);

		JLabel tbkupwon3 = new JLabel();
		if (tableLv[0] == 3) {
			tbkupwon3.setText("100,000¿ø");
		} else {
			tbkupwon3.setVisible(false);
		}
		tbkupwon3.setBounds(80, 480, 200, 200);

		mPanel.add(label4);
		mPanel.add(tbkupwon1);
		mPanel.add(tbkupwon2);
		mPanel.add(tbkupwon3);

		// Æ¢±èÆÇ ¶óº§
		JLabel label5 = new JLabel();
		label5.setVisible(false);
		if (equipsLv[1] > 0) {
			label5.setVisible(true);
			label5.setText("Æ¢±èÆÇ ¾÷±×·¹ÀÌµå");
			label5.setBounds(250, 460, 200, 200);

			JLabel tkupwon1 = new JLabel();
			if (tableLv[1] == 1) {
				tkupwon1.setText("30,000¿ø");
			} else {
				tkupwon1.setVisible(false);
			}
			tkupwon1.setBounds(270, 480, 200, 200);

			JLabel tkupwon2 = new JLabel();
			if (tableLv[1] == 2) {
				tkupwon2.setText("60,000¿ø");
			} else {
				tkupwon2.setVisible(false);
			}
			tkupwon2.setBounds(270, 480, 200, 200);

			JLabel tkupwon3 = new JLabel();
			if (tableLv[1] == 3) {
				tkupwon3.setText("100,000¿ø");
			} else {
				tkupwon3.setVisible(false);
			}
			tkupwon3.setBounds(270, 480, 200, 200);

			mPanel.add(label5);
			mPanel.add(tkupwon1);
			mPanel.add(tkupwon2);
			mPanel.add(tkupwon3);

		}

		// ¿Àµ­ÆÇ ¶óº§
		JLabel label6 = new JLabel();
		label6.setVisible(false);
		if (equipsLv[2] > 0) {
			label6.setVisible(true);
			label6.setText("¿Àµ­ÆÇ ¾÷±×·¹ÀÌµå");
			label6.setBounds(440, 460, 200, 200);

			JLabel odupwon1 = new JLabel();
			if (tableLv[2] == 1) {
				odupwon1.setText("30,000¿ø");
			} else {
				odupwon1.setVisible(false);
			}
			odupwon1.setBounds(460, 480, 200, 200);

			JLabel odupwon2 = new JLabel();
			if (tableLv[2] == 2) {
				odupwon2.setText("60,000¿ø");
			} else {
				odupwon2.setVisible(false);
			}
			odupwon2.setBounds(460, 480, 200, 200);

			JLabel odupwon3 = new JLabel();
			if (tableLv[2] == 3) {
				odupwon3.setText("100,000¿ø");
			} else {
				odupwon3.setVisible(false);
			}
			odupwon3.setBounds(460, 480, 200, 200);

			mPanel.add(label6);
			mPanel.add(odupwon1);
			mPanel.add(odupwon2);
			mPanel.add(odupwon3);

		}

		// ¶ó¸éÆÇ ¶óº§
		JLabel label7 = new JLabel();
		label7.setVisible(false);
		if (equipsLv[3] > 0) {
			label7.setVisible(true);
			label7.setText("¶ó¸éÆÇ ¾÷±×·¹ÀÌµå");
			label7.setBounds(630, 460, 200, 200);

			JLabel rmupwon1 = new JLabel();
			if (tableLv[3] == 1) {
				rmupwon1.setText("30,000¿ø");
			} else {
				rmupwon1.setVisible(false);
			}
			rmupwon1.setBounds(650, 480, 200, 200);

			JLabel rmupwon2 = new JLabel();
			if (tableLv[3] == 2) {
				rmupwon2.setText("60,000¿ø");
			} else {
				rmupwon2.setVisible(false);
			}
			rmupwon2.setBounds(650, 480, 200, 200);

			JLabel rmupwon3 = new JLabel();
			if (tableLv[3] == 3) {
				rmupwon3.setText("100,000¿ø");
			} else {
				rmupwon3.setVisible(false);
			}
			rmupwon3.setBounds(650, 480, 200, 200);

			mPanel.add(label7);
			mPanel.add(rmupwon1);
			mPanel.add(rmupwon2);
			mPanel.add(rmupwon3);

		}

	}// setting

	class EquipSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// ¶±ººÀÌ±â±¸ ¾×¼Ç¸®½º³Ê
			if (e.getActionCommand().equals("¶±ººÀÌ±â±¸")) {
				System.out.println("tbk");
				if (equipsLv[0] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (equipsLv[0] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else {
					System.out.println("·¹º§ ¸¸¶¥~");
				}
			}

			// Æ¢±è±â±¸ ¾×¼Ç¸®½º³Ê
			if (e.getActionCommand().equals("Æ¢±è±â±¸")) {
				System.out.println("tk");

				if (equipsLv[1] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (equipsLv[1] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (equipsLv[1] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else {
					System.out.println("·¹º§ ¸¸¶¥~");
				}
			}

			// ¿Àµ­±â±¸ ¾×¼Ç¸®½º³Ê
			if (e.getActionCommand().equals("¿Àµ­±â±¸")) {
				System.out.println("od");

				if (equipsLv[2] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (equipsLv[2] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (equipsLv[2] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else {
					System.out.println("·¹º§ ¸¸¶¥~");
				}
			}

			// ¶ó¸é±â±¸ ¾×¼Ç¸®½º³Ê
			if (e.getActionCommand().equals("¶ó¸é±â±¸")) {
				System.out.println("rm");

				if (equipsLv[3] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (equipsLv[3] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (equipsLv[3] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else {
					System.out.println("·¹º§ ¸¸¶¥~");
				}
			}

			// ¶±ººÀÌÆÇ ¾×¼Ç¸®½º³Ê
			if (e.getActionCommand().equals("¶±ººÀÌÆÇ ¾÷±Û")) {
				System.out.println("tbkup");
				if (tableLv[0] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}

				} else if (tableLv[0] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (tableLv[0] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else {
					System.out.println("·¹º§ ¸¸¶¥~");

				}
			}

			// Æ¢±èÆÇ ¾×¼Ç¸®½º³Ê
			if (e.getActionCommand().equals("Æ¢±èÆÇ ¾÷±Û")) {
				System.out.println("tkup");
				if (tableLv[1] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}

				} else if (tableLv[1] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (tableLv[1] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else {
					System.out.println("·¹º§ ¸¸¶¥~");

				}
			}

			// ¿Àµ­ÆÇ ¾×¼Ç¸®½º³Ê
			if (e.getActionCommand().equals("¿Àµ­ÆÇ ¾÷±Û")) {
				System.out.println("odup");
				if (tableLv[2] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (tableLv[2] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (tableLv[2] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else {
					System.out.println("·¹º§ ¸¸¶¥~");
				}
			}

			// ¶ó¸éÆÇ ¾×¼Ç¸®½º³Ê
			if (e.getActionCommand().equals("¶ó¸éÆÇ ¾÷±Û")) {
				System.out.println("rmup");
				if (tableLv[3] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (tableLv[3] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else if (tableLv[3] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(null, "±× µ·À¸·Ð ¾î¸²¾øÁö~");
					}
				} else {
					System.out.println("·¹º§ ¸¸¶¥~");
				}
			}
			refresh();
			if (e.getActionCommand().equals("µ¹¾Æ°¡±â")) {
				marketMusic.close();
				marketMusic.interrupt();
				ChangePanel.changePanel(mf, mPanel, new StageView(mf, m));
			};
		}

		public void refresh() {
			m.setEquipsLv(equipsLv);
			m.setTableLv(tableLv);
			MemberDao mDao = new MemberDao();
			mDao.saveMember(m);
			MarketPanel temp;
			new ChangePanel().changePanel(mf, mPanel, temp = new MarketPanel(mf, m));
			temp.setting(temp);
			mPanel = temp;
		}
	}
	public Music getMarketMusic() {
		return marketMusic;
	}
}// class