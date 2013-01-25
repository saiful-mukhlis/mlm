package org.basic.comp.abst;

import javax.swing.JMenuBar;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface Menu {

	void init();

	void build(ODatabaseDocumentTx db);

	Window getWindow();

	void setWindow(Window window);

	JMenuBar getMenuBar();
	
}