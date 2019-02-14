package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
	private JTextField IDText; 			//아이디필드
	private JPasswordField passText; 	//패스워드필드
	private JTextField EmailText; 		//이메일 필드
	private JButton joinbt; 			//가입하기버튼
	private JButton cancelbt; 			//가입취소 버튼
	private JButton IDCheck; 			//아이디 중복확인
	private JButton EmailCheck; 		//이메일 중복확인

	private MainFrame mf;
	private JPanel jView;
	private MemberDao memberDao = new MemberDao();
	
	private Music titleMusic;


	public JoinPanel(MainFrame mf) {
		this.mf = mf;
		this.jView = this;

		setSize(1024, 768);
		// 레이아웃 설정
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1024, 768);
		layeredPane.setLayout(null);

		// 패널
		// 이미지 받아오기

		try {
			img = ImageIO.read(new File("images/LoguinFrame01_01.jpg"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		joinimg panel = new joinimg();
		panel.setBounds(0, 0, 1024, 768);

		// ID 라벨
		JLabel ID = new JLabel("아이디 :");

		ID.setBounds(346, 200, 200, 370);

		// Pass 라벨

		JLabel Pass = new JLabel("비밀번호:");
		Pass.setBounds(346, 250, 200, 370);

		// Email 라벨
		JLabel Email = new JLabel("이메일:");
		Email.setBounds(346, 300, 200, 370);

		// ID 텍스트 필드
		IDText = new JTextField(15);
		IDText.setBounds(400, 370, 200, 30);
		IDText.setForeground(Color.BLACK);
		IDText.setBackground(Color.WHITE);

		/*
		 * JLabel Pass = new JLabel("비밀번호:"); Pass.setBounds(346, 250, 200, 370);
		 */
		// pass 텍스트 필드
		passText = new JPasswordField(15);
		passText.setBounds(400, 420, 200, 30);
		passText.setForeground(Color.BLACK);
		passText.setBackground(Color.white);

		// EmailText 텍스트 필드
		EmailText = new JTextField(15);
		EmailText.setBounds(400, 470, 200, 30);
		EmailText.setForeground(Color.BLACK);
		EmailText.setBackground(Color.white);

		// 가입하기 버튼 추가
		joinbt = new JButton();
		joinbt.setContentAreaFilled(false);
		joinbt.setBorderPainted(false);
		joinbt.setIcon(new ImageIcon(new ImageIcon("images/btnImage/가입하기.png").getImage().getScaledInstance(90, 40, 0)));
		joinbt.setBounds(400, 510, 110, 30);

		// 가입취소 버튼 추가
		cancelbt = new JButton();
		cancelbt.setContentAreaFilled(false);
		cancelbt.setBorderPainted(false);
		cancelbt.setIcon(new ImageIcon(new ImageIcon("images/btnImage/가입취소.png").getImage().getScaledInstance(90, 40, 0)));
		cancelbt.setBounds(510, 510, 110, 30);

		//아이디 중복확인 버튼
		IDCheck = new JButton("확인");
		IDCheck.setBounds(615, 370, 70, 30);

		//이메일 중복확인 버튼
		EmailCheck = new JButton("확인");
		EmailCheck.setBounds(614, 470, 70, 30);

		// 마지막 추가들

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

			//아이디 중복확인
			if(e.getSource() == IDCheck) {
				if(!IDText.getText().equals("")) {
					if(memberDao.checkID(IDText.getText())&&!IDText.getText().equals("guest")) {	//ID 중복 체크 및 guest 가 아닌지 확인
						JOptionPane.showMessageDialog(mf, "사용가능한 아이디 입니다."); 

					} else {
						JOptionPane.showMessageDialog(mf, "이미 존재하는 아이디입니다.");
					}
				}else {
					JOptionPane.showMessageDialog(mf, "아이디를 입력해주세요.");
				}
			}

			//이메일 중복확인
			if(e.getSource() == EmailCheck) {
				if(!EmailText.getText().equals("")) {
					if(memberDao.checkEmail(EmailText.getText())) {
						JOptionPane.showMessageDialog(mf, "사용가능한 이메일 입니다."); 
					} else {
						JOptionPane.showMessageDialog(mf, "이미 존재하는 이메일입니다."); 
					}
				}else {
					JOptionPane.showMessageDialog(mf, "이메일을 입력해주세요.");
				}
			}
			//가입하기
			if (e.getSource() == joinbt) {
				//아이디 이미 존재하는지 확인
				if(!IDText.getText().equals("")&&!EmailText.getText().equals("")) {
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
						JOptionPane.showMessageDialog(mf, "아이디,이메일을 확인해주세요");
					}
				}else {
					JOptionPane.showMessageDialog(mf, "아이디,이메일을 확인해주세요");
				}

			}
			//가입 취소
			if (e.getSource() == cancelbt) {
				new ChangePanel().changePanel(mf, jView, new LoginPanel(mf));
				titleMusic.close();
			}

		}
	}

}
