package com.basic.comp.impl.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import org.basic.comp.abst.WindowInterfaces;
import org.basic.comp.base.LoginDialog;
import org.noos.xing.mydoggy.ToolWindowManager;

import com.basic.icon.IconBase;
import com.basic.lang.LApp;
import com.global.App;
import com.global.DataUser;

public class LoginAction extends AbstractAction {
	private ToolWindowManager toolWindowManager;
	private JFrame frame;
	public LoginAction(ToolWindowManager toolWindowManager, JFrame frame) {
        super(LApp.LOGIN, IconBase.LOGIN); 
        this.toolWindowManager=toolWindowManager;
        this.frame=frame;
        putValue(SHORT_DESCRIPTION, LApp.LOGIN_DESC);  
        putValue(MNEMONIC_KEY, (int)LApp.LOGIN_MNEMONIC);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl L"));
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (DataUser.getUsr() == null) {
			LoginDialog form = new LoginDialog();
			form.build();
			App.showDialog(frame, form.getPanel());
		} else {
			//logout
			DataUser.setUsr(null);
			DataUser.setGrp(null);
			toolWindowManager.getContentManager().removeAllContents();
			
		}
		DataUser.setAkses();
//		window.changePrivilege();
	}

}
