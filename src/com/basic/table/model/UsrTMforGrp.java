package com.basic.table.model;

import java.util.List;

import com.basic.comp.impl.master.UsrTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class UsrTMforGrp extends UsrTableModel {



	@Override
	public void setModels(List models) {
		this.models=models;
		fireTableDataChanged();
	}



	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			paging.getPanel().setVisible(false);
		}
		
	}



	

}
