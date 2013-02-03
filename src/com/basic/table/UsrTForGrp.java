package com.basic.table;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import com.basic.comp.impl.table.UsrTable;
import com.basic.table.model.UsrTMforGrp;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import org.jdesktop.swingx.JXTable;

public class UsrTForGrp extends UsrTable{

	public void init() {
		super.init();
		tableModel = new UsrTMforGrp();
		table = new JXTable(tableModel);
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		super.build(db);
		JLabel label=new  JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(App.borderBlackBottom5555b);
		label.setText(App.getT("Member"));
		panel.add(label, BorderLayout.NORTH);
	}
	
	

}
