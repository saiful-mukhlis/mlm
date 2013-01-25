package com.mlm.table.model;

import java.util.List;

import org.basic.comp.abst.TableModelDefault;
import com.global.App;
import com.mlm.dao.impl.PpDao;
import com.mlm.db.FPp;
import com.mlm.lang.LPp;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PpTMS extends TableModelDefault {

	public PpTMS(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int CODE = 0;
	protected final int NAMA = 1;
	protected final int PAKET = 2;

	public void initNamaKolom(ODatabaseDocumentTx db) {
		namaKolom = new String[3];
		namaKolom[CODE] = LPp.CODE;
		namaKolom[NAMA] = LPp.NAMA_PELANGGAN;
		namaKolom[PAKET] = LPp.NAMA_PAKET;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m = model.get(rowIndex);
		PpDao d = App.getPpDao();
		if (columnIndex == CODE) {
			return d.getCode(m);
		}

		else if (columnIndex == NAMA) {
			return d.pelangganToString(null, m);
		}
		else if (columnIndex == PAKET) {
			return d.paketToString(null, m);
		}

		else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao = App.getPpDao();
	}

	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (textSearch == null || textSearch.equalsIgnoreCase("")) {
			model = (List<ODocument>) getDao().getAll(db, 0, 50);
		} else {
			model = (List<ODocument>) (App.getPpDao()).getAllByColumnLike(
					db, FPp.PELANGGAN + ".toLowerCase()", textSearch + "%",
					FPp.CODE + ".toLowerCase()", textSearch, 0, 50);
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
