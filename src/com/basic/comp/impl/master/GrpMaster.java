package com.basic.comp.impl.master;

import com.basic.comp.impl.table.GrpTable;
import com.basic.view.add.GrpDN;
import com.basic.view.detail.GrpDetail;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.abst.MasterAbs;
import org.basic.comp.abst.WidgetChangeObj;
import org.basic.comp.base.impl.ToolbarSmallCRUDS;

import javax.swing.*;

public class GrpMaster extends MasterAbs {
	public static String TITLE_ADD = App.getT("Tambah Data Hak Akses");
	public static String TITLE_EDIT = App.getT("Edit Data Hak Akses");

	public static String MNEMONIC = App.getT("H");
	public static String ID_MASTER = "Hak Akses";
	public static String TITLE = App.getT("Master Data Hak Akses");
	
	public static String KEY_STROKE_ACTION = App.getT("ctrl H");
	public static String TITLE_ACTION = App.getT("Hak Akses");
	public static String DESC_ACTION = TITLE_ACTION;
	
	public static String URL_ICON_16 = "/image/hakakses-16.png";
	public static String URL_ICON_32 = "/image/hakakses-32.png";
	public static String URL_ICON_48 = "/image/hakakses-48.png";
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
		addDialog=new GrpDN();
		listWidget=new GrpTable();
		detail=new GrpDetail();
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
	public String getTitleAction() {
		return TITLE_ACTION;
	}

	@Override
	public String getDescAction() {
		return DESC_ACTION;
	}
	
	@Override
	public KeyStroke getKeyStroke() {
		return KeyStroke.getKeyStroke(KEY_STROKE_ACTION);
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
