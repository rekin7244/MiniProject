package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.kh.miniProject.model.vo.member.Member;



public class RankingPanel extends JPanel {




	private JTable tb;

	private String[] menu = {"���","���̵�","�ְ�������","�������"};


	private String rowData2[][] = new String[3][16];

	private JScrollPane scroll;

	private int point;
	private ArrayList<Member> tempMember = new ArrayList<Member>();




	DefaultTableModel defaultTable;

	private JPanel rPanel;
	private JLabel a;
	private Font f1;
	private Image backImage = new ImageIcon("C:\\Users\\y_s_r\\OneDrive\\���� ȭ��\\back.jpg"/*"images/ranking.jpg"*/).getImage();
	private JScrollPane table;
	private MainFrame mf;

	public RankingPanel(MainFrame mf) {
		this.mf = mf;
		memberRead();
		sortScore();


		tb = new JTable();
		this.add(tb);



		tb.setBounds(280,200,450,300);

		defaultTable = new DefaultTableModel(menu, tempMember.size());

		tb.setModel(defaultTable);


		for(int i=0; i<tempMember.size();i++) {
			defaultTable.setValueAt(i+1, i, 0);
			defaultTable.setValueAt(tempMember.get(i).getMemberId(), i, 1);
			defaultTable.setValueAt(tempMember.get(i).getMaxStage(), i, 2);
			defaultTable.setValueAt(tempMember.get(i).getGold(), i, 3);

		}

		this.setSize(1024,768);
		this.setLayout(null);


		JTableHeader table1 = tb.getTableHeader();
		table1.setBackground(Color.black); 
		table1.setForeground(Color.yellow);
		table1.setBounds(280, 170, 450, 30);
		this.add(table1);

		JButton btn1 = new JButton("Main");
		btn1.setBounds(380, 500, 300, 40);
		this.add(btn1);
		btn1.addActionListener(new BackBtn());

		f1 = new Font("�ü�ü",Font.BOLD,50);

		a = new JLabel("�αۺα� �н�");
		a.setForeground(Color.RED); //�갡 ���ͺ��� �Ʒ��� �־�� ��  


		a.setSize(500,100);
		a.setLocation(320,50);

		a.setFont(f1);
		rPanel = this;
		this.add(a);
	}		

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("ȣ��?");
		g.setColor(Color.black);
		g.drawImage(backImage,0,0, null);


	}

	public void sortScore() {
		tempMember.sort(new Comparator<Member>(){

			int result = 0;
			@Override
			public int compare (Member m1,Member m2) {
				if(m1.getMaxStage() > m2.getMaxStage()) {    //m1�� m2���� ���� ���������ϰ��
					result = -1;


				}else if(m1.getMaxStage() == m2.getMaxStage()) {//m1�� m2���� ���� ���������ϰ��


					if(m1.getGold() > m2.getGold()) {   //m1������ m2�������� ������
						result = -1;

					}else if(m1.getGold() < m2.getGold()) {
						result = 1;

					}else {
						result = 0;
					}

				}else if(m1.getMaxStage() < m2.getMaxStage()){
					result = 1;

				}else {
					result = 1;
				}
				return result;
			}
		});
	}

	public void memberRead() {
		Member[] testMember = new Member[16];

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("members.dat"));	
			for(int i=0; i<testMember.length;i++) {
				tempMember.add((Member)ois.readObject());
				System.out.println(tempMember.get(i));
			}
		}catch (EOFException e) {
			System.out.println("member ����");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	class BackBtn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Main")) {
				new ChangePanel().changePanel(mf, rPanel, new LoginPanel(mf));
			}
		}
		
	}
}









