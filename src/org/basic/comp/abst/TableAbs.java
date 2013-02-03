package org.basic.comp.abst;

import com.basic.icon.IconBase;
import com.basic.lang.LActions;
import com.basic.lang.LDialog;
import com.basic.lang.LWindow;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.base.ScrollPane;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TableAbs implements ListWidget{
	protected JPanel panel;
	protected JXTable table;
	protected Master master;
	protected TableModel tableModel;
	protected List<WidgetChangeObj> widgetChangeObjs=new ArrayList<>();

	protected JPopupMenu popup;
	protected boolean showSimple = true;
	protected JMenuItem reload;
	protected JMenuItem add;
	protected JMenuItem edit;
	protected JMenuItem del;
	protected JMenuItem showControl;
	protected JMenuItem showColumn;

	protected boolean isDoubleClick = true;
	protected int indexRowSelected = -1;

	public void init() {
		panel = new JPanel(new BorderLayout());
		popup = new JPopupMenu();
		reload = new JMenuItem();
		add = new JMenuItem();
		edit = new JMenuItem();
		del = new JMenuItem();
		showControl = new JMenuItem();
		showColumn = new JMenuItem();
	}

	public void build(ODatabaseDocumentTx db) {
		tableModel.load(db);

		table.setHorizontalScrollEnabled(true);
		table.setColumnControlVisible(true);
		table.setHighlighters(HighlighterFactory.createSimpleStriping());

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(App.selected);
		table.setShowHorizontalLines(false);
		table.setBorder(null);
		table.setColumnControlVisible(false);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clickedOnTable(e);
				if (e.getClickCount() == 2) {
					if (isDoubleClick) {
						doubleClickedOnTable(e);
						isDoubleClick = false;
					} else {
						isDoubleClick = true;
					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				mouseReleasedOnTable(e);
			}

		});
		table.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				keyPressedOnTable(e);
			}
		});
		
		
		popup = new JPopupMenu();
		showControl = new JMenuItem(LActions.SHOW_CONTROL);
		showColumn = new JMenuItem(LActions.SHOW_COLUMN_ALL);
		
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
				if (master != null) {
					master.actionAdd();
				}
			}
		});
		
		
		

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					master.actionEdit();
				}
			}
		});

		reload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					master.actionReload();
				}

			}
		});

		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (master != null) {
					master.actionDel();
				}

			}
		});

		

		showControl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.isColumnControlVisible()) {
					table.setColumnControlVisible(false);

					showControl.setText("Show Control Column");
				} else {
					table.setColumnControlVisible(true);
					showControl.setText("Hidden Control Column");
				}
			}
		});

		showColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (showSimple) {
					setShowAll();
					showSimple = false;
					showColumn.setText("Show Simple");
				} else {
					setSimple();
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
		popup.add(showColumn);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					try {
						int row = table.rowAtPoint(e.getPoint());
						table.clearSelection();
						table.addRowSelectionInterval(row, row);
						selected();
						
						
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
		
		
		
		
		ScrollPane ss=new ScrollPane(table);
		panel.add(ss, BorderLayout.CENTER);
		if (tableModel.getPaging()!=null) {
			panel.add(tableModel.getPaging().getPanel(), BorderLayout.SOUTH);
		}
		
		setSimple();
	}

	public void setSimple() {
	}
	private void setShowAll() {
		
	}

	protected void keyPressedOnTable(KeyEvent e) {
		selected();
	}

	protected void mouseReleasedOnTable(MouseEvent e) {
		selected();
	}

	protected void doubleClickedOnTable(MouseEvent e) {
		selected();
	}

	protected void clickedOnTable(MouseEvent e) {
		selected();
	}

	public void selected() {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				indexRowSelected = i;
				selectedValid(i);
			} else {
				indexRowSelected = -1;
				selectedNotValid();
			}

		}
	}

	public void aksiDelete(ODatabaseDocumentTx db) {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				if (JOptionPane.showConfirmDialog(null,
						LDialog.INGIN_MENGHAPUS, LDialog.TITLE_MENGHAPUS,
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
					int itmp = table.convertRowIndexToModel(i);
					// ODocument tmp=(ODocument) tableModel.getModels().get(itmp
					// );
					try {
						// tmp.delete();
						tableModel.delModel(db, itmp);
						selected();
					} catch (Exception e) {
						App.printErr(e);
					}
				}
			}
		}

	}

	public void selectedNotValid() {
		if (widgetChangeObjs.size() != 0) {
			for (WidgetChangeObj view : widgetChangeObjs) {
				if (view != null) {
					view.changeObj(null);
				}
			}
		}
	}

	public void selectedValid(int i) {
		if (widgetChangeObjs.size() != 0) {
			for (WidgetChangeObj view : widgetChangeObjs) {
				if (view != null) {
					view.changeObj(tableModel.getModels().get(
							table.convertRowIndexToModel(i)));

				}
			}
		}
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JXTable getTable() {
		return table;
	}

	public void setTable(JXTable table) {
		this.table = table;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public List<WidgetChangeObj> getWidgetChangeObjs() {
		return widgetChangeObjs;
	}

	public void setWidgetChangeObjs(List<WidgetChangeObj> widgetChangeObjs) {
		this.widgetChangeObjs = widgetChangeObjs;
	}

	public JPopupMenu getPopup() {
		return popup;
	}

	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
	}

	public boolean isShowSimple() {
		return showSimple;
	}

	public void setShowSimple(boolean showSimple) {
		this.showSimple = showSimple;
	}

	public JMenuItem getReload() {
		return reload;
	}

	public void setReload(JMenuItem reload) {
		this.reload = reload;
	}

	public JMenuItem getAdd() {
		return add;
	}

	public void setAdd(JMenuItem add) {
		this.add = add;
	}

	public JMenuItem getEdit() {
		return edit;
	}

	public void setEdit(JMenuItem edit) {
		this.edit = edit;
	}

	public JMenuItem getDel() {
		return del;
	}

	public void setDel(JMenuItem del) {
		this.del = del;
	}

	public JMenuItem getShowControl() {
		return showControl;
	}

	public void setShowControl(JMenuItem showControl) {
		this.showControl = showControl;
	}

	public JMenuItem getShowColumn() {
		return showColumn;
	}

	public void setShowColumn(JMenuItem showColumn) {
		this.showColumn = showColumn;
	}

	public boolean isDoubleClick() {
		return isDoubleClick;
	}

	public void setDoubleClick(boolean isDoubleClick) {
		this.isDoubleClick = isDoubleClick;
	}

	public int getIndexRowSelected() {
		return indexRowSelected;
	}

	public void setIndexRowSelected(int indexRowSelected) {
		this.indexRowSelected = indexRowSelected;
	}

	@Override
	public void addWidgetChange(WidgetChangeObj widgetChangeObj) {
		widgetChangeObjs.add(widgetChangeObj);
	}



}
