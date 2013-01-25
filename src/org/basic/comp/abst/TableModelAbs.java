package org.basic.comp.abst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.basic.comp.adapter.TableModelAdapter;
import org.basic.dao.adapter.DaoInterface;

import com.global.App;
import com.global.util.Load;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class TableModelAbs extends TableModelAdapter implements TableModel {
	protected String[] nameColumn;
	protected List models;
	protected List<ODocument> modelDocs = new ArrayList<>();
	protected Paging paging;
	protected ListWidget listWidget;
	protected DaoInterface dao;
	protected String textSearch;
	protected HashMap<String, String> mapSearch;

	public TableModelAbs() {
		super();
		init();
	}

	public void init() {
		models=new ArrayList<>();
	}

	public String[] getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(String[] nameColumn) {
		this.nameColumn = nameColumn;
	}

	public List getModels() {
		return models;
	}

	public void setModels(List models) {
		this.models = models;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public ListWidget getListWidget() {
		return listWidget;
	}

	public void setListWidget(ListWidget listWidget) {
		this.listWidget = listWidget;
	}

	@Override
	public int getRowCount() {
		return models.size();
	}

	@Override
	public int getColumnCount() {
		return nameColumn.length;
	}

	@Override
	public String getColumnName(int i) {
		return nameColumn[i];
	}

	@Override
	public void load(ODatabaseDocumentTx db) {
		modelDocs=Load.load(db, paging, dao, modelDocs);
		convertDocsToObjects();
		fireTableDataChanged();
	}

	public void convertDocsToObjects() {
		
	}

	public void loadSearchLike(ODatabaseDocumentTx db, String col, String value) {
		Load.loadSimpleSearchLikePersenValuePersen(db, paging, dao, modelDocs, col, value);
		convertDocsToObjects();
		fireTableDataChanged();
	}
	
	public void loadCollectionSearchLike(ODatabaseDocumentTx db, String col, String col2, String value) {
		Load.loadCollectionSimpleSearchLikePersenValuePersen(db, paging, dao, modelDocs, col, col2, value);
		convertDocsToObjects();
		fireTableDataChanged();
	}

	@Override
	public void reload() {
		ODatabaseDocumentTx db = App.getDbdLocal();
		if ((textSearch == null || textSearch.trim().equals(""))
				&& (mapSearch == null || mapSearch.size() == 0)) {
			load(db);
		} else if (mapSearch != null && mapSearch.size() > 0) {
		} else if (textSearch != null && !textSearch.trim().equals("")) {
		}
		fireTableDataChanged();

	}
	public int getNo(int rowIndex){
		int no = rowIndex + 1;
		if (paging != null) {
				no +=paging.getCountStart();
		}
		return no;
	}

	@Override
	public void editModel(Object model, int index) {
		fireTableDataChanged();
	}

	@Override
	public void addModel(Object model) {
		fireTableDataChanged();
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	public HashMap<String, String> getMapSearch() {
		return mapSearch;
	}

	public void setMapSearch(HashMap<String, String> mapSearch) {
		this.mapSearch = mapSearch;
	}

	@Override
	public void setBind(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delModel(ODatabaseDocumentTx db, int i) {
		ODocument o=modelDocs.get(i);
		dao.delete(db, o);
		modelDocs.remove(i);
		models.remove(i);
		fireTableDataChanged();
	}

}
