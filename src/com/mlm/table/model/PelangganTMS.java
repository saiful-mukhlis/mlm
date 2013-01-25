package com.mlm.table.model;

import java.util.List;

import org.basic.comp.abst.TableModelDefault;
import com.basic.lang.LPelanggan;
import com.global.App;
import com.mlm.dao.impl.PelangganDao;
import com.mlm.db.FPelanggan;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganTMS extends TableModelDefault {

	public PelangganTMS(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int CODE = 0;
	protected final int NAMA = 1;

	public void initNamaKolom(ODatabaseDocumentTx db) {
		namaKolom = new String[2];
		namaKolom[CODE] = LPelanggan.CODE;
		namaKolom[NAMA] = LPelanggan.NAMA_TOKO;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m = model.get(rowIndex);
		PelangganDao d = App.getPelangganDao();
		if (columnIndex == CODE) {
			return d.getCode(m);
		}

		else if (columnIndex == NAMA) {
			return d.getNamaToko(m);
		}

		else {
			return null;
		}
	}

	@Override
	public void initDao() {
		dao = App.getPelangganDao();
	}

	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (textSearch == null || textSearch.equalsIgnoreCase("")) {
			model = (List<ODocument>) getDao().getAll(db, 0, 50);
		} else {
			model = (List<ODocument>) (App.getPelangganDao()).getAllByColumnLike(
					db, FPelanggan.NAMA_TOKO + ".toLowerCase()", textSearch + "%",
					FPelanggan.CODE + ".toLowerCase()", textSearch, 0, 50);
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
