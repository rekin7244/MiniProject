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

	// �޾ƿ� �ʵ�
	MainFrame mf;
	Member m;

	// ��� �ʵ�
  private Music marketMusic;
	// �ⱸ ���ۺ��
	private int level1 = 25000;
	private int level2 = 50000;
	private int level3 = 100000;
	// �� ���ۺ��
	private int plevel2 = 30000;
	private int plevel3 = 60000;
	private int plevel4 = 100000;

	// �ⱸ
	JButton mo = new JButton("�����̱ⱸ");
	JButton mo1 = new JButton("Ƣ��ⱸ");
	JButton mo2 = new JButton("�����ⱸ");
	JButton mo3 = new JButton("���ⱸ");

	// ��
	JButton mo4 = new JButton("�������� ����");
	JButton mo5 = new JButton("Ƣ���� ����");
	JButton mo6 = new JButton("������ ����");
	JButton mo7 = new JButton("����� ����");

	MarketPanel mPanel;
	JButton gold;
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
			new ImageIcon("images/��4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0), };

	// Ƣ�� ��
	private Image[] tableImages1 = { new ImageIcon("images/Ƣ2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Ƣ3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/Ƣ4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// ���� ��
	private Image[] tableImages2 = { new ImageIcon("images/��2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��4.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/MAX.jpg").getImage().getScaledInstance(200, 200, 0) };

	// ��� ��
	private Image[] tableImages3 = { new ImageIcon("images/��2.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��3.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/��4.jpg").getImage().getScaledInstance(200, 200, 0),
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

		// ��� ���
		gold = new JButton("���");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0, 0, 200, 30);

		// ���ư���
		JButton returnBtn = new JButton("���ư���");
		returnBtn.setBounds(660, 620, 140, 30);
		this.add(returnBtn);
		returnBtn.addActionListener(e);

		mPanel = this;
	}// marketpanel

	// ���� ��ư �� ��� ����
	public void setting(MarketPanel mPanel) {
		// ��� ���
		gold.setText("������: " + m.getGold() + "G");
		mPanel.add(gold);

		// ������ �ⱸ
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

		// Ƣ�� �ⱸ
		mo1.setVisible(false);
		if (equipsLv[0] >= 2) {
			mo1.setVisible(true);
		}
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

		// ���� �ⱸ
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

		// ��� �ⱸ
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

		// ��������
		if (tableLv[0] == 1) {
			mo4.setIcon(new ImageIcon(tableImages[0])); // �Ƿ�2 ��2�̹���
		} else if (tableLv[0] == 2) {
			mo4.setIcon(new ImageIcon(tableImages[1])); // �Ƿ�3 ��3�̹���
		} else if (tableLv[0] == 3) {
			mo4.setIcon(new ImageIcon(tableImages[2])); // �Ƿ�4 ��4�̹���
		} else {
			mo4.setIcon(new ImageIcon(tableImages[3])); // �����̹���
			mo4.setEnabled(false);
		}

		mo4.setBounds(20, 360, 180, 180);
		mo4.removeActionListener(e);
		mo4.addActionListener(e);

		// Ƣ�� ��
		mo5.setVisible(false);
		if (equipsLv[1] > 0) {
			mo5.setVisible(true);
		}
		if (tableLv[1] == 1) {
			mo5.setIcon(new ImageIcon(tableImages1[0])); // �Ƿ�2 ��2�̹���
		} else if (tableLv[1] == 2) {
			mo5.setIcon(new ImageIcon(tableImages1[1])); // �Ƿ�3 ��3�̹���
		} else if (tableLv[1] == 3) {
			mo5.setIcon(new ImageIcon(tableImages1[2])); // �Ƿ�4 ��4�̹���
		} else {
			mo5.setIcon(new ImageIcon(tableImages1[3])); // �����̹���
			mo5.setEnabled(false);
		}

		mo5.setBounds(210, 360, 180, 180);
		mo5.removeActionListener(e);
		mo5.addActionListener(e);

		// ���� ��
		mo6.setVisible(false);
		if (equipsLv[2] > 0) {
			mo6.setVisible(true);
		}
		if (tableLv[2] == 1) {
			mo6.setIcon(new ImageIcon(tableImages2[0])); // �Ƿ�2 ��2�̹���
		} else if (tableLv[2] == 2) {
			mo6.setIcon(new ImageIcon(tableImages2[1])); // �Ƿ�3 ��3�̹���
		} else if (tableLv[2] == 3) {
			mo6.setIcon(new ImageIcon(tableImages2[2])); // �Ƿ�4 ��4�̹���
		} else {
			mo6.setIcon(new ImageIcon(tableImages2[3])); // �����̹���
			mo6.setEnabled(false);
		}

		mo6.setBounds(400, 360, 180, 180);
		mo6.removeActionListener(e);
		mo6.addActionListener(e);

		// ��� ��
		mo7.setVisible(false);
		if (equipsLv[3] > 0) {
			mo7.setVisible(true);
		}
		if (tableLv[3] == 1) {
			mo7.setIcon(new ImageIcon(tableImages3[0])); // �Ƿ�2 ��2�̹���
		} else if (tableLv[3] == 2) {
			mo7.setIcon(new ImageIcon(tableImages3[1])); // �Ƿ�3 ��3�̹���
		} else if (tableLv[3] == 3) {
			mo7.setIcon(new ImageIcon(tableImages3[2])); // �Ƿ�4 ��4�̹���
		} else {
			mo7.setIcon(new ImageIcon(tableImages3[3])); // �����̹���
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

		// ��
		// ������ ��
		JLabel label = new JLabel();
		label.setText("������ ���׷��̵�");
		label.setBounds(50, 160, 200, 200);

		JLabel tbkwon1 = new JLabel();
		if (equipsLv[0] == 1) {
			tbkwon1.setText("50,000��");
		} else {
			tbkwon1.setVisible(false);
		}
		tbkwon1.setBounds(80, 180, 200, 200);

		JLabel tbkwon2 = new JLabel();
		if (equipsLv[0] == 2) {
			tbkwon2.setText("100,000��");
		} else {
			tbkwon2.setVisible(false);
		}
		tbkwon2.setBounds(80, 180, 200, 200);

		mPanel.add(label);
		mPanel.add(tbkwon1);
		mPanel.add(tbkwon2);

		// Ƣ�� ��
		JLabel label1 = new JLabel();
		label1.setVisible(false);
		if (equipsLv[0] > 1) {
			label1.setVisible(true);

			label1.setText("Ƣ�� ���׷��̵�");
			label1.setBounds(250, 160, 200, 200);

			JLabel tkwon = new JLabel();
			if (equipsLv[1] == 0) {
				tkwon.setText("25,000��");
			} else {
				tkwon.setVisible(false);
			}
			tkwon.setBounds(270, 180, 200, 200);

			JLabel tkwon1 = new JLabel();
			if (equipsLv[1] == 1) {
				tkwon1.setText("50,000��");
			} else {
				tkwon1.setVisible(false);
			}
			tkwon1.setBounds(270, 180, 200, 200);

			JLabel tkwon2 = new JLabel();
			if (equipsLv[1] == 2) {
				tkwon2.setText("100,000��");
			} else {
				tkwon2.setVisible(false);
			}
			tkwon2.setBounds(270, 180, 200, 200);

			mPanel.add(label1);
			mPanel.add(tkwon);
			mPanel.add(tkwon1);
			mPanel.add(tkwon2);
		}

		// ���� ��
		JLabel label2 = new JLabel();
		label2.setVisible(false);
		if (equipsLv[1] > 0) {
			label2.setVisible(true);

			label2.setText("���� ���׷��̵�");
			label2.setBounds(450, 160, 200, 200);

			JLabel odwon = new JLabel();
			if (equipsLv[2] == 0) {
				odwon.setText("25,000��");
			} else {
				odwon.setVisible(false);
			}
			odwon.setBounds(470, 180, 200, 200);

			JLabel odwon1 = new JLabel();
			if (equipsLv[2] == 1) {
				odwon1.setText("50,000��");
			} else {
				odwon1.setVisible(false);
			}
			odwon1.setBounds(470, 180, 200, 200);

			JLabel odwon2 = new JLabel();
			if (equipsLv[2] == 2) {
				odwon2.setText("100,000��");
			} else {
				odwon2.setVisible(false);
			}
			odwon2.setBounds(470, 180, 200, 200);

			mPanel.add(label2);
			mPanel.add(odwon);
			mPanel.add(odwon1);
			mPanel.add(odwon2);
		}

		// ��� ��
		JLabel label3 = new JLabel();
		label3.setVisible(false);
		if (equipsLv[2] > 0) {
			label3.setVisible(true);

			label3.setText("��� ���׷��̵�");
			label3.setBounds(630, 160, 200, 200);

			JLabel rmwon = new JLabel();
			if (equipsLv[3] == 0) {
				rmwon.setText("25,000��");
			} else {
				rmwon.setVisible(false);
			}
			rmwon.setBounds(650, 180, 200, 200);

			JLabel rmwon1 = new JLabel();
			if (equipsLv[3] == 1) {
				rmwon1.setText("50,000��");
			} else {
				rmwon1.setVisible(false);
			}
			rmwon1.setBounds(650, 180, 200, 200);

			JLabel rmwon2 = new JLabel();
			if (equipsLv[3] == 2) {
				rmwon2.setText("100,000��");
			} else {
				rmwon2.setVisible(false);
			}
			rmwon2.setBounds(650, 180, 200, 200);

			mPanel.add(label3);
			mPanel.add(rmwon);
			mPanel.add(rmwon1);
			mPanel.add(rmwon2);
		}

		// �������� ��
		JLabel label4 = new JLabel();
		label4.setText("�������� ���׷��̵�");
		label4.setBounds(50, 460, 200, 200);

		JLabel tbkupwon1 = new JLabel();
		if (tableLv[0] == 1) {
			tbkupwon1.setText("30,000��");
		} else {
			tbkupwon1.setVisible(false);
		}
		tbkupwon1.setBounds(80, 480, 200, 200);

		JLabel tbkupwon2 = new JLabel();
		if (tableLv[0] == 2) {
			tbkupwon2.setText("60,000��");
		} else {
			tbkupwon2.setVisible(false);
		}
		tbkupwon2.setBounds(80, 480, 200, 200);

		JLabel tbkupwon3 = new JLabel();
		if (tableLv[0] == 3) {
			tbkupwon3.setText("100,000��");
		} else {
			tbkupwon3.setVisible(false);
		}
		tbkupwon3.setBounds(80, 480, 200, 200);

		mPanel.add(label4);
		mPanel.add(tbkupwon1);
		mPanel.add(tbkupwon2);
		mPanel.add(tbkupwon3);

		// Ƣ���� ��
		JLabel label5 = new JLabel();
		label5.setVisible(false);
		if (equipsLv[1] > 0) {
			label5.setVisible(true);
			label5.setText("Ƣ���� ���׷��̵�");
			label5.setBounds(250, 460, 200, 200);

			JLabel tkupwon1 = new JLabel();
			if (tableLv[1] == 1) {
				tkupwon1.setText("30,000��");
			} else {
				tkupwon1.setVisible(false);
			}
			tkupwon1.setBounds(270, 480, 200, 200);

			JLabel tkupwon2 = new JLabel();
			if (tableLv[1] == 2) {
				tkupwon2.setText("60,000��");
			} else {
				tkupwon2.setVisible(false);
			}
			tkupwon2.setBounds(270, 480, 200, 200);

			JLabel tkupwon3 = new JLabel();
			if (tableLv[1] == 3) {
				tkupwon3.setText("100,000��");
			} else {
				tkupwon3.setVisible(false);
			}
			tkupwon3.setBounds(270, 480, 200, 200);

			mPanel.add(label5);
			mPanel.add(tkupwon1);
			mPanel.add(tkupwon2);
			mPanel.add(tkupwon3);

		}

		// ������ ��
		JLabel label6 = new JLabel();
		label6.setVisible(false);
		if (equipsLv[2] > 0) {
			label6.setVisible(true);
			label6.setText("������ ���׷��̵�");
			label6.setBounds(440, 460, 200, 200);

			JLabel odupwon1 = new JLabel();
			if (tableLv[2] == 1) {
				odupwon1.setText("30,000��");
			} else {
				odupwon1.setVisible(false);
			}
			odupwon1.setBounds(460, 480, 200, 200);

			JLabel odupwon2 = new JLabel();
			if (tableLv[2] == 2) {
				odupwon2.setText("60,000��");
			} else {
				odupwon2.setVisible(false);
			}
			odupwon2.setBounds(460, 480, 200, 200);

			JLabel odupwon3 = new JLabel();
			if (tableLv[2] == 3) {
				odupwon3.setText("100,000��");
			} else {
				odupwon3.setVisible(false);
			}
			odupwon3.setBounds(460, 480, 200, 200);

			mPanel.add(label6);
			mPanel.add(odupwon1);
			mPanel.add(odupwon2);
			mPanel.add(odupwon3);

		}

		// ����� ��
		JLabel label7 = new JLabel();
		label7.setVisible(false);
		if (equipsLv[3] > 0) {
			label7.setVisible(true);
			label7.setText("����� ���׷��̵�");
			label7.setBounds(630, 460, 200, 200);

			JLabel rmupwon1 = new JLabel();
			if (tableLv[3] == 1) {
				rmupwon1.setText("30,000��");
			} else {
				rmupwon1.setVisible(false);
			}
			rmupwon1.setBounds(650, 480, 200, 200);

			JLabel rmupwon2 = new JLabel();
			if (tableLv[3] == 2) {
				rmupwon2.setText("60,000��");
			} else {
				rmupwon2.setVisible(false);
			}
			rmupwon2.setBounds(650, 480, 200, 200);

			JLabel rmupwon3 = new JLabel();
			if (tableLv[3] == 3) {
				rmupwon3.setText("100,000��");
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

			// �����̱ⱸ �׼Ǹ�����
			if (e.getActionCommand().equals("�����̱ⱸ")) {
				System.out.println("tbk");
				if (equipsLv[0] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (equipsLv[0] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else {
					System.out.println("���� ����~");
				}
			}

			// Ƣ��ⱸ �׼Ǹ�����
			if (e.getActionCommand().equals("Ƣ��ⱸ")) {
				System.out.println("tk");

				if (equipsLv[1] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (equipsLv[1] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (equipsLv[1] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else {
					System.out.println("���� ����~");
				}
			}

			// �����ⱸ �׼Ǹ�����
			if (e.getActionCommand().equals("�����ⱸ")) {
				System.out.println("od");

				if (equipsLv[2] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (equipsLv[2] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (equipsLv[2] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else {
					System.out.println("���� ����~");
				}
			}

			// ���ⱸ �׼Ǹ�����
			if (e.getActionCommand().equals("���ⱸ")) {
				System.out.println("rm");

				if (equipsLv[3] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (equipsLv[3] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (equipsLv[3] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else {
					System.out.println("���� ����~");
				}
			}

			// �������� �׼Ǹ�����
			if (e.getActionCommand().equals("�������� ����")) {
				System.out.println("tbkup");
				if (tableLv[0] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}

				} else if (tableLv[0] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (tableLv[0] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else {
					System.out.println("���� ����~");

				}
			}

			// Ƣ���� �׼Ǹ�����
			if (e.getActionCommand().equals("Ƣ���� ����")) {
				System.out.println("tkup");
				if (tableLv[1] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}

				} else if (tableLv[1] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (tableLv[1] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else {
					System.out.println("���� ����~");

				}
			}

			// ������ �׼Ǹ�����
			if (e.getActionCommand().equals("������ ����")) {
				System.out.println("odup");
				if (tableLv[2] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (tableLv[2] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (tableLv[2] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else {
					System.out.println("���� ����~");
				}
			}

			// ����� �׼Ǹ�����
			if (e.getActionCommand().equals("����� ����")) {
				System.out.println("rmup");
				if (tableLv[3] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (tableLv[3] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else if (tableLv[3] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(null, "�� ������ �����~");
					}
				} else {
					System.out.println("���� ����~");
				}
			}
			refresh();
			if (e.getActionCommand().equals("���ư���")) {
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