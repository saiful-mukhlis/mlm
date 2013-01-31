package org.basic.comp.adapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.table.TableModel;

public interface ParentPagingInterface extends TableModel {
	public void setPaging(PagingInterface paging);

	public void loadJumlahData(ODatabaseDocumentTx db);

	public void reload(ODatabaseDocumentTx db);
}
