package com.mlm.comp.impl;

import org.basic.comp.abst.WindowAbs;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import com.basic.comp.impl.master.UsrMaster;
import com.basic.icon.IconBase;
import com.basic.lang.LWindow;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class WindowMlm extends WindowAbs {
	@Override
	public void init() {
		super.init();

		toolbar = new ToolbarMlm();
		menu = new MenuMlm();
		welcomeComponent = new WelcomePanel();
		((WelcomePanel) welcomeComponent).init();
		welcomeAca = new AddContentAction(toolWindowManager,
				LWindow.ID_WELCOME, LWindow.TITLE_WELCOME, IconBase.APP,
				welcomeComponent, LWindow.TOOLTIP_WELCOME,
				LWindow.MNEMONIC_WELCOME.charAt(0));
		welcomeVca=new ViewContextAction(LWindow.TITLE_WELCOME, IconBase.APP, viewContext, LWindow.ID_WELCOME);
		viewContext.addViewContextChangeListener(welcomeAca);
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init();
		UsrMaster um=new UsrMaster();
		um.setWindow(this);
		um.build(db);
		mapMaster.put(UsrMaster.ID_MASTER, um);
		viewContext.addViewContextChangeListener(um.getAca());
		super.build(db);
		showWelcome();
	}
	
	

}
