package org.basic.comp.abst;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface EditPanel {

	public abstract void init();

	public abstract void build(ODatabaseDocumentTx db);

	public abstract JPanel getPanel();

	public abstract void setPanel(JPanel panel);

	void requestDefaultFocus();

	void setMaster(Master master);

	Master getMaster();

	void setBuilded(boolean builded);

	boolean isBuilded();

	void setIndex(int index);
}