package org.basic.comp.abst;

import com.basic.icon.IconBase;
import com.basic.lang.LApp;
import com.basic.lang.LWindow;
import com.global.App;
import com.jgoodies.looks.BorderStyle;
import com.jgoodies.looks.HeaderStyle;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuAbs implements Menu {
	protected JMenuBar menuBar;
	protected Window window;
	protected JMenu file;
	protected JMenuItem login;
//	protected JMenuItem exit;
	protected JMenuItem close;
	protected JMenuItem print;

	protected JMenu editMenu;
	protected JMenuItem add;
	protected JMenuItem edit;
	protected JMenuItem del;
	protected JMenuItem reload;
	
	protected JMenu help;
	protected JMenuItem registrasi;
	protected JMenuItem about;
	
	@Override
	public void init() {
		menuBar = new JMenuBar();
		file = new JMenu();
		login = new JMenuItem();
//		exit = new JMenuItem();
		close = new JMenuItem();
		print = new JMenuItem();

		editMenu = new JMenu();
		add = new JMenuItem();
		edit = new JMenuItem();
		del = new JMenuItem();
		reload = new JMenuItem();

		help = new JMenu();
		registrasi = new JMenuItem();
		about = new JMenuItem();

		help.add(registrasi);
		help.add(about);
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init();

		// set menu bar berwarna putih saja
		menuBar.setUI(new BasicMenuBarUI() {
			public void paint(Graphics g, JComponent c) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, c.getWidth(), c.getHeight());
			}
		});
		// memperbaiki tampilan menu bar
		menuBar.putClientProperty(Options.HEADER_STYLE_KEY, HeaderStyle.BOTH);
		menuBar.putClientProperty(Options.HEADER_STYLE_KEY, HeaderStyle.SINGLE);
		menuBar.putClientProperty(Options.NO_ICONS_KEY, Boolean.TRUE);
		menuBar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY,
				BorderStyle.SEPARATOR);

		// set text dan key
		createMenu(file, LApp.FILE);
		createMenu(login, LApp.LOGIN, KeyEvent.VK_L, KeyEvent.VK_L,
				ActionEvent.CTRL_MASK, IconBase.LOGIN);
		createMenu(close, LApp.CLOSE, KeyEvent.VK_C, KeyEvent.VK_W,
				ActionEvent.CTRL_MASK, IconBase.CLOSE);
		createMenu(print, LApp.PRINT, KeyEvent.VK_P, KeyEvent.VK_P,
				ActionEvent.CTRL_MASK, IconBase.PRINT);
//		createMenu(exit, LApp.EXIT, KeyEvent.VK_E, KeyEvent.VK_X,
//				ActionEvent.CTRL_MASK, IconBase.EXIT);
		createMenu(editMenu, LApp.EDIT);
		createMenu(add, LApp.ADD, KeyEvent.VK_T, KeyEvent.VK_N,
				ActionEvent.CTRL_MASK, IconBase.ADD);
		createMenu(edit, LApp.EDIT, KeyEvent.VK_E, KeyEvent.VK_E,
				ActionEvent.CTRL_MASK, IconBase.EDIT);
		createMenu(del, LApp.DEL, KeyEvent.VK_H, KeyEvent.VK_DELETE, 0,
				IconBase.DEL);
		createMenu(reload, LApp.RELOAD, KeyEvent.VK_R, KeyEvent.VK_F5, 0,
				IconBase.RELOAD);
		createMenu(help, LWindow.help);
		createMenu(registrasi, LWindow.registrasi, IconBase.REGISTRASI);
		createMenu(about, LWindow.about, IconBase.ABAOUT);

		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionLogin();
			}
		});

//		exit.addActionListener(App.getActions().get("exit"));

		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionClose();
			}
		});

		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionPrint();
			}
		});


		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionAdd();
			}
		});

		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionEdit();
			}
		});

		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionDel();
			}
		});


		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.actionReload();
			}
		});
		
		registrasi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.actionReg();
			}
		});
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.actionAbout();
			}
		});
		
		
		file.add(login);
		file.add(close);
		file.add(print);
		file.addSeparator();
		file.add(App.getActions().get("exit"));

		editMenu.add(add);
		editMenu.add(edit);
		editMenu.add(del);
		editMenu.add(reload);
		
		menuBar.add(file);
		menuBar.add(editMenu);

		
	}

	@Override
	public Window getWindow() {
		return window;
	}

	@Override
	public void setWindow(Window window) {
		this.window = window;
	}

	// init menu
	public void createMenu(JMenu m, String nama, int key) {
		m.setText(App.getT(nama));
		m.setMnemonic(key);
	}

	public void createMenu(JMenu m, String nama) {
		m.setText(App.getT(nama));
	}

	public void createMenu(JMenuItem m, String nama) {
		m.setText(App.getT(nama) + "               ");
	}

	public void createMenu(JMenuItem m, String nama, int key, int ke, int ae) {
		m.setText(App.getT(nama) + "               ");
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
	}

	public void createMenu(JMenuItem m, String nama, int key) {
		m.setText(App.getT(nama) + "               ");
		m.setMnemonic(key);
	}

	public void createMenu(JMenuItem m, String nama, String urlIcon16) {
		m.setText(App.getT(nama) + "               ");
		m.setIcon(App.getIcon(urlIcon16));
	}

	public void createMenu(JMenuItem m, String nama, int key, int ke, int ae,
			String urlIcon16) {
		m.setText(App.getT(nama) + "               ");
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
		m.setIcon(App.getIcon(urlIcon16));
	}

	public void createMenu(JMenuItem m, String nama, int key, String urlIcon16) {
		m.setText(App.getT(nama) + "               ");
		m.setMnemonic(key);
		m.setIcon(App.getIcon(urlIcon16));
	}

	public void createMenu(JMenuItem m, String nama, Icon icon16) {
		m.setText(App.getT(nama) + "               ");
		m.setIcon(icon16);
	}

	public void createMenu(JMenuItem m, String nama, int key, int ke, int ae,
			Icon icon16) {
		m.setText(App.getT(nama) + "               ");
		m.setMnemonic(key);
		m.setAccelerator(KeyStroke.getKeyStroke(ke, ae));
		m.setIcon(icon16);
	}

	public void createMenu(JMenuItem m, String nama, int key, Icon icon16) {
		m.setText(App.getT(nama) + "               ");
		m.setMnemonic(key);
		m.setIcon(icon16);
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	
	
}
