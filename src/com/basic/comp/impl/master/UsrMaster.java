package com.basic.comp.impl.master;

import javax.swing.Icon;

import org.basic.comp.abst.MasterAbs;
import org.basic.comp.abst.WidgetChangeObj;
import org.basic.comp.base.impl.ToolbarSmallCRUDS;

import com.basic.comp.impl.table.UsrTable;
import com.basic.view.add.UsrDN;
import com.basic.view.detail.UsrDetail;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class UsrMaster extends MasterAbs {
	public static String TITLE_ADD = App.getT("Tambah Data Pegawai");
	public static String TITLE_EDIT = App.getT("Edit Data Pegawai");

	public static String MNEMONIC = App.getT("P");
	public static String ID_MASTER = "Pegawai";
	public static String TITLE = App.getT("Master Data Pegawai");
	public static String TITLE_MENU = App.getT("Pegawai");
	public static String TITLE_TOOLBAR = TITLE_MENU;
	public static String URL_ICON_16 = "/image/pegawai-16.png";
	public static String URL_ICON_32 = "/image/pegawai-32.png";
	public static String URL_ICON_48 = "/image/pegawai-48.png";
	public static Icon ICON_16 = App.getIcon(URL_ICON_16);
	public static Icon ICON_32 = App.getIcon(URL_ICON_32);
	public static Icon ICON_48 = App.getIcon(URL_ICON_48);
	public static boolean ADD = false;
	public static boolean EDIT = false;
	public static boolean DEL = false;
	public static boolean VIEW = false;

	@Override
	public void init() {
		super.init();
		toolbarSmall = new ToolbarSmallCRUDS();
		addDialog=new UsrDN();
		listWidget=new UsrTable();
		detail=new UsrDetail();
	}
	
	

	@Override
	public void build(ODatabaseDocumentTx db) {
		init();
		toolbarSmall.setMaster(this);
		listWidget.addWidgetChange((WidgetChangeObj) detail);
		super.build(db);
	}



	public boolean isAdd() {
		return ADD;
	}

	public boolean isDel() {
		return DEL;
	}

	public boolean isView() {
		return VIEW;
	}

	public boolean isEdit() {
		return EDIT;
	}

	@Override
	public Icon getIcon16() {
		return ICON_16;
	}

	@Override
	public Icon getIcon32() {
		return ICON_32;
	}

	@Override
	public String getTitleToolBar() {
		return TITLE.toUpperCase();
	}
	
	public String getTitleMenu() {
		return TITLE_MENU;
	}
	

	public String getIdMaster() {
		return ID_MASTER;
	}
	
	public int getMnemonic() {
		return MNEMONIC.charAt(0);
	}
	
	public String getTitle() {
		return TITLE;
	}
}
