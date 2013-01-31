package com.mlm.entity;

import com.mlm.db.FPelanggan;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

import java.util.ArrayList;
import java.util.List;

public class Pelanggan {
	private String namaToko;
	private String jenisIdentitas;
	private String namaPemilik;
	private String noIdentitas;
	private String alamat;
	private String kota;
	private String noTelp;
	private String noFax;
	private String noHp1;
	private String noHp2;
	private String pinBb1;
	private String pinBb2;
	private int status;
	private List<Pp> pakets;
	
	private ODocument doc;

	
	
	public Pelanggan(ODocument doc) {
		super();
		setDoc(doc);
	}

	public String getNamaToko() {
		return namaToko;
	}

	public void setNamaToko(String namaToko) {
		doc.field(FPelanggan.NAMA_TOKO, namaToko, OType.STRING);
		this.namaToko = namaToko;
	}

	public String getJenisIdentitas() {
		return jenisIdentitas;
	}

	public void setJenisIdentitas(String jenisIdentitas) {
		doc.field(FPelanggan.JENIS_IDENTITAS, jenisIdentitas, OType.STRING);
		this.jenisIdentitas = jenisIdentitas;
	}

	public String getNamaPemilik() {
		return namaPemilik;
	}

	public void setNamaPemilik(String namaPemilik) {
		doc.field(FPelanggan.NAMA_PEMILIK, namaPemilik, OType.STRING);
		this.namaPemilik = namaPemilik;
	}

	public String getNoIdentitas() {
		return noIdentitas;
	}

	public void setNoIdentitas(String noIdentitas) {
		doc.field(FPelanggan.NO_IDENTITAS, noIdentitas, OType.STRING);
		this.noIdentitas = noIdentitas;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		doc.field(FPelanggan.ALAMAT, alamat, OType.STRING);
		this.alamat = alamat;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		doc.field(FPelanggan.KOTA, kota, OType.STRING);
		this.kota = kota;
	}

	public String getNoTelp() {
		return noTelp;
	}

	public void setNoTelp(String noTelp) {
		doc.field(FPelanggan.NO_TELP, noTelp, OType.STRING);
		this.noTelp = noTelp;
	}

	public String getNoFax() {
		return noFax;
	}

	public void setNoFax(String noFax) {
		doc.field(FPelanggan.NO_FAX, noFax, OType.STRING);
		this.noFax = noFax;
	}

	public String getNoHp1() {
		return noHp1;
	}

	public void setNoHp1(String noHp1) {
		doc.field(FPelanggan.NO_HP1, noHp1, OType.STRING);
		this.noHp1 = noHp1;
	}

	public String getNoHp2() {
		return noHp2;
	}

	public void setNoHp2(String noHp2) {
		doc.field(FPelanggan.NO_HP2, noHp2, OType.STRING);
		this.noHp2 = noHp2;
	}

	public String getPinBb1() {
		return pinBb1;
	}

	public void setPinBb1(String pinBb1) {
		doc.field(FPelanggan.PIN_BB1, pinBb1, OType.STRING);
		this.pinBb1 = pinBb1;
	}

	public String getPinBb2() {
		return pinBb2;
	}

	public void setPinBb2(String pinBb2) {
		doc.field(FPelanggan.PIN_BB2, pinBb2, OType.STRING);
		this.pinBb2 = pinBb2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		doc.field(FPelanggan.STATUS, status, OType.INTEGER);
		this.status = status;
	}

	public List<Pp> getPakets() {
		return pakets;
	}

	public void setPakets(List<ODocument> pakets) {
		doc.field(FPelanggan.PAKETS, pakets, OType.LINKSET);
		List<Pp> pps=new ArrayList<>();
		for (ODocument o : pakets) {
			pps.add(new Pp(o));
		}
		this.pakets = pps;
	}

	public ODocument getDoc() {
		return doc;
	}

	public void setDoc(ODocument doc) {
		this.namaToko = doc.field(FPelanggan.NAMA_TOKO);
		this.jenisIdentitas = doc.field(FPelanggan.JENIS_IDENTITAS);
		this.namaPemilik = doc.field(FPelanggan.NAMA_PEMILIK);
		this.noIdentitas = doc.field(FPelanggan.NO_IDENTITAS);
		this.alamat = doc.field(FPelanggan.ALAMAT);
		this.kota = doc.field(FPelanggan.KOTA);
		this.noTelp = doc.field(FPelanggan.NO_TELP);
		this.noFax = doc.field(FPelanggan.NO_FAX);
		this.noHp1 = doc.field(FPelanggan.NO_HP1);
		this.noHp2 = doc.field(FPelanggan.NO_HP2);
		this.pinBb1 = doc.field(FPelanggan.PIN_BB1);
		this.pinBb2 = doc.field(FPelanggan.PIN_BB2);
		this.status = doc.field(FPelanggan.STATUS);
		this.pakets = doc.field(FPelanggan.PAKETS);
		this.doc = doc;
	}

	
	

}
