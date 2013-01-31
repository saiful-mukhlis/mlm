package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public interface ListWidget {

	JPanel getPanel();

	void setPanel(JPanel panel);

	void build(ODatabaseDocumentTx db);
	
	void addWidgetChange(WidgetChangeObj widgetChangeObj);
	
	TableModel getTableModel();

}