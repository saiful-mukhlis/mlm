package org.basic.comp.abst;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface AddPanel {

	void init();

	void build(ODatabaseDocumentTx db);

	JPanel getPanel();

	void setPanel(JPanel panel);

	boolean isBuilded();

	void setBuilded(boolean builded);

	Master getMaster();

	void setMaster(Master master);
	
	void requestDefaultFocus();

}