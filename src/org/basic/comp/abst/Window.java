package org.basic.comp.abst;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.base.PanelBottom;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.yasaf.view.ViewContext;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

public interface Window {

	void init();

	void build(ODatabaseDocumentTx db);

	void actionPrint();

	void actionPdf();

	void actionAdd();

	void actionEdit();

	void actionDel();

	void actionReload();

	void actionReg();

	void actionAbout();

	void actionExit();

	void actionClose();

	void actionPrintPreview();

	void actionWord();

	void actionExcel();

	void showWelcome();

	Toolbar getToolbar();

	void setToolbar(Toolbar toolbar);

	Menu getMenu();

	void setMenu(Menu menu);

	ToolWindowManager getToolWindowManager();

	void setToolWindowManager(ToolWindowManager toolWindowManager);

	ViewContext getViewContext();

	void setViewContext(ViewContext viewContext);

	JFrame getFrame();

	void setFrame(JFrame frame);

	PanelBottom getPanelBottom();

	void setPanelBottom(PanelBottom panelBottom);

	JProgressBar getProgressBar();

	void setProgressBar(JProgressBar progressBar);

	boolean isUseProgressBar();

	void setUseProgressBar(boolean useProgressBar);

	List getWidgetProtects();

	void setWidgetProtects(List widgetProtects);

//	List getMasters();

//	void setMasters(List masters);

	String getIdMasterOpen();

	void setIdMasterOpen(String idMasterOpen);

	void actionLogin();
	
	HashMap<String, Master> getMapMaster();

}