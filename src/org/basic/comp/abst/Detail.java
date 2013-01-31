package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public interface Detail {

	public abstract void init();

	public abstract void build(ODatabaseDocumentTx db);

	public abstract JPanel getPanel();

	public abstract void setPanel(JPanel panel);

}