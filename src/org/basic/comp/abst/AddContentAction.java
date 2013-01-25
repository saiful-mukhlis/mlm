package org.basic.comp.abst;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.noos.xing.mydoggy.*;
import org.noos.xing.yasaf.view.ViewContextChangeListener;
import org.noos.xing.yasaf.view.event.ViewContextChangeEvent;

import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class AddContentAction implements ActionListener,
		ViewContextChangeListener {

	public AddContentAction(Master master) {
		this.master=master;
	}

	public void contextChange(ViewContextChangeEvent evt) {
		actionPerformed(null);
	}

	public void actionPerformed(ActionEvent e) {
		ContentManager contentManager = master.getWindow().getToolWindowManager().getContentManager();
		Content content = contentManager.getContent(master.getIdMaster());
		if (content == null) {
			if (!master.isBuilded()) {
				ODatabaseDocumentTx db=App.getDbdLocal();
				master.buildPanel(db);
				db.close();
			}
			content = contentManager.addContent(master.getIdMaster(), master.getTitle(), master.getIcon16(),
					master.getPanel(), master.getTitle()); // yang terakhir ini tooltip
			content.getContentUI().setAlwaysOnTop(false);
			if ((master.getMnemonic()) != -1)
				content.setMnemonic(((int)master.getMnemonic()));
			content.setSelected(true);
		} else {
			content.setSelected(true);
		}
	}
	


	private Master master;

}
