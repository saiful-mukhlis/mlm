package com.mlm.tree;

import com.basic.lang.LPaket;
import com.basic.lang.LWindow;
import com.global.App;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.db.FPaket;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.adapter.ListInterfaces;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;
import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.PanelBottomInterface;
import org.basic.comp.listener.WidgetInterface;
import org.basic.comp.model.ODocumentToString;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;



public class PaketTree2 implements ListInterfaces{
	
	protected DynamicTree tree;
	protected List<ODocument> models;
	protected List<ODocumentToString> modelStrings;
	protected List<WidgetInterface> widgets = new ArrayList<WidgetInterface>();

	@Override
	public void build(ODatabaseDocumentTx db) {
		tree=new DynamicTree(PaketM.TITLE_MENU);
		setAksiListenerTree();
		load();
	}
	private boolean isDoubleClick = true;
	public void setAksiListenerTree() {
		tree.getTree().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clickedOnTree(e);
				if (e.getClickCount() == 2) {
					if (isDoubleClick) {
						doubleClickedOnTree(e);
						isDoubleClick = false;
					} else {
						isDoubleClick = true;
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				mouseReleasedOnTree(e);
			}

		});
		tree.getTree().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				keyPressedOnTree(e);
			}
		});
	}
	
	protected void keyPressedOnTree(KeyEvent e) {
		selected();

	}

	protected void mouseReleasedOnTree(MouseEvent e) {
		 selected();

	}

	protected void doubleClickedOnTree(MouseEvent e) {
		selected();

	}

	protected void clickedOnTree(MouseEvent e) {
		selected();
	}
	
	public void selectedNotValid() {
		if (widgets.size() != 0) {
			for (WidgetInterface view : widgets) {
				if (view!=null) {
					view.load(null);
				}
			}
		}
	}

	public void selected() {
		TreePath p=tree.getTree().getSelectionPath();
		if (p==null) {
			selectedNotValid();
		}else{
			 Object selectedNode =((DefaultMutableTreeNode )p. getLastPathComponent()). getUserObject();
			 if (selectedNode instanceof ODocumentToString) {
				 ODocument o=((ODocumentToString) selectedNode).getO();
				selectedValid(o);
			}else{
				selectedNotValid();
			}

		}
	}
	
	public void selectedValid(ODocument o) {
		if (widgets.size() != 0) {
			for (WidgetInterface view : widgets) {
				if (view!=null) {
					view.load(o);
				}
			}
		}
	}

	@Override
	public void load(ODocument model) {}
	
	public void load(){
		tree.clear();
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		models=App.getPaketDao().getAll(db);
		modelStrings=new ArrayList<>();
		for (ODocument oDocument : models) {
			ODocumentToString tmp=new ODocumentToString(App.getPaketDao(), oDocument);
			modelStrings.add(tmp);
			tree.addObject(tmp);
		}
	}
	
	
	
	
	
	
	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch = FPaket.NAMA;
	protected JPopupMenu menuItemSearch;

	public TextFieldSearch getFieldSearch() {
		if (fieldSearch == null) {
			fieldSearch = new TextFieldSearch();
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
	
	
	public void actionSearchOneField(String col, String value){
//		ODatabaseDocumentTx db = App.getDbd();
//		ODatabaseRecordThreadLocal.INSTANCE.set(db);
//		loadJumlahDataSearchLike(db, col, value);
//		loadDataModelSearchLike(db, col, value);
//		db.close();
//		fireTableDataChanged();
//		if (getTable() != null) {
//			getTable().selected();
//		}
	}
	
	
	
	

	@Override
	public JPanel getPanel() {
		return tree.getPanel();
	}

	@Override
	public void reload(ODatabaseDocumentTx db) {
		load();
		
	}

	@Override
	public void addModel(ODocument o) {
		if (o!=null && o.field("@class").equals(FPaket.TABLE)) {
			tree.addObject(new ODocumentToString(App.getPaketDao(), o));
		}
		
	}

	@Override
	public void editModel(ODocument model, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWidget(WidgetInterface widget) {
		this.widgets.add(widget);
	}



	@Override
	public void aksiDelete(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ODocument getModelSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndexRowSelected() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSearching() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MasterInterface getMaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMaster(MasterInterface master) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PanelBottomInterface getPanelBottom() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
