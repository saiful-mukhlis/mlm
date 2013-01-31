package com.mlm.comp.model;

import com.basic.lang.LPaket;
import com.global.App;
import com.mlm.dao.impl.PaketDao;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.model.DefaultTreeNodeModel;

import java.util.List;

public class PaketTreeTableNodeModel extends DefaultTreeNodeModel {

	protected final int NAMA = 0;
	protected final int CODE = 1;
//	protected final int KET = 2;
	protected final int HARGA = 2;
	protected final int DOWNLINE = 3;
	protected final int WAKTU = 4;
	protected final int STATUS = 5;
//	protected final int TOTAL_PELANGGAN = 7;

	public PaketTreeTableNodeModel(ODocument userObject) {
		super(userObject);
		documentToString=new PaketToStringNode(userObject, this);
		columnCount = 6;
		
		if (App.getPaketDao().isTrueChildThis(userObject)) {
			List<ODocument> tmp=App.getPaketDao().getDownlines(null, userObject);
			for (ODocument o : tmp) {
				add(new PaketTreeTableNodeModel(o));
			}
		}else if (App.getPpDao().isTrueChildThis(userObject)) {
			List<ODocument> tmp=App.getPpDao().getDownlines(null, userObject);
			for (ODocument o : tmp) {
				add(new PaketTreeTableNodeModel(o));
			}
		}
	}

	@Override
	public Object getValueAt(int column) {
		ODocument o = documentToString.getO();
		if (App.getPaketDao().isTrueChildThis(o)) {
			PaketDao d = App.getPaketDao();
			if (column == NAMA) {
				return documentToString;
			} else if (column == CODE) {
				return d.getCode(o);
			} 
			else if (column == HARGA) {
				return d.hargaToString(o);
			} 
//			else if (column == KET) {
//				return d.getKet(o);
//			} 
			else if (column == DOWNLINE) {
				return d.getDownline(o) +LPaket.KET_PELANGGAN;
			} else if (column == WAKTU) {
				return d.getWaktu(o)+LPaket.KET_BULAN;
			} else if (column == STATUS) {
				return d.statusToString(o);
			} 
//			else if (column == TOTAL_PELANGGAN) {
//				return d.getTotalPelanggan(o);
//			} 
		}else if(App.getPpDao().isTrueChildThis(o)){
			if (column == NAMA) {
				return documentToString;
			}
		}
		return null;
	}

}
