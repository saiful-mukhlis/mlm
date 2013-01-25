package com.mlm.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mlm.db.FPaket;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Paket {
	private String code;
	private String nama;
	private String ket;
	private BigDecimal harga;
	private int downline;
	private int waktu;
	private int status;
	private int totalPelanggan;
	private List<Pp> downlines;
	
	private ODocument doc;

	
	
	public Paket(ODocument doc) {
		super();
		setDoc(doc);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		doc.field(FPaket.CODE, code, OType.STRING);
		this.code = code;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		doc.field(FPaket.NAMA, nama, OType.STRING);
		this.nama = nama;
	}

	public String getKet() {
		return ket;
	}

	public void setKet(String ket) {
		doc.field(FPaket.KET, ket, OType.STRING);
		this.ket = ket;
	}

	public BigDecimal getHarga() {
		return harga;
	}

	public void setHarga(BigDecimal harga) {
		doc.field(FPaket.HARGA, harga, OType.DECIMAL);
		this.harga = harga;
	}

	public int getDownline() {
		return downline;
	}

	public void setDownline(int downline) {
		doc.field(FPaket.DOWNLINE, downline, OType.INTEGER);
		this.downline = downline;
	}

	public int getWaktu() {
		return waktu;
	}

	public void setWaktu(int waktu) {
		doc.field(FPaket.WAKTU, waktu, OType.INTEGER);
		this.waktu = waktu;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		doc.field(FPaket.STATUS, status, OType.INTEGER);
		this.status = status;
	}

	public int getTotalPelanggan() {
		return totalPelanggan;
	}

	public void setTotalPelanggan(int totalPelanggan) {
		doc.field(FPaket.TOTAL_PELANGGAN, totalPelanggan, OType.INTEGER);
		this.totalPelanggan = totalPelanggan;
	}

	public List<Pp> getDownlines() {
		return downlines;
	}

	public void setDownlines(List<ODocument> downlines) {
		doc.field(FPaket.DOWNLINES, downlines, OType.LINKLIST);
		List<Pp> pps=new ArrayList<>();
		for (ODocument o : downlines) {
			pps.add(new Pp(o));
		}
		this.downlines = pps;
	}

	public ODocument getDoc() {
		return doc;
	}

	
	

	public void setDoc(ODocument doc) {
		this.code = doc.field(FPaket.CODE);
		this.nama = doc.field(FPaket.NAMA);
		this.ket = doc.field(FPaket.KET);
		this.harga = doc.field(FPaket.HARGA);
		this.downline = doc.field(FPaket.DOWNLINE);
		this.waktu = doc.field(FPaket.WAKTU);
		this.status = doc.field(FPaket.STATUS);
		this.totalPelanggan = doc.field(FPaket.TOTAL_PELANGGAN);
		this.downlines = doc.field(FPaket.DOWNLINES);
		this.doc = doc;
	}
	
	
}
