package org.basic.comp.adapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

import javax.swing.*;

public interface TableInterfaces extends ListInterfaces {
//	public void reload(ODatabaseDocumentTx db);
//
//	public void addModel(ODocument o);
//	
//	public void editModel(ODocument model, int index);

//	public ODocument getModelSelected();
//
//	public int getIndexRowSelected();

	public JTable getTable();

//	public boolean isSearching();

	public TableModelInterface getTableModel();

	public void setMasterEfectWidget(EfectWidget master);

	public void setTypeEfectWidget(int typeEfectWidget);

//	public void selected();

	public void initTableModel(ODatabaseDocumentTx db);

//	public void addWidget(WidgetInterface widget);

//	public void aksiDelete(ODatabaseDocumentTx db);

	public void setShowAll();

	public void setSimple();

	public WindowInterfaces getWindow();

	public void setWindow(WindowInterfaces window);

//	public MasterInterface getMaster();
//
//	public void setMaster(MasterInterface master);

//	public PanelBottomInterface getPanelBottom();

	public String[] getNamaKolom();

//	public SplitButton getItemSearch();
//	public TextFieldSearch getFieldSearch();

	public void setBind();

	public ODocument getBind();
}
