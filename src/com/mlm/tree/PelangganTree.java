package com.mlm.tree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.jdesktop.swingx.table.TableColumnExt;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;

import com.basic.icon.IconBase;
import com.basic.lang.LActions;
import com.basic.lang.LPelanggan;
import com.basic.lang.LWindow;
import com.global.App;
import com.mlm.comp.impl.master.PelangganM;
import com.mlm.comp.model.PelangganTreeTableNodeModel;
import com.mlm.db.FPelanggan;
import com.mlm.db.FPp;
import com.mlm.view.add.PelangganDAddDownline;
import com.mlm.view.add.PelangganDAddPaket;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganTree extends TreeTableDefault {

	public void initTreeModel() {
		colNames.add(App.getT("Member&Paket"));

		treeModel = new DefaultTreeTableModel(root, colNames);
	}

	public void load(ODatabaseDocumentTx db) {
		super.load(db);
		root = new DefaultMutableTreeTableNode(PelangganM.TITLE_MENU);
		for (ODocument o : models) {
			PelangganTreeTableNodeModel p = new PelangganTreeTableNodeModel(o);
			root.add(p);
		}
		if (treeModel != null) {
			treeModel.setRoot(root);
		}
	}

	@Override
	public void reload(ODatabaseDocumentTx db) {
		super.reload(db);
		root = new DefaultMutableTreeTableNode(PelangganM.TITLE_MENU);
		for (ODocument o : models) {
			PelangganTreeTableNodeModel p = new PelangganTreeTableNodeModel(o);
			root.add(p);
		}
		treeModel.setRoot(root);
		
		if (showSimple) {
			setSimple();
			showSimple = true;
			showColumn.setText("Show All");
		}else{
			
		}
		
		if (defaultIsExspan) {
			treeTable.expandAll();
		}else{
			treeTable.collapseAll();
		}
	}

	public TextFieldSearch getFieldSearch() {
		if (fieldSearch == null) {
			fieldSearch = new TextFieldSearch();
			fieldSearch = new TextFieldSearch();
			fieldSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String tmp = fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					} else {
						ODatabaseDocumentTx db = App.getDbd();
						ODatabaseRecordThreadLocal.INSTANCE.set(db);
						reload(db);
						db.close();
					}
				}
			});
		}
		return fieldSearch;
	}

	public SplitButton getItemSearch() {
		if (itemSearch == null) {
			itemSearch = new SplitButton(LWindow.KET_SEARCH
					+ LPelanggan.NAMA_TOKO);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LPelanggan.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPelanggan.CODE);
					colSearch = FPelanggan.CODE;
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LPelanggan.NAMA_TOKO);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH
							+ LPelanggan.NAMA_TOKO);
					colSearch = FPelanggan.NAMA_TOKO;
				}
			});
			menuItemSearch.add(item);

			itemSearch.setDropDownMenu(menuItemSearch);
			itemSearch.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String tmp = fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					} else {
						ODatabaseDocumentTx db = App.getDbd();
						ODatabaseRecordThreadLocal.INSTANCE.set(db);
						reload(db);
						db.close();
					}

				}
			});

		}
		return itemSearch;
	}

	public void initDao() {
		dao = App.getPelangganDao();
	}

	public MutableTreeTableNode createNode(ODocument o) {
		return new PelangganTreeTableNodeModel(o);
	}

