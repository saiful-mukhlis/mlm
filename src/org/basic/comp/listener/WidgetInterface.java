package org.basic.comp.listener;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

import javax.swing.*;

public interface WidgetInterface {
	public void build(ODatabaseDocumentTx db);
	public void load(ODocument model);
	public JPanel getPanel();
}
