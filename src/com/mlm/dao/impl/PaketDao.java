package com.mlm.dao.impl;

import com.basic.lang.LPaket;
import com.global.App;
import com.mlm.db.FPaket;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;
import org.basic.dao.abst.DaoAbstract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;



public class PaketDao extends DaoAbstract {

	public PaketDao() {
		super(FPaket.TABLE);
	}

	
	public String getCode(ODocument o){
		String tmp=o.field(FPaket.CODE);
		if (tmp==null) {
			tmp="";
		}
		return tmp;
	}
	
	public String getNama(ODocument o){
		String tmp=o.field(FPaket.NAMA);
		if (tmp==null) {
			tmp="";
		}
		return tmp;
	}
	
	public boolean vCode(ODatabaseDocumentTx db, TextField code){
		if (!(code.getText().trim().equals("") || code.getText().trim().equalsIgnoreCase("Auto"))) {
			long tmp=getCountByColumn(db, FPaket.CODE, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LPaket.CODE);
				return false;
			}
		}
		return true;
	}
	
	public boolean vCode(ODatabaseDocumentTx db, TextField code, ODocument model){
		if (!code.getText().equalsIgnoreCase((String) model.field(FPaket.CODE))) {
			long tmp=getCountByColumn(db, FPaket.CODE, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LPaket.CODE);
				return false;
			}
			if (code.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LPaket.CODE);
				return false;
			}
		}
		return true;
	}
	
	public boolean vNama(ODatabaseDocumentTx db, TextField nama){
		if (!validate(db, nama, LPaket.NAMA)) {
			return false;
		}
			long tmp=getCountByColumn(db, LPaket.NAMA, nama.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LPaket.NAMA);
				return false;
			}
		return true;
	}
	
	public boolean vNama(ODatabaseDocumentTx db, TextField nama, ODocument model){
		if (!validate(db, nama, LPaket.NAMA)) {
			return false;
		}
		if (!nama.getText().equalsIgnoreCase((String) model.field(LPaket.NAMA))) {
			long tmp=App.getUsrDao().getCountByColumn(db, LPaket.NAMA, nama.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LPaket.NAMA);
				return false;
			}
			if (nama.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LPaket.NAMA);
				return false;
			}
		}
		return true;
	}

	public PaketDao setCode(ODocument o, String code){
		o.field(FPaket.CODE, code);
		return this;
	}
	
	public PaketDao setCode(ODocument o, TextField code){
		setCode(o, code.getText());
		return this;
	}
	
	public PaketDao setNama(ODocument o, String nama){
		o.field(FPaket.NAMA, nama);
		return this;
	}
	
	public PaketDao setNama(ODocument o, TextField nama){
		setNama(o, nama.getText());
		return this;
	}
	
	
	
	
	
	
	public PaketDao setKet(ODocument o, String ket) {
		o.field(FPaket.KET, ket);
		return this;
	}

	public PaketDao setKet(ODocument o, TextArea ket) {
		setKet(o, ket.getText());
		return this;
	}

	public String getKet(ODocument o) {
		String tmp = o.field(FPaket.KET);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public boolean vKet(ODatabaseDocumentTx db, TextField ket) {
		if (!validate(db, ket, LPaket.KET)) {
			return false;
		}
		return true;
	}

	public boolean vKet(ODatabaseDocumentTx db, TextField ket, ODocument model) {
		if (!validate(db, ket, LPaket.KET)) {
			return false;
		}
		return true;
	}

	public PaketDao setHarga(ODocument o, BigDecimal harga) {
		o.field(FPaket.HARGA, harga, OType.DECIMAL);
		return this;
	}
//
//	public PaketDao setHarga(ODocument o, BigDecimal harga) {
//		o.field(Paket.HARGA, harga, OType.DECIMAL);
//		return this;
//	}
//
	public PaketDao setHarga(ODocument o, TextFieldAmount harga) {
		
//		if (!harga.getText().trim().equalsIgnoreCase("")) {
//			//hilangkan koma
//			String tmp0=CharMatcher.is(',').removeFrom(harga.getText());
//			// hanya boloeh angka dan satu titik
//			if (tmp0.matches("[0-9]*\\.?[0-9]*")) {
//				BigDecimal tmp=new BigDecimal(tmp0);
//				setHarga(o, tmp);
//			}
//		}
		setHarga(o, TextFieldAmount.getValue(harga));
		return this;
	}

	public BigDecimal getHarga(ODocument o) {
		BigDecimal tmp = o.field(FPaket.HARGA);
		return tmp;
	}
	
	public String hargaToString(ODocument o) {
		BigDecimal tmp = getHarga(o);
		if (tmp==null) {
			return "";
		}
		BigDecimal tmp2=tmp.setScale(2, RoundingMode.HALF_UP); 
		return App.paymentFormat2.format(tmp2.doubleValue());
	}


	public boolean vHarga(ODatabaseDocumentTx db, TextField harga) {
		if (!validate(db, harga, LPaket.HARGA)) {
			return false;
		}
		try {
			Double tmp = Double.parseDouble(harga.getText());
		} catch (Exception e) {
			App.showErrorNotValid(db, LPaket.HARGA);
			harga.setText("0");
			harga.requestFocus();
			return false;
		}
		return true;
	}

	public boolean vHarga(ODatabaseDocumentTx db, TextField harga, ODocument model) {
		return vHarga(db, harga);
	}
	

	
	
	
	
	
	public PaketDao setDownline(ODocument o, int maxDownline) {
		o.field(FPaket.DOWNLINE, maxDownline, OType.INTEGER);
		return this;
	}

	public PaketDao setDownline(ODocument o, TextField maxDownline) {
		int tmp = 0;
		try {
			tmp = Integer.parseInt(maxDownline.getText());
		} catch (Exception e) {
		}
		setDownline(o, tmp);
		return this;
	}

	public int getDownline(ODocument o) {
		Integer tmp = o.field(FPaket.DOWNLINE);
		if (tmp == null) {
			tmp = 0;
		}
		return tmp;
	}
	
	public String downlineToString(ODocument o){
		int tmp =getDownline(o);
		if (tmp==0) {
			return "-";
		}
		return tmp+"";
	}

	public boolean vDownline(ODatabaseDocumentTx db, TextField maxDownline) {
		try {
			Integer tmp = Integer.parseInt(maxDownline.getText());
			if (tmp<=0) {
				App.showErrorNilaiHarusDiAtasNol(db, LPaket.DOWNLINE);
				return false;
			}
		} catch (Exception e) {
			App.showErrorNotValid(db, LPaket.DOWNLINE);
			maxDownline.setText("0");
			maxDownline.requestFocus();
			return false;
		}
		return true;
	}

	public boolean vDownline(ODatabaseDocumentTx db, TextField maxDownline, ODocument model) {
		return vDownline(db, maxDownline);
	}
	
	
	
	public PaketDao setWaktu(ODocument o, int waktu) {
		o.field(FPaket.WAKTU, waktu, OType.INTEGER);
		return this;
	}

	public PaketDao setWaktu(ODocument o, TextField waktu) {
		int tmp = 0;
		try {
			tmp = Integer.parseInt(waktu.getText());
		} catch (Exception e) {
		}
		setWaktu(o, tmp);
		return this;
	}

	public int getWaktu(ODocument o) {
		Integer tmp = o.field(FPaket.WAKTU);
		if (tmp == null) {
			tmp = 0;
		}
		return tmp;
	}
	
	public String waktuToString(ODocument o){
		int tmp =getWaktu(o);
		if (tmp==0) {
			return "-";
		}
		return tmp+"";
	}

	public boolean vWaktu(ODatabaseDocumentTx db, TextField maxWaktu) {
		try {
			Integer tmp = Integer.parseInt(maxWaktu.getText());
		} catch (Exception e) {
			App.showErrorNotValid(db, LPaket.WAKTU);
			maxWaktu.setText("0");
			maxWaktu.requestFocus();
			return false;
		}
		return true;
	}

	public boolean vWaktu(ODatabaseDocumentTx db, TextField maxWaktu, ODocument model) {
		return vWaktu(db, maxWaktu);
	}
	
	
	
	public PaketDao setStatus(ODocument o, int status) {
		o.field(FPaket.STATUS, status, OType.INTEGER);
		return this;
	}
	

	public PaketDao setStatus(ODocument o, TextField status) {
		Integer tmp=0;
		try {
			tmp=Integer.parseInt(status.getText());
		} catch (Exception e) {
		}
		setStatus(o, tmp);
		return this;
	}

	public String statusToString(ODocument o) {
		Integer tmp = o.field(FPaket.STATUS);
		if (tmp == null) {
			return "";
		}
		String [] tmp2=getStatusData();
		return tmp2[tmp];
	}
	
	public int getStatus(ODocument o) {
		Integer tmp = o.field(FPaket.STATUS);
		if (tmp == null) {
			return 0;
		}
		return tmp;
	}
	
	public String [] getStatusData(){
		String a1=App.getT("Pilih Status Paket");
		String ta=App.getT("Tidak Aktif");
		String a=App.getT("Aktif");
		String [] stringStatus={a1,ta,a};
		return stringStatus;
	}
	

	public PaketDao setTotalPelanggan(ODocument o, int totalPelanggan) {
		o.field(FPaket.TOTAL_PELANGGAN, totalPelanggan, OType.INTEGER);
		return this;
	}

	public PaketDao setTotalPelanggan(ODocument o, TextField totalPelanggan) {
		int tmp=0;
		try {
			tmp=Integer.parseInt(totalPelanggan.getText());
		} catch (Exception e) {
		}
		setTotalPelanggan(o, tmp);
		return this;
	}

	public int getTotalPelanggan(ODocument o) {
		Integer tmp = o.field(FPaket.TOTAL_PELANGGAN);
		if (tmp == null) {
			tmp = 0;
		}
		return tmp;
	}
	
	public String totalPelangganToString(ODocument o){
		int tmp =getTotalPelanggan(o);
		if (tmp==0) {
			return "-";
		}
		return tmp+"";
	}

	public boolean vTotalPelanggan(ODatabaseDocumentTx db, TextField totalPelanggan) {
		if (!validate(db, totalPelanggan, LPaket.TOTAL_PELANGGAN)) {
			return false;
		}
		try {
			Integer tmp=Integer.parseInt(totalPelanggan.getText());
		} catch (Exception e) {
			App.showErrorNotValid(db, LPaket.TOTAL_PELANGGAN);
			totalPelanggan.setText("0");
			totalPelanggan.requestFocus();
			return false;
		}
		return true;
	}

	public boolean vTotalPelanggan(ODatabaseDocumentTx db, TextField totalPelanggan, ODocument model) {
		return vTotalPelanggan(db, totalPelanggan);
	}
	


	
	public PaketDao setDownlines(ODocument o, List<ODocument> downlines){
		o.field(FPaket.DOWNLINES, downlines, OType.LINKLIST);
		return this;
	}
	public List<ODocument> getDownlines(ODatabaseDocumentTx db,ODocument o){
		List<ODocument> tmp=o.field(FPaket.DOWNLINES);
		if (tmp==null) {
			tmp=new ArrayList<>();
		}
		return tmp;
	}
	
	

	
	
	

	
	
	@Override
	public String getNameFielsToString() {
		return FPaket.NAMA;
	}


	@Override
	public boolean beforeDelete(ODatabaseDocumentTx db, ODocument o) {
		// hapus semua pp downline
		List<ODocument> pps=getDownlines(db, o);
		for (ODocument opp : pps) {
			App.getPpDao().beforeDelete(db, opp);
			opp.delete();
		}
		return super.beforeDelete(db, o);
	}
	
	
}
