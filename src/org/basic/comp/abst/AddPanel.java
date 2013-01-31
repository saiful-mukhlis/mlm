package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

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