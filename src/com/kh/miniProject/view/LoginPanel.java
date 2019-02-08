package com.kh.miniProject.view;

import java.awt.Color;
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

import com.kh.miniProject.model.vo.member.Member;

public class LoginPanel extends JPanel {

	BufferedImage img = null;
	JTextField IDText;
	JPasswordField passText;
	JButton loginbt;
	JButton Joinbt;
	JButton guestbt;
	private MainFrame mf;
	private JPanel mainPage;
	private Member m;
	private JPanel lView;

	 
	
	// 생성자
	public LoginPanel(MainFrame mf, Member m) {
		this.mf = mf;
		this.m = m;
		this.lView = this;
		//this.lView = this;
					
		setSize(1024, 768);
		// 레이아웃 설정
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1024, 768);
		layeredPane.setLayout(null);

		// 패널1
		// 이미지 받아오기
		try {
			img = ImageIO.read(new File("images/LoguinFrame01_01.jpg"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1024, 768);

		// 로그인 필드
		/*
		 * loginTextField = new JTextField(15);
		 * loginTextField.setBounds(348,150,681,369); layeredPane.add(loginTextField);
		 * loginTextField.setOpaque(false); loginTextField.setForeground(Color.black);
		 * loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		 */
		// 로그인 라밸
		JLabel ID = new JLabel("아이디 :");
		// ID.setLocation(100, 200);
		ID.setBounds(346, 200, 200, 370);

		/*this.add(ID);*/

		IDText = new JTextField(15);
		IDText.setBounds(400, 200, 666, 372);
		/*layeredPane.add(IDText);*/
		IDText.setOpaque(false);
		IDText.setForeground(Color.BLACK);
		IDText.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 패스워드 라벨
		JLabel Pass = new JLabel("비밀번호:");
		// Pass.setLocation(100, 200);
		Pass.setBounds(346, 250, 200, 370);

		/*this.add(Pass);*/

		passText = new JPasswordField(15);
		passText.setBounds(400, 250, 666, 372);
		/*layeredPane.add(passText);*/
		passText.setOpaque(false);
		passText.setForeground(Color.BLACK);
		passText.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 패스워드
		/*
		 * passwordField = new JPasswordField(15);
		 * passwordField.setBounds(345,200,677,466); passwordField.setOpaque(false);
		 * passwordField.setForeground(Color.black);
		 * passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		 * layeredPane.add(passwordField);
		 */

		// 로그인버튼 추가
		loginbt = new JButton("로그인");
		// loginbt.setLocation(85, 85);
		loginbt.setBounds(400, 450, 90, 30);

		
		guestbt= new JButton("guest");
		guestbt.setBounds(300, 450, 90, 30); 
		
		
		Joinbt = new JButton("회원가입");
		Joinbt.setBounds(500, 450, 90, 30);
		
		
		
		
		
		
		
		
			
		/*this.add(loginbt);
		loginbt.addActionListener(new Login());*/

		// 회원 가입 버튼 추가

		// 게스트 버튼 추가

		/*
		 * loginbt.setBounds(50,300,676,536); this.add(loginbt);
		 * 
		 * loginbt.addActionListener(new Login());
		 */

		// 버튼 투명처리
		/*
		 * loginbt.setBorderPainted(false); loginbt.setFocusPainted(false);
		 * loginbt.setContentAreaFilled(false);
		 */
		// layeredPane.add(loginbt);

		// 마지막 추가들
		this.add(ID);
		this.add(Pass);
		layeredPane.add(IDText);
		layeredPane.add(passText);
		this.add(loginbt);
		this.add(guestbt); 
		this.add(Joinbt);
		loginbt.addActionListener(new Login()); 
		guestbt.addActionListener(new guest());
		layeredPane.add(panel);
		add(layeredPane);
		setVisible(true);

	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	private class Login implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("로그인 버튼 클릭 성공");
			if(e.getSource() == loginbt) {
				new ChangePanel().changePanel(mf, lView, new StageView(mf,m));
			}
			
			

		}

	}
	
	private class guest implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
		}
		
	}
	
	
}