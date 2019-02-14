package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.miniProject.model.dao.MemberDao;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.Music;


public class JoinPanel extends JPanel {
	private BufferedImage img = null;
	private JTextField IDText; 			//���̵��ʵ�
	private JPasswordField passText; 	//�н������ʵ�
	private JTextField EmailText; 		//�̸��� �ʵ�
	private JButton joinbt; 			//�����ϱ��ư
	private JButton cancelbt; 			//������� ��ư
	private JButton IDCheck; 			//���̵� �ߺ�Ȯ��
	private JButton EmailCheck; 		//�̸��� �ߺ�Ȯ��

	private MainFrame mf;
	private JPanel jView;
	private MemberDao memberDao = new MemberDao();
	
	private Music titleMusic;


	public JoinPanel(MainFrame mf) {
		this.mf = mf;
		this.jView = this;

		setSize(1024, 768);
		// ���̾ƿ� ����
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1024, 768);
		layeredPane.setLayout(null);

		// �г�
		// �̹��� �޾ƿ���

		try {
			img = ImageIO.read(new File("images/LoguinFrame01_01.jpg"));
		} catch (IOException e) {
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0);
		}

		joinimg panel = new joinimg();
		panel.setBounds(0, 0, 1024, 768);

		// ID ��
		JLabel ID = new JLabel("���̵� :");

		ID.setBounds(346, 200, 200, 370);

		// Pass ��

		JLabel Pass = new JLabel("��й�ȣ:");
		Pass.setBounds(346, 250, 200, 370);

		// Email ��
		JLabel Email = new JLabel("�̸���:");
		Email.setBounds(346, 300, 200, 370);

		// ID �ؽ�Ʈ �ʵ�
		IDText = new JTextField(15);
		IDText.setBounds(400, 370, 200, 30);
		IDText.setForeground(Color.BLACK);
		IDText.setBackground(Color.WHITE);

		/*
		 * JLabel Pass = new JLabel("��й�ȣ:"); Pass.setBounds(346, 250, 200, 370);
		 */
		// pass �ؽ�Ʈ �ʵ�
		passText = new JPasswordField(15);
		passText.setBounds(400, 420, 200, 30);
		passText.setForeground(Color.BLACK);
		passText.setBackground(Color.white);

		// EmailText �ؽ�Ʈ �ʵ�
		EmailText = new JTextField(15);
		EmailText.setBounds(400, 470, 200, 30);
		EmailText.setForeground(Color.BLACK);
		EmailText.setBackground(Color.white);

		// �����ϱ� ��ư �߰�
		joinbt = new JButton("�����ϱ� ");
		joinbt.setBounds(400, 510, 90, 30);

		// ������� ��ư �߰�
		cancelbt = new JButton("���� ���");
		cancelbt.setBounds(510, 510, 90, 30);

		//���̵� �ߺ�Ȯ�� ��ư
		IDCheck = new JButton("Ȯ��");
		IDCheck.setBounds(615, 370, 70, 30);

		//�̸��� �ߺ�Ȯ�� ��ư
		EmailCheck = new JButton("Ȯ��");
		EmailCheck.setBounds(614, 470, 70, 30);

		// ������ �߰���

		this.add(ID);
		this.add(Pass);
		this.add(Email);

		layeredPane.add(IDText);
		layeredPane.add(passText);
		layeredPane.add(EmailText);
		this.add(joinbt);
		this.add(cancelbt);
		this.add(IDCheck);
		this.add(EmailCheck);
		joinbt.addActionListener(new BtnAction());
		cancelbt.addActionListener(new BtnAction());
		IDCheck.addActionListener(new BtnAction());
		EmailCheck.addActionListener(new BtnAction());
		layeredPane.add(panel);
		add(layeredPane);
	}
	
	public void setTitleMusic(Music titleMusic) {
		this.titleMusic = titleMusic;
	}

	class joinimg extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	private class BtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			//���̵� �ߺ�Ȯ��
			if(e.getSource() == IDCheck) {
				if(!IDText.getText().equals("")) {
					if(memberDao.checkID(IDText.getText())&&!IDText.getText().equals("guest")) {	//ID �ߺ� üũ �� guest �� �ƴ��� Ȯ��
						JOptionPane.showMessageDialog(mf, "��밡���� ���̵� �Դϴ�."); 

					} else {
						JOptionPane.showMessageDialog(mf, "�̹� �����ϴ� ���̵��Դϴ�.");
					}
				}else {
					JOptionPane.showMessageDialog(mf, "���̵� �Է����ּ���.");
				}
			}

			//�̸��� �ߺ�Ȯ��
			if(e.getSource() == EmailCheck) {
				if(!EmailText.getText().equals("")) {
					if(memberDao.checkEmail(EmailText.getText())) {
						JOptionPane.showMessageDialog(mf, "��밡���� �̸��� �Դϴ�."); 
					} else {
						JOptionPane.showMessageDialog(mf, "�̹� �����ϴ� �̸����Դϴ�."); 
					}
				}else {
					JOptionPane.showMessageDialog(mf, "�̸����� �Է����ּ���.");
				}
			}
			//�����ϱ�
			if (e.getSource() == joinbt) {
				//���̵� �̹� �����ϴ��� Ȯ��
				if(memberDao.checkMember(IDText.getText(), EmailText.getText())) {
					String inputPass = "";
					char[] pass = passText.getPassword();
					for (int i = 0; i < pass.length; i++) {
						inputPass += pass[i];
					}
					memberDao.addMember(new Member(IDText.getText(), inputPass, EmailText.getText()));
					new ChangePanel().changePanel(mf, jView, new LoginPanel(mf));
					titleMusic.close();
				} else {
					JOptionPane.showMessageDialog(mf, "���̵�/�̸��� �ߺ�Ȯ���� ���ּ���");
				}

			}
			//���� ���
			if (e.getSource() == cancelbt) {
				new ChangePanel().changePanel(mf, jView, new LoginPanel(mf));
				titleMusic.close();
			}

		}
	}

}
