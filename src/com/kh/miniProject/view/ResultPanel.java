package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.kh.miniProject.model.vo.member.Member;
import com.kh.miniProject.run.Run;

public class ResultPanel extends JPanel{
	private JButton mainButton;
	private int stageGold;
	private Image flareImage = new ImageIcon("images/ResultPanelImage1.png").getImage();
	private Image bgrImage = new ImageIcon("images/rank.jpg").getImage();
	
	public ResultPanel(MainFrame mf, Member m) {
		this.stageGold = m.getStageGold();
		
		this.setLayout(null);
		this.setBounds(0,0, Run.SCREEN_WIDTH, Run.SCREEN_HEIGHT);
		
		mainButton = new JButton("스테이지로 가기");
		mainButton.setBounds(200, 600, 300, 100);
		
		
		
		
		
		mainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				m.setStageGold(0);
			}	
		});
		
		this.add(mainButton);
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(bgrImage, 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.setColor(Color.white);
		g.drawString("GAME RESULT", 400, 100);
		
		g.setFont(new Font("Elephant",Font.ITALIC,30));
		g.setColor(Color.CYAN);
		g.drawString(stageGold+"", 400, 300);
		g.drawImage(flareImage, 340, 150,null);
		
	}
}
