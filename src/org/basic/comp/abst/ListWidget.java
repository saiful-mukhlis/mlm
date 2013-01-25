package org.basic.comp.abst;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface ListWidget {

	JPanel getPanel();

	void setPanel(JPanel panel);

	void build(ODatabaseDocumentTx db);
	
	void addWidgetChange(WidgetChangeObj widgetChangeObj);
	
	TableModel getTableModel();

}