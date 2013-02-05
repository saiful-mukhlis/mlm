package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public interface Menu {

	void init();

	void build(ODatabaseDocumentTx db);

	WindowInterfaces getWindow();

	void setWindow(WindowInterfaces window);

	JMenuBar getMenuBar();
	
}