package com.bmb.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.basic.comp.abst.FormBuilder;

import com.basic.lang.LJenisPekerjaan;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;


public class Test2 {
	private JPanel panel;
	private JFrame frame;
	private Panel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel p5;
	private JPanel p6;
	
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	
	public void init(){
		frame=new JFrame();
		
		p1=new Panel();
		p2=new Panel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		p6=new JPanel();
		
		b1=new JButton("Login");
		b2=new JButton("Setting");
		b3=new JButton("Transaksi");
		b4=new JButton("Penjualan");
		b5=new JButton("Pembelian");
		b6=new JButton("Order");
		
		buildButton(b1, p1);
		buildButton(b2, p2);
		buildButton(b3, p3);
		buildButton(b4, p4);
		buildButton(b5, p5);
		buildButton(b6, p6);
		
		FormLayout layout = new FormLayout(
				"3dlu, f:max(200dlu;p):g,	5dlu,   	f:max(200dlu;p):g,	5dlu,   	f:max(200dlu;p):g,	3dlu",

				"5dlu,f:100dlu:g,5dlu,f:100dlu:g,5dlu");

//		layout.setColumnGroups(new int[][] { { 5, 7 } });
		FormBuilder builder = new FormBuilder(layout, true);

		builder.append(p1,  2, 2, 1 ,1);
		builder.append(p2,  4, 4, 1 ,1);
		builder.append(p3,  6, 2, 1 ,1);
		builder.append(p4,  2, 4, 1 ,1);
		builder.append(p5,  4, 2, 1 ,1);
		builder.append(p6,  6, 4, 1 ,1);

		panel = builder.getPanel();
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void buildButton(JButton b, final JPanel p){
		p.setLayout(new BorderLayout());
		b.setForeground(new Color(255, 255, 255));
		b.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		b.setContentAreaFilled(false);
		p.add(b, BorderLayout.CENTER);
		p.setBackground(new Color(13, 105, 141));
		b.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				p.setBackground(new Color(13, 105, 141));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				p.setBackground(new Color(10, 84, 113));
			}
		});
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Test2 frame = new Test2();
					frame.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
