package com.kh.miniProject.testSample.progressBar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

class WorkPanel extends JPanel{
	int nValue;

	public void paint(Graphics g){
		int x, y, w, h;
		x = y = w= h = 0;

		for(int i=0; i<nValue; i++){
			x = (int) (getSize().width * i / 200.0f);
			w = getSize().width -2 * x;
			y = (int) (getSize().height * i / 200.0f);
			h = getSize().height -2 * y;

			g.setColor(new Color(0, 0, i*2,+50));
			g.drawRect(x, y, w, h);
		}

		g.setColor(Color.black);
		g.drawRect(x, y, w, h);
	}
}

public class JProgressBarTest extends JFrame implements Runnable {
	JPanel xStatusPanel;
	JProgressBar xPBar;
	WorkPanel xWPanel;

	public JProgressBarTest() {
		super("JProgressBar 데모");
		getContentPane().setLayout(new BorderLayout());

		xStatusPanel = new JPanel(new BorderLayout());
		xStatusPanel.setBorder(new TitledBorder("진행상태"));
		xPBar = new JProgressBar(0, 100);
		xPBar.setStringPainted(true);

		xStatusPanel.add("Center", xPBar);
		getContentPane().add("South", xStatusPanel);

		xWPanel = new WorkPanel();
		getContentPane().add("Center", xWPanel);
	}

	public void run() {
		for(int i=1; i<=100; i++){
			xWPanel.nValue = i;
			xWPanel.repaint();

			try{
				Thread.sleep(100);
			}catch(Exception e){}

			xPBar.setValue(i);
			xPBar.setString("현재"+(int)(xPBar.getPercentComplete()*100) + "%");

		}
	}

	public static void main(String[] args){
		JProgressBarTest mf = new JProgressBarTest();
		/*mf.addWindowListener(new WindowAdapter(){
			public void windowCloseing(WindowEvent e){
				System.exit(0);
			}
		});*/

		mf.pack();
		mf.setSize(300, 200);
		mf.setVisible(true);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Thread thd = new Thread(mf);

		try{
			thd.start();
		}catch(Exception e){}
	}
}