package org.basic.comp.abst;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface Toolbar {

	public abstract JPanel getPanel();

	public abstract void setPanel(JPanel panel);

	public abstract Window getWindow();

	public abstract void setWindow(Window window);
	
	public void init();
	
	public void build(ODatabaseDocumentTx db);

}