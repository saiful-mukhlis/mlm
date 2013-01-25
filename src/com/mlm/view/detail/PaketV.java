package com.mlm.view.detail;


import com.basic.icon.IconBase;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.view.def.PaketC;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class PaketV extends PaketC {
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = PaketM.TITLE_VIEW;
		icon = IconBase.VIEW;
		initComponent(db);
		setColorView();
		setEditable(false);
		buildLabel(db);
		buildForm(db);
		buildPanel();
	}

	
	
	
}
