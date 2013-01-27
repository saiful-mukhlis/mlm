package com.mlm.comp.impl;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.basic.comp.abst.MenuAbs;

import com.basic.comp.impl.master.GrpM;
import com.basic.comp.impl.master.GrpMaster;
import com.basic.comp.impl.master.JenisPekerjaanM;
import com.basic.comp.impl.master.JenisPekerjaanMaster;
import com.basic.comp.impl.master.UsrM;
import com.basic.comp.impl.master.UsrMaster;
import com.basic.lang.LMenu;
import com.basic.lang.LWindow;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.comp.impl.master.PelangganM;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class MenuMlm extends MenuAbs{
	protected JMenuItem pelanggan;
	protected JMenuItem paket;
	protected JMenu master;
	protected JMenuItem grp;
	protected JMenuItem usr;
	protected JMenuItem jenisPekerjaan;
	@Override
	public void init() {
		super.init();
		
		pelanggan=new JMenuItem();
		paket=new JMenuItem();
		master=new JMenu();
		grp=new JMenuItem();
		usr=new JMenuItem();
		jenisPekerjaan=new JMenuItem();
		
	}
	@Override
	public void build(ODatabaseDocumentTx db) {
		super.build(db);
		
		
		createMenu(master, LWindow.master);
		createMenu(grp, LMenu.GRP, GrpM.ICON_16 );
		createMenu(usr, LMenu.USR, UsrM.ICON_16);
		createMenu(jenisPekerjaan, LMenu.JENIS_PEKERJAAN, JenisPekerjaanM.ICON_16);
		
		createMenu(pelanggan, LMenu.PELANGGAN, PelangganM.ICON_16);
		createMenu(paket, LMenu.PAKET, PaketM.ICON_16);
		
		
		master.add(grp);
		master.add(usr);
		master.add(jenisPekerjaan);

		usr.addActionListener(window.getMapMaster().get(UsrMaster.ID_MASTER).getAca());
		grp.addActionListener(window.getMapMaster().get(GrpMaster.ID_MASTER).getAca());
		jenisPekerjaan.addActionListener(window.getMapMaster().get(JenisPekerjaanMaster.ID_MASTER).getAca());

		
		menuBar.add(master);
	}
	
	
	
	
}
