package org.basic.comp.abst;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class ToolbarAbs implements Toolbar {
	protected JPanel panel;
	protected Window window;
	
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
	public Window getWindow() {
		return window;
	}

	@Override
	public void setWindow(Window window) {
		this.window = window;
	}

}
