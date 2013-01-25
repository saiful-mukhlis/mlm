package com.mlm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mlm.db.FPp;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Pp {
	private String code;
	private Pelanggan pelanggan;
	private Paket paket;
	private BigDecimal bayar;
	private Pelanggan upline;
	private List<Pp> downlines;
	private int jmlDownline;
	private Date tglDaftar;
	private Date tglLunas;
	private Date tglAktif;
	private int status;
	private List<HistoryBayar> historyBayars;

	private ODocument doc;
	
	

	public Pp(ODocument doc) {
		super();
		setDoc(doc);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		doc.field(FPp.CODE, code, OType.STRING);
		this.code = code;
	}

	public Pelanggan getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(Pelanggan pelanggan) {
		doc.field(FPp.PELANGGAN, pelanggan.getDoc(), OType.LINK);
		this.pelanggan = pelanggan;
	}

	public Paket getPaket() {
		return paket;
	}

	public void setPaket(Paket paket) {
		doc.field(FPp.PAKET, paket.getDoc(), OType.LINK);
		this.paket = paket;
	}

	public BigDecimal getBayar() {
		return bayar;
	}

	public void setBayar(BigDecimal bayar) {
		doc.field(FPp.BAYAR, bayar, OType.DECIMAL);
		this.bayar = bayar;
	}

	public Pelanggan getUpline() {
		return upline;
	}

	public void setUpline(Pelanggan upline) {
		doc.field(FPp.UP_LINE, upline, OType.LINK);
		this.upline = upline;
	}

	public List<Pp> getDownlines() {
		return downlines;
	}

	public void setDownlines(List<ODocument> downlines) {
		doc.field(FPp.DOWNLINES, downlines, OType.LINKLIST);
		List<Pp> ps=new ArrayList<>();
		for (ODocument doc : downlines) {
			ps.add(new Pp(doc));
		}
		this.downlines = ps;
	}
	
	public int getJmlDownline() {
		return jmlDownline;
	}

	public void setJmlDownline(int jmlDownline) {
		doc.field(FPp.JML_DOWNLINE, jmlDownline, OType.INTEGER);
		this.jmlDownline = jmlDownline;
	}

	public Date getTglDaftar() {
		return tglDaftar;
	}

	public void setTglDaftar(Date tglDaftar) {
		doc.field(FPp.TGL_DAFTAR, tglDaftar, OType.DATE);
		this.tglDaftar = tglDaftar;
	}

	public Date getTglLunas() {
		return tglLunas;
	}

	public void setTglLunas(Date tglLunas) {
		doc.field(FPp.TGL_LUNAS, tglLunas, OType.DATE);
		this.tglLunas = tglLunas;
	}

	public Date getTglAktif() {
		return tglAktif;
	}

	public void setTglAktif(Date tglAktif) {
		doc.field(FPp.TGL_AKTIF, tglAktif, OType.DATE);
		this.tglAktif = tglAktif;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		doc.field(FPp.STATUS, status, OType.INTEGER);
		this.status = status;
	}

	public List<HistoryBayar> getHistoryBayars() {
		return historyBayars;
	}

	public void setHistoryBayars(List<ODocument> historyBayars) {
		doc.field(FPp.HISTORY_BAYARS, historyBayars, OType.EMBEDDEDLIST);
		List<HistoryBayar> hs=new ArrayList<>();
		for (ODocument doc : historyBayars) {
			hs.add(new HistoryBayar(doc));
		}
		this.historyBayars = hs;
	}

	public ODocument getDoc() {
		return doc;
	}
	
	


	public void setDoc(ODocument doc) {
		this.code = doc.field(FPp.CODE);
		ODocument dp=doc.field(FPp.PELANGGAN);
		this.pelanggan = new Pelanggan(dp);
		ODocument dpaket= doc.field(FPp.PAKET);
		this.paket = new Paket(dpaket);
		this.bayar = doc.field(FPp.BAYAR);
		ODocument du=doc.field(FPp.UP_LINE);
		this.upline = new Pelanggan(du);
		List<ODocument> ld=doc.field(FPp.DOWNLINES);
		List<Pp> lp=new ArrayList<>();
		for (ODocument o : ld) {
			lp.add(new Pp(o));
		}
		this.downlines = lp;
		this.jmlDownline = doc.field(FPp.JML_DOWNLINE);
		this.tglDaftar = doc.field(FPp.TGL_DAFTAR);
		this.tglLunas = doc.field(FPp.TGL_LUNAS);
		this.tglAktif = doc.field(FPp.TGL_AKTIF);
		this.status = doc.field(FPp.STATUS);
		List<ODocument> lh=doc.field(FPp.HISTORY_BAYARS);
		List<HistoryBayar> lh2=new ArrayList<>();
		for (ODocument o : lh) {
			lh2.add(new HistoryBayar(o));
		}
		this.historyBayars = lh2;
		this.doc = doc;
	}

}
