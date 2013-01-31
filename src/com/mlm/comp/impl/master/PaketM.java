package com.mlm.comp.impl.master;


import com.global.App;
import com.mlm.tree.PaketTree;
import com.mlm.view.add.PaketDN;
import com.mlm.view.detail.PaketV;
import com.mlm.view.edit.PaketDE;
import org.basic.comp.abst.MasterDefault;

import javax.swing.*;

public class PaketM extends MasterDefault {
	
	public static String TITLE_ADD=App.getT("Tambah Data Paket");
	public static String TITLE_EDIT=App.getT("Edit Data Paket");
	public static String TITLE_VIEW=App.getT("Data Paket");
	
	public static String TITLE=App.getT("Master Paket");
	public static String TITLE_MENU=App.getT("Paket");
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

	public PaketM() {
		super();
		lebar=0.6;
		title=TITLE_MENU;
	}

	@Override
	public void setDetailWidget() {
		detailWidget=new PaketV();
	}

	@Override
	public void setEditWidget() {
		editWidget=new PaketDE();
		
	}

	@Override
	public void setAddWidget() {
		addWidget=new PaketDN();
	}

	@Override
	public void setTableWidget() {
		table =new PaketTree();
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

	public void perspectiveDefault() {
		perspectiveDefault=true;
		table.getPanel().setVisible(true);
		detailWidget.getPanel().setVisible(true);
		if (editWidget!=null) {
			editWidget.getPanel().setVisible(true);
		}
		if (addWidget!=null) {
			addWidget.getPanel().setVisible(true);
		}
		splitPane.setDividerLocation(getDevide());
	}

}
