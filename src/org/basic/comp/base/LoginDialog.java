package org.basic.comp.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.basic.db.FUsr;
import com.basic.entity.Grp;
import com.basic.entity.Usr;
import com.basic.lang.LApp;
import com.basic.lang.LLogin;
import com.global.App;
import com.global.DataUser;
import com.global.util.Account;
import com.global.util.Err;
import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LoginDialog {
	private JPanel panel;

	private JPanel panelForm;
	private TextField usernameField;
	private PasswordField passwordField;
	private JButton okButton;
	private JButton cancelButton;

	protected JPanel panelTitle;
	protected JLabel titleLabel;
	protected JLabel ketLabel;
	protected Icon icon;

	private void init() {
		panel = new JPanel();
		panel.setBackground(Color.WHITE);

		panelForm = new JPanel();
		panelForm.setBackground(Color.WHITE);
		usernameField = new TextField();
		usernameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passwordField.requestFocus();
			}
		});
		passwordField = new PasswordField();
		okButton = new JButton(LApp.LOGIN);
		cancelButton = new JButton(LApp.CANCEL);

		panelTitle = new JPanel();
		titleLabel = new JLabel(LLogin.title);
		ketLabel = new JLabel(LLogin.ket);
		icon = App.getIcon(LApp.iconLogin32);
	}

	public void build() {
		init();

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiCancel();
			}
		});

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionOk();
			}
		});
		passwordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionOk();
			}
		});

		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FormLayout layout = new FormLayout(
				"10dlu,f:p:g, 10dlu, f:32px,  10dlu", // cols
				"5dlu, p, 3dlu,p, 3dlu,p, 3dlu"); // rows

		PanelBuilder builder = new PanelBuilder(layout);
		GradientPanel p = new GradientPanel();
		p.setBackground(new Color(240, 255, 255));
		p.setForeground(SystemColor.WHITE);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		builder.add(titleLabel, cc.xy(2, 2));
		// builder.add(ketLabel, cc.xy(2, 4));
		// ketLabel.setLineWrap(true);
		JLabel l = new JLabel();
		l.setIcon(icon);
		builder.add(l, cc.xywh(4, 2, 1, 3, "center, top"));
		// builder.addSeparator("", cc.xyw(1, 3, 2));
		// builder.getPanel().setBackground(SystemColor.WHITE);
		builder.getPanel().setOpaque(false);
		JPanel pan = builder.getPanel();
		// pan.setBackground(Color.WHITE);
		p.add(pan);
		// p.setBackground(Color.WHITE);
		panelTitle.setBackground(Color.WHITE);
		panelTitle.setLayout(new BorderLayout(0, 0));
		panelTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		panelTitle.add(p, BorderLayout.CENTER);
		JSeparator s = new JSeparator();
		panelTitle.add(s, BorderLayout.SOUTH);

		FormLayout layout2 = new FormLayout(
				"r:p:g(.1), 4dlu, f:max(200dlu;p):g(.4), 4dlu:g");
		// layout.setColumnGroups(new int[][] { { 3, 5, 7, 13, 15 } });
		DefaultFormBuilder builder2 = new DefaultFormBuilder(layout2);
		builder2.setDefaultDialogBorder();

		builder2.append(LLogin.username, usernameField);
		builder2.append(LLogin.password, passwordField);
		JPanel panel3 = builder2.getPanel();
		panel3.setBackground(Color.WHITE);

		ButtonBarBuilder2 builderButton = new ButtonBarBuilder2();
		builderButton
				.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		builderButton.addGlue();
		builderButton.addButton(okButton, cancelButton);
		JPanel panel2 = builderButton.getPanel();

		panelForm.setLayout(new BorderLayout());
		panelForm.add(panel3, BorderLayout.CENTER);
		// panelLogin.add(new JSeparator(), BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout());
		panel.add(panelTitle, BorderLayout.NORTH);
		panel.add(panelForm, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);

	}

	/**
	 *  Action untuk button OK
	 */
	protected void actionOk() {
		String u = usernameField.getText();
		String p = new String(passwordField.getPassword());
		try {
			ODatabaseDocumentTx db = App.getDbdLocal();
			ODocument usr = App.getUsrDao().getOne(db, FUsr.USERNAME, u);
			if (usr == null) {
				Err.showErrUsernameTidakTerdaftar();
			} else {
				Usr user=new Usr(usr); 
				Account util = new Account();
				String tmp = util.md5(p);
				if (tmp.equalsIgnoreCase(user.getPassword())) {
					// password sama
					DataUser.setUsr(usr);
					Grp group=user.getGrp();
					DataUser.setGrp(group.getDoc());
					App.getBosDao().minus(db);
					// perp();
					db.close();
					dispose(panel);
				} else {
					db.close();
					App.showErrorPasswordSalah();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void aksiCancel() {
		dispose(panel);
	}

	public void dispose(Object o) {
		if (o instanceof Window) {
			((Window) o).dispose();
		} else {
			if (o instanceof Component) {
				dispose(((Component) o).getParent());
			}
		}
	}

	public JPanel getPanel() {
		return panel;
	}

}
