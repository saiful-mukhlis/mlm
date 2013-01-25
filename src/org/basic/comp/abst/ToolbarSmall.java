package org.basic.comp.abst;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface ToolbarSmall {

	void init();

	void build(ODatabaseDocumentTx db);

	JPanel getPanel();

	void setPanel(JPanel panel);
	
	void setMaster(Master master);
	
	Master getMaster();

	void setFactorySearch(FactorySearch factorySearch);

}