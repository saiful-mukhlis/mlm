package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public class ToolbarSmallAbs implements ToolbarSmall {
	protected JPanel panel;

	@Override
	public void init() {

	}

	@Override
	public void build(ODatabaseDocumentTx db) {

	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
