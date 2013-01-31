package com.basic.comp.impl.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.basic.icon.IconBase;
import com.basic.lang.LApp;

public class ExitAction extends AbstractAction {

	public ExitAction() {
        super(LApp.EXIT, IconBase.EXIT); 
        putValue(SHORT_DESCRIPTION, LApp.EXIT_DESC);  
        putValue(MNEMONIC_KEY, (int)LApp.EXIT_MNEMONIC);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
