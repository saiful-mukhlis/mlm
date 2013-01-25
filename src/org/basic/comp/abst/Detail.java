package org.basic.comp.abst;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface Detail {

	public abstract void init();

	public abstract void build(ODatabaseDocumentTx db);

	public abstract JPanel getPanel();

	public abstract void setPanel(JPanel panel);

}