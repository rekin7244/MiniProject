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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kh.miniProject.model.dao.MemberDao;
import com.kh.miniProject.model.vo.member.Member;
/*import com.kh.miniProject.view.LoginPanel.Login;
import com.kh.miniProject.view.LoginPanel.guest;
*/

public class JoinPanel extends JPanel {
	BufferedImage img = null;
	JTextField IDText;
	JPasswordField passText;
	JTextField EmailText;
	String inputId;
	String inputPass;
	String inputEamil;
	JButton joinbt;
	JButton cancelbt;

	private MainFrame mf;
	/* private JPanel mainPage; */
	private JPanel jView;

	private MemberDao memberDao;
	private Member m;

	public JoinPanel(MainFrame mf) {
		this.mf = mf;
		this.jView = this;
		memberDao = new MemberDao();

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
		joinbt = new JButton("가입하기 ");
		joinbt.setBounds(400, 510, 90, 30);

		// 가입취소 버튼 추가
		cancelbt = new JButton("가입 취소");
		cancelbt.setBounds(510, 510, 90, 30);
		
		
		

		// 마지막 추가들

		this.add(ID);
		this.add(Pass);
		this.add(Email);

		layeredPane.add(IDText);
		layeredPane.add(passText);
		layeredPane.add(EmailText);
		this.add(joinbt);
		this.add(cancelbt);
		joinbt.addActionListener(new BtnAction());
		cancelbt.addActionListener(new BtnAction());
		layeredPane.add(panel);
		add(layeredPane);
	}

	class joinimg extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	private class BtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// 가입 하기
			if (e.getSource() == joinbt) {
				if (memberDao.JoinMember(IDText.getText(), EmailText.getText())) {
					String inputPass = "";
					char[] pass = passText.getPassword();
					for (int i = 0; i < pass.length; i++) {
						inputPass += pass[i];
					}
					memberDao.addMember(new Member(IDText.getText(), inputPass, EmailText.getText()));
					new ChangePanel().changePanel(mf, jView, new LoginPanel(mf));
				} else {
					System.out.println("이미 존재하는 아이디 입니다...");
				}
			}

			/*
			 * if(e.getSource() ==joinbt ) { new ChangePanel().changePanel(mf, jView, new
			 * LoginPanel(mf)); }
			 */
			// 가입취소 버튼 기능
			if (e.getSource() == cancelbt) {
				new ChangePanel().changePanel(mf, jView, new LoginPanel(mf));
			}
		}

	}

	/*
	 * private class join implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent arg0) { // TODO
	 * Auto-generated method stub System.out.println("가입 버튼 클릭 성공");
	 * 
	 * } }
	 * 
	 * private class Join implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub if(e.getSource() == joinbt) { new ChangePanel().changePanel(mf,
	 * jView, new LoginPanel(mf)); } }
	 * 
	 * 
	 * }
	 * 
	 * private class Cancel implements ActionListener{
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
	 * method stub if(e.getSource() == cancelbt) { new ChangePanel().changePanel(mf,
	 * jView, new LoginPanel(mf)); } }
	 * 
	 * 
	 * }
	 */

}
