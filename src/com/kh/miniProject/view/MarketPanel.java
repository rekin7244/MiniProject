
package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Graphics;
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
import com.kh.miniProject.run.Run;

public class MarketPanel extends JPanel {

	// �޾ƿ� �ʵ�
	MainFrame mf;
	Member m;

	// ��� �ʵ�
	private Music marketMusic;
	private JPanel panel;
	// �ⱸ ���ۺ��
	private int level1 = 25000;
	private int level2 = 50000;
	private int level3 = 100000;
	// �� ���ۺ��
	private int plevel2 = 15000;
	private int plevel3 = 40000;
	private int plevel4 = 90000;

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
		this.setSize(Run.SCREEN_WIDTH,Run.SCREEN_HEIGHT);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(110, 50, 800, 650);
		panel.setBackground(Color.PINK);

		// ��� ���
		gold = new JButton("���");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0, 0, 200, 30);

		// ���ư���
		JButton returnBtn = new JButton("���ư���");
		returnBtn.setBounds(660, 620, 140, 30);
		panel.add(returnBtn);
		returnBtn.addActionListener(e);

		this.add(panel);
		mPanel = this;
	}// marketpanel
	
	public void marketMusic() {
		marketMusic = new Music("intro3.mp3",false);
		marketMusic.start();
	}

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

		panel.add(mo);
		panel.add(mo1);
		panel.add(mo2);
		panel.add(mo3);
		panel.add(mo4);
		panel.add(mo5);
		panel.add(mo6);
		panel.add(mo7);

		// ��
		// ������ ��
		JLabel label = new JLabel();
		label.setText("������ ���׷��̵�");
		label.setBounds(50, 160, 200, 200);

		JLabel tbkwon1 = new JLabel();
		JLabel tbkwon11=new JLabel();
		if (equipsLv[0] == 1) {
			tbkwon1.setText("50,000��");
			tbkwon11.setText("������ ���� : 2�� / ��Ÿ�� 7��");
		} else {
			tbkwon1.setVisible(false);
			tbkwon11.setVisible(false);
		}
		tbkwon1.setBounds(80, 200, 200, 200);
		tbkwon11.setBounds(30,180,200,200);

		JLabel tbkwon2 = new JLabel();
		JLabel tbkwon22=new JLabel();
		if (equipsLv[0] == 2) {
			tbkwon2.setText("100,000��");
			tbkwon22.setText("������ ���� : 3�� / ��Ÿ�� 6��");
		} else {
			tbkwon2.setVisible(false);
			tbkwon22.setVisible(false);
		}
		tbkwon2.setBounds(80, 200, 200, 200);
		tbkwon22.setBounds(30,180,200,200);

		panel.add(label);
		panel.add(tbkwon1);
		panel.add(tbkwon11);
		panel.add(tbkwon2);
		panel.add(tbkwon22);

		// Ƣ�� ��
		JLabel label1 = new JLabel();
		label1.setVisible(false);
		if (equipsLv[0] >= 1) {
			label1.setVisible(true);
			
			if(equipsLv[1]==0) {
			label1.setText("Ƣ���� ����");
			label1.setBounds(250, 160, 200, 200);
			}else {
				label1.setText("Ƣ���� ���׷��̵�");
				label1.setBounds(240, 160, 200, 200);
			}

			JLabel tkwon = new JLabel();
			JLabel tkwonn=new JLabel();
			if (equipsLv[1] == 0) {
				tkwon.setText("25,000��");
				tkwonn.setText("Ƣ�� ���� : 1�� / ��Ÿ�� : 7��");
			} else {
				tkwon.setVisible(false);
				tkwonn.setVisible(false);
			}
			tkwon.setBounds(270, 200, 200, 200);
			tkwonn.setBounds(220,180,200,200);

			JLabel tkwon1 = new JLabel();
			JLabel tkwon11=new JLabel();
			if (equipsLv[1] == 1) {
				tkwon1.setText("50,000��");
				tkwon11.setText("Ƣ�� ���� : 2�� / ��Ÿ�� : 8��");
			} else {
				tkwon1.setVisible(false);
				tkwon11.setVisible(false);
			}
			tkwon1.setBounds(270, 200, 200, 200);
			tkwon11.setBounds(220,180,200,200);

			JLabel tkwon2 = new JLabel();
			JLabel tkwon22=new JLabel();
			if (equipsLv[1] == 2) {
				tkwon2.setText("100,000��");
				tkwon22.setText("Ƣ�� ���� : 3�� / ��Ÿ�� : 8��");
			} else {
				tkwon2.setVisible(false);
				tkwon22.setVisible(false);
			}
			tkwon2.setBounds(270, 200, 200, 200);
			tkwon22.setBounds(220,180,200,200);

			panel.add(label1);
			panel.add(tkwon);
			panel.add(tkwonn);
			panel.add(tkwon1);
			panel.add(tkwon11);
			panel.add(tkwon2);
			panel.add(tkwon22);
		}

		// ���� ��
		JLabel label2 = new JLabel();
		label2.setVisible(false);
		if (equipsLv[1] > 0) {
			label2.setVisible(true);
			
			if(equipsLv[2]==0) {
			label2.setText("������� ����");
			label2.setBounds(450, 160, 200, 200);
			}else {
				label2.setText("������� ���׷��̵�");
				label2.setBounds(430, 160, 200, 200);
			}

			JLabel odwon = new JLabel();
			JLabel odwonn=new JLabel();
			if (equipsLv[2] == 0) {
				odwon.setText("25,000��");
				odwonn.setText("���� ���� : 1�� / ��Ÿ�� : 8��");
			} else {
				odwon.setVisible(false);
				odwonn.setVisible(false);
			}
			odwon.setBounds(470, 200, 200, 200);
			odwonn.setBounds(410,180,200,200);

			JLabel odwon1 = new JLabel();
			JLabel odwon11=new JLabel();
			if (equipsLv[2] == 1) {
				odwon1.setText("50,000��");
				odwon11.setText("���� ���� : 2�� / ��Ÿ�� : 8��");
			} else {
				odwon1.setVisible(false);
				odwon11.setVisible(false);
			}
			odwon1.setBounds(470, 200, 200, 200);
			odwon11.setBounds(410,180,200,200);

			JLabel odwon2 = new JLabel();
			JLabel odwon22=new JLabel();
			if (equipsLv[2] == 2) {
				odwon2.setText("100,000��");
				odwon22.setText("���� ���� : 3�� / ��Ÿ�� : 8��");
			} else {
				odwon2.setVisible(false);
				odwon22.setVisible(false);
			}
			odwon2.setBounds(470, 200, 200, 200);
			odwon22.setBounds(410,180,200,200);

			panel.add(label2);
			panel.add(odwon);
			panel.add(odwonn);
			panel.add(odwon1);
			panel.add(odwon11);
			panel.add(odwon2);
			panel.add(odwon22);
		}

		// ��� ��
		JLabel label3 = new JLabel();
		label3.setVisible(false);
		if (equipsLv[2] > 0) {
			label3.setVisible(true);

			if(equipsLv[3]==0) {
			label3.setText("����� ����");
			label3.setBounds(630, 160, 200, 200);
			}else {
				label3.setText("����� ���׷��̵�");
				label3.setBounds(610, 160, 200, 200);
			}

			JLabel rmwon = new JLabel();
			JLabel rmwonn=new JLabel();
			if (equipsLv[3] == 0) {
				rmwon.setText("25,000��");
				rmwonn.setText("��� ���� : 1�� / ��Ÿ�� : 9��");
			} else {
				rmwon.setVisible(false);
				rmwonn.setVisible(false);
			}
			rmwon.setBounds(650, 200, 200, 200);
			rmwonn.setBounds(600,180,200,200);

			JLabel rmwon1 = new JLabel();
			JLabel rmwon11=new JLabel();
			if (equipsLv[3] == 1) {
				rmwon1.setText("50,000��");
				rmwon11.setText("��� ���� : 2�� / ��Ÿ�� : 10��");
			} else {
				rmwon1.setVisible(false);
				rmwon11.setVisible(false);
			}
			rmwon1.setBounds(650, 200, 200, 200);
			rmwon11.setBounds(600,180,200,200);

			JLabel rmwon2 = new JLabel();
			JLabel rmwon22=new JLabel();			
			if (equipsLv[3] == 2) {
				rmwon2.setText("100,000��");
				rmwon22.setText("��� ���� : 3�� / ��Ÿ�� : 10��");
			} else {
				rmwon2.setVisible(false);
				rmwon22.setVisible(false);
			}
			rmwon2.setBounds(650, 200, 200, 200);
			rmwon22.setBounds(600,180,200,200);

			panel.add(label3);
			panel.add(rmwon);
			panel.add(rmwonn);
			panel.add(rmwon1);
			panel.add(rmwon11);
			panel.add(rmwon2);
			panel.add(rmwon22);
		}

		// �������� ��
		JLabel label4 = new JLabel();
		label4.setText("�������� ���׷��̵�");
		label4.setBounds(50, 460, 200, 200);

		JLabel tbkupwon1 = new JLabel();
		JLabel tbkupwon11=new JLabel();
		if (tableLv[0] == 1) {
			tbkupwon1.setText("15,000��");
			tbkupwon11.setText("������ �������� : 2��");
		} else {
			tbkupwon1.setVisible(false);
			tbkupwon11.setVisible(false);
		}
		tbkupwon1.setBounds(80, 500, 200, 200);
		tbkupwon11.setBounds(50,480,200,200);

		JLabel tbkupwon2 = new JLabel();
		JLabel tbkupwon22=new JLabel();
		if (tableLv[0] == 2) {
			tbkupwon2.setText("40,000��");
			tbkupwon22.setText("������ �������� : 3��");
		} else {
			tbkupwon2.setVisible(false);
			tbkupwon22.setVisible(false);
		}
		tbkupwon2.setBounds(80, 500, 200, 200);
		tbkupwon22.setBounds(50,480,200,200);

		JLabel tbkupwon3 = new JLabel();
		JLabel tbkupwon33=new JLabel();
		if (tableLv[0] == 3) {
			tbkupwon3.setText("90,000��");
			tbkupwon33.setText("������ �������� : 4��");
		} else {
			tbkupwon3.setVisible(false);
			tbkupwon33.setVisible(false);
		}
		tbkupwon3.setBounds(80, 500, 200, 200);
		tbkupwon33.setBounds(50,480,200,200);

		panel.add(label4);
		panel.add(tbkupwon1);
		panel.add(tbkupwon11);
		panel.add(tbkupwon2);
		panel.add(tbkupwon22);
		panel.add(tbkupwon3);
		panel.add(tbkupwon33);

		// Ƣ���� ��
		JLabel label5 = new JLabel();		
		label5.setVisible(false);
		if (equipsLv[1] > 0) {
			label5.setVisible(true);
			label5.setText("Ƣ���� ���׷��̵�");
			label5.setBounds(250, 460, 200, 200);

			JLabel tkupwon1 = new JLabel();
			JLabel tkupwon11=new JLabel();
			if (tableLv[1] == 1) {
				tkupwon1.setText("15,000��");
				tkupwon11.setText("Ƣ�� �������� : 2��");
			} else {
				tkupwon1.setVisible(false);
				tkupwon11.setVisible(false);
			}
			tkupwon1.setBounds(270, 500, 200, 200);
			tkupwon11.setBounds(250,480,200,200);

			JLabel tkupwon2 = new JLabel();
			JLabel tkupwon22=new JLabel();
			if (tableLv[1] == 2) {
				tkupwon2.setText("40,000��");
				tkupwon22.setText("Ƣ�� �������� : 3��");
			} else {
				tkupwon2.setVisible(false);
				tkupwon22.setVisible(false);
			}
			tkupwon2.setBounds(270, 500, 200, 200);
			tkupwon22.setBounds(250,480,200,200);

			JLabel tkupwon3 = new JLabel();
			JLabel tkupwon33=new JLabel();
			if (tableLv[1] == 3) {
				tkupwon3.setText("90,000��");
				tkupwon33.setText("Ƣ�� �������� : 4��");
			} else {
				tkupwon3.setVisible(false);
				tkupwon33.setVisible(false);
			}
			tkupwon3.setBounds(270, 500, 200, 200);
			tkupwon33.setBounds(250,480,200,200);

			panel.add(label5);
			panel.add(tkupwon1);
			panel.add(tkupwon11);
			panel.add(tkupwon2);
			panel.add(tkupwon22);
			panel.add(tkupwon3);
			panel.add(tkupwon33);

		}

		// ������ ��
		JLabel label6 = new JLabel();
		label6.setVisible(false);
		if (equipsLv[2] > 0) {
			label6.setVisible(true);
			label6.setText("������ ���׷��̵�");
			label6.setBounds(440, 460, 200, 200);

			JLabel odupwon1 = new JLabel();
			JLabel odupwon11=new JLabel();
			if (tableLv[2] == 1) {
				odupwon1.setText("15,000��");
				odupwon11.setText("���� �������� : 2��");
			} else {
				odupwon1.setVisible(false);
				odupwon11.setVisible(false);
			}
			odupwon1.setBounds(460, 500, 200, 200);
			odupwon11.setBounds(440, 480, 200, 200);

			JLabel odupwon2 = new JLabel();
			JLabel odupwon22=new JLabel();
			if (tableLv[2] == 2) {
				odupwon2.setText("40,000��");
				odupwon22.setText("���� �������� : 3��");
			} else {
				odupwon2.setVisible(false);
				odupwon22.setVisible(false);
			}
			odupwon2.setBounds(460, 500, 200, 200);
			odupwon22.setBounds(440, 480, 200, 200);

			JLabel odupwon3 = new JLabel();
			JLabel odupwon33=new JLabel();
			if (tableLv[2] == 3) {
				odupwon3.setText("90,000��");
				odupwon33.setText("���� �������� : 4��");
			} else {
				odupwon3.setVisible(false);
				odupwon33.setVisible(false);
			}
			odupwon3.setBounds(460, 500, 200, 200);
			odupwon33.setBounds(440, 480, 200, 200);

			panel.add(label6);
			panel.add(odupwon1);
			panel.add(odupwon11);
			panel.add(odupwon2);
			panel.add(odupwon22);
			panel.add(odupwon3);
			panel.add(odupwon33);

		}

		// ����� ��
		JLabel label7 = new JLabel();
		label7.setVisible(false);
		if (equipsLv[3] > 0) {
			label7.setVisible(true);
			label7.setText("����� ���׷��̵�");
			label7.setBounds(630, 460, 200, 200);

			JLabel rmupwon1 = new JLabel();
			JLabel rmupwon11=new JLabel();
			if (tableLv[3] == 1) {
				rmupwon1.setText("15,000��");
				rmupwon11.setText("��� �������� : 2��");
			} else {
				rmupwon1.setVisible(false);
				rmupwon11.setVisible(false);
			}
			rmupwon1.setBounds(650, 500, 200, 200);
			rmupwon11.setBounds(630, 480, 200, 200);

			JLabel rmupwon2 = new JLabel();
			JLabel rmupwon22=new JLabel();
			if (tableLv[3] == 2) {
				rmupwon2.setText("40,000��");
				rmupwon22.setText("��� �������� : 3��");
			} else {
				rmupwon2.setVisible(false);
				rmupwon22.setVisible(false);
			}
			rmupwon2.setBounds(650, 500, 200, 200);
			rmupwon22.setBounds(630, 480, 200, 200);

			JLabel rmupwon3 = new JLabel();
			JLabel rmupwon33=new JLabel();
			if (tableLv[3] == 3) {
				rmupwon3.setText("90,000��");
				rmupwon33.setText("��� �������� : 4��");
			} else {
				rmupwon3.setVisible(false);
				rmupwon33.setVisible(false);
			}
			rmupwon3.setBounds(650, 500, 200, 200);
			rmupwon33.setBounds(630, 480, 200, 200);

			panel.add(label7);
			panel.add(rmupwon1);
			panel.add(rmupwon11);
			panel.add(rmupwon2);
			panel.add(rmupwon22);
			panel.add(rmupwon3);
			panel.add(rmupwon33);

		}

	}// setting

	class EquipSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// �����̱ⱸ �׼Ǹ�����
			if (e.getActionCommand().equals("�����̱ⱸ")) {
				System.out.println("tbk");
				
				String[] buttons= {"�翬����","�ѹ� �� ���"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"�ǰ��� ������ �̰� ��ٰ�??","�αۺαۺн�",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {				
				
				if (equipsLv[0] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[0] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[0] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("���� ����~");
				}
			}else {
				System.out.println("���");
			}
			}
			// Ƣ��ⱸ �׼Ǹ�����
			if (e.getActionCommand().equals("Ƣ��ⱸ")) {
				System.out.println("tk");
				
				String[] buttons= {"�翬����","�ѹ� �� ���"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"�ǰ��� ������ �̰� ��ٰ�??","�αۺαۺн�",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {

				if (equipsLv[1] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[1] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[1] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[1] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("���� ����~");
				}
			}
			}
			// �����ⱸ �׼Ǹ�����
			if (e.getActionCommand().equals("�����ⱸ")) {
				System.out.println("od");
				
				String[] buttons= {"�翬����","�ѹ� �� ���"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"�ǰ��� ������ �̰� ��ٰ�??","�αۺαۺн�",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {

				if (equipsLv[2] == 0) {					
					if (m.getGold() >= level1) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[2] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[2] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[2] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("���� ����~");
				}
			}
			}

			// ���ⱸ �׼Ǹ�����
			if (e.getActionCommand().equals("���ⱸ")) {
				System.out.println("rm");
				
				String[] buttons= {"�翬����","�ѹ� �� ���"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"�ǰ��� ������ �̰� ��ٰ�??","�αۺαۺн�",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {

				if (equipsLv[3] == 0) {
					if (m.getGold() >= level1) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level1);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[3] == 1) {
					if (m.getGold() >= level2) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level2);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (equipsLv[3] == 2) {
					if (m.getGold() >= level3) {
						equipsLv[3] += 1;
						m.setGold(m.getGold() - level3);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("���� ����~");
				}
			}
			}

			// �������� �׼Ǹ�����
			if (e.getActionCommand().equals("�������� ����")) {
				System.out.println("tbkup");
				
				String[] buttons= {"�翬����","�ѹ� �� ���"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"�ǰ��� ������ �̰� ��ٰ�??","�αۺαۺн�",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {
				
				if (tableLv[0] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}

				} else if (tableLv[0] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[0] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[0] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("���� ����~");

				}
			}
			}

			// Ƣ���� �׼Ǹ�����
			if (e.getActionCommand().equals("Ƣ���� ����")) {
				System.out.println("tkup");
				
				String[] buttons= {"�翬����","�ѹ� �� ���"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"�ǰ��� ������ �̰� ��ٰ�??","�αۺαۺн�",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {
				
				if (tableLv[1] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}

				} else if (tableLv[1] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[1] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[1] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("���� ����~");

				}
			}
			}

			// ������ �׼Ǹ�����
			if (e.getActionCommand().equals("������ ����")) {
				System.out.println("odup");
				
				String[] buttons= {"�翬����","�ѹ� �� ���"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"�ǰ��� ������ �̰� ��ٰ�??","�αۺαۺн�",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {
				
				if (tableLv[2] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[2] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[2] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[2] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("���� ����~");
				}
			}
			}

			// ����� �׼Ǹ�����
			if (e.getActionCommand().equals("����� ����")) {
				System.out.println("rmup");
				
				String[] buttons= {"�翬����","�ѹ� �� ���"};
				int result;
				result=JOptionPane.showOptionDialog(mf,"�ǰ��� ������ �̰� ��ٰ�??","�αۺαۺн�",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
				
				if(result==0) {
				
				if (tableLv[3] == 1) {
					if (m.getGold() >= plevel2) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel2);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[3] == 2) {
					if (m.getGold() >= plevel3) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel3);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else if (tableLv[3] == 3) {
					if (m.getGold() >= plevel4) {
						tableLv[3] += 1;
						m.setGold(m.getGold() - plevel4);
					} else {
						JOptionPane.showMessageDialog(mf, "�� ������ �����~","�αۺα� �н�",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					System.out.println("���� ����~");
				}
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
			temp.setMarketMusic(marketMusic);
			mPanel = temp;
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		Image img = new ImageIcon("images/LoguinFrame01_01.jpg").getImage();
		g.drawImage(img, 0, 0, null);
		repaint();
	}
	public Music getMarketMusic() {
		return marketMusic;
	}
	public void setMarketMusic(Music music) {
		this.marketMusic = music;
	}
}// class