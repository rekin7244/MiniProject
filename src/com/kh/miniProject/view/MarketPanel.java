
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

	// 받아올 필드
	MainFrame mf;
	Member m;

	// 사용 필드
  private Music marketMusic;
	// 기구 업글비용
	private int level1 = 25000;
	private int level2 = 50000;
	private int level3 = 100000;
	// 판 업글비용
	private int plevel2 = 15000;
	private int plevel3 = 40000;
	private int plevel4 = 90000;

	// 기구
	JButton mo = new JButton("떡볶이기구");
	JButton mo1 = new JButton("튀김기구");
	JButton mo2 = new JButton("오뎅기구");
	JButton mo3 = new JButton("라면기구");

	// 판
	JButton mo4 = new JButton("떡볶이판 업글");
	JButton mo5 = new JButton("튀김판 업글");
	JButton mo6 = new JButton("오뎅판 업글");
	JButton mo7 = new JButton("라면판 업글");

	MarketPanel mPanel;
	JButton gold;
	private int[] equipsLv;
	private int[] tableLv;
	private EquipSetting e;

	// 떡볶이 기구
	private Image[] equipsImages = { new ImageIcon("images/떡2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/떡3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// 튀김 기구
	private Image[] equipsImages1 = { new ImageIcon("images/튀1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/튀2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/튀3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// 오뎅 기구
	private Image[] equipsImages2 = { new ImageIcon("images/오1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/오2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/오3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// 라면 기구
	private Image[] equipsImages3 = { new ImageIcon("images/라1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/라2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/라3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// 떡볶이 판
	private Image[] tableImages = { new ImageIcon("images/떡2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/떡3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/떡4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0), };

	// 튀김 판
	private Image[] tableImages1 = { new ImageIcon("images/튀2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/튀3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/튀4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// 오뎅 판
	private Image[] tableImages2 = { new ImageIcon("images/오2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/오3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/오4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// 라면 판
	private Image[] tableImages3 = { new ImageIcon("images/라2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/라3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/라4.jpg").getImage().getScaledInstance(200, 200, 0),
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
		this.setBackground(Color.PINK);

		// 골드 출력
		gold = new JButton("골드");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0, 0, 200, 30);

		// 돌아가기
		JButton returnBtn = new JButton("돌아가기");
		returnBtn.setBounds(660, 620, 140, 30);
		this.add(returnBtn);
		returnBtn.addActionListener(e);

		mPanel = this;
	}// marketpanel
	
	public void marketMusic() {
		marketMusic = new Music("intro3.mp3",false);
		marketMusic.start();
	}

	// 상점 버튼 및 기능 구현
	public void setting(MarketPanel mPanel) {
		// 골드 출력
		gold.setText("소지금: " + m.getGold() + "G");
		mPanel.add(gold);

		// 떡볶이 기구
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

		// 오뎅 기구
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

		// 라면 기구
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

		// 떡볶이판
		if (tableLv[0] == 1) {
			mo4.setIcon(new ImageIcon(tableImages[0])); // 판렙2 렙2이미지
		} else if (tableLv[0] == 2) {
			mo4.setIcon(new ImageIcon(tableImages[1])); // 판렙3 렙3이미지
		} else if (tableLv[0] == 3) {
			mo4.setIcon(new ImageIcon(tableImages[2])); // 판렙4 렙4이미지
		} else {
			mo4.setIcon(new ImageIcon(tableImages[3])); // 만렙이미지
			mo4.setEnabled(false);
		}

		mo4.setBounds(20, 360, 180, 180);
		mo4.removeActionListener(e);
		mo4.addActionListener(e);

		// 튀김 판
		mo5.setVisible(false);
		if (equipsLv[1] > 0) {
			mo5.setVisible(true);
		}
		if (tableLv[1] == 1) {
			mo5.setIcon(new ImageIcon(tableImages1[0])); // 판렙2 렙2이미지
		} else if (tableLv[1] == 2) {
			mo5.setIcon(new ImageIcon(tableImages1[1])); // 판렙3 렙3이미지
		} else if (tableLv[1] == 3) {
			mo5.setIcon(new ImageIcon(tableImages1[2])); // 판렙4 렙4이미지
		} else {
			mo5.setIcon(new ImageIcon(tableImages1[3])); // 만렙이미지
			mo5.setEnabled(false);
		}

		mo5.setBounds(210, 360, 180, 180);
		mo5.removeActionListener(e);
		mo5.addActionListener(e);

		// 오뎅 판
		mo6.setVisible(false);
		if (equipsLv[2] > 0) {
			mo6.setVisible(true);
		}
		if (tableLv[2] == 1) {
			mo6.setIcon(new ImageIcon(tableImages2[0])); // 판렙2 렙2이미지
		} else if (tableLv[2] == 2) {
			mo6.setIcon(new ImageIcon(tableImages2[1])); // 판렙3 렙3이미지
		} else if (tableLv[2] == 3) {
			mo6.setIcon(new ImageIcon(tableImages2[2])); // 판렙4 렙4이미지
		} else {
			mo6.setIcon(new ImageIcon(tableImages2[3])); // 만렙이미지
			mo6.setEnabled(false);
		}

		mo6.setBounds(400, 360, 180, 180);
		mo6.removeActionListener(e);
		mo6.addActionListener(e);

		// 라면 판
		mo7.setVisible(false);
		if (equipsLv[3] > 0) {
			mo7.setVisible(true);
		}
		if (tableLv[3] == 1) {
			mo7.setIcon(new ImageIcon(tableImages3[0])); // 판렙2 렙2이미지
		} else if (tableLv[3] == 2) {
			mo7.setIcon(new ImageIcon(tableImages3[1])); // 판렙3 렙3이미지
		} else if (tableLv[3] == 3) {
			mo7.setIcon(new ImageIcon(tableImages3[2])); // 판렙4 렙4이미지
		} else {
			mo7.setIcon(new ImageIcon(tableImages3[3])); // 만렙이미지
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

		// 라벨
		// 떡볶이 라벨
		JLabel label = new JLabel();
		label.setText("떡볶이 업그레이드");
		label.setBounds(50, 160, 200, 200);

		JLabel tbkwon1 = new JLabel();
		JLabel tbkwon11=new JLabel();
		if (equipsLv[0] == 1) {
			tbkwon1.setText("50,000원");
			tbkwon11.setText("떡볶이 갯수 : 2개 / 쿨타임 7초");
		} else {
			tbkwon1.setVisible(false);
			tbkwon11.setVisible(false);
		}
		tbkwon1.setBounds(80, 200, 200, 200);
		tbkwon11.setBounds(30,180,200,200);

		JLabel tbkwon2 = new JLabel();
		JLabel tbkwon22=new JLabel();
		if (equipsLv[0] == 2) {
			tbkwon2.setText("100,000원");
			tbkwon22.setText("떡볶이 갯수 : 3개 / 쿨타임 6초");
		} else {
			tbkwon2.setVisible(false);
			tbkwon22.setVisible(false);
		}
		tbkwon2.setBounds(80, 200, 200, 200);
		tbkwon22.setBounds(30,180,200,200);

		mPanel.add(label);
		mPanel.add(tbkwon1);
		mPanel.add(tbkwon11);
		mPanel.add(tbkwon2);
		mPanel.add(tbkwon22);

		// 튀김 라벨
		JLabel label1 = new JLabel();
		label1.setVisible(false);
		if (equipsLv[0] >= 1) {
			label1.setVisible(true);

			label1.setText("튀김기기 구매");
			label1.setBounds(250, 160, 200, 200);			

			JLabel tkwon = new JLabel();
			JLabel tkwonn=new JLabel();
			if (equipsLv[1] == 0) {
				tkwon.setText("25,000원");
				tkwonn.setText("튀김 갯수 : 1개 / 쿨타임 : 7초");
			} else {
				tkwon.setVisible(false);
				tkwonn.setVisible(false);
			}
			tkwon.setBounds(270, 200, 200, 200);
			tkwonn.setBounds(220,180,200,200);

			JLabel tkwon1 = new JLabel();
			JLabel tkwon11=new JLabel();
			if (equipsLv[1] == 1) {
				tkwon1.setText("50,000원");
				tkwon11.setText("튀김 갯수 : 2개 / 쿨타임 : 8초");
			} else {
				tkwon1.setVisible(false);
				tkwon11.setVisible(false);
			}
			tkwon1.setBounds(270, 200, 200, 200);
			tkwon11.setBounds(220,180,200,200);

			JLabel tkwon2 = new JLabel();
			JLabel tkwon22=new JLabel();
			if (equipsLv[1] == 2) {
				tkwon2.setText("100,000원");
				tkwon22.setText("튀김 갯수 : 3개 / 쿨타임 : 8초");
			} else {
				tkwon2.setVisible(false);
				tkwon22.setVisible(false);
			}
			tkwon2.setBounds(270, 200, 200, 200);
			tkwon22.setBounds(220,180,200,200);

			mPanel.add(label1);
			mPanel.add(tkwon);
			mPanel.add(tkwonn);
			mPanel.add(tkwon1);
			mPanel.add(tkwon11);
			mPanel.add(tkwon2);
			mPanel.add(tkwon22);
		}

		// 오뎅 라벨
		JLabel label2 = new JLabel();
		label2.setVisible(false);
		if (equipsLv[1] > 0) {
			label2.setVisible(true);

			label2.setText("오뎅기기 구매");
			label2.setBounds(450, 160, 200, 200);

			JLabel odwon = new JLabel();
			JLabel odwonn=new JLabel();
			if (equipsLv[2] == 0) {
				odwon.setText("25,000원");
				odwonn.setText("오뎅 갯수 : 1개 / 쿨타임 : 8초");
			} else {
				odwon.setVisible(false);
				odwonn.setVisible(false);
			}
			odwon.setBounds(470, 200, 200, 200);
			odwonn.setBounds(410,180,200,200);

			JLabel odwon1 = new JLabel();
			JLabel odwon11=new JLabel();
			if (equipsLv[2] == 1) {
				odwon1.setText("50,000원");
				odwon11.setText("오뎅 갯수 : 2개 / 쿨타임 : 8초");
			} else {
				odwon1.setVisible(false);
				odwon11.setVisible(false);
			}
			odwon1.setBounds(470, 200, 200, 200);
			odwon11.setBounds(410,180,200,200);

			JLabel odwon2 = new JLabel();
			JLabel odwon22=new JLabel();
			if (equipsLv[2] == 2) {
				odwon2.setText("100,000원");
				odwon22.setText("오뎅 갯수 : 3개 / 쿨타임 : 8초");
			} else {
				odwon2.setVisible(false);
				odwon22.setVisible(false);
			}
			odwon2.setBounds(470, 200, 200, 200);
			odwon22.setBounds(410,180,200,200);

			mPanel.add(label2);
			mPanel.add(odwon);
			mPanel.add(odwonn);
			mPanel.add(odwon1);
			mPanel.add(odwon11);
			mPanel.add(odwon2);
			mPanel.add(odwon22);
		}

		// 라면 라벨
		JLabel label3 = new JLabel();
		label3.setVisible(false);
		if (equipsLv[2] > 0) {
			label3.setVisible(true);

			label3.setText("라면기기 구매");
			label3.setBounds(630, 160, 200, 200);

			JLabel rmwon = new JLabel();
			JLabel rmwonn=new JLabel();
			if (equipsLv[3] == 0) {
				rmwon.setText("25,000원");
				rmwonn.setText("라면 갯수 : 1개 / 쿨타임 : 9초");
			} else {
				rmwon.setVisible(false);
				rmwonn.setVisible(false);
			}
			rmwon.setBounds(650, 200, 200, 200);
			rmwonn.setBounds(600,180,200,200);

			JLabel rmwon1 = new JLabel();
			JLabel rmwon11=new JLabel();
			if (equipsLv[3] == 1) {
				rmwon1.setText("50,000원");
				rmwon11.setText("라면 갯수 : 2개 / 쿨타임 : 10초");
			} else {
				rmwon1.setVisible(false);
				rmwon11.setVisible(false);
			}
			rmwon1.setBounds(650, 200, 200, 200);
			rmwon11.setBounds(600,180,200,200);

			JLabel rmwon2 = new JLabel();
			JLabel rmwon22=new JLabel();			
			if (equipsLv[3] == 2) {
				rmwon2.setText("100,000원");
				rmwon22.setText("라면 갯수 : 3개 / 쿨타임 : 10초");
			} else {
				rmwon2.setVisible(false);
				rmwon22.setVisible(false);
			}
			rmwon2.setBounds(650, 200, 200, 200);
			rmwon22.setBounds(600,180,200,200);

			mPanel.add(label3);
			mPanel.add(rmwon);
			mPanel.add(rmwonn);
			mPanel.add(rmwon1);
			mPanel.add(rmwon11);
			mPanel.add(rmwon2);
			mPanel.add(rmwon22);
		}

		// 떡볶이판 라벨
		JLabel label4 = new JLabel();
		label4.setText("떡볶이판 업그레이드");
		label4.setBounds(50, 460, 200, 200);

		JLabel tbkupwon1 = new JLabel();
		JLabel tbkupwon11=new JLabel();
		if (tableLv[0] == 1) {
			tbkupwon1.setText("15,000원");
			tbkupwon11.setText("떡볶이 보관갯수 : 2개");
		} else {
			tbkupwon1.setVisible(false);
			tbkupwon11.setVisible(false);
		}
		tbkupwon1.setBounds(80, 500, 200, 200);
		tbkupwon11.setBounds(50,480,200,200);

		JLabel tbkupwon2 = new JLabel();
		JLabel tbkupwon22=new JLabel();
		if (tableLv[0] == 2) {
			tbkupwon2.setText("40,000원");
			tbkupwon22.setText("떡볶이 보관갯수 : 3개");
		} else {
			tbkupwon2.setVisible(false);
			tbkupwon22.setVisible(false);
		}
		tbkupwon2.setBounds(80, 500, 200, 200);
		tbkupwon22.setBounds(50,480,200,200);

		JLabel tbkupwon3 = new JLabel();
		JLabel tbkupwon33=new JLabel();
		if (tableLv[0] == 3) {
			tbkupwon3.setText("90,000원");
			tbkupwon33.setText("떡볶이 보관갯수 : 4개");
		} else {
			tbkupwon3.setVisible(false);
			tbkupwon33.setVisible(false);
		}
		tbkupwon3.setBounds(80, 500, 200, 200);
		tbkupwon33.setBounds(50,480,200,200);

		mPanel.add(label4);
		mPanel.add(tbkupwon1);
		mPanel.add(tbkupwon11);
		mPanel.add(tbkupwon2);
		mPanel.add(tbkupwon22);
		mPanel.add(tbkupwon3);
		mPanel.add(tbkupwon33);

		// 튀김판 라벨
		JLabel label5 = new JLabel();		
		label5.setVisible(false);
		if (equipsLv[1] > 0) {
			label5.setVisible(true);
			label5.setText("튀김판 업그레이드");
			label5.setBounds(250, 460, 200, 200);

			JLabel tkupwon1 = new JLabel();
			JLabel tkupwon11=new JLabel();
			if (tableLv[1] == 1) {
				tkupwon1.setText("15,000원");
				tkupwon11.setText("튀김 보관갯수 : 2개");
			} else {
				tkupwon1.setVisible(false);
				tkupwon11.setVisible(false);
			}
			tkupwon1.setBounds(270, 500, 200, 200);
			tkupwon11.setBounds(250,480,200,200);

			JLabel tkupwon2 = new JLabel();
			JLabel tkupwon22=new JLabel();
			if (tableLv[1] == 2) {
				tkupwon2.setText("40,000원");
				tkupwon22.setText("튀김 보관갯수 : 3개");
			} else {
				tkupwon2.setVisible(false);
				tkupwon22.setVisible(false);
			}
			tkupwon2.setBounds(270, 500, 200, 200);
			tkupwon22.setBounds(250,480,200,200);

			JLabel tkupwon3 = new JLabel();
			JLabel tkupwon33=new JLabel();
			if (tableLv[1] == 3) {
				tkupwon3.setText("90,000원");
				tkupwon33.setText("튀김 보관갯수 : 4개");
			} else {
				tkupwon3.setVisible(false);
				tkupwon33.setVisible(false);
			}
			tkupwon3.setBounds(270, 500, 200, 200);
			tkupwon33.setBounds(250,480,200,200);

			mPanel.add(label5);
			mPanel.add(tkupwon1);
			mPanel.add(tkupwon11);
			mPanel.add(tkupwon2);
			mPanel.add(tkupwon22);
			mPanel.add(tkupwon3);
			mPanel.add(tkupwon33);

		}

		// 오뎅판 라벨
		JLabel label6 = new JLabel();
		label6.setVisible(false);
		if (equipsLv[2] > 0) {
			label6.setVisible(true);
			label6.setText("오뎅판 업그레이드");
			label6.setBounds(440, 460, 200, 200);

			JLabel odupwon1 = new JLabel();
			JLabel odupwon11=new JLabel();
			if (tableLv[2] == 1) {
				odupwon1.setText("15,000원");
				odupwon11.setText("오뎅 보관갯수 : 2개");
			} else {
				odupwon1.setVisible(false);
				odupwon11.setVisible(false);
			}
			odupwon1.setBounds(460, 500, 200, 200);
			odupwon11.setBounds(440, 480, 200, 200);

			JLabel odupwon2 = new JLabel();
			JLabel odupwon22=new JLabel();
			if (tableLv[2] == 2) {
				odupwon2.setText("40,000원");
				odupwon22.setText("오뎅 보관갯수 : 3개");
			} else {
				odupwon2.setVisible(false);
				odupwon22.setVisible(false);
			}
			odupwon2.setBounds(460, 500, 200, 200);
			odupwon22.setBounds(440, 480, 200, 200);

			JLabel odupwon3 = new JLabel();
			JLabel odupwon33=new JLabel();
			if (tableLv[2] == 3) {
				odupwon3.setText("90,000원");
				odupwon33.setText("오뎅 보관갯수 : 4개");
			} else {
				odupwon3.setVisible(false);
				odupwon33.setVisible(false);
			}
			odupwon3.setBounds(460, 500, 200, 200);
			odupwon33.setBounds(440, 480, 200, 200);

			mPanel.add(label6);
			mPanel.add(odupwon1);
			mPanel.add(odupwon11);
			mPanel.add(odupwon2);
			mPanel.add(odupwon22);
			mPanel.add(odupwon3);
			mPanel.add(odupwon33);

		}

		// 라면판 라벨
		JLabel label7 = new JLabel();
		label7.setVisible(false);
		if (equipsLv[3] > 0) {
			label7.setVisible(true);
			label7.setText("라면판 업그레이드");
			label7.setBounds(630, 460, 200, 200);

			JLabel rmupwon1 = new JLabel();
			JLabel rmupwon11=new JLabel();
			if (tableLv[3] == 1) {
				rmupwon1.setText("15,000원");
				rmupwon11.setText("라면 보관갯수 : 2개");
			} else {
				rmupwon1.setVisible(false);
				rmupwon11.setVisible(false);
			}
			rmupwon1.setBounds(650, 500, 200, 200);
			rmupwon11.setBounds(630, 480, 200, 200);

			JLabel rmupwon2 = new JLabel();
			JLabel rmupwon22=new JLabel();
			if (tableLv[3] == 2) {
				rmupwon2.setText("40,000원");
				rmupwon22.setText("라면 보관갯수 : 3개");
			} else {
				rmupwon2.setVisible(false);
				rmupwon22.setVisible(false);
			}
			rmupwon2.setBounds(650, 500, 200, 200);
			rmupwon22.setBounds(630, 480, 200, 200);

			JLabel rmupwon3 = new JLabel();
			JLabel rmupwon33=new JLabel();
			if (tableLv[3] == 3) {
				rmupwon3.setText("90,000원");
				rmupwon33.setText("라면 보관갯수 : 4개");
			} else {
				rmupwon3.setVisible(false);
				rmupwon33.setVisible(false);
			}
			rmupwon3.setBounds(650, 500, 200, 200);
			rmupwon33.setBounds(630, 480, 200, 200);

			mPanel.add(label7);
			mPanel.add(rmupwon1);
			mPanel.add(rmupwon11);
			mPanel.add(rmupwon2);
			mPanel.add(rmupwon22);
			mPanel.add(rmupwon3);
			mPanel.add(rmupwon33);

		}

	}// setting

	class EquipSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// 떡볶이기구 액션리스너
			if (e.getActionCommand().equals("떡볶이기구")) {
				System.out.println("tbk");
				
				String[] buttons= {"당연하지","한번 더 고민"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"피같은 돈으로 이걸 산다고??","부글부글분식",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {				
				
				if (equipsLv[0] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[0] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}else {
				System.out.println("취소");
			}
			}
			// 튀김기구 액션리스너
			if (e.getActionCommand().equals("튀김기구")) {
				System.out.println("tk");
				
				String[] buttons= {"당연하지","한번 더 고민"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"피같은 돈으로 이걸 산다고??","부글부글분식",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {

				if (equipsLv[1] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[1] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[1] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}
			}
			// 오뎅기구 액션리스너
			if (e.getActionCommand().equals("오뎅기구")) {
				System.out.println("od");
				
				String[] buttons= {"당연하지","한번 더 고민"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"피같은 돈으로 이걸 산다고??","부글부글분식",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {

				if (equipsLv[2] == 0) {					
					if (m.getGold() >= level1) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[2] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[2] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}
			}

			// 라면기구 액션리스너
			if (e.getActionCommand().equals("라면기구")) {
				System.out.println("rm");
				
				String[] buttons= {"당연하지","한번 더 고민"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"피같은 돈으로 이걸 산다고??","부글부글분식",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {

				if (equipsLv[3] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[3] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[3] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}
			}

			// 떡볶이판 액션리스너
			if (e.getActionCommand().equals("떡볶이판 업글")) {
				System.out.println("tbkup");
				
				String[] buttons= {"당연하지","한번 더 고민"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"피같은 돈으로 이걸 산다고??","부글부글분식",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {
				
				if (tableLv[0] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}

				} else if (tableLv[0] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[0] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("레벨 만땅~");

				}
			}
			}

			// 튀김판 액션리스너
			if (e.getActionCommand().equals("튀김판 업글")) {
				System.out.println("tkup");
				
				String[] buttons= {"당연하지","한번 더 고민"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"피같은 돈으로 이걸 산다고??","부글부글분식",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {
				
				if (tableLv[1] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}

				} else if (tableLv[1] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[1] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("레벨 만땅~");

				}
			}
			}

			// 오뎅판 액션리스너
			if (e.getActionCommand().equals("오뎅판 업글")) {
				System.out.println("odup");
				
				String[] buttons= {"당연하지","한번 더 고민"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"피같은 돈으로 이걸 산다고??","부글부글분식",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {
				
				if (tableLv[2] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[2] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[2] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}
			}

			// 라면판 액션리스너
			if (e.getActionCommand().equals("라면판 업글")) {
				System.out.println("rmup");
				
				String[] buttons= {"당연하지","한번 더 고민"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"피같은 돈으로 이걸 산다고??","부글부글분식",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {
				
				if (tableLv[3] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[3] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[3] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(mf, "그 돈으론 어림없지~","부글부글 분식",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}
			}
			
			refresh();
			if (e.getActionCommand().equals("돌아가기")) {
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
			temp.setMarketMusic(marketMusic);
			mPanel = temp;
		}
	}
	public Music getMarketMusic() {
		return marketMusic;
	}
	public void setMarketMusic(Music music) {
		this.marketMusic = music;
	}
}// class