package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Dialog;
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
import javax.swing.JOptionPane;
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
	Dialog dialog;
	private MemberDao memberDao;
	private Member m;

	// 생성자
	public LoginPanel(MainFrame mf) {
		this.mf = mf;
		this.lView = this;
		memberDao = new MemberDao();		//멤버Dao 실행 (생성자에 의해 저장된 멤버 다 불러옴)
		//memberDao.removeMember("test", "pass");
		//memberDao.addMember(new Member("test","pass","email"));

		setSize(1024, 768);
		// 레이아웃 설정
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1024, 768);
		layeredPane.setLayout(null);

		// 이미지 받아오기
		try {
			img = ImageIO.read(new File("images/LoguinFrame01_01.jpg"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1024, 768);

		// 로그인 라밸
		JLabel ID = new JLabel("아이디 :");
		ID.setBounds(346, 200, 200, 370);

		IDText = new JTextField(15);
		IDText.setBounds(400, 370, 200, 30);
		IDText.setForeground(Color.BLACK);
		IDText.setBackground(Color.WHITE);
		// 패스워드 라벨
		JLabel Pass = new JLabel("비밀번호:");
		Pass.setBounds(346, 250, 200, 370);

		passText = new JPasswordField(15);
		passText.setBounds(400, 420, 200, 30);
		passText.setForeground(Color.BLACK);
		passText.setBackground(Color.white);


		// 로그인버튼 추가
		loginbt = new JButton("로그인");
		loginbt.setBounds(400, 460, 200, 30);

		guestbt= new JButton("guest");
		guestbt.setBounds(510, 510, 90, 30); 

		Joinbt = new JButton("회원가입");
		Joinbt.setBounds(400, 510, 90, 30);

		// 마지막 추가들
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
		layeredPane.add(panel);
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
					String[] command = {"회원가입","아이디, 비밀번호 찾기"};
					int result;

					result = JOptionPane.showOptionDialog(null,"아이디, 비밀번호를 확인해주세요","부글부글분식",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, command, command[0]);
					if(result==0) {
						ChangePanel.changePanel(mf, lView, new JoinPanel(mf));
					}else if(result==1){
						//이메일 입력받는 창
						//이메일 입력하면 orderDao.searchMember() 수행하여 member객체 불러옴
						//불러온 member객체로부터 id,pass 출력
					}
				}
			}
			if(e.getSource() == guestbt) {
				new ChangePanel().changePanel(mf, lView, new StageView(mf,new Member("guest","guestpass","guestemail")));
			}
			if(e.getSource() == Joinbt) {
				new ChangePanel().changePanel(mf, lView, new JoinPanel(mf));
				
			}
		}
	}
}