//	protected boolean addCol = true;
	protected boolean showSimple = true;
	protected JMenuItem reload;
	protected JMenuItem add;
	protected JMenuItem edit;
	protected JMenuItem del;
	protected JMenuItem showControl;
	protected JMenuItem expandAll;
	protected JMenuItem collapseAll;
	protected JMenuItem showColumn;
	
	protected JMenuItem addPaket;
	protected JMenuItem addDownline;
	
	protected PelangganDAddPaket dialogAddPaket;
	protected PelangganDAddDownline dialogAddDownline;
	protected ListInterfaces table=this;
	
	protected boolean defaultIsExspan=false;

	protected void createPopup() {
		
		popup = new JPopupMenu();
		showControl = new JMenuItem(LActions.SHOW_CONTROL);
		expandAll = new JMenuItem(LActions.EXPAND_ALL);
		collapseAll = new JMenuItem(LActions.COLLAPSE_ALL);
		showColumn = new JMenuItem(LActions.SHOW_COLUMN_ALL);
		
		reload = new JMenuItem(LWindow.RELOAD);
		add = new JMenuItem(LWindow.ADD);
		edit = new JMenuItem(LWindow.EDIT);
		del = new JMenuItem(LWindow.DELETE);
		
		addPaket = new JMenuItem(LPelanggan.ADD_PAKET);
		addDownline =  new JMenuItem(LPelanggan.ADD_DOWNLINE);

		reload.setIcon(IconBase.RELOAD);
		add.setIcon(IconBase.ADD);
		edit.setIcon(IconBase.EDIT);
		del.setIcon(IconBase.DEL);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (masterAction != null) {
					masterAction.actionAdd();
				}
			}
		});
		
		addPaket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				if (true) {
				if (dialogAddPaket==null) {
					dialogAddPaket=new PelangganDAddPaket(); 
					ODatabaseDocumentTx db = App.getDbd();
				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				    dialogAddPaket.build(db);
				    dialogAddPaket.setListWidget(table);
					db.close();
				}
				PelangganTreeTableNodeModel x=(PelangganTreeTableNodeModel)nodeSelected;
				dialogAddPaket.load(x.getDocumentToString().getO());
				JDialog d = new JDialog(getWindow(panel), ModalityType.APPLICATION_MODAL);
				d.getContentPane().add(dialogAddPaket.getPanel());
				d.pack();
				setCenterDialog(d);
				dialogAddPaket.actionReset();
				d.setVisible(true);
				if (table instanceof TableInterfaces) {
					((TableInterfaces) table).getTable().addRowSelectionInterval(((TableInterfaces) table).getTableModel().getRowCount()-1, ((TableInterfaces) table).getTableModel().getRowCount()-1);
				}
				table.selected();
			}}
		});
		
		
		addDownline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				if (true) {
				if (dialogAddDownline==null) {
					dialogAddDownline=new PelangganDAddDownline(); 
					ODatabaseDocumentTx db = App.getDbd();
				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				    dialogAddDownline.build(db);
				    dialogAddDownline.setListWidget(table);
					db.close();
				}
				PelangganTreeTableNodeModel x=(PelangganTreeTableNodeModel)nodeSelected;
				dialogAddDownline.load(x.getDocumentToString().getO());
				JDialog d = new JDialog(getWindow(panel), ModalityType.APPLICATION_MODAL);
				d.getContentPane().add(dialogAddDownline.getPanel());
				d.pack();
				setCenterDialog(d);
				dialogAddDownline.actionReset();
				d.setVisible(true);
				if (table instanceof TableInterfaces) {
					((TableInterfaces) table).getTable().addRowSelectionInterval(((TableInterfaces) table).getTableModel().getRowCount()-1, ((TableInterfaces) table).getTableModel().getRowCount()-1);
				}
				table.selected();
			}}
		});
		
		

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (masterAction != null) {
					masterAction.actionEdit();
				}
			}
		});

		reload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (masterAction != null) {
					masterAction.actionReload();
				}

			}
		});

		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (masterAction != null) {
					masterAction.actionDel();
				}

			}
		});

		

		showControl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (treeTable.isColumnControlVisible()) {
					treeTable.setColumnControlVisible(false);

					showControl.setText("Show Control Column");
				} else {
					treeTable.setColumnControlVisible(true);
					showControl.setText("Hidden Control Column");
				}
			}
		});

		expandAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				treeTable.expandAll();
				defaultIsExspan=true;
			}
		});
		collapseAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				treeTable.collapseAll();
				defaultIsExspan=false;
			}
		});
		showColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (showSimple) {
//					if (addCol) {
						setColShowAll();
						treeModel.setRoot(root);
//						setSimple();
//						addCol = false;

//					}
//					else {
//						for (int i = 1; i < colNames.size(); i++) {
//							TableColumnExt tcx = treeTable
//									.getColumnExt(colNames.get(i));
//							if (tcx != null) {
//								tcx.setVisible(false);
//							}
//						}
//					}
					showSimple = false;
					showColumn.setText("Show Simple");
				} else {
					setSimple();
					treeModel.setRoot(root);
					showSimple = true;
					showColumn.setText("Show All");
				}

			}
		});

		popup.add(reload);
		popup.add(add);
		popup.add(edit);
		popup.add(del);

		popup.addSeparator();
		
		popup.add(addPaket);
		popup.add(addDownline);
		
		popup.addSeparator();

		popup.add(showControl);
		popup.add(expandAll);
		popup.add(collapseAll);
		popup.add(showColumn);

		treeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					try {
						int row = treeTable.rowAtPoint(e.getPoint());
						treeTable.clearSelection();
						treeTable.addRowSelectionInterval(row, row);
						selected();
						
						PelangganTreeTableNodeModel x=(PelangganTreeTableNodeModel)nodeSelected;
						ODocument tmp=x.getDocumentToString().getO();
						if (tmp.field("@class").equals(FPp.TABLE)) {
							addPaket.setEnabled(false);
							addDownline.setEnabled(true);
						}else{
							addPaket.setEnabled(true);
							addDownline.setEnabled(false);
						}
						
						edit.setEnabled(true);
						del.setEnabled(true);
					} catch (Exception e2) {
						edit.setEnabled(false);
						del.setEnabled(false);
						addPaket.setEnabled(false);
						addDownline.setEnabled(false);
					}

					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void setColShowAll(){
		colNames.add(App.getT("Code"));
		colNames.add(App.getT("Nama Member"));
		colNames.add(App.getT("Pemilik"));
		colNames.add(App.getT("Jenis Identitas"));
		colNames.add(App.getT("No Identitas"));
		colNames.add(App.getT("Alamat"));
		colNames.add(App.getT("Kota"));
		colNames.add(App.getT("No Telp"));
		colNames.add(App.getT("No Fax"));
		colNames.add(App.getT("No Hp1"));
		colNames.add(App.getT("No Hp2"));
		colNames.add(App.getT("Pin Bb1"));
		colNames.add(App.getT("Pin Bb2"));
		colNames.add(App.getT("Status"));
//		treeModel.setColumnIdentifiers(colNames);
	}
	
	public void setSimple() {
		int tmp=colNames.size();
		for (int i = 1; i < tmp; i++) {
			TableColumnExt tcx = treeTable.getColumnExt(colNames.get(i));
			if (tcx != null) {
				tcx.setVisible(false);
			}
		}
		
		int jml=colNames.size()-1;
		if (jml>1) {
			
			for (int i = jml; i >= 1; i--) {
				colNames.remove(i);
			}
		}
//		treeModel.setColumnIdentifiers(colNames);
		


	}
	
	
	public Window getWindow(Object o){
		if (o instanceof Window) {
			return ((Window) o);
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
	public void addModel(ODocument o) {
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		reload(db);
		db.close();
//		try {
//			treeTable.setRowSelectionInterval(indexRowSelected+1, indexRowSelected+1);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		selected();
//		PaketTreeTableNodeModel n=new PaketTreeTableNodeModel(o);
		//root.add(n);
//		if (o.field("@class").equals(Pelanggan.TABLE)) {
//			treeModel.insertNodeInto(createNode(o), root, root.getChildCount()); 	
//			treeTable.setRowSelectionInterval(treeTable.getRowCount()-1, treeTable.getRowCount()-1);
//		}else{
//			treeModel.insertNodeInto(createNode(o), nodeSelected, nodeSelected.getChildCount()); 
//			if (indexRowSelected!=-1) {
//				if ((indexRowSelected+1)<treeTable.getRowCount()) {
//					treeTable.setRowSelectionInterval(indexRowSelected+1, indexRowSelected+1);
//				}else{
//					treeTable.setRowSelectionInterval(treeTable.getRowCount()-1, treeTable.getRowCount()-1);
//				}
//			}else{
//				treeTable.setRowSelectionInterval(treeTable.getRowCount()-1, treeTable.getRowCount()-1);
//			}
//			
//			
//		}
		
	}

}
