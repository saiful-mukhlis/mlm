package com.basic.comp.impl.table.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.basic.comp.abst.FactorySearch;
import org.basic.comp.abst.TableModelAbs;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import com.basic.db.FGrp;
import com.basic.db.FJenisPekerjaan;
import com.basic.entity.Grp;
import com.basic.entity.Usr;
import com.basic.lang.LGrp;
import com.basic.lang.LWindow;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpTableModel extends TableModelAbs implements FactorySearch {
	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAME = 2;
	protected final int NOTE = 3;

	public GrpTableModel() {
		super();
	}

	@Override
	public void init() {
		super.init();

		dao = App.getGrpDao();

		nameColumn = new String[4];
		nameColumn[NO] = LGrp.NO;
		nameColumn[CODE] = LGrp.CODE;
		nameColumn[NAME] = LGrp.NAME;
		nameColumn[NOTE] = LGrp.NOTE;
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object m = models.get(rowIndex);
		if (m instanceof Grp) {
			Grp u = (Grp) m;
			if (columnIndex == NO) {
				return getNo(rowIndex);
			} else if (columnIndex == CODE) {
				return u.getCode();
			}else if (columnIndex == NAME) {
				return u.getName();
			}else if (columnIndex == NOTE) {
				return u.getNote();
			}
		}

		return null;
	}

	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch = FGrp.NAME;
	protected JPopupMenu menuItemSearch;

	public TextFieldSearch getFieldSearch() {
		if (fieldSearch == null) {
			fieldSearch = new TextFieldSearch();
			fieldSearch = new TextFieldSearch();
			fieldSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					actionSearch();
				}
			});
		}
		return fieldSearch;
	}

	public SplitButton getItemSearch() {
		if (itemSearch == null) {
			itemSearch = new SplitButton(LWindow.KET_SEARCH + LGrp.NAME);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LGrp.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LGrp.CODE);
					colSearch = FGrp.CODE;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LGrp.NAME);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LGrp.NAME);
					colSearch = FGrp.NAME;
				}
			});

		}
		return itemSearch;
	}

	public void actionSearch() {

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

	public void actionSearchOneField(String col, String value) {
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		loadSearchLike(db, col, value);
		db.close();
		fireTableDataChanged();
	}

	public void actionSearchOneField(String col, String col2, String value) {
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		loadCollectionSearchLike(db, col, col2, value);
		db.close();
		fireTableDataChanged();
	}

	public void convertDocsToObjects() {
		models = new ArrayList<>();
		for (ODocument o : modelDocs) {
			Grp u = new Grp(o);
			models.add(u);
		}
	}

}
