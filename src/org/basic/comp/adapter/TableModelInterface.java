package org.basic.comp.adapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.dao.adapter.DaoInterface;

import javax.swing.table.TableModel;
import java.util.List;

public interface TableModelInterface extends TableModel {
	public void addModel(ODocument model);

	public void editModel(ODocument model, int index);

	public void setTableParent(TableInterfaces table);

	public TableInterfaces getTableParent();

	public boolean isSearching();

	public void reload(ODatabaseDocumentTx db);

	public void setPaging(PagingInterface paging);

	public void fireTableDataChanged();

	public void loadDataModel(ODatabaseDocumentTx db);

	public void loadJumlahData(ODatabaseDocumentTx db);

	public void initDao();

	public void initNamaKolom(ODatabaseDocumentTx db);

	public List getModels();

	public void setModels(List model);

	public DaoInterface getDao();

	public String[] getNamaKolom();

	public SplitButton getItemSearch();

	public TextFieldSearch getFieldSearch();

	public void setBind();

	public ODocument getBind();
	
	public void load(ODatabaseDocumentTx db);
}
