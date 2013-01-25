package org.basic.comp.adapter;

import javax.swing.JPanel;

import org.basic.comp.abst.WidgetPrivilege;


import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface ToolbarAdapter extends WidgetPrivilege{
	public void build(ODatabaseDocumentTx db);
	public JPanel getPanel();
	public void setWindow(WindowInterfaces window);
	public void showMaxi();
	public void showMini();
}
