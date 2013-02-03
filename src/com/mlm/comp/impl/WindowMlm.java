package com.mlm.comp.impl;

import javax.swing.JPanel;

import com.basic.comp.impl.action.LoginAction;
import com.basic.comp.impl.master.GrpMaster;
import com.basic.comp.impl.master.JenisPekerjaanMaster;
import com.basic.comp.impl.master.UsrMaster;
import com.basic.icon.IconBase;
import com.basic.lang.LApp;
import com.basic.lang.LWindow;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.abst.WindowAbs;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;
import org.noos.xing.yasaf.view.ViewContextChangeListener;

public class WindowMlm extends WindowAbs {
	@Override
	public void init() {
		super.init();

		toolbar = new ToolbarMlm();
		menu = new MenuMlm();
//		welcomeComponent = new WelcomePanel();
		welcomeComponent = new JPanel();
//		((WelcomePanel) welcomeComponent).init();
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
		viewContext.addViewContextChangeListener((ViewContextChangeListener) App.getActions().get(UsrMaster.ID_MASTER));
		GrpMaster grpm=new GrpMaster();
		grpm.setWindow(this);
		grpm.build(db);
		mapMaster.put(GrpMaster.ID_MASTER, grpm);
		viewContext.addViewContextChangeListener((ViewContextChangeListener) App.getActions().get(GrpMaster.ID_MASTER));
		JenisPekerjaanMaster jpm=new JenisPekerjaanMaster();
		jpm.setWindow(this);
		jpm.build(db);
		mapMaster.put(JenisPekerjaanMaster.ID_MASTER, jpm);
		viewContext.addViewContextChangeListener((ViewContextChangeListener) App.getActions().get(JenisPekerjaanMaster.ID_MASTER));
		super.build(db);
		showWelcome();
		
		
		// ============================
		LoginAction loginAction=new LoginAction(this);
		App.getActions().put(LApp.LOGIN, loginAction);
	}
	
	

}
