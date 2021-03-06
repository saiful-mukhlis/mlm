package com.basic.comp.impl.table;

import com.basic.comp.impl.table.model.GrpTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.abst.PagingAbs;
import org.basic.comp.abst.TableAbs;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

public class GrpTable extends TableAbs {

	@Override
	public void init() {
		super.init();
		PagingAbs p = new PagingAbs();
		p.build();
		tableModel = new GrpTableModel();
		p.setTableModel(tableModel);
		tableModel.setPaging(p);
		table = new JXTable(tableModel);
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init();
		super.build(db);
	}

	public void setSimple() {
		String[] x = tableModel.getNameColumn();
		for (String string : x) {
			TableColumnExt tcx = table.getColumnExt(string);
			if (tcx != null) {
				tcx.setVisible(false);
			}
		}

		TableColumnExt tcx = table.getColumnExt(x[0]);
		if (tcx != null) {
			tcx.setVisible(true);
		}

		tcx = table.getColumnExt(x[1]);
		if (tcx != null) {
			tcx.setVisible(true);
		}

		tcx = table.getColumnExt(x[2]);
		if (tcx != null) {
			tcx.setVisible(true);
		}



	}

	public void setShowAll() {
		String[] x = tableModel.getNameColumn();
		for (String string : x) {
			TableColumnExt tcx = table.getColumnExt(string);
			if (tcx != null) {
				tcx.setVisible(true);
			}
		}

	}
}
