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
	// 업글비용
	private int level1 = 25000;
	private int level2 = 50000;
	private int level3 = 100000;

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
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// 튀김 판
	private Image[] tableImages1 = { 
			new ImageIcon("images/튀2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/튀3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// 오뎅 판
	private Image[] tableImages2 = { 
			new ImageIcon("images/오2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/오3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// 라면 판
	private Image[] tableImages3 = { 
			new ImageIcon("images/라2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/라3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

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

		// 상점 버튼 및 기능 구현
		mPanel = this;
		// setting(mPanel);
	}// marketpanel

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

		// 튀김 기구
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
			mo4.setIcon(new ImageIcon(tableImages[0]));
		} else if (tableLv[0] == 2) {
			mo4.setIcon(new ImageIcon(tableImages[1]));
		} else {
			mo4.setIcon(new ImageIcon(tableImages[2]));
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
			mo5.setIcon(new ImageIcon(tableImages1[0]));
		} else if (tableLv[1] == 2) {
			mo5.setIcon(new ImageIcon(tableImages1[1]));
		} else {
			mo5.setIcon(new ImageIcon(tableImages1[2]));
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
			mo6.setIcon(new ImageIcon(tableImages2[0]));
		} else if (tableLv[2] == 2) {
			mo6.setIcon(new ImageIcon(tableImages2[1]));
		} else {
			mo6.setIcon(new ImageIcon(tableImages2[2]));
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
			mo7.setIcon(new ImageIcon(tableImages3[0]));
		} else if (tableLv[3] == 2) {
			mo7.setIcon(new ImageIcon(tableImages3[1]));
		} else {
			mo7.setIcon(new ImageIcon(tableImages3[2]));
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
		JLabel label = new JLabel();
		label.setText("떡볶이 업그레이드");
		label.setBounds(50, 160, 200, 200);

		JLabel tbkwon1 = new JLabel();
		if (equipsLv[0] == 1) {
			tbkwon1.setText("50,000원");
		} else {
			tbkwon1.setVisible(false);
		}
		tbkwon1.setBounds(80, 180, 200, 200);

		JLabel tbkwon2 = new JLabel();
		if (equipsLv[0] == 2) {
			tbkwon2.setText("100,000원");
		} else {
			tbkwon2.setVisible(false);
		}
		tbkwon2.setBounds(80, 180, 200, 200);

		mPanel.add(label);
		mPanel.add(tbkwon1);
		mPanel.add(tbkwon2);

		JLabel label1 = new JLabel();
		label1.setText("튀김 업그레이드");
		label1.setBounds(250, 160, 200, 200);

		JLabel tkwon = new JLabel();
		if (equipsLv[1] == 0) {
			tkwon.setText("25,000원");
		} else {
			tkwon.setVisible(false);
		}
		tkwon.setBounds(270, 180, 200, 200);

		JLabel tkwon1 = new JLabel();
		if (equipsLv[1] == 1) {
			tkwon1.setText("50,000원");
		} else {
			tkwon1.setVisible(false);
		}
		tkwon1.setBounds(270, 180, 200, 200);

		JLabel tkwon2 = new JLabel();
		if (equipsLv[1] == 2) {
			tkwon2.setText("100,000원");
		} else {
			tkwon2.setVisible(false);
		}
		tkwon2.setBounds(270, 180, 200, 200);

		mPanel.add(label1);
		mPanel.add(tkwon);
		mPanel.add(tkwon1);
		mPanel.add(tkwon2);

		JLabel label2 = new JLabel();
		label2.setText("오뎅 업그레이드");
		label2.setBounds(450, 160, 200, 200);

		JLabel odwon = new JLabel();
		if (equipsLv[2] == 0) {
			odwon.setText("25,000원");
		} else {
			odwon.setVisible(false);
		}
		odwon.setBounds(470, 180, 200, 200);

		JLabel odwon1 = new JLabel();
		if (equipsLv[2] == 1) {
			odwon1.setText("50,000원");
		} else {
			odwon1.setVisible(false);
		}
		odwon1.setBounds(470, 180, 200, 200);

		JLabel odwon2 = new JLabel();
		if (equipsLv[2] == 2) {
			odwon2.setText("100,000원");
		} else {
			odwon2.setVisible(false);
		}
		odwon2.setBounds(470, 180, 200, 200);

		mPanel.add(label2);
		mPanel.add(odwon);
		mPanel.add(odwon1);
		mPanel.add(odwon2);

		JLabel label3 = new JLabel();
		label3.setText("라면 업그레이드");
		label3.setBounds(630, 160, 200, 200);

		JLabel rmwon = new JLabel();
		if (equipsLv[3] == 0) {
			rmwon.setText("25,000원");
		} else {
			rmwon.setVisible(false);
		}
		rmwon.setBounds(650, 180, 200, 200);

		JLabel rmwon1 = new JLabel();
		if (equipsLv[3] == 1) {
			rmwon1.setText("50,000원");
		} else {
			rmwon1.setVisible(false);
		}
		rmwon1.setBounds(650, 180, 200, 200);

		JLabel rmwon2 = new JLabel();
		if (equipsLv[3] == 2) {
			rmwon2.setText("100,000원");
		} else {
			rmwon2.setVisible(false);
		}
		rmwon2.setBounds(650, 180, 200, 200);

		mPanel.add(label3);
		mPanel.add(rmwon);
		mPanel.add(rmwon1);
		mPanel.add(rmwon2);

		JLabel label4 = new JLabel();
		label4.setText("떡볶이판 업그레이드");
		label4.setBounds(50, 460, 200, 200);

		JLabel tbkupwon1 = new JLabel();
		if (tableLv[0] == 1) {
			tbkupwon1.setText("50,000원");
		} else {
			tbkupwon1.setVisible(false);
		}
		tbkupwon1.setBounds(80, 480, 200, 200);

		JLabel tbkupwon2 = new JLabel();
		if (tableLv[0] == 2) {
			tbkupwon2.setText("100,000원");
		} else {
			tbkupwon2.setVisible(false);
		}
		tbkupwon2.setBounds(80, 480, 200, 200);

		mPanel.add(label4);
		mPanel.add(tbkupwon1);
		mPanel.add(tbkupwon2);

		JLabel label5 = new JLabel();
		label5.setVisible(false);
		if (equipsLv[1] > 0) {
			label5.setVisible(true);
			label5.setText("튀김판 업그레이드");
			label5.setBounds(250, 460, 200, 200);

			JLabel tkupwon1 = new JLabel();
			if (tableLv[1] == 1) {
				tkupwon1.setText("50,000원");
			} else {
				tkupwon1.setVisible(false);
			}
			tkupwon1.setBounds(270, 480, 200, 200);

			JLabel tkupwon2 = new JLabel();
			if (tableLv[1] == 2) {
				tkupwon2.setText("100,000원");
			} else {
				tkupwon2.setVisible(false);
			}
			tkupwon2.setBounds(270, 480, 200, 200);

			mPanel.add(label5);
			mPanel.add(tkupwon1);
			mPanel.add(tkupwon2);

		}

		JLabel label6 = new JLabel();
		label6.setVisible(false);
		if (equipsLv[2] > 0) {
			label6.setVisible(true);
			label6.setText("오뎅판 업그레이드");
			label6.setBounds(440, 460, 200, 200);

			JLabel odupwon1 = new JLabel();
			if (tableLv[2] == 1) {
				odupwon1.setText("50,000원");
			} else {
				odupwon1.setVisible(false);
			}
			odupwon1.setBounds(460, 480, 200, 200);

			JLabel odupwon2 = new JLabel();
			if (tableLv[2] == 2) {
				odupwon2.setText("100,000원");
			} else {
				odupwon2.setVisible(false);
			}
			odupwon2.setBounds(460, 480, 200, 200);

			mPanel.add(label6);
			mPanel.add(odupwon1);
			mPanel.add(odupwon2);

		}

		JLabel label7 = new JLabel();
		label7.setVisible(false);
		if (equipsLv[3] > 0) {
			label7.setVisible(true);
			label7.setText("라면판 업그레이드");
			label7.setBounds(630, 460, 200, 200);

			JLabel rmupwon1 = new JLabel();
			if (tableLv[3] == 1) {
				rmupwon1.setText("50,000원");
			} else {
				rmupwon1.setVisible(false);
			}
			rmupwon1.setBounds(650, 480, 200, 200);

			JLabel rmupwon2 = new JLabel();
			if (tableLv[3] == 2) {
				rmupwon2.setText("100,000원");
			} else {
				rmupwon2.setVisible(false);
			}
			rmupwon2.setBounds(650, 480, 200, 200);

			mPanel.add(label7);
			mPanel.add(rmupwon1);
			mPanel.add(rmupwon2);

		}

	}// setting

	class EquipSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("떡볶이기구")) {
				System.out.println("tbk");
				if (equipsLv[0] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (equipsLv[0] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}

			if (e.getActionCommand().equals("튀김기구")) {
				System.out.println("tk");
				if (equipsLv[1] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (equipsLv[1] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (equipsLv[1] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}

			if (e.getActionCommand().equals("오뎅기구")) {
				System.out.println("od");
				if (equipsLv[2] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (equipsLv[2] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (equipsLv[2] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}

			if (e.getActionCommand().equals("라면기구")) {
				System.out.println("rm");
				if (equipsLv[3] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (equipsLv[3] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (equipsLv[3] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}

			if (e.getActionCommand().equals("떡볶이판 업글")) {
				System.out.println("tbkup");
				if (tableLv[0] == 1) {
					if (m.getGold() >= level2) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}

				} else if (tableLv[0] == 2) {
					if (m.getGold() >= level3) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}

			if (e.getActionCommand().equals("튀김판 업글")) {
				System.out.println("tkup");
				if (tableLv[1] == 0) {
					if (m.getGold() >= level1) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (tableLv[1] == 1) {
					if (m.getGold() >= level2) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (tableLv[1] == 2) {
					if (m.getGold() >= level3) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}

			if (e.getActionCommand().equals("오뎅판 업글")) {
				System.out.println("odup");
				if (tableLv[2] == 0) {
					if (m.getGold() >= level1) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (tableLv[2] == 1) {
					if (m.getGold() >= level2) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (tableLv[2] == 2) {
					if (m.getGold() >= level3) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}

			if (e.getActionCommand().equals("라면판 업글")) {
				System.out.println("rmup");
				if (tableLv[3] == 0) {
					if (m.getGold() >= level1) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (tableLv[3] == 1) {
					if (m.getGold() >= level2) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else if (tableLv[3] == 2) {
					if (m.getGold() >= level3) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "그 돈으론 어림없지~");						
					}
				} else {
					System.out.println("레벨 만땅~");
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
			mPanel = temp;
		}
	}
	public Music getMarketMusic() {
		return marketMusic;
	}
}// class