package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.kh.miniProject.model.vo.member.Member;

public class MarketPanel extends JPanel {
	
	// �޾ƿ� �ʵ�
	MainFrame mf;
	Member m;
	
	// ��� �ʵ�
	//�ⱸ
	private int tbkLv = 1;
	JButton mo = new JButton("�����̱ⱸ");
	private int tkLv = 1;
	JButton mo1 = new JButton("Ƣ��ⱸ");
	private int odLv = 1;
	JButton mo2 = new JButton("�����ⱸ");
	private int rmLv = 1;
	JButton mo3 = new JButton("���ⱸ");
	
	//��
	private int tbkup = 1;
	JButton mo4 = new JButton("�������� ����");
	private int tkup = 1;
	JButton mo5 = new JButton("Ƣ���� ����");
	private int odup = 1;
	JButton mo6 = new JButton("������ ����");
	private int rmup = 1;
	JButton mo7 = new JButton("����� ����");

	JPanel mPanel;
	private int[] equipsLv;
	private int[] tableLv;
	private EquipSetting e;

	// ������ �ⱸ
	private Image[] equipsImages = { new ImageIcon("images/��2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// Ƣ�� �ⱸ
	private Image[] equipsImages1 = { new ImageIcon("images/Ƣ1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Ƣ2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Ƣ3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// ���� �ⱸ
	private Image[] equipsImages2 = { new ImageIcon("images/��1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };
	// ��� �ⱸ
	private Image[] equipsImages3 = { new ImageIcon("images/��1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// ������ ��
	private Image[] tableImages = { new ImageIcon("images/��2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// Ƣ�� ��
	private Image[] tableImages1 = { new ImageIcon("images/Ƣ1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Ƣ2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Ƣ3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// ���� ��
	private Image[] tableImages2 = { new ImageIcon("images/��1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// ��� ��
	private Image[] tableImages3 = { new ImageIcon("images/��1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��3.jpg").getImage().getScaledInstance(200, 200, 0),
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

		// ������
		JButton gold = new JButton("���");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0, 0, 200, 30);
		gold.setText("������: " + m.getGold() + "G");
		this.add(gold);

		// ���ư���
		JButton returnBtn = new JButton("���ư���");
		returnBtn.setBounds(660, 620, 140, 30);
		this.add(returnBtn);

		// ���� ��ư �� ��� ����
		setting(this);

		// Action
		mPanel = this;
		returnBtn.addActionListener(new EquipSetting());
	
	}//marketpanel

	public void setting(JPanel panel) {
		// ������ �ⱸ
		if (equipsLv[0] == 1) {
			mo.setIcon(new ImageIcon(equipsImages[0]));
		} else if (equipsLv[0] == 2) {
			mo.setIcon(new ImageIcon(equipsImages[1]));
		} else {
			mo.setIcon(new ImageIcon(equipsImages3[3]));
		}
		mo.setBounds(20, 60, 180, 180);
		mo.removeActionListener(e);
		mo.addActionListener(e);

		// Ƣ�� �ⱸ
		if (equipsLv[1] == 0) {
			mo1.setIcon(new ImageIcon(equipsImages1[0]));
		} else if (equipsLv[1] == 1) {
			mo1.setIcon(new ImageIcon(equipsImages1[1]));
		} else if (equipsLv[1] == 2) {
			mo1.setIcon(new ImageIcon(equipsImages1[2]));
		} else {
			mo1.setIcon(new ImageIcon(equipsImages3[3]));
		}
		mo1.setBounds(210, 60, 180, 180);
		mo1.removeActionListener(e);
		mo1.addActionListener(e);

		// ���� �ⱸ
		if (equipsLv[2] == 0) {
			mo2.setIcon(new ImageIcon(equipsImages2[0]));
		} else if (equipsLv[2] == 1) {
			mo2.setIcon(new ImageIcon(equipsImages2[1]));
		} else if (equipsLv[2] == 2) {
			mo2.setIcon(new ImageIcon(equipsImages2[2]));
		} else {
			mo2.setIcon(new ImageIcon(equipsImages3[3]));
		}
		mo2.setBounds(400, 60, 180, 180);
		mo2.removeActionListener(e);
		mo2.addActionListener(e);

		// ��� �ⱸ
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

		// ��������
		if (tableLv[0] == 1) {
			mo4.setIcon(new ImageIcon(tableImages[0]));
		} else if (tableLv[0] == 2) {
			mo4.setIcon(new ImageIcon(tableImages[1]));
		} else {
			mo4.setIcon(new ImageIcon(tableImages[2]));
		}

		mo4.setBounds(20, 360, 180, 180);
		mo4.removeActionListener(e);
		mo4.addActionListener(e);

		// Ƣ�� ��
		if (tableLv[1] == 0) {
			mo5.setIcon(new ImageIcon(tableImages1[0]));
		} else if (tableLv[1] == 1) {
			mo5.setIcon(new ImageIcon(tableImages1[1]));
		} else if (tableLv[1] == 2) {
			mo5.setIcon(new ImageIcon(tableImages1[2]));
		} else {
			mo5.setIcon(new ImageIcon(tableImages1[3]));
		}

		mo5.setBounds(210, 360, 180, 180);
		mo5.removeActionListener(e);
		mo5.addActionListener(e);

		// ���� ��
		if (tableLv[2] == 0) {
			mo6.setIcon(new ImageIcon(tableImages2[0]));
		} else if (tableLv[2] == 1) {
			mo6.setIcon(new ImageIcon(tableImages2[1]));
		} else if (tableLv[2] == 2) {
			mo6.setIcon(new ImageIcon(tableImages2[2]));
		} else {
			mo6.setIcon(new ImageIcon(tableImages2[3]));
		}

		mo6.setBounds(400, 360, 180, 180);
		mo6.removeActionListener(e);
		mo6.addActionListener(e);

		// ��� ��
		if (tableLv[3] == 0) {
			mo7.setIcon(new ImageIcon(tableImages3[0]));
		} else if (tableLv[3] == 1) {
			mo7.setIcon(new ImageIcon(tableImages3[1]));
		} else if (tableLv[3] == 2) {
			mo7.setIcon(new ImageIcon(tableImages3[2]));
		} else {
			mo7.setIcon(new ImageIcon(tableImages3[3]));
		}

		mo7.setBounds(590, 360, 180, 180);
		mo7.removeActionListener(e);
		mo7.addActionListener(e);

		panel.add(mo);
		panel.add(mo1);
		panel.add(mo2);
		panel.add(mo3);
		panel.add(mo4);
		panel.add(mo5);
		panel.add(mo6);
		panel.add(mo7);

		// ��
		JLabel label = new JLabel();
		label.setText("������ ���׷��̵�");
		label.setBounds(50, 160, 200, 200);
		panel.add(label);
		JLabel label1 = new JLabel();
		label1.setText("Ƣ�� ���׷��̵�");
		label1.setBounds(250, 160, 200, 200);
		panel.add(label1);
		JLabel label2 = new JLabel();
		label2.setText("���� ���׷��̵�");
		label2.setBounds(450, 160, 200, 200);
		panel.add(label2);
		JLabel label3 = new JLabel();
		label3.setText("��� ���׷��̵�");
		label3.setBounds(630, 160, 200, 200);
		panel.add(label3);
		JLabel label4 = new JLabel();
		label4.setText("�������� ���׷��̵�");
		label4.setBounds(50, 460, 200, 200);
		panel.add(label4);
		JLabel label5 = new JLabel();
		label5.setText("Ƣ���� ���׷��̵�");
		label5.setBounds(250, 460, 200, 200);
		panel.add(label5);
		JLabel label6 = new JLabel();
		label6.setText("������ ���׷��̵�");
		label6.setBounds(440, 460, 200, 200);
		panel.add(label6);
		JLabel label7 = new JLabel();
		label7.setText("����� ���׷��̵�");
		label7.setBounds(630, 460, 200, 200);
		panel.add(label7);

	}//setting

	class EquipSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().equals("�����̱ⱸ")) {
				System.out.println("tbk");
				if (equipsLv[0] == 1) {
					if (m.getGold() > 500000) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - 500000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (equipsLv[0] == 2) {
					if (m.getGold() > 1000000) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - 1000000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else {
					System.out.println("���� ����~");
				}
				refresh();
			}
			
			if (e.getActionCommand().equals("Ƣ��ⱸ")) {
				System.out.println("tk");
				if (equipsLv[1] == 0) {
					if (m.getGold() > 250000) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - 250000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (equipsLv[1] == 1) {
					if (m.getGold() > 500000) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - 500000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (equipsLv[1] == 2) {
					if (m.getGold() > 1000000) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - 1000000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else {
					System.out.println("���� ����~");

				}
				refresh();
			}
			
			if (e.getActionCommand().equals("�����ⱸ")) {
				System.out.println("od");
				if (equipsLv[2] == 0) {
					if (m.getGold() > 250000) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - 250000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (equipsLv[2] == 1) {
					if (m.getGold() > 500000) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - 500000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (equipsLv[2] == 2) {
					if (m.getGold() > 1000000) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - 1000000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else {
					System.out.println("���� ����~");

				}
				refresh();
			}
			
			if (e.getActionCommand().equals("���ⱸ")) {
				System.out.println("rm");
				if (equipsLv[3] == 0) {
					if (m.getGold() > 250000) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - 250000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (equipsLv[3] == 1) {
					if (m.getGold() > 500000) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - 500000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (equipsLv[3] == 2) {
					if (m.getGold() > 1000000) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - 1000000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else {
					System.out.println("���� ����~");

				}
				refresh();
			}

			if (e.getActionCommand().equals("�������� ����")) {
				System.out.println("tbkup");
				if (tableLv[0] == 1) {
					if (m.getGold() > 500000) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - 500000);
					} else {
						System.out.println("�׵����� �����~");
					}

				} else if (tableLv[0] == 2) {
					if (m.getGold() > 1000000) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - 1000000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else {
					System.out.println("���� ����~");
				}
				refresh();
			}
			
			if (e.getActionCommand().equals("Ƣ���� ����")) {
				System.out.println("tkup");
				if (tableLv[1] == 0) {
					if (m.getGold() > 250000) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - 250000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (tableLv[1] == 1) {
					if (m.getGold() > 500000) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - 500000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (tableLv[1] == 2) {
					if (m.getGold() > 1000000) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - 1000000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else {
					System.out.println("���� ����~");

				}
				refresh();
			}

			if (e.getActionCommand().equals("������ ����")) {
				System.out.println("odup");
				if (tableLv[2] == 0) {
					if (m.getGold() > 250000) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - 250000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (tableLv[2] == 1) {
					if (m.getGold() > 500000) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - 500000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (tableLv[2] == 2) {
					if (m.getGold() > 1000000) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - 1000000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else {
					System.out.println("���� ����~");

				}
				refresh();
			}

			if (e.getActionCommand().equals("����� ����")) {
				System.out.println("rmup");
				if (tableLv[3] == 0) {
					if (m.getGold() > 250000) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - 250000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (tableLv[3] == 1) {
					if (m.getGold() > 500000) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - 500000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else if (tableLv[3] == 2) {
					if (m.getGold() > 1000000) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - 1000000);
					} else {
						System.out.println("�׵����� �����~");
					}
				} else {
					System.out.println("���� ����~");

				}
				refresh();
			}

			if (e.getActionCommand().equals("���ư���")) {
				new ChangePanel().changePanel(mf, mPanel, new StageView(mf, m));
			}
			;
		}

		public void refresh() {
			setting(mPanel);
			m.setEquipsLv(equipsLv);
			m.setTableLv(tableLv);
		}
	}
}// class
