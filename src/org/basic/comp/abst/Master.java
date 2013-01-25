package org.basic.comp.abst;

import javax.swing.Icon;
import javax.swing.JPanel;

import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface Master {

	void init();

	void build(ODatabaseDocumentTx db);

	JPanel getPanel();

	void setPanel(JPanel panel);

	AddPanel getAddDialog();

	void setAddDialog(AddPanel addDialog);

	ListWidget getListWidget();

	void setListWidget(ListWidget listWidget);

	EditPanel getEditDialog();

	void setEditDialog(EditPanel editDialog);

	Detail getDetail();

	void setDetail(Detail detail);

	ToolbarSmall getToolbarSmall();

	void setToolbarSmall(ToolbarSmall toolbarSmall);

	int getDevide();

	void setDevide(int devide);

	double getLebar();

	void setLebar(double lebar);

	Window getWindow();

	void setWindow(Window window);

	void requestDefaultSelected();

	void actionAdd();

	void actionEdit();

	void actionView();

	void actionReload();

	void actionDel();

	void actionPrint();

	void actionPrintPreview();

	void actionSearch();

	void actionPdf();

	void actionWord();

	void actionExcel();

	boolean isAdd();

	boolean isDel();

	boolean isView();

	boolean isEdit();

	boolean isPrint();

	boolean isSearch();

	boolean isPdf();

	boolean isWord();

	boolean isExcel();

	String getTitle();

	String getUrlIcon();

	Icon getIcon16();

	Icon getIcon32();

	String getTitleToolBar();
	
	AddContentAction getAca();
	
	ViewContextAction getVca();

	String getIdMaster();

	boolean isBuilded();

	int getMnemonic();

	void buildPanel(ODatabaseDocumentTx db);


}