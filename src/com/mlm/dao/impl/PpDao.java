package com.mlm.dao.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.basic.comp.base.DatePicker;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;
import org.basic.dao.abst.DaoAbstract;

import com.global.App;
import com.google.common.base.CharMatcher;
import com.mlm.db.FHistoryBayar;
import com.mlm.db.FPaket;
import com.mlm.db.FPelanggan;
import com.mlm.db.FPp;
import com.mlm.lang.LPp;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;



public class PpDao extends DaoAbstract {

	public PpDao() {
		super(FPp.TABLE);
	}

	
	public String getCode(ODocument o){
		String tmp=o.field(FPp.CODE);
		if (tmp==null) {
			tmp="";
		}
		return tmp;
	}
	

	
	public boolean vCode(ODatabaseDocumentTx db, TextField code){
		if (!(code.getText().trim().equals("") || code.getText().trim().equalsIgnoreCase("Auto"))) {
			long tmp=getCountByColumn(db, FPp.CODE, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LPp.CODE);
				return false;
			}
		}
		return true;
	}
	
	public boolean vCode(ODatabaseDocumentTx db, TextField code, ODocument model){
		if (!code.getText().equalsIgnoreCase((String) model.field(FPp.CODE))) {
			long tmp=getCountByColumn(db, FPp.CODE, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LPp.CODE);
				return false;
			}
			if (code.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LPp.CODE);
				return false;
			}
		}
		return true;
	}
	

	public PpDao setCode(ODocument o, String code){
		o.field(FPp.CODE, code);
		return this;
	}
	
	public PpDao setCode(ODocument o, TextField code){
		setCode(o, code.getText());
		return this;
	}
	
	public PpDao setPelanggan(ODocument o, ODocument pelanggan){
		o.field(FPp.PELANGGAN, pelanggan, OType.LINK);
		return this;
	}
	
	
	public ODocument getPelanggan(ODatabaseDocumentTx db, ODocument o){
		if (o.isLazyLoad()) {
			return o.field(FPp.PELANGGAN);
		}else{
			ORecordId id=o.field(FPp.PELANGGAN);
			if (id!=null) {
				if (db==null) {
					db = App.getDbd();
					ODatabaseRecordThreadLocal.INSTANCE.set(db);
					ODocument tmp= getOneByRid(db, id.toString());
					db.close();
					return tmp;
				}
				ODocument tmp= getOneByRid(db, id.toString());
				return tmp;
			}
			return null;
		}
	}
	
	public String pelangganToString(ODatabaseDocumentTx db, ODocument o){
		ODocument tmp=getPelanggan(db, o);
		if (tmp==null) {
			return "";
		}
		return App.getPelangganDao().getNamaToko(tmp);
	}
	
	
	public PpDao setPaket(ODocument o, ODocument paket){
		o.field(FPp.PAKET, paket, OType.LINK);
		return this;
	}
	
	
	public ODocument getPaket(ODatabaseDocumentTx db, ODocument o){
		if (o.isLazyLoad()) {
			return o.field(FPp.PAKET);
		}else{
			ORecordId id=o.field(FPp.PAKET);
			if (id!=null) {
				if (db==null) {
					db = App.getDbd();
					ODatabaseRecordThreadLocal.INSTANCE.set(db);
					ODocument tmp= getOneByRid(db, id.toString());
					db.close();
					return tmp;
				}
				ODocument tmp= getOneByRid(db, id.toString());
				return tmp;
			}
			return null;
		}
	}
	
	public String paketToString(ODatabaseDocumentTx db, ODocument o){
		ODocument tmp=getPaket(db, o);
		if (tmp==null) {
			return "";
		}
		return App.getPaketDao().getNama(tmp);
	}
	
	public PpDao setBayar(ODocument o, BigDecimal bayar) {
		o.field(FPp.BAYAR, bayar, OType.DECIMAL);
		return this;
	}

	public PpDao setBayar(ODocument o, TextFieldAmount bayar) {
		
		if (!bayar.getText().trim().equalsIgnoreCase("")) {
			//hilangkan koma
			String tmp0=CharMatcher.is(',').removeFrom(bayar.getText());
			// hanya boloeh angka dan satu titik
			if (tmp0.matches("[0-9]*\\.?[0-9]*")) {
				BigDecimal tmp=new BigDecimal(tmp0);
				setBayar(o, tmp);
			}
		}
		return this;
	}

	public BigDecimal getBayar(ODocument o) {
		BigDecimal tmp = o.field(FPp.BAYAR);
		return tmp;
	}
	
	public String bayarToString(ODocument o) {
		BigDecimal tmp = getBayar(o);
		if (tmp==null) {
			return "";
		}
		BigDecimal tmp2=tmp.setScale(2, RoundingMode.HALF_UP); 
		return App.paymentFormat2.format(tmp2.doubleValue());
	}
	


	public boolean vBayar(ODatabaseDocumentTx db, TextField bayar) {
		if (!validate(db, bayar, LPp.BAYAR)) {
			return false;
		}
		try {
			Double tmp = Double.parseDouble(bayar.getText());
		} catch (Exception e) {
			App.showErrorNotValid(db, LPp.BAYAR);
			bayar.setText("0");
			bayar.requestFocus();
			return false;
		}
		return true;
	}

	public boolean vBayar(ODatabaseDocumentTx db, TextField bayar, ODocument model) {
		return vBayar(db, bayar);
	}
	
	
	public PpDao setUpLine(ODocument o, ODocument upLine){
		o.field(FPp.UP_LINE, upLine, OType.LINK);
		return this;
	}
	
	
	public ODocument getUpLine(ODatabaseDocumentTx db, ODocument o){
		if (o.isLazyLoad()) {
			return o.field(FPp.UP_LINE);
		}else{
			ORecordId id=o.field(FPp.UP_LINE);
			if (id!=null) {
				if (db==null) {
					db = App.getDbd();
					ODatabaseRecordThreadLocal.INSTANCE.set(db);
					ODocument tmp= getOneByRid(db, id.toString());
					db.close();
					return tmp;
				}
				ODocument tmp= getOneByRid(db, id.toString());
				return tmp;
			}
			return null;
		}
	}
	
	public String upLineToString(ODatabaseDocumentTx db, ODocument o){
		ODocument tmp=getUpLine(db, o);
		if (tmp==null) {
			return "";
		}
		return App.getPelangganDao().getNamaToko(tmp);
	}
	
	public PpDao setJmlDownline(ODocument o, int jmlDownline) {
		o.field(FPp.JML_DOWNLINE, jmlDownline, OType.INTEGER);
		return this;
	}

	public PpDao setJmlDownline(ODocument o, TextField jmlDownline) {
		int tmp=0;
		try {
			tmp=Integer.parseInt(jmlDownline.getText());
		} catch (Exception e) {
		}
		setJmlDownline(o, tmp);
		return this;
	}

	public int getJmlDownline(ODocument o) {
		Integer tmp = o.field(FPp.JML_DOWNLINE);
		if (tmp == null) {
			tmp = 0;
		}
		return tmp;
	}
	
	public String jmlDownlineToString(ODocument o){
		int tmp =getJmlDownline(o);
		if (tmp==0) {
			return "-";
		}
		return tmp+"";
	}

	public boolean vJmlDownline(ODatabaseDocumentTx db, TextField jmlDownline) {
		if (!validate(db, jmlDownline, LPp.JML_DOWNLINE)) {
			return false;
		}
		try {
			Integer tmp=Integer.parseInt(jmlDownline.getText());
		} catch (Exception e) {
			App.showErrorNotValid(db, LPp.JML_DOWNLINE);
			jmlDownline.setText("0");
			jmlDownline.requestFocus();
			return false;
		}
		return true;
	}

	public boolean vJmlDownline(ODatabaseDocumentTx db, TextField jmlDownline, ODocument model) {
		return vJmlDownline(db, jmlDownline);
	}
	
	public PpDao setTglDaftar(ODocument o, Date tglDaftar) {
		o.field(FPp.TGL_DAFTAR, tglDaftar, OType.DATE);
		return this;
	}

	public PpDao setTglDaftar(ODocument o, DatePicker tglDaftar) {
		setTglDaftar(o, tglDaftar.getDate());
		return this;
	}
	
	public Date getTglDaftar(ODocument o){
		return o.field(FPp.TGL_DAFTAR);
	}

	public String tglDaftarToString(ODocument o) {
		Date tmp = getTglDaftar(o);
		if (tmp == null) {
			return "";
		}
		return App.dateFormat.format(tmp);
	}
	
	public PpDao setTglLunas(ODocument o, Date tglLunas) {
		o.field(FPp.TGL_LUNAS, tglLunas, OType.DATE);
		return this;
	}

	public PpDao setTglLunas(ODocument o, DatePicker tglLunas) {
		setTglLunas(o, tglLunas.getDate());
		return this;
	}
	
	public Date getTglLunas(ODocument o){
		return o.field(FPp.TGL_LUNAS);
	}

	public String tglLunasToString(ODocument o) {
		Date tmp = getTglLunas(o);
		if (tmp == null) {
			return "";
		}
		return App.dateFormat.format(tmp);
	}
	
	public PpDao setTglAktif(ODocument o, Date tglAktif) {
		o.field(FPp.TGL_AKTIF, tglAktif, OType.DATE);
		return this;
	}

	public PpDao setTglAktif(ODocument o, DatePicker tglAktif) {
		setTglAktif(o, tglAktif.getDate());
		return this;
	}
	
	public Date getTglAktif(ODocument o){
		return o.field(FPp.TGL_AKTIF);
	}

	public String tglAktifToString(ODocument o) {
		Date tmp = getTglAktif(o);
		if (tmp == null) {
			return "";
		}
		return App.dateFormat.format(tmp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public PpDao setStatus(ODocument o, int status) {
		o.field(FPp.STATUS, status, OType.INTEGER);
		return this;
	}
	

	public PpDao setStatus(ODocument o, TextField status) {
		Integer tmp=0;
		try {
			tmp=Integer.parseInt(status.getText());
		} catch (Exception e) {
		}
		setStatus(o, tmp);
		return this;
	}

	public String statusToString(ODocument o) {
		Integer tmp = o.field(FPp.STATUS);
		if (tmp == null) {
			return "";
		}
		String [] tmp2=getStatusData();
		return tmp2[tmp];
	}
	
	public int getStatus(ODocument o) {
		Integer tmp = o.field(FPp.STATUS);
		if (tmp == null) {
			return 0;
		}
		return tmp;
	}
	
	
	
	
	public List<ODocument> getHistoryBayars(ODatabaseDocumentTx db,ODocument o){
		List<ODocument> tmp=o.field(FPp.HISTORY_BAYARS);
		if (tmp==null) {
			tmp=new ArrayList<>();
		}
		return tmp;
	}
	
	public PpDao setDownlines(ODocument o, List<ODocument> downlines){
		o.field(FPp.DOWNLINES, downlines, OType.LINKLIST);
		return this;
	}
	public List<ODocument> getDownlines(ODatabaseDocumentTx db,ODocument o){
		List<ODocument> tmp=o.field(FPp.DOWNLINES);
		if (tmp==null) {
			tmp=new ArrayList<>();
		}
		return tmp;
	}
	
	public ODocument createHistoryBayar(Date tglBayar, BigDecimal jmlBayar, BigDecimal jmlSisa){
		ODocument tmp=new ODocument(FHistoryBayar.TABLE);
		tmp.field(FHistoryBayar.TGL_BAYAR, tglBayar, OType.DATE);
		tmp.field(FHistoryBayar.JML_BAYAR, jmlBayar, OType.DECIMAL);
		tmp.field(FHistoryBayar.JML_SISA, jmlSisa, OType.DECIMAL);
		return tmp;
	}
	
	public PpDao setHistoryBayar(ODatabaseDocumentTx db,ODocument o, Date tglBayar, BigDecimal jmlBayar, BigDecimal jmlSisa){
		List<ODocument> tmp=getHistoryBayars(db, o);
		tmp.add(createHistoryBayar(tglBayar, jmlBayar, jmlSisa));
		o.field(FPp.HISTORY_BAYARS, tmp , OType.EMBEDDEDLIST);
		return this;
	}
	
	
	public String [] getStatusData(){
		String a1=App.getT("Pilih Status");
		String ta=App.getT("Tidak Aktif");
		String a=App.getT("Aktif");
		String a2=App.getT("Warning");
		String a3=App.getT("Expired");
		String [] stringStatus={a1,ta,a,a2,a3};
		return stringStatus;
	}
	

	
	
	@Override
	public String getNameFielsToString() {
		return FPp.CODE;
	}


	@Override
	public boolean beforeDelete(ODatabaseDocumentTx db, ODocument o) {
		//pelanggan
		command(db, "update "+FPelanggan.TABLE+" remove "+FPelanggan.PAKETS+" = "+o.getIdentity());
		
		//paket
		command(db, "update "+FPaket.TABLE+" remove "+FPaket.DOWNLINES+" = "+o.getIdentity());
		
		//upline
		//hapus downline yang di upline dan looping mines 1 jumlah downline
		ODocument tmp=App.getPpDao().getUpLine(db, o);
		if (tmp!=null) {
			recursiveMinDownline(db, tmp);
		}
		
		ODocument oDocumentPaket=getPaket(db, o);
		int tmpTotalPelanggan=App.getPaketDao().getTotalPelanggan(oDocumentPaket);
		tmpTotalPelanggan++;
		App.getPaketDao().setTotalPelanggan(oDocumentPaket, tmpTotalPelanggan);
		App.getPaketDao().update(db, oDocumentPaket);
		
		return super.beforeDelete(db, o);
	}
	
	public void recursiveMinDownline(ODatabaseDocumentTx db,ODocument o){
		if (App.getPpDao().isTrueChildThis(o)) {
			int tmp1=App.getPpDao().getJmlDownline(o);
			tmp1--;
			App.getPpDao().setJmlDownline(o, tmp1);
			App.getPpDao().update(db, o);
			ODocument tmp=App.getPpDao().getUpLine(db, o);
			if (tmp!=null) {
				recursiveMinDownline(db, tmp);
			}
		}
		
	}
	
	public void recursivePenambahanDownline(ODatabaseDocumentTx db, ODocument o){
		if (App.getPpDao().isTrueChildThis(o)) {
			int tmp1=App.getPpDao().getJmlDownline(o);
			tmp1++;
			App.getPpDao().setJmlDownline(o, tmp1);
			App.getPpDao().update(db, o);
			ODocument tmp=App.getPpDao().getUpLine(db, o);
			if (tmp!=null) {
				recursivePenambahanDownline(db, tmp);
			}
		}
	}
	
	
}
