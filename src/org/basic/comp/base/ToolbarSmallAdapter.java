package org.basic.comp.base;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.abst.WidgetPrivilege;
import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.ToolbarSmallInterface;

import javax.swing.*;
import java.awt.*;

public class ToolbarSmallAdapter extends JToolBar implements ToolbarSmallInterface, WidgetPrivilege {
	public MasterInterface master;
	public ToolbarSmallAdapter(MasterInterface master) {
		super();
		this.master=master;
//		FormLayout layout = new FormLayout(
//				" 4dlu,  	f:p,  4dlu,   p,  4dlu,   	p,  4dlu,   	p:g,  4dlu,  p,  4dlu,  	p:g,  4dlu,  	p,  4dlu,   	"
//						+ "p,  2dlu,  p,  2dlu,p,  2dlu,p,  2dlu,p,   4dlu,",
//
//				"p,3dlu");
//		setLayout(layout);

		setBackground(Color.WHITE);
	}


	public MasterInterface getMaster() {
		return master;
	}

	public void setMaster(MasterInterface master) {
		this.master = master;
	}


	@Override
	public void build(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	protected JPanel panel;
	@Override
	public JPanel getPanel() {
		if (panel==null) {
			panel=new JPanel(new BorderLayout());
			panel.add(this, BorderLayout.CENTER);
		}
		return panel;
	}


	@Override
	public WindowInterfaces getWindow() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setWindow(WindowInterfaces window) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setItemSearch(SplitButton splitButton) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setFieldSearch(TextFieldSearch fieldSearch) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void changePrivilege() {
		// TODO Auto-generated method stub
		
	}


	


	
	
}
