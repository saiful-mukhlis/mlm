package com.mlm.tree;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.adapter.TableInterfaces;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.table.TableColumnExt;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import com.basic.icon.IconBase;
import com.basic.lang.LActions;
import com.basic.lang.LDialog;
import com.basic.lang.LPaket;
import com.basic.lang.LPelanggan;
import com.basic.lang.LWindow;
import com.global.App;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.comp.model.PaketTreeTableNodeModel;
import com.mlm.comp.model.PelangganTreeTableNodeModel;
import com.mlm.db.FPaket;
import com.mlm.db.FPelanggan;
import com.mlm.db.FPp;
import com.mlm.view.add.PelangganDAddDownline;
import com.mlm.view.add.PelangganDAddPaket;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class PaketTree extends TreeTableDefault{
	public void initTreeModel() {
		colNames.add(App.getT("Paket"));
		setColShowAll();
		treeModel = new DefaultTreeTableModel(root, colNames);
	}
	public void load(ODatabaseDocumentTx db) {
		super.load(db);
		root=new DefaultMutableTreeTableNode(PaketM.TITLE_MENU);
		for (ODocument o : models) {
			PaketTreeTableNodeModel p=new PaketTreeTableNodeModel(o);
			root.add(p);
		}
		if (treeModel!=null) {
			treeModel.setRoot(root);
		}
	}
	
	@Override
	public void reload(ODatabaseDocumentTx db) {
		super.reload(db);
		root=new DefaultMutableTreeTableNode(PaketM.TITLE_MENU);
		for (ODocument o : models) {
			PaketTreeTableNodeModel p=new PaketTreeTableNodeModel(o);
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
			fieldSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String tmp=fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						actionSearchOneField(colSearch, tmp);
					}else{
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
			itemSearch = new SplitButton(LWindow.KET_SEARCH + LPaket.NAMA);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LPaket.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPaket.CODE);
					colSearch = FPaket.CODE;
				}
			});
			menuItemSearch.add(item);
			
			item = new JMenuItem(LWindow.KET_SEARCH + LPaket.NAMA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPaket.NAMA);
					colSearch = FPaket.NAMA;
				}
			});
			menuItemSearch.add(item);
			
			item = new JMenuItem(LWindow.KET_SEARCH + LPaket.KET);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPaket.KET);
					colSearch = FPaket.KET;
				}
			});
			menuItemSearch.add(item);
			
			item = new JMenuItem(LWindow.KET_SEARCH + LPaket.HARGA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LPaket.HARGA);
					colSearch = FPaket.HARGA;
				}
			});
			menuItemSearch.add(item);
			
			
			itemSearch.setDropDownMenu(menuItemSearch);
			itemSearch.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String tmp=fieldSearch.getText();
					if (!tmp.trim().equalsIgnoreCase("")) {
						
						actionSearchOneField(colSearch, tmp);
					}else{
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

	public void initDao(){
		dao=App.getPaketDao();
	}

	public void actionSearchOneField(String col, String value){
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		loadJumlahDataSearchLike(db, col, value);
		loadDataModelSearchLike(db, col, value);
		db.close();
		
		root=new DefaultMutableTreeTableNode(PaketM.TITLE_MENU);
		for (ODocument o : models) {
			PaketTreeTableNodeModel p=new PaketTreeTableNodeModel(o);
			root.add(p);
		}
		if (treeModel!=null) {
			treeModel.setRoot(root);
		}
		
		selected();
	}
	
	
	
	protected boolean showSimple = false;
	protected JMenuItem reload;
	protected JMenuItem add;
	protected JMenuItem edit;
	protected JMenuItem del;
	protected JMenuItem showControl;
	protected JMenuItem expandAll;
	protected JMenuItem collapseAll;
	protected JMenuItem showColumn;
	
	
	protected ListInterfaces table=this;
	
	protected boolean defaultIsExspan=false;

	protected void createPopup() {
		
		popup = new JPopupMenu();
		showControl = new JMenuItem(LActions.SHOW_CONTROL);
		expandAll = new JMenuItem(LActions.EXPAND_ALL);
		collapseAll = new JMenuItem(LActions.COLLAPSE_ALL);
		showColumn = new JMenuItem(LActions.SHOW_COLUMN_SIMPLE);
		
		reload = new JMenuItem(LWindow.RELOAD);
		add = new JMenuItem(LWindow.ADD);
		edit = new JMenuItem(LWindow.EDIT);
		del = new JMenuItem(LWindow.DELETE);
		

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
						
//						PelangganTreeTableNodeModel x=(PelangganTreeTableNodeModel)nodeSelected;
//						ODocument tmp=x.getDocumentToString().getO();
						
						edit.setEnabled(true);
						del.setEnabled(true);
					} catch (Exception e2) {
						edit.setEnabled(false);
						del.setEnabled(false);
					}

					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void setColShowAll(){
		colNames.add(App.getT("Kode"));
		colNames.add(App.getT("Harga"));
		colNames.add(App.getT("Downline"));
		colNames.add(App.getT("Waktu"));
		colNames.add(App.getT("Status"));
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
	
	
	
	public void initTreeTable() {
		
		treeTable=new JXTreeTable(treeModel);
		createPopup();
		setJXTreeTable(treeTable);
		
		if (master!=null) {
			treeTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
			treeTable.getTableHeader().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (master.isPerspectiveDefault()) {
							if (typeEfectWidget==WIDGET_1) {
								master.perspective1();
							}else if(typeEfectWidget==WIDGET_2){
								master.perspective2();
							}else if (typeEfectWidget==WIDGET_3) {
								master.perspective3();
							}else if (typeEfectWidget==WIDGET_4) {
								master.perspective4();
							}
						}else{
							master.perspectiveDefault();
						}
					}
				}
				public void mouseReleased(MouseEvent e) {}
			});
		}
		
	}
	
	
	@Override
	public void aksiDelete(ODatabaseDocumentTx db) {
		Object tmp=nodeSelected.getUserObject();
		if (tmp!=null) {
			if (JOptionPane.showConfirmDialog(null,
					LDialog.INGIN_MENGHAPUS,
					 LDialog.TITLE_MENGHAPUS,
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
				if (tmp instanceof ODocument) {
					if (((ODocument) tmp).field("@class").equals(FPaket.TABLE)) {
						App.getPaketDao().delete(db, (ODocument) tmp);
					}
				}
			}
			
			
			reload(db);
		}
		
	}

}
