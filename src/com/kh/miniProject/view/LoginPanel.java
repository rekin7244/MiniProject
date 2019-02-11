package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.miniProject.model.dao.MemberDao;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.Music;

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
	Dialog dialog;
	private MemberDao memberDao;
	private Member m;
	private Music titleMusic;

	// ������
	public LoginPanel(MainFrame mf) {
		this.mf = mf;
		this.lView = this;
		
		memberDao = new MemberDao();		//���Dao ���� (�����ڿ� ���� ����� ��� �� �ҷ���)
		//memberDao.removeMember("master", "1234");
		//memberDao.addMember(new Member("test","pass","email"));
		titleMusic = new Music("TitleMusic.mp3",false);
		titleMusic.start();

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
		this.add(ID);
		this.add(Pass);
		layeredPane.add(IDText);
		layeredPane.add(passText);
		this.add(loginbt);
		this.add(guestbt); 
		this.add(Joinbt);
		loginbt.addMouseListener(new BtnAction());; 
		guestbt.addMouseListener(new BtnAction());
		Joinbt.addMouseListener(new BtnAction());
		layeredPane.add(panel);
		add(layeredPane);
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	private class BtnAction implements MouseListener {

	

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == loginbt) {
				Music buttonEnteredMusic = new Music("decision8.mp3",false);
				buttonEnteredMusic.start();
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
					titleMusic.close();				
				}else {
					String[] command = {"ȸ������","���̵�, ��й�ȣ ã��"};
					int result;

					result = JOptionPane.showOptionDialog(null,"���̵�, ��й�ȣ�� Ȯ�����ּ���","�αۺαۺн�",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, command, command[0]);
					if(result==0) {
						ChangePanel.changePanel(mf, lView, new JoinPanel(mf));
					}else if(result==1){
						//�̸��� �Է¹޴� â
						//�̸��� �Է��ϸ� orderDao.searchMember() �����Ͽ� member��ü �ҷ���
						//�ҷ��� member��ü�κ��� id,pass ���
						String str = JOptionPane.showInputDialog("�̸����� �Է��ϼ���");
						Member tempM = memberDao.searchMember(str);
						if(tempM == null) {
							JOptionPane.showMessageDialog(null, "������ ȸ���� �ƴմϴ�.");
						} else {
						JOptionPane.showMessageDialog(null, "���̵� : " + tempM.getMemberId()+ "\n" + "��й�ȣ : " + tempM.getMemberPwd());
						}
					}
				}
			}
			if(e.getSource() == guestbt) {
				ChangePanel.changePanel(mf, lView, new StageView(mf,new Member("guest","guestpass","guestemail")));
				titleMusic.close();
			}
			if(e.getSource() == Joinbt) {
				ChangePanel.changePanel(mf, lView, new JoinPanel(mf));
				titleMusic.close();
			}
		}
		


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			Music buttonEnteredMusic = new Music("cursor7.mp3",false);
			buttonEnteredMusic.start();
		}



		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
		
	}
}