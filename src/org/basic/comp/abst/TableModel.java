package org.basic.comp.abst;

import java.util.HashMap;
import java.util.List;

import org.basic.dao.adapter.DaoInterface;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface TableModel extends javax.swing.table.TableModel{
	void editModel(Object model, int index);
	void addModel(Object model);
	void setListWidget(ListWidget listWidget);
	void setTextSearch(String text);
	void setMapSearch(HashMap<String, String> mapSearch);
	String[] getNameColumn();
	void setBind(Object o);
	void load(ODatabaseDocumentTx db);
	void reload();
	void setPaging(Paging paging);
	List getModels();
	void setModels(List models);
	void delModel(ODatabaseDocumentTx db, int itmp);
	Paging getPaging();
	void reload(ODatabaseDocumentTx db);
}
