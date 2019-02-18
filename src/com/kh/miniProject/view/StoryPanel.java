package com.kh.miniProject.view;

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

import com.kh.miniProject.controller.LastStory;
import com.kh.miniProject.controller.Letter;
import com.kh.miniProject.controller.Story;
import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.music.Music;

public class StoryPanel extends JPanel {

	private MainFrame mf;
	private StoryPanel lView;
	private Member m;
	private int count = 0;
	private StoryPanel sp;
	private JLabel[] Jb = new JLabel[3];
	private Story st;
	private Letter le;
	private LastStory ls;
	private Music storyMusic;
	
	BufferedImage img = null;

	JButton bag;

	public StoryPanel(MainFrame mf, Member m) {
		this.mf = mf;
		this.m = m;
		this.lView = this;

		storyMusic = new Music("prologueMusic.mp3",false);
		storyMusic.start();
		
		setSize(1024, 768);
		setLayout(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1024, 768);
		layeredPane.setLayout(null);

		bag = new JButton();
		bag.setBounds(0, 0, 1024, 768);
		bag.setIcon(null);
		bag.setBackground(null);
		bag.setOpaque(false);
		bag.setContentAreaFilled(false);
		bag.setBorderPainted(false);
		bag.addActionListener(new BtnAction());
		this.add(bag);

		st = new Story(this);

		try {
			img = ImageIO.read(new File("images/stroyImage.jpg"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");

			System.exit(0);
		}

		// 이미지 추가 패널
		this.setBounds(0, 0, 1024, 768);

		this.sp = this;

	}

	

	public void letter() {

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	private class BtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == bag) {
				if (count == 0) {
					st.stop();
					le = new Letter(sp);
					count++;
				}else if (count == 1) {
					le.stop();
					ls = new LastStory(sp);
					count++;
				} else {
					new ChangePanel().changePanel(mf, sp, new StageView(mf,m));
					storyMusic.close();
				}
			}
		}
	}
}
