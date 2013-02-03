package com.mlm.comp.impl;

import com.basic.comp.impl.master.GrpMaster;
import com.basic.comp.impl.master.JenisPekerjaanMaster;
import com.basic.comp.impl.master.UsrMaster;
import com.basic.lang.LMenu;
import com.basic.lang.LWindow;
import com.global.App;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.comp.impl.master.PelangganM;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.abst.MenuAbs;

import javax.swing.*;

public class MenuMlm extends MenuAbs {
	protected JMenuItem pelanggan;
	protected JMenuItem paket;
	protected JMenu master;

	@Override
	public void init() {
		super.init();

		pelanggan = new JMenuItem();
		paket = new JMenuItem();
		master = new JMenu();

	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		super.build(db);

		createMenu(master, LWindow.master);

		createMenu(pelanggan, LMenu.PELANGGAN, PelangganM.ICON_16);
		createMenu(paket, LMenu.PAKET, PaketM.ICON_16);

		master.add(App.getActions().get(GrpMaster.ID_MASTER));
		master.add(App.getActions().get(UsrMaster.ID_MASTER));
		master.add(App.getActions().get(JenisPekerjaanMaster.ID_MASTER));
		
		menuBar.add(master);
	}

}
