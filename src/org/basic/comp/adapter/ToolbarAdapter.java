package org.basic.comp.adapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.abst.WidgetPrivilege;

import javax.swing.*;

public interface ToolbarAdapter extends WidgetPrivilege{
	public void build(ODatabaseDocumentTx db);
	public JPanel getPanel();
	public void setWindow(WindowInterfaces window);
	public void showMaxi();
	public void showMini();
}
