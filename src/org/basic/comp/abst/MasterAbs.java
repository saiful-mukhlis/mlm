package org.basic.comp.abst;

import com.basic.comp.impl.action.ViewMasterAction;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.base.SplitPane;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import javax.swing.*;

import java.awt.*;

public class MasterAbs implements Master {
	protected JPanel panel;
	protected AddPanel addDialog;
	protected ListWidget listWidget;
	protected EditPanel editDialog;
	protected Detail detail;
	protected ToolbarSmall toolbarSmall;
	protected int devide;
	protected double lebar=0.5;
	protected WindowInterfaces window;
	protected ViewMasterAction viewMasterAction;
//	protected ViewContextAction vca;
//	protected AddContentAction aca;
	protected boolean builded=false;
	
	protected FactorySearch factorySearch;

	public WindowInterfaces getWindow() {
		return window;
	}

	public void setWindow(WindowInterfaces window) {
		this.window = window;
	}

	@Override
	public void init() {
		panel=new JPanel(new BorderLayout());
		
		viewMasterAction=new ViewMasterAction(this, window.getViewContext(), getIdMaster());
		App.getActions().put(getIdMaster(), viewMasterAction);
//		vca=new ViewContextAction(getTitleMenu(), getIcon16(), window.getViewContext(), getIdMaster());
		
//		aca=new AddContentAction(this);
	}

	public int getMnemonic() {
		return 0;
	}

	public String getTitleMenu() {
		return null;
	}

	public String getIdMaster() {
		return null;
	}

	public void build(ODatabaseDocumentTx db) {
		addDialog.setMaster(this);
		addDialog.addParent((WidgetAddObj) listWidget.getTableModel());
		Double tmp=App.getW()*lebar;
		devide=tmp.intValue();
		
		toolbarSmall.setMaster(this);
		
		
		
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
	public AddPanel getAddDialog() {
		return addDialog;
	}

	@Override
	public void setAddDialog(AddPanel addDialog) {
		this.addDialog = addDialog;
	}

	@Override
	public ListWidget getListWidget() {
		return listWidget;
	}

	@Override
	public void setListWidget(ListWidget listWidget) {
		this.listWidget = listWidget;
	}

	@Override
	public EditPanel getEditDialog() {
		return editDialog;
	}

	@Override
	public void setEditDialog(EditPanel editDialog) {
		this.editDialog = editDialog;
	}

	@Override
	public Detail getDetail() {
		return detail;
	}

	@Override
	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	@Override
	public ToolbarSmall getToolbarSmall() {
		return toolbarSmall;
	}

	@Override
	public void setToolbarSmall(ToolbarSmall toolbarSmall) {
		this.toolbarSmall = toolbarSmall;
	}

	@Override
	public int getDevide() {
		return devide;
	}

	@Override
	public void setDevide(int devide) {
		this.devide = devide;
	}

	@Override
	public double getLebar() {
		return lebar;
	}

	@Override
	public void setLebar(double lebar) {
		this.lebar = lebar;
	}

	@Override
	public void requestDefaultSelected() {
	}

	@Override
	public void actionAdd() {
		if (!addDialog.isBuilded()) {
			ODatabaseDocumentTx db=App.getDbdLocal();
			addDialog.init();
			addDialog.build(db);
			db.close();
		}
		
		App.showDialog(panel, addDialog.getPanel());
	}

	@Override
	public void actionEdit() {
		
		
	}

	@Override
	public void actionView() {
		
		
	}

	@Override
	public void actionReload() {
		
		
	}

	@Override
	public void actionDel() {
		
		
	}

	@Override
	public void actionPrint() {
		
		
	}

	@Override
	public void actionPrintPreview() {
		
		
	}

	@Override
	public void actionSearch() {
		
		
	}

	@Override
	public void actionPdf() {
		
		
	}

	@Override
	public void actionWord() {
		
		
	}

	@Override
	public void actionExcel() {
		
		
	}

	@Override
	public boolean isAdd() {
		
		return false;
	}

	@Override
	public boolean isDel() {
		
		return false;
	}

	@Override
	public boolean isView() {
		
		return false;
	}

	@Override
	public boolean isEdit() {
		
		return false;
	}

	@Override
	public boolean isPrint() {
		
		return false;
	}

	@Override
	public boolean isSearch() {
		
		return false;
	}

	@Override
	public boolean isPdf() {
		
		return false;
	}

	@Override
	public boolean isWord() {
		
		return false;
	}

	@Override
	public boolean isExcel() {
		
		return false;
	}

	@Override
	public String getTitle() {
		
		return null;
	}

	@Override
	public String getUrlIcon() {
		
		return null;
	}

	@Override
	public Icon getIcon16() {
		
		return null;
	}

	@Override
	public Icon getIcon32() {
		
		return null;
	}

	@Override
	public String getTitleToolBar() {
		return null;
	}



	public ViewMasterAction getViewMasterAction() {
		return viewMasterAction;
	}

	public void setViewMasterAction(ViewMasterAction viewMasterAction) {
		this.viewMasterAction = viewMasterAction;
	}

//	public AddContentAction getAca() {
//		return aca;
//	}
//
//	public void setAca(AddContentAction aca) {
//		this.aca = aca;
//	}

	@Override
	public boolean isBuilded() {
		return builded;
	}

	@Override
	public void buildPanel(ODatabaseDocumentTx db) {
		builded=true;
		listWidget.build(db);
		detail.build(db);
		
		TableModel tm=listWidget.getTableModel();
		if (tm instanceof FactorySearch) {
			FactorySearch factorySearch=(FactorySearch) tm;
			toolbarSmall.setFactorySearch(factorySearch);
		}
		toolbarSmall.build(db);
		
		SplitPane splitPane = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
				listWidget.getPanel(), detail.getPanel());
		
		panel.add(toolbarSmall.getPanel(), BorderLayout.NORTH);
		panel.add(splitPane, BorderLayout.CENTER);
		

		if (addDialog==null) {
			System.out.println("Asdfasdf");
		}
		
	}

	@Override
	public String getTitleAction() {
		return null;
	}

	@Override
	public String getDescAction() {
		return null;
	}

	@Override
	public KeyStroke getKeyStroke() {
		return null;
	}



	
	

}
