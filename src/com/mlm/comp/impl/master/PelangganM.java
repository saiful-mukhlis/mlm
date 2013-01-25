package com.mlm.comp.impl.master;



import javax.swing.Icon;

import org.basic.comp.abst.MasterDefault;

import com.global.App;
import com.mlm.tree.PelangganTree;
import com.mlm.view.add.PelangganDN;
import com.mlm.view.detail.PelangganV;
import com.mlm.view.detail.PpV;
import com.mlm.view.edit.PelangganDE;

public class PelangganM extends MasterDefault {
	
	public static String TITLE_ADD=App.getT("Tambah Data Pelanggan");
	public static String TITLE_EDIT=App.getT("Edit Data Pelanggan");
	public static String TITLE_VIEW=App.getT("Data Pelanggan");
	
	public static String TITLE=App.getT("Master Pelanggan");
	public static String TITLE_MENU=App.getT("Pelanggan");
	public static String TITLE_TOOLBAR=TITLE_MENU;
	public static String URL_ICON_16="/image/work-16.png";
	public static String URL_ICON_32="/image/work-32.png";
	public static String URL_ICON_48="/image/work-48.png";
	public static Icon ICON_16=App.getIcon(URL_ICON_16);
	public static Icon ICON_32=App.getIcon(URL_ICON_32);
	public static Icon ICON_48=App.getIcon(URL_ICON_48);
	public static boolean ADD=false;
	public static boolean EDIT=false;
	public static boolean DEL=false;
	public static boolean VIEW=false;

	public PelangganM() {
		super();
		lebar=0.4;
		title=TITLE_MENU;
	}

	@Override
	public void setDetailWidget() {
		detailWidget=new PpV();
	}

	@Override
	public void setEditWidget() {
		editWidget=new PelangganDE();
		
	}

	@Override
	public void setAddWidget() {
		addWidget=new PelangganDN();
	}

	@Override
	public void setTableWidget() {
		table =new PelangganTree();
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



}
