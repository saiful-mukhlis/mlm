package com.basic.comp.impl.table.model;

import com.basic.db.FJenisPekerjaan;
import com.basic.entity.JenisPekerjaan;
import com.basic.lang.LJenisPekerjaan;
import com.basic.lang.LWindow;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.abst.FactorySearch;
import org.basic.comp.abst.TableModelAbs;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JenisPekerjaanTableModel extends TableModelAbs implements
		FactorySearch {
	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;

	public JenisPekerjaanTableModel() {
		super();
	}

	@Override
	public void init() {
		super.init();

		dao = App.getJenisPekerjaanDao();

		nameColumn = new String[3];
		nameColumn[NO] = LJenisPekerjaan.NO;
		nameColumn[CODE] = LJenisPekerjaan.CODE;
		nameColumn[NAMA] = LJenisPekerjaan.NAMA;

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object m = models.get(rowIndex);
		if (m instanceof JenisPekerjaan) {
			JenisPekerjaan j = (JenisPekerjaan) m;
			if (columnIndex == NO) {
				return getNo(rowIndex);
			} else if (columnIndex == CODE) {
				return j.getCode();
			} else if (columnIndex == NAMA) {
				return j.getNama();
			}
		}
		return null;
	}

	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch = FJenisPekerjaan.NAMA;
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
			itemSearch = new SplitButton(LWindow.KET_SEARCH + LJenisPekerjaan.NAMA);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LJenisPekerjaan.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LJenisPekerjaan.CODE);
					colSearch = FJenisPekerjaan.CODE;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LJenisPekerjaan.NAMA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LJenisPekerjaan.NAMA);
					colSearch = FJenisPekerjaan.NAMA;
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
			JenisPekerjaan u = new JenisPekerjaan(o);
			models.add(u);
		}
	}

}
