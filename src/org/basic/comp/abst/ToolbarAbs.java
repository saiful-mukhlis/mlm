package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public class ToolbarAbs implements Toolbar {
	protected JPanel panel;
	protected WindowInterfaces window;
	
	public void init() {
		panel=new JPanel();
	}
	
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

	@Override
	public WindowInterfaces getWindow() {
		return window;
	}

	@Override
	public void setWindow(WindowInterfaces window) {
		this.window = window;
	}

}
