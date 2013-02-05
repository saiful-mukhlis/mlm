package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public interface Toolbar {

	public abstract JPanel getPanel();

	public abstract void setPanel(JPanel panel);

	public abstract WindowInterfaces getWindow();

	public abstract void setWindow(WindowInterfaces window);
	
	public void init();
	
	public void build(ODatabaseDocumentTx db);

}