package com.basic.comp.impl.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.basic.comp.abst.WindowInterfaces;
import org.basic.comp.base.LoginDialog;

import com.basic.icon.IconBase;
import com.basic.lang.LApp;
import com.global.App;
import com.global.DataUser;

public class LoginAction extends AbstractAction {
	private WindowInterfaces window;
	public LoginAction(WindowInterfaces window) {
        super(LApp.LOGIN, IconBase.LOGIN); 
        this.window=window;
        putValue(SHORT_DESCRIPTION, LApp.LOGIN_DESC);  
        putValue(MNEMONIC_KEY, (int)LApp.LOGIN_MNEMONIC);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl L"));
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (DataUser.getUsr() == null) {
			LoginDialog form = new LoginDialog();
			form.build();
			App.showDialog(window.getFrame(), form.getPanel());
		} else {
			//logout
			DataUser.setUsr(null);
			DataUser.setGrp(null);
			window.getToolWindowManager().getContentManager().removeAllContents();
			
		}
		DataUser.setAkses();
		window.changePrivilege();
	}

}
