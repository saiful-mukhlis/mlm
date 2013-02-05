package org.basic.comp.listener;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

import javax.swing.*;

public interface WidgetInterface {
	void init();
	void build(ODatabaseDocumentTx db);
	void load(ODocument model);
	JPanel getPanel();
}
