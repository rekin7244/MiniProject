package com.kh.miniProject.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kh.miniProject.controller.OrderManager;
import com.kh.miniProject.model.dao.OrderDao;


public class GuestPanel extends JPanel {
	private int x = 864; //�ֹ� �̹��� ��ġ
	private int y = 15; //�ֹ� �̹��� ��ġ
	private int level = 5; //�������� ����
	private Image img;
	private JLabel[] label = new JLabel[level];
	private OrderManager om;

	public GuestPanel(Image img,OrderDao od) {
		this.img = img;
		om = new OrderManager(od,this);
		guest();
	}

	public void guest() {
		Image icon = new ImageIcon("images/guest.PNG")
				.getImage().getScaledInstance(120, 200, 0); // �մ� �̹���
		JLabel guest = new JLabel(new ImageIcon(icon)); // �մԶ�

		addImage(level); //���� �̹��� ����

		guest.setBounds(744, 0, 120, 200); // �մ� ��ġ
		this.add(guest); // �гο� �մԶ� �߰�
		//mf.add(this); //���������ӿ� �г� ���

	}

	//���� �̹��� ��� �޼ҵ�
	public void addImage(int level) {
		if(level >= 5) { //�������� 5����
			level = 4; //�޴��ֹ� 4����
		}

		//�������� ����ŭ �޴� �̹��� ���
		for (int i = 0; i < level; i++) {
			int result = om.createOrder(level);
			//������ 0�� ��� ������
			if (result == 0) {
				Image food = new ImageIcon("images/�����̼���.jpg")
						.getImage().getScaledInstance(50, 40, 0);
				label[i] = new JLabel(new ImageIcon(food));
				label[i].setBounds(x, y, 100, 30);
				y += 40;
				this.add(label[i]);
				//������ 1�ϰ�� ���
			} else if (result == 1) {
				Image food = new ImageIcon("images/ramen.png")
						.getImage().getScaledInstance(50, 40, 0);
				label[i] = new JLabel(new ImageIcon(food));
				label[i].setBounds(x, y, 100, 30);
				y += 40;
				this.add(label[i]);
				//������ 2�� ��� �ݶ�	
			} else if(result == 2){
				Image food = new ImageIcon("images/drinkImage.jpg")
						.getImage().getScaledInstance(50, 40, 0);
				label[i] = new JLabel(new ImageIcon(food));
				label[i].setBounds(x, y, 100, 30);
				y += 40;
				this.add(label[i]);
				//������ 3�ϰ�� Ƣ��
			} else if(result == 3) {
				Image food = new ImageIcon("images/friedImage.jpeg")
						.getImage().getScaledInstance(50, 40, 0);
				label[i] = new JLabel(new ImageIcon(food));
				label[i].setBounds(x, y, 100, 30);
				y += 40;
				this.add(label[i]);
			}
		}
	}
	//���� �̹��� ���� �޼ҵ�
	public void deleteImage(int menuNo) {
		
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 1024, 350, null);
	}
}
