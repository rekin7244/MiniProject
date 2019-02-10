package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.miniProject.model.dao.MemberDao;
import com.kh.miniProject.model.vo.member.Member;

public class MarketPanel extends JPanel {

	// 받아올 필드
	MainFrame mf;
	Member m;

	// 사용 필드
	//업글비용
	private int level1=25000;
	private int level2=50000;
	private int level3=100000;

	//기구
	JButton mo = new JButton("떡볶이기구");
	JButton mo1 = new JButton("튀김기구");
	JButton mo2 = new JButton("오뎅기구");
	JButton mo3 = new JButton("라면기구");

	//판
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
	private Image[] tableImages1 = { /*new ImageIcon("images/튀1.jpg").getImage().getScaledInstance(200, 200, 0),*/
			new ImageIcon("images/튀2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/튀3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// 오뎅 판
	private Image[] tableImages2 = { /*new ImageIcon("images/오1.jpg").getImage().getScaledInstance(200, 200, 0),*/
			new ImageIcon("images/오2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/오3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// 라면 판
	private Image[] tableImages3 = { /*new ImageIcon("images/라1.jpg").getImage().getScaledInstance(200, 200, 0),*/
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
		
		//골드 출력
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
		//setting(mPanel);
	}//marketpanel

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
		}
		mo3.setBounds(590, 60, 180, 180);
		mo3.removeActionListener(e);
		mo3.addActionListener(e);

		// 떡볶이판
		if(tableLv[0]==1) {
			mo4.setIcon(new ImageIcon(tableImages[0]));
		}else if(tableLv[0]==2) {
			mo4.setIcon(new ImageIcon(tableImages[1]));
		}else {
			mo4.setIcon(new ImageIcon(tableImages[2]));
		}
		mo4.setBounds(20, 360, 180, 180);
		mo4.removeActionListener(e);
		mo4.addActionListener(e);

		// 튀김 판
		mo5.setVisible(false);
		if(equipsLv[1]>0) {
			mo5.setVisible(true);
		}
		if(tableLv[1]==1) {
			mo5.setIcon(new ImageIcon(tableImages1[0]));
		}else if(tableLv[1]==2) {
			mo5.setIcon(new ImageIcon(tableImages1[1]));
		}else {
			mo5.setIcon(new ImageIcon(tableImages1[2]));
		}
		mo5.setBounds(210, 360, 180, 180);
		mo5.removeActionListener(e);
		mo5.addActionListener(e);

		// 오뎅 판
		mo6.setVisible(false);
		if(equipsLv[2]>0) {
			mo6.setVisible(true);
		}
		if(tableLv[2]==1) {
			mo6.setIcon(new ImageIcon(tableImages2[0]));
		}else if(tableLv[2]==2) {
			mo6.setIcon(new ImageIcon(tableImages2[1]));
		}else {
			mo6.setIcon(new ImageIcon(tableImages2[2]));
		}		
		mo6.setBounds(400, 360, 180, 180);
		mo6.removeActionListener(e);
		mo6.addActionListener(e);

		// 라면 판
		mo7.setVisible(false);
		if(equipsLv[3]>0) {
			mo7.setVisible(true);
		}
		if(tableLv[3]==1) {
			mo7.setIcon(new ImageIcon(tableImages3[0]));
		}else if(tableLv[3]==2) {
			mo7.setIcon(new ImageIcon(tableImages3[1]));
		}else {
			mo7.setIcon(new ImageIcon(tableImages3[2]));
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
		mPanel.add(label);
		JLabel label1 = new JLabel();
		label1.setText("튀김 업그레이드");
		label1.setBounds(250, 160, 200, 200);
		mPanel.add(label1);
		JLabel label2 = new JLabel();
		label2.setText("오뎅 업그레이드");
		label2.setBounds(450, 160, 200, 200);
		mPanel.add(label2);
		JLabel label3 = new JLabel();
		label3.setText("라면 업그레이드");
		label3.setBounds(630, 160, 200, 200);
		mPanel.add(label3);
		JLabel label4 = new JLabel();
		label4.setText("떡볶이판 업그레이드");
		label4.setBounds(50, 460, 200, 200);
		mPanel.add(label4);
		JLabel label5 = new JLabel();
		label5.setText("튀김판 업그레이드");
		label5.setBounds(250, 460, 200, 200);
		mPanel.add(label5);
		JLabel label6 = new JLabel();
		label6.setText("오뎅판 업그레이드");
		label6.setBounds(440, 460, 200, 200);
		mPanel.add(label6);
		JLabel label7 = new JLabel();
		label7.setText("라면판 업그레이드");
		label7.setBounds(630, 460, 200, 200);
		mPanel.add(label7);

	}//setting

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
						System.out.println("그돈으론 어림없지~");
					}
				} else if (equipsLv[0] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						System.out.println("그돈으론 어림없지~");
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
						System.out.println("그돈으론 어림없지~");
					}
				} else if (equipsLv[1] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						System.out.println("그돈으론 어림없지~");
					}
				} else if (equipsLv[1] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						System.out.println("그돈으론 어림없지~");
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
						System.out.println("그돈으론 어림없지~");
					}
				} else if (equipsLv[2] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						System.out.println("그돈으론 어림없지~");
					}
				} else if (equipsLv[2] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						System.out.println("그돈으론 어림없지~");
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
						System.out.println("그돈으론 어림없지~");
					}
				} else if (equipsLv[3] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						System.out.println("그돈으론 어림없지~");
					}
				} else if (equipsLv[3] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						System.out.println("그돈으론 어림없지~");
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
						System.out.println("그돈으론 어림없지~");
					}

				} else if (tableLv[0] == 2) {
					if (m.getGold() >= level3) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						System.out.println("그돈으론 어림없지~");
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
						System.out.println("그돈으론 어림없지~");
					}
				} else if (tableLv[1] == 1) {
					if (m.getGold() >= level2) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						System.out.println("그돈으론 어림없지~");
					}
				} else if (tableLv[1] == 2) {
					if (m.getGold() >= level3) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						System.out.println("그돈으론 어림없지~");
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
						System.out.println("그돈으론 어림없지~");
					}
				} else if (tableLv[2] == 1) {
					if (m.getGold() >= level2) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						System.out.println("그돈으론 어림없지~");
					}
				} else if (tableLv[2] == 2) {
					if (m.getGold() >= level3) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						System.out.println("그돈으론 어림없지~");
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
						System.out.println("그돈으론 어림없지~");
					}
				} else if (tableLv[3] == 1) {
					if (m.getGold() >= level2) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						System.out.println("그돈으론 어림없지~");
					}
				} else if (tableLv[3] == 2) {
					if (m.getGold() >= level3) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						System.out.println("그돈으론 어림없지~");
					}
				} else {
					System.out.println("레벨 만땅~");
				}
			}
			refresh();
			if (e.getActionCommand().equals("돌아가기")) {
				new ChangePanel().changePanel(mf, mPanel, new StageView(mf, m));
			};
		}

		public void refresh() {
			m.setEquipsLv(equipsLv);
			m.setTableLv(tableLv);
			MemberDao mDao = new MemberDao();
			mDao.saveMember(m);
			MarketPanel temp;
			new ChangePanel().changePanel(mf,mPanel, temp=new MarketPanel(mf,m));
			temp.setting(temp);
			mPanel=temp;
		}
	}
}// class