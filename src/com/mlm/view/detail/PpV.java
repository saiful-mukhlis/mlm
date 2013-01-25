package com.mlm.view.detail;

import com.basic.icon.IconBase;
import com.mlm.comp.impl.master.PelangganM;
import com.mlm.view.def.PpC;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class PpV extends PpC {
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = PelangganM.TITLE_VIEW;
		icon = IconBase.VIEW;
		initComponent(db);
		setColorView();
		setEditable(false);
		buildLabel(db);
		buildForm(db);
		buildPanel();
	}

	
	
	
}
