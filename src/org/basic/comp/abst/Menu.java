package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public interface Menu {

	void init();

	void build(ODatabaseDocumentTx db);

	Window getWindow();

	void setWindow(Window window);

	JMenuBar getMenuBar();
	
}