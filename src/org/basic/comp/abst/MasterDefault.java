package org.basic.comp.abst;

import com.basic.print.interfaces.PrintInterface;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.adapter.DetailWidgetInterface;
import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.adapter.MasterAdapter;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.base.SplitPane;
import org.basic.comp.base.ToolbarSmallRLTEDPPEWWIthSearch;
import org.basic.comp.listener.AddDialogInterface;
import org.basic.comp.listener.EditDialogInterface;
import org.basic.comp.listener.MasterDefaultInterface;
import org.basic.comp.listener.ToolbarSmallInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.Dialog.ModalityType;

public abstract  class MasterDefault extends MasterAdapter implements MasterDefaultInterface{


	protected ListInterfaces table;
	
	protected AddDialogInterface addWidget;
	protected EditDialogInterface editWidget;
	protected DetailWidgetInterface detailWidget;

	protected SplitPane splitPane;

	protected JPanel panelAction;

	protected ToolbarSmallInterface toolBar;
	protected double lebar;
	protected String title;
	
	protected PrintInterface printer;
	
	public int getDevide() {
		Double tmp = App.getW()*lebar;
		return tmp.intValue();
	}
	
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		table.build(db);
		table.setMaster(this);
		toolBar = new ToolbarSmallRLTEDPPEWWIthSearch(this);
		toolBar.setFieldSearch(table.getFieldSearch());
		toolBar.setItemSearch(table.getItemSearch());
		toolBar.build(db);
		table.addWidget(toolBar);
		
	}
	
	@Override
	public void build(ODatabaseDocumentTx db) {
		setDetailWidget();
		detailWidget.build(db);
		setTableWidget();
		table.addWidget(detailWidget);
		init(db);
		buildBody(db);
		setLayout();
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		splitPane = new SplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table.getPanel(), detailWidget.getPanel());
		

		//splitPane.setDividerSize(20);
		
		//splitPane.setOneTouchExpandable(true);
		//splitPane.setBorder(App.borderWhite);
		perspectiveDefault();
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar.getPanel(), BorderLayout.NORTH);
		
		
	}

	public ListInterfaces getTable() {
		return table;
	}
	
	public void actionReload() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.reload(db);
		db.close();
		
	}
	
	public void actionDel(){
		if (isDel()) {
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			table.aksiDelete(db);
			db.close();
		}
	}

	public void perspectiveForm() {
		splitPane.setDividerLocation(getDevide());
	}

	

	public void buildBody(ODatabaseDocumentTx db){
		detailWidget.build(db);
	}
	
	



	
	

	public void actionView() {
		perspectiveForm();
		splitPane.setRightComponent(detailWidget.getPanel());
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	

	
	public void actionEdit() {
		if (isEdit()) {

			if (editWidget==null) {
				setEditWidget();
				ODatabaseDocumentTx db = App.getDbd();
			    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			    editWidget.build(db);
			    editWidget.setListWidget(table);
			    table.addWidget(editWidget);
			    table.selected();
				db.close();
			}
			editWidget.setIndex(table.getIndexRowSelected());
			JDialog d = new JDialog(getWindow(panel), ModalityType.APPLICATION_MODAL);
			d.getContentPane().add(editWidget.getPanel());
			d.pack();
			setCenterDialog(d);
			d.setVisible(true);
			table.selected();
		
		}
	}
	
//	public void actionEdit() {
//		if (editForm==null) {
//			setEditForm();
//			editForm.setMaster(this);
//			ODatabaseDocumentTx db = App.getDbd();
//		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//			editForm.build(db);
//			db.close();
//			table.addWidgetChange(getEditForm());
//			editForm.addWidget(table);
//			editForm.addWidget(this);
//			table.selected();
//		}
//		perspectiveForm();
//		splitPane.setRightComponent(editForm.getPanel());
//		editForm.requestDefaultFocus();
//	}
	
	
	
//	@Override
//	public void actionSearch() {
//		if (searchForm==null) {
//			setSearchForm();
//			ODatabaseDocumentTx db = App.getDbd();
//		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//			searchForm.setMasterEfectWidget(this);
//			searchForm.build(db);
//			db.close();
//			searchForm.addWidget(table);
//		}
//		perspectiveForm();
//		splitPane.setRightComponent(searchForm.getPanel());
//		searchForm.requestDefaultFocus();
//	}


//	public void actionAdd() {
//		if (form==null) {
//			setForm();
//			ODatabaseDocumentTx db = App.getDbd();
//		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//		    form.setMaster(this);
//			form.build(db);
//			form.addWidget(table);
//			db.close();
//		}
//		splitPane.setRightComponent(form.getPanel());
//		perspectiveForm();
//		getForm().actionReset();
//		form.requestDefaultFocus();
//	}
	public void actionAdd() {
		if (isAdd()) {
			if (addWidget==null) {
				setAddWidget();
				ODatabaseDocumentTx db = App.getDbd();
			    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				addWidget.build(db);
				addWidget.setListWidget(table);
				db.close();
			}
			
			JDialog d = new JDialog(getWindow(panel), ModalityType.APPLICATION_MODAL);
			d.getContentPane().add(addWidget.getPanel());
			d.pack();
			setCenterDialog(d);
			addWidget.actionReset();
			d.setVisible(true);
			if (table instanceof TableInterfaces) {
				((TableInterfaces) table).getTable().addRowSelectionInterval(((TableInterfaces) table).getTableModel().getRowCount()-1, ((TableInterfaces) table).getTableModel().getRowCount()-1);
			}
			table.selected();
		}
	}
	@Override
	public void initPrefernce(ODatabaseDocumentTx db) {
		perspectiveDefault();
	}
	public void perspectiveDefault() {
		perspectiveDefault=true;
		table.getPanel().setVisible(true);
		table.setSimple();
		
//		if (table instanceof ListInterfaces) {
//			((TableInterfaces) table).setSimple();
//		}

		detailWidget.getPanel().setVisible(true);
		if (editWidget!=null) {
			editWidget.getPanel().setVisible(true);
		}
		if (addWidget!=null) {
			addWidget.getPanel().setVisible(true);
		}
		splitPane.setDividerLocation(getDevide());
	}
	public void perspective1() {
		perspectiveDefault=false;
		table.getPanel().setVisible(true);
		if (table instanceof TableInterfaces) {
			((TableInterfaces) table).setShowAll();
		}
		
		splitPane.setDividerLocation(1.0);
		detailWidget.getPanel().setVisible(false);
		if (editWidget!=null) {
			editWidget.getPanel().setVisible(false);
		}
		if (addWidget!=null) {
			addWidget.getPanel().setVisible(false);
		}
	}
	public void perspective2() {
		perspectiveDefault=false;
		table.getPanel().setVisible(false);
		detailWidget.getPanel().setVisible(true);
		if (editWidget!=null) {
			editWidget.getPanel().setVisible(true);
		}
		if (addWidget!=null) {
			addWidget.getPanel().setVisible(true);
		}
		splitPane.setDividerLocation(0.0);
	}
	


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public String getUrlIcon() {
//		return urlIcon;
//	}
//
//	public void setUrlIcon(String urlIcon) {
//		this.urlIcon = urlIcon;
//	}

	public boolean isPerspectiveDefault() {
		return perspectiveDefault;
	}


	public void setPerspectiveDefault(boolean perspectiveDefault) {
		this.perspectiveDefault = perspectiveDefault;
	}
	
	
	
	
	

	
	
	@Override
	public void actionPrint() {
		if (printer!=null) {
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			printer.runPrint(db);
			db.close();
		}
	}


	@Override
	public void actionPdf() {
		if (printer!=null) {
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			printer.runPdf(db);
			db.close();
		}
	}


	@Override
	public void actionWord() {
		if (printer!=null) {
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			printer.runWord(db);
			db.close();
		}
	}


	@Override
	public void actionExcel() {
		if (printer!=null) {
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			printer.runExcel(db);
			db.close();
		}
	}


	@Override
	public void actionPrintPreview() {
		if (printer!=null) {
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			printer.run(db);
			db.close();
		}
	}
	
	public WindowInterfaces getWindow(Object o){
		if (o instanceof WindowInterfaces) {
			return ((WindowInterfaces) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}
	
	public void setCenterDialog(JDialog d) {
		d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - d
				.getPreferredSize().width) / 2, (Toolkit.getDefaultToolkit()
				.getScreenSize().height - d.getPreferredSize().height) / 2);

	}


	@Override
	public void requestDefaultSelected() {
		if (table!=null) {
			if (table instanceof TableInterfaces) {
				((TableInterfaces) table).getTable().addRowSelectionInterval(0, 0);
			}
			table.selected();
		}
	}
	
	
	
}
