package org.basic.comp.adapter;

import javax.swing.table.TableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface ParentPagingInterface extends TableModel {
	public void setPaging(PagingInterface paging);

	public void loadJumlahData(ODatabaseDocumentTx db);

	public void reload(ODatabaseDocumentTx db);
}
