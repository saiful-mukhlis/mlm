package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public interface Toolbar {

	public abstract JPanel getPanel();

	public abstract void setPanel(JPanel panel);

	public abstract Window getWindow();

	public abstract void setWindow(Window window);
	
	public void init();
	
	public void build(ODatabaseDocumentTx db);

}