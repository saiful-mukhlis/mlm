package org.basic.comp.abst;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.basic.comp.base.ScrollPane;

import com.basic.icon.IconBase;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class DetailAbs implements Detail, WidgetChangeObj {
	protected JPanel panel;
	protected JLabel label;
	protected JPanel panelForm;
	protected Master master;
	protected ScrollPane scrollPane;

	@Override
	public void init() {
		panel=new JPanel(new BorderLayout());
		label=new JLabel();
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setIcon(IconBase.VIEW);
		label.setBorder(App.borderBlackBottom5555);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(label, BorderLayout.NORTH);
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
	public void changeObj(Object o) {
		
	}

}
