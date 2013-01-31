package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public interface ToolbarSmall {

	void init();

	void build(ODatabaseDocumentTx db);

	JPanel getPanel();

	void setPanel(JPanel panel);
	
	void setMaster(Master master);
	
	Master getMaster();

	void setFactorySearch(FactorySearch factorySearch);

}