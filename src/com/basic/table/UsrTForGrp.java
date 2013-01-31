package com.basic.table;

import com.basic.comp.impl.table.UsrTable;
import com.basic.table.model.UsrTMforGrp;
import org.jdesktop.swingx.JXTable;

public class UsrTForGrp extends UsrTable{

	public void init() {
		super.init();
		tableModel = new UsrTMforGrp();
		table = new JXTable(tableModel);
	}

}
