package com.basic.table.model;

import com.basic.comp.impl.table.model.UsrTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import java.util.List;

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
