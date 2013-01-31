package com.basic.comp.impl.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.basic.comp.abst.Master;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManager;
import org.noos.xing.yasaf.view.ViewContext;
import org.noos.xing.yasaf.view.ViewContextChangeListener;
import org.noos.xing.yasaf.view.event.ViewContextChangeEvent;

import com.basic.icon.IconBase;
import com.basic.lang.LApp;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class ViewMasterAction extends AbstractAction implements ViewContextChangeListener{

	private Master master;
	private ViewContext viewContext;
	private Object key;
	private Object enableKey;
	
	public ViewMasterAction(Master master, ViewContext viewContext, Object key) {
        super(master.getTitleAction(), master.getIcon16());
        this.master=master;
        putValue(SHORT_DESCRIPTION, master.getDescAction());  
        putValue(MNEMONIC_KEY, master.getMnemonic());
        putValue(ACCELERATOR_KEY, master.getKeyStroke());
        
        this.viewContext = viewContext;
		this.key = key;
    }
	@Override
	public void actionPerformed(ActionEvent e) {

		ContentManager contentManager = master.getWindow().getToolWindowManager().getContentManager();
		Content content = contentManager.getContent(master.getIdMaster());
		if (content == null) {
			if (!master.isBuilded()) {
				ODatabaseDocumentTx db=App.getDbdLocal();
				master.buildPanel(db);
				db.close();
			}
			content = contentManager.addContent(master.getIdMaster(), master.getTitleAction(), master.getIcon16(),
					master.getPanel(), master.getTitle()); // yang terakhir ini tooltip
			content.getContentUI().setAlwaysOnTop(false);
			if ((master.getMnemonic()) != -1)
				content.setMnemonic(((int)master.getMnemonic()));
			content.setSelected(true);
		} else {
			content.setSelected(true);
		}
	
		
		//viewContext.put(key, e);
	}

	public void contextChange(ViewContextChangeEvent evt) {
		setEnabled(evt.getNewValue() != null);
	}

}
