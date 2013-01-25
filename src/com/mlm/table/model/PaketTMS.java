package com.mlm.table.model;

import java.util.List;

import org.basic.comp.abst.TableModelDefault;
import com.basic.lang.LPaket;
import com.global.App;
import com.mlm.dao.impl.PaketDao;
import com.mlm.db.FPaket;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PaketTMS extends TableModelDefault {

	public PaketTMS(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int CODE = 0;
	protected final int NAMA = 1;

	public void initNamaKolom(ODatabaseDocumentTx db) {
		namaKolom = new String[2];
		namaKolom[CODE] = LPaket.CODE;
		namaKolom[NAMA] = LPaket.NAMA;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m = model.get(rowIndex);
		PaketDao d = App.getPaketDao();
		if (columnIndex == CODE) {
			return d.getCode(m);
		}

		else if (columnIndex == NAMA) {
			return d.getNama(m);
		}

		else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao = App.getPaketDao();
	}

	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (textSearch == null || textSearch.equalsIgnoreCase("")) {
			model = (List<ODocument>) getDao().getAll(db, 0, 50);
		} else {
			model = (List<ODocument>) (App.getPaketDao()).getAllByColumnLike(
					db, FPaket.NAMA + ".toLowerCase()", textSearch + "%",
					FPaket.CODE + ".toLowerCase()", textSearch, 0, 50);
			if (model.size() == 0) {
				model = (List<ODocument>) getDao().getAll(db, 0, 50);
			}
		}
	}

	public String getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(String textSearch) {
		this.textSearch = textSearch;
	}

	private String textSearch;

}
