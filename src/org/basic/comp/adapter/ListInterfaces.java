package org.basic.comp.adapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.PanelBottomInterface;
import org.basic.comp.listener.WidgetInterface;

public interface ListInterfaces extends WidgetInterface {
	public void reload(ODatabaseDocumentTx db);

	public void addModel(ODocument o);
	
	public void editModel(ODocument model, int index);
	
	public void addWidget(WidgetInterface widget);
	
	public SplitButton getItemSearch();

	public TextFieldSearch getFieldSearch();

	public void aksiDelete(ODatabaseDocumentTx db);
	
	public void selected();
	
	public ODocument getModelSelected();

	public int getIndexRowSelected();
	
	public boolean isSearching();
	
	public MasterInterface getMaster();

	public void setMaster(MasterInterface master);
	
	public PanelBottomInterface getPanelBottom();

	public void setSimple();
	
}
