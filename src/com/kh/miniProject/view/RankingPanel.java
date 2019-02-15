package com.kh.miniProject.view;

import java.awt.*;
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
import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.Music;



public class RankingPanel extends JPanel {

	private JTable tb;
	private String[] menu = {"등수","아이디","최고스테이지","소유골드"};
	private String rowData2[][] = new String[3][16];
	private JScrollPane scroll;
	private int point;
	private ArrayList<Member> tempMember = new ArrayList<Member>();

	DefaultTableModel defaultTable;

	private JPanel rPanel;
	private JLabel a;
	private Font f1;
	private Image backImage = new ImageIcon("images/rank.jpg"/*"images/ranking.jpg"*/).getImage();
	private JScrollPane table;
	private MainFrame mf;

	
	private Timer rangTimer;
	private int rangIndex;
	private JLabel[] medalLabel;
	private Image[] medalImage = {
								new ImageIcon("images/best.png").getImage().getScaledInstance(30, 30, 0),
								new ImageIcon("images/second.png").getImage().getScaledInstance(30, 30, 0),
								new ImageIcon("images/third.png").getImage().getScaledInstance(30, 30, 0)
	};
	
	
	
	private DefaultTableCellRenderer celAlignCenter;
	private DefaultTableCellRenderer[] rankingRenderer;

	public RankingPanel(MainFrame mf) {
		this.mf = mf;
		this.rPanel = this;
		medalLabel = new JLabel[3];
		/*medalLabel = new JLabel[3];*/
		this.setLayout(null);
		
		memberRead();
		sortScore();

	    celAlignCenter = new DefaultTableCellRenderer();   
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);//가운데정렬
	    //celAlignCenter.setIcon(new ImageIcon(medalImage[0]));
	    
	    rankingRenderer = new DefaultTableCellRenderer[3];
	    
	    for(int i=0;i<rankingRenderer.length;i++) {
	    	rankingRenderer[i] = new DefaultTableCellRenderer();
	    	rankingRenderer[i].setIcon(new ImageIcon(medalImage[i]));
	    }
	    
	    
		tb = new JTable();
		tb.setBounds(175,200,700,230);
		defaultTable = new DefaultTableModel(menu, tempMember.size()) {
			public boolean isCellEditable(int rowIndex,int colIndex) {
				return false;
			}
		};
		tb.setModel(defaultTable);
		tb.getTableHeader().setReorderingAllowed(false);
		tb.getTableHeader().setResizingAllowed(false);

		int max =0;
		
		for(int i=0; i<tempMember.size();i++) {
			
			
			if(i>=3) {
			defaultTable.setValueAt(i+1, i, 0);
			defaultTable.setValueAt(tempMember.get(i).getMemberId(), i, 1);
			defaultTable.setValueAt(tempMember.get(i).getMaxStage(), i, 2);
			defaultTable.setValueAt(tempMember.get(i).getGold(), i, 3);
		
			}
			
			/*if(tempMember.get(i).getMemberId().length()>max) {
				max = tempMember.get(i).getMemberId().length();
				System.out.println("max : " + max);
			}*/
			tb.setRowHeight(i, 40);		
		}
		for(int i=0;i<4;i++) {
			tb.getColumnModel().getColumn(i).setCellRenderer(celAlignCenter);		
		}
		
		tb.getColumn("아이디").setPreferredWidth(max);
		tb.getColumn("최고스테이지").setPreferredWidth(6);
		tb.getColumn("등수").setPreferredWidth(1);
		
		
		rangTimer = new Timer(500,new RankingTimer());
		rangTimer.start();
		rangIndex = 2;
		
		int temp =50;
		
		for(int i=0;i<medalLabel.length;i++) {
			medalLabel[i] = new JLabel("");
			medalLabel[i].setSize(30, 30);

			temp-=50;
			this.add(medalLabel[i]);
		}
		
		table = new JScrollPane(tb,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		table.setBounds(175,200,650,230);
		table.setOpaque(false);
		tb.setOpaque(false);

		

		this.add(table);
		
		
		table.setOpaque(false);
		table.getViewport().setOpaque(false);
		
		this.setSize(1024,768);
		

		JTableHeader table1 = tb.getTableHeader();
		table1.setBackground(Color.black); 
		table1.setForeground(Color.yellow);
		table1.setBounds(280, 170, 450, 30);
		this.add(table1);

		JButton btn1 = new JButton("Main");
		btn1.setBounds(380, 500, 300, 40);
		this.add(btn1);
		btn1.addActionListener(new BackBtn());

		f1 = new Font("Dialog",Font.BOLD,50);

		a = new JLabel("TOTAL RANKING");
		a.setForeground(Color.RED); //얘가 윗것보다 아래에 있어야 함  
		a.setSize(500,100);
		a.setLocation(300,50);
		a.setFont(f1);
		
		rPanel = this;
		this.add(a);
		
		Music music = new Music("드럼.mp3",false);
		music.start();
	}		

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawImage(backImage,0,0, null);
		
		
	}
	
	


	public void sortScore() {
		tempMember.sort(new Comparator<Member>(){

			int result = 0;
			@Override
			public int compare (Member m1,Member m2) {
				if(m1.getMaxStage() > m2.getMaxStage()) {   		//m1이 m2보다 높은 스테이지일경우
					result = -1;
				}else if(m1.getMaxStage() == m2.getMaxStage()) {	//m1이 m2보다 낮은 스테이지일경우
					if(m1.getGold() > m2.getGold()) {   			//m1점수가 m2점수보다 높을때
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
			System.out.println("member 부족");
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
				ChangePanel.changePanel(mf, rPanel, new LoginPanel(mf));
			}
		}
	}
	
	class RankingTimer implements ActionListener{
		private int time = 3;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			/*DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();   
			celAlignCenter.setHorizontalAlignment(JLabel.CENTER);//가운데정렬
*/			
			if(time==0) {
				rangTimer.stop();
			}else {
				defaultTable.setValueAt(rangIndex+1, rangIndex, 0);
				defaultTable.setValueAt(tempMember.get(rangIndex).getMemberId(), rangIndex, 1);
				defaultTable.setValueAt(tempMember.get(rangIndex).getMaxStage(), rangIndex, 2);
				defaultTable.setValueAt(tempMember.get(rangIndex).getGold(), rangIndex, 3);
				tb.getColumnModel().getColumn(0).setCellRenderer(rankingRenderer[rangIndex]);
			
				//tb.getCellRenderer(0, 0)
				
				//getCellRenderer ;
				
			rangIndex--;
			time--;
			}
		}
	

	}
}	










