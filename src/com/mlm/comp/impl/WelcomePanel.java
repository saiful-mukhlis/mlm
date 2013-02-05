package com.mlm.comp.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.basic.comp.abst.WindowInterfaces;

import com.basic.comp.impl.master.GrpMaster;
import com.basic.lang.LApp;

public class WelcomePanel {

	private JPanel panel;
	protected WindowInterfaces window;

	private JPanel panelLogin, panelExit, panelGrp, panelAddGrp, panelPegawai, panelAddPegawai;
	private JButton buttonLogin, buttonExit, buttonGrp, buttonAddGrp, buttonPegawai, buttonAddPegawai;

	public void setWindow(WindowInterfaces window) {
		this.window = window;
	}

	public void build() {
		panelLogin = new JPanel(new BorderLayout());
		buttonLogin = new JButton(LApp.LOGIN);
		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.actionLogin();
			}
		});

		buildButton(buttonLogin, panelLogin);

		panelExit = new JPanel(new BorderLayout());
		buttonExit = new JButton(LApp.EXIT);
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.actionExit();
			}
		});

		buildButton(buttonExit, panelExit);

		panelGrp = new JPanel(new BorderLayout());
		buttonGrp = new JButton(LApp.LOGIN);
		buttonGrp.addActionListener(window.getMapMaster().get(GrpMaster.ID_MASTER).getAca());

		buildButton(buttonGrp, panelGrp);
		
		
		
		
		
		int jumlah = actionListeners.size();
		for (int i = 0; i < jumlah; i++) {
			JPanel p = new JPanel(new BorderLayout());
			JButton b = new JButton(labels.get(i));
			b.addActionListener(actionListeners.get(i));
			buildButton(b, p);
		}

		if (jumlah < 4) {

		} else if (jumlah < 7) {

		} else {

		}

	}

	public void init() {
		panels = new ArrayList<>();
		buttons = new ArrayList<>();
		actionListeners = new ArrayList<>();

		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.login();
			}
		});

		buildButton(b1, p1);
		buildButton(b2, p2);
		buildButton(b3, p3);
		buildButton(b4, p4);
		buildButton(b5, p5);
		buildButton(b6, p6);

		b5.addActionListener(window.getKomponentMaps().get(WindowInterfaces.PEGAWAI)
				.getAdd());
		b4.addActionListener(window.getKomponentMaps()
				.get(WindowPraktikum.PELANGGAN).getAdd());
		b3.addActionListener(window.getKomponentMaps()
				.get(WindowPraktikum.TYPE_MOBIL).getAdd());
		b2.addActionListener(window.getKomponentMaps()
				.get(WindowPraktikum.SEWA).getAdd());

		FormLayout layout = new FormLayout(
				"3dlu, f:max(200dlu;p):g,	5dlu,   	f:max(200dlu;p):g,	5dlu,   	f:max(200dlu;p):g,	3dlu",

				"5dlu,f:100dlu:g,5dlu,f:100dlu:g,5dlu");

		// layout.setColumnGroups(new int[][] { { 5, 7 } });
		FormBuilder builder = new FormBuilder(layout, true);

		builder.append(p1, 2, 2, 1, 1);
		builder.append(p2, 4, 4, 1, 1);
		builder.append(p3, 6, 2, 1, 1);
		builder.append(p4, 2, 4, 1, 1);
		builder.append(p5, 4, 2, 1, 1);
		builder.append(p6, 6, 4, 1, 1);

		panel = builder.getPanel();

		changeHakAkses();
		// frame.add(panel);
		// frame.pack();
		// frame.setVisible(true);
	}

	public void buildButton(JButton b, final JPanel p) {
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

	@Override
	public void changeHakAkses() {
		b5.setEnabled(PegawaiM.VIEW);
		b4.setEnabled(PelangganM.VIEW);
		b3.setEnabled(PelangganM.VIEW);
		b2.setEnabled(SewaM.VIEW);

		if (com.global.DataUser.getUsr() != null) {
			b1.setText(LApp.LOGOUT);
		} else {
			b1.setText(LApp.LOGIN);
		}
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
