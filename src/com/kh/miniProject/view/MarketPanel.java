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
	//¹Þ¾Æ¿Ã ÇÊµå
	MainFrame mf;
	Member m;
	//»ç¿ë ÇÊµå
	private int tbkLv = 1;
	JButton mo = new JButton("¶±ººÀÌ±â±¸");
	private int tkLv = 1;
	JButton mo1 = new JButton("Æ¢±è±â±¸");
	//20190208 Ãß°¡
	private int odLv=1;
	JButton mo2=new JButton("¿Àµ­±â±¸");
	private int rmLv=1;
	JButton mo3=new JButton("¶ó¸é±â±¸");

	private int tbkup=1;
	JButton mo4=new JButton("¶±ººÀÌÆÇ ¾÷±Û");
	private int tkup=1;
	JButton mo5=new JButton("Æ¢±èÆÇ ¾÷±Û");
	private int odup=1;
	JButton mo6=new JButton("¿Àµ­ÆÇ ¾÷±Û");
	private int rmup=1;
	JButton mo7=new JButton("¶ó¸éÆÇ ¾÷±Û");

	JPanel mPanel;
	private int[] equipsLv;
	private int[] tableLv;
	private EquipSetting e;


	//¶±ººÀÌ ±â±¸
	private Image[] equipsImages = { new ImageIcon("images/1Lvtbk.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/2Lvtbk.jpg").getImage().getScaledInstance(200, 200, 0) };
	//Æ¢±è ±â±¸
	private Image[] equipsImages1 = { new ImageIcon("images/1Lvfried.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/2Lvfried.jpg").getImage().getScaledInstance(200, 200, 0) };
	//¿Àµ­ ±â±¸
	private Image[] equipsImages2 = { new ImageIcon("images/¿Àµ­Lv1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¿Àµ­Lv2.jpg").getImage().getScaledInstance(200, 200, 0) };
	//¶ó¸é ±â±¸
	private Image[] equipsImages3 = { new ImageIcon("images/¶ó¸éLv1.jpg").getImage().getScaledInstance(200, 200, 0),
			new ImageIcon("images/¶ó¸éLv2.jpg").getImage().getScaledInstance(200, 200, 0) };

	//¶±ººÀÌ ÆÇ
	private Image[] tableImages= {new ImageIcon("images/tbk1.png").getImage().getScaledInstance(200,200,0),
			new ImageIcon("images/tbk2.png").getImage().getScaledInstance(200,200,0) };
	//Æ¢±è ÆÇ
	private Image[] tableImages1= {new ImageIcon("images/fried1.png").getImage().getScaledInstance(200,200,0),
			new ImageIcon("images/fried2.png").getImage().getScaledInstance(200,200,0)};

	//¿Àµ­ ÆÇ
	private Image[] tableImages2= {new ImageIcon("images/fried1.png").getImage().getScaledInstance(200,200,0),
			new ImageIcon("images/fried2.png").getImage().getScaledInstance(200,200,0)};

	//¶ó¸é ÆÇ
	private Image[] tableImages3= {new ImageIcon("images/fried1.png").getImage().getScaledInstance(200,200,0),
			new ImageIcon("images/fried2.png").getImage().getScaledInstance(200,200,0)};

	public MarketPanel(MainFrame mf, Member m) {
		e = new EquipSetting();
		this.mf = mf;
		this.m = m;
		this.equipsLv = m.getEquipsLv();
		this.tableLv=m.getTableLv();
		this.setLayout(null);
		this.setBounds(110, 50, 800, 650);
		this.setBackground(Color.orange);

		// °ñµåÃâ·Â
		JButton gold = new JButton("°ñµå");
		gold.setEnabled(false);
		gold.setBackground(Color.yellow);
		gold.setBounds(0, 0, 200, 30);
		gold.setText("¼ÒÁö±Ý: " + m.getGold() + "G");
		this.add(gold);

		// µ¹¾Æ°¡±â
		JButton returnBtn = new JButton("µ¹¾Æ°¡±â");
		returnBtn.setBounds(660, 620, 140, 30);
		this.add(returnBtn);

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
		mo.setBounds(20, 60, 180, 180);
		mo.removeActionListener(e);
		mo.addActionListener(e);

		//Æ¢±è
		if (equipsLv[1] == 1) {
			mo1.setIcon(new ImageIcon(equipsImages1[0]));
		} else if (equipsLv[1] == 2) {
			mo1.setIcon(new ImageIcon(equipsImages1[1]));
		}
		mo1.setBounds(210, 60, 180, 180);
		mo1.removeActionListener(e);
		mo1.addActionListener(e);

		//¿Àµ­
		if (equipsLv[2] == 1) {
			mo2.setIcon(new ImageIcon(equipsImages2[0]));
		} else if (equipsLv[2] == 2) {
			mo2.setIcon(new ImageIcon(equipsImages2[1]));
		}
		mo2.setBounds(400, 60, 180, 180);
		mo2.removeActionListener(e);
		mo2.addActionListener(e);

		//¶ó¸é		
		if (equipsLv[3] == 1) {
			mo3.setIcon(new ImageIcon(equipsImages3[0]));
		} else if (equipsLv[3] == 2) {
			mo3.setIcon(new ImageIcon(equipsImages3[1]));
		}
		mo3.setBounds(590, 60, 180, 180);
		mo3.removeActionListener(e);
		mo3.addActionListener(e);

		//¶±ººÀÌÆÇ
		if(tableLv[0]==1) {
			mo4.setIcon(new ImageIcon(tableImages[0]));
		}else if(equipsLv[0]==2) {
			mo4.setIcon(new ImageIcon(tableImages[1]));
		}
		mo4.setBounds(20,360, 180,180);
		mo4.removeActionListener(e);
		mo4.addActionListener(e);

		//Æ¢±è ÆÇ		
		if(tableLv[1]==1) {
			mo5.setIcon(new ImageIcon(tableImages1[0]));
		}else if(tableLv[1]==2) {
			mo5.setIcon(new ImageIcon(tableImages1[1]));
		}
		mo5.setBounds(220,360, 180,180);
		mo5.removeActionListener(e);
		mo5.addActionListener(e);

		//¿Àµ­ ÆÇ
		if(tableLv[2]==1) {
			mo6.setIcon(new ImageIcon(tableImages2[0]));
		}else if(tableLv[2]==2) {
			mo6.setIcon(new ImageIcon(tableImages2[1]));
		}
		mo6.setBounds(420,360, 180,180);
		mo6.removeActionListener(e);
		mo6.addActionListener(e);

		//¶ó¸é ÆÇ
		if(tableLv[3]==1) {
			mo7.setIcon(new ImageIcon(tableImages3[0]));
		}else if(tableLv[3]==2) {
			mo7.setIcon(new ImageIcon(tableImages3[1]));
		}
		mo7.setBounds(620,360, 180,180);
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

		//¶óº§
		JLabel label=new JLabel();
		label.setText("¶±ººÀÌ ¾÷±×·¹ÀÌµå");
		label.setBounds(50,160,200,200);
		panel.add(label);
		JLabel label1=new JLabel();
		label1.setText("Æ¢±è ¾÷±×·¹ÀÌµå");
		label1.setBounds(250,160,200,200);
		panel.add(label1);
		JLabel label2=new JLabel();
		label2.setText("¿Àµ­ ¾÷±×·¹ÀÌµå");
		label2.setBounds(450,160,200,200);
		panel.add(label2);
		JLabel label3=new JLabel();
		label3.setText("¶ó¸é ¾÷±×·¹ÀÌµå");
		label3.setBounds(630,160,200,200);
		panel.add(label3);
		//		panel.setVisible(true);


	}

	class EquipSetting implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("¶±ººÀÌ±â±¸")) {
				System.out.println("tbk");
				if (equipsLv[0] == 1) {
					if(m.getGold()>50000) {
						equipsLv[0] += 1;
						m.setGold(m.getGold()-50000);
					}else {
						System.out.println("money more");
					}
				} else {
					System.out.println("lv max error");
				}
				refresh();
			}
			if (e.getActionCommand().equals("Æ¢±è±â±¸")) {
				System.out.println("tk");
				if (equipsLv[1] == 0) {
					if(m.getGold()>20000) {
						equipsLv[1] += 1;
						m.setGold(m.getGold()-20000);
					}else {
						System.out.println("money more");
					}
				} else {
					System.out.println("lv max error");
				}
				refresh();
			}
			if(e.getActionCommand().equals("¿Àµ­±â±¸")) {
				System.out.println("od");
				if(equipsLv[2]==1) {
					equipsLv[2]+=1;
				}else {
					System.out.println("lv max error");
				}
				refresh();
			}
			if(e.getActionCommand().equals("¶ó¸é±â±¸")) {
				System.out.println("rm");
				if(equipsLv[3]==1) {
					equipsLv[3]+=1;
				}else {
					System.out.println("lv max error");
				}
				refresh();				
			}
			if(e.getActionCommand().equals("¶±ººÀÌÆÇ ¾÷±Û")) {
				System.out.println("tbkup");
				if(tableLv[0]==1) {
					tableLv[0]+=1;
				}else {
					System.out.println("lv max error");
				}
				refresh();
			}
			if(e.getActionCommand().equals("Æ¢±èÆÇ ¾÷±Û")) {
				System.out.println("tkup");
				if(tableLv[1]==1) {
					tableLv[1]+=1;
				}else {
					System.out.println("lv max error");
				}
				refresh();
			}
			if(e.getActionCommand().equals("¿Àµ­ÆÇ ¾÷±Û")) {
				System.out.println("odup");
				if(tableLv[2]==1) {
					tableLv[2]+=1;
				}else {
					System.out.println("lv max error");
				}
				refresh();
			}
			if(e.getActionCommand().equals("¶ó¸éÆÇ ¾÷±Û")) {
				System.out.println("rmup");
				if(tableLv[3]==1) {
					tableLv[3]+=1;
				}else {
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
			m.setEquipsLv(equipsLv);
			m.setTableLv(tableLv);
		}
	}
}//class
