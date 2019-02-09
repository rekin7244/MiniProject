package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.miniProject.model.dao.MemberDao;
import com.kh.miniProject.model.vo.member.Member;

public class LoginPanel extends JPanel {
	private MainFrame mf;
	private LoginPanel lView;

	BufferedImage img = null;
	JTextField IDText;
	String inputId;
	JPasswordField passText;
	String inputPass;
	
	
	
	JButton loginbt;
	JButton Joinbt;
	JButton guestbt;
	
	private MemberDao memberDao;
	private Member m;

	// ������
	public LoginPanel(MainFrame mf) {
		this.mf = mf;
		this.lView = this;
		memberDao = new MemberDao();		//���Dao ���� (�����ڿ� ���� ����� ��� �� �ҷ���)

		//���� ������ �Է�
		//memberDao.addMember(new Member("test","pass","email"));
		memberDao.printMember();	//����Ȯ��

		setSize(1024, 768);
		// ���̾ƿ� ����
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1024, 768);
		layeredPane.setLayout(null);

		// �̹��� �޾ƿ���
		try {
			img = ImageIO.read(new File("images/LoguinFrame01_01.jpg"));
		} catch (IOException e) {
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1024, 768);

		// �α��� ���
		JLabel ID = new JLabel("���̵� :");
		ID.setBounds(346, 200, 200, 370);

		IDText = new JTextField(15);
		IDText.setBounds(400, 370, 200, 30);
		IDText.setForeground(Color.BLACK);
		IDText.setBackground(Color.WHITE);
		// �н����� ��
		JLabel Pass = new JLabel("��й�ȣ:");
		Pass.setBounds(346, 250, 200, 370);

		passText = new JPasswordField(15);
		passText.setBounds(400, 420, 200, 30);
		passText.setForeground(Color.BLACK);
		passText.setBackground(Color.white);


		// �α��ι�ư �߰�
		loginbt = new JButton("�α���");
		loginbt.setBounds(400, 460, 200, 30);

		guestbt= new JButton("guest");
		guestbt.setBounds(510, 510, 90, 30); 

		Joinbt = new JButton("ȸ������");
		Joinbt.setBounds(400, 510, 90, 30);

		// ������ �߰���
		layeredPane.add(panel);
		this.add(ID);
		this.add(Pass);
		layeredPane.add(IDText);
		layeredPane.add(passText);
		this.add(loginbt);
		this.add(guestbt); 
		this.add(Joinbt);
		loginbt.addActionListener(new BtnAction()); 
		guestbt.addActionListener(new BtnAction());
		Joinbt.addActionListener(new BtnAction());
		//layeredPane.add(panel);
		add(layeredPane);
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	private class BtnAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == loginbt) {
				inputId = IDText.getText();
				inputPass = "";
				char[] pass = passText.getPassword();
				for (int i = 0; i < pass.length; i++) {
					inputPass += pass[i];
				}
				System.out.println("inputId : "+inputId);
				System.out.println("inputPass : "+inputPass);
				if((m=memberDao.loginMember(inputId, inputPass))!=null) {
					new ChangePanel().changePanel(mf, lView, new StageView(mf,m));					
				}else {
					
					JFrame 	fr;
					Dialog  da;
					JPanel  pa; 
					
					fr=new JFrame("�α��� �ǻ�");
					fr.setBounds(325, 600, 350, 90);
					
					pa=new JPanel();
					pa.setBounds(400,200 , 300, 300);
					
					JButton join =new JButton("ȸ�� ����");
					join.setBounds(150, 250, 30, 30); 
					
					JButton search=new JButton("���� ã��");
					search.setBounds(100, 250, 30, 30); 
					
					
					
					
					da=new Dialog(fr);
					
					
					
					fr.add(pa);
					
					pa.add(join);
					pa.add(search);
					
					
					fr.setVisible(true);
				
					
					
					
					
				}
			}
			if(e.getSource() == guestbt) {
				new ChangePanel().changePanel(mf, lView, new StageView(mf,new Member("guest","pass","email")));
			}
			//ȸ������
			if(e.getSource() == Joinbt) {
				new ChangePanel().changePanel(mf, lView, new JoinPanel(mf));
			}
		}
	}
	private class tnAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}


}

