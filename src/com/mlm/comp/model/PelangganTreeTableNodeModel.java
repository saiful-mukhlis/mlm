package com.mlm.comp.model;

import java.util.List;

import org.basic.comp.model.DefaultTreeNodeModel;

import com.global.App;
import com.mlm.dao.impl.PelangganDao;
import com.mlm.db.FPelanggan;
import com.mlm.db.FPp;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganTreeTableNodeModel extends DefaultTreeNodeModel {

	protected final int CODE = 1;
	protected final int NAMA_TOKO = 2;
	protected final int NAMA_PEMILIK = 3;
	protected final int JENIS_IDENTITAS = 4;
	protected final int NO_IDENTITAS = 5;
	protected final int ALAMAT = 6;
	protected final int KOTA = 7;
	protected final int NO_TELP = 8;
	protected final int NO_FAX = 9;
	protected final int NO_HP1 = 10;
	protected final int NO_HP2 = 11;
	protected final int PIN_BB1 = 12;
	protected final int PIN_BB2 = 13;
	protected final int STATUS = 14;

	public PelangganTreeTableNodeModel(ODocument userObject) {
		super(userObject);
		documentToString = new PelangganToStringNode(userObject, this);
		columnCount = 14;
		if (userObject.field("@class").equals(FPelanggan.TABLE)) {
			List<ODocument> tmp=App.getPelangganDao().getPakets(null, userObject);
			for (ODocument o : tmp) {
				add(new PelangganTreeTableNodeModel(o, true));
			}
		}else if (userObject.field("@class").equals(FPp.TABLE)) {
			List<ODocument> tmp=App.getPpDao().getDownlines(null, userObject);
			for (ODocument o : tmp) {
				add(new PelangganTreeTableNodeModel(o));
			}
		}
	}
	public PelangganTreeTableNodeModel(ODocument userObject, boolean paket) {
		super(userObject);
		documentToString = new PelangganToStringNode(userObject, this, paket);
		columnCount = 14;
		if (userObject.field("@class").equals(FPelanggan.TABLE)) {
			List<ODocument> tmp=App.getPelangganDao().getPakets(null, userObject);
			for (ODocument o : tmp) {
				add(new PelangganTreeTableNodeModel(o, true));
			}
		}else if (userObject.field("@class").equals(FPp.TABLE)) {
			List<ODocument> tmp=App.getPpDao().getDownlines(null, userObject);
			for (ODocument o : tmp) {
				add(new PelangganTreeTableNodeModel(o));
			}
		}
	}
	

	@Override
	public Object getValueAt(int column) {
		ODocument o = documentToString.getO();
		if (o.field("@class").equals(FPelanggan.TABLE)) {
			PelangganDao d = App.getPelangganDao();
			if (column == 0) {
				return documentToString;
			} 
			else if (column == CODE) {
				return d.getCode(o);
			} 
			else if (column == NAMA_TOKO) {
				return d.getNamaToko(o);
			} else if (column == NAMA_PEMILIK) {
				return d.getNamaPemilik(o);
			} else if (column == JENIS_IDENTITAS) {
				return d.getJenisIdentitas(o);
			} else if (column == NO_IDENTITAS) {
				return d.getNoIdentitas(o);
			} else if (column == ALAMAT) {
				return d.getAlamat(o);
			} else if (column == KOTA) {
				return d.getKota(o);
			} else if (column == NO_TELP) {
				return d.getNoTelp(o);
			} else if (column == NO_FAX) {
				return d.getNoFax(o);
			} else if (column == NO_HP1) {
				return d.getNoHp1(o);
			} else if (column == NO_HP2) {
				return d.getNoHp2(o);
			} else if (column == PIN_BB1) {
				return d.getPinBb1(o);
			} else if (column == PIN_BB2) {
				return d.getPinBb2(o);
			} else if (column == STATUS) {
				return d.statusToString(null, o);
			}
		}else if (o.field("@class").equals(FPp.TABLE)) {
			if (column == 0) {
				return documentToString;
			}else{
				return "";
			}
		}
		
		return "";
	}

}
