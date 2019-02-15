package com.kh.miniProject.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.kh.miniProject.controller.Story;
import com.kh.miniProject.model.vo.member.Member;

public class StoryPanel extends JPanel {

   private MainFrame mf;
   private StoryPanel lView;
   private Member m;
  /* private String[] story = {"안","녕","하","세","요","\n","반"," "," ","갑"};*/
   private Story st;
   
   
   
   BufferedImage img = null;

   JButton bag;

   public StoryPanel(MainFrame mf, Member m ) {
      this.mf = mf;
      this.m=m;
      this.lView = this;

      setSize(1024, 768);
      setLayout(null);
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setBounds(0, 0, 1024, 768);
      layeredPane.setLayout(null);
      
      bag =new JButton(); 
      bag.setBounds(0, 0, 1024, 768);
      bag.setIcon(null);
      bag.setBackground(null);
      bag.setOpaque(false);
      bag.setContentAreaFilled(false);
      bag.setBorderPainted(false);
      bag.addMouseListener(new BtnAction());
      this.add(bag);
      
      st = new Story(this/*, story*/);
      

      try {
         img = ImageIO.read(new File(/*"images/편지지2_2.jpg"*/"images/stroyImage.jpg"));
      } catch (IOException e) {
         System.out.println("이미지 불러오기 실패");

         System.exit(0);
      }

      // 이미지 추가 패널

    /*  StoryImage panel = new StoryImage();*/
      this.setBounds(0, 0, 1024, 768);
   

   }

   @Override
      public void paintComponent(Graphics g) {
         g.drawImage(img, 0, 0, null);
      }
    
   
   private class BtnAction implements MouseListener{

      public void actionPerformed(MouseListener e) {
         if(((EventObject) e).getSource()==bag) {
            new ChangePanel().changePanel(mf, lView, new StageView(mf,m));
         }
      }

	@Override
	public void mouseClicked(MouseEvent e) {
		 new ChangePanel().changePanel(mf, lView, new StageView(mf,m));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
   }
}

