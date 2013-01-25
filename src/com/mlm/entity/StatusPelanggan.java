package com.mlm.entity;

import com.mlm.db.FStatusPelanggan;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class StatusPelanggan {
	private String code;
	private String nama;

	private ODocument doc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		doc.field(FStatusPelanggan.CODE, code, OType.STRING);
		this.code = code;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		doc.field(FStatusPelanggan.NAMA, nama, OType.STRING);
		this.nama = nama;
	}

	public ODocument getDoc() {
		return doc;
	}

	public void setDoc(ODocument doc) {
		this.code = doc.field(FStatusPelanggan.CODE);
		this.nama = doc.field(FStatusPelanggan.NAMA);
		this.doc = doc;
	}
}
