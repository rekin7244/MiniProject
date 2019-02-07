package com.kh.miniProject.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.*;

import com.kh.miniProject.run.Run;

public class MenuTablePanel extends JPanel{
	private int panelSize = 200;			//�޴� ���̺� �г� ����ũ��
	private Image[] drinks = {new ImageIcon("images/drink1.jpg")	//����� �̹���
			.getImage().getScaledInstance(200,panelSize,0),
			new ImageIcon("images/drink2.jpg")
			.getImage().getScaledInstance(200,panelSize,0),
			new ImageIcon("images/drink3.jpg")
			.getImage().getScaledInstance(200,panelSize,0)};
	private Image[] tbk = {new ImageIcon("images/tbk1.png")			//������ �̹���
			.getImage().getScaledInstance(250,panelSize,0),
			new ImageIcon("images/tbk2.png")
			.getImage().getScaledInstance(250,panelSize,0),
			new ImageIcon("images/tbk3.png")
			.getImage().getScaledInstance(250,panelSize,0),
			new ImageIcon("images/tbk4.png")
			.getImage().getScaledInstance(250,panelSize,0)};
	private Image[] fried = {new ImageIcon("images/fried1.png")		//Ƣ�� �̹���
			.getImage().getScaledInstance(180,panelSize,0),
			new ImageIcon("images/fried2.png")
			.getImage().getScaledInstance(180,panelSize,0),
			new ImageIcon("images/fried3.png")
			.getImage().getScaledInstance(180,panelSize,0),
			new ImageIcon("images/fried4.png")
			.getImage().getScaledInstance(180,panelSize,0)};

	private JButton drinkBtn = new JButton("�����");
	private JButton friedBtn = new JButton("Ƣ��");
	private JButton tbkBtn = new JButton("������");

	//cons
	public MenuTablePanel() {
	}

	public void setting(JPanel panel,int drinksNo, int tbkNo, int friedNo) {				//���� ���¿� ���缭 ��ư ����
		//�����
		if(drinksNo == 3) {
			drinkBtn.setIcon(new ImageIcon(drinks[2]));
		}else if(drinksNo == 2) {
			drinkBtn.setIcon(new ImageIcon(drinks[1]));
		}else if(drinksNo == 1) {
			drinkBtn.setIcon(new ImageIcon(drinks[0]));
		}else if(drinksNo == 0) {
			drinkBtn.setIcon(null);
		}
		drinkBtn.setBounds(Run.SCREEN_WIDTH-150,0,150,panelSize);
		
		//������
		if(tbkNo == 4) {
			tbkBtn.setIcon(new ImageIcon(tbk[3]));
		}else if(tbkNo == 3) {
			tbkBtn.setIcon(new ImageIcon(tbk[2]));
		}else if(tbkNo == 2) {
			tbkBtn.setIcon(new ImageIcon(tbk[1]));
		}else if(tbkNo == 1) {
			tbkBtn.setIcon(new ImageIcon(tbk[0]));
		}else if(tbkNo == 0) {
			tbkBtn.setIcon(null);
		}
		tbkBtn.setBounds(200,0,250,panelSize);
		
		//Ƣ��
		if(friedNo == 4) {
			friedBtn.setIcon(new ImageIcon(fried[3]));
		}else if(friedNo == 3) {
			friedBtn.setIcon(new ImageIcon(fried[2]));
		}else if(friedNo == 2) {
			friedBtn.setIcon(new ImageIcon(fried[1]));
		}else if(friedNo == 1) {
			friedBtn.setIcon(new ImageIcon(fried[0]));
		}else if(friedNo == 0) {
			friedBtn.setIcon(null);
		}
		friedBtn.setBounds(500,0,180,panelSize);
		
		/*drinkBtn.removeActionListener(GameView.eC);		//ActionListener �缳��.. ���ϸ� ��ư�� ������..
		drinkBtn.addActionListener(GameView.eC);
		tbkBtn.removeActionListener(GameView.eC);
		tbkBtn.addActionListener(GameView.eC);
		friedBtn.removeActionListener(GameView.eC);
		friedBtn.addActionListener(GameView.eC);*/
		
		//add Button
		panel.add(drinkBtn);
		panel.add(tbkBtn);
		panel.add(friedBtn);
	}

	public JButton getDrinkBtn() {
		return drinkBtn;
	}
	public JButton getFriedBtn() {
		return friedBtn;
	}
	public JButton getTbkBtn() {
		return tbkBtn;
	}	
}
