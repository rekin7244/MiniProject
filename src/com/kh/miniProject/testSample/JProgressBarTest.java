package com.kh.miniProject.testSample;

import java.awt.*; 
import javax.swing.*; 

class JProgressBarTest extends JPanel { 
        JProgressBar p; 
        Label status; 

        public JProgressBarTest() { 
                setLayout(new BorderLayout()); 
                p = new JProgressBar(); 
                p.setMinimum(0); 
                p.setMaximum(100); 
                p.setValue(0); 

                status = new Label(""); 

                add(p,"Center"); 
                add(status, "South"); 
        } 

        public void go() { 
                try        { 
               
                        for(int i=0;i<=100;i++) { 
                                p.setValue(i); 
                                Thread.sleep(50); 
                                status.setText(i+"% ÁøÇàÁß..."); 
                        } 
                } 
                catch (InterruptedException e) {} 
        } 

        public Dimension getPreferredSize() { 
                return new Dimension(300, 80); 
        } 

        public static void main(String[] args) { 
                JFrame f = new  JFrame("ProgressBar Sample..."); 
                JProgressBarTest panel = new JProgressBarTest(); 
                f.getContentPane().add(panel, "Center"); 
                f.setSize(panel.getPreferredSize()); 
                f.setVisible(true); 
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                panel.go(); 
        } 
} 