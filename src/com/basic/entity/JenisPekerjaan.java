package com.basic.entity;

import com.basic.db.FJenisPekerjaan;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class JenisPekerjaan {
	private String code;
	private String nama;

	private ODocument doc;

	
	
	public JenisPekerjaan() {
		super();
		doc=new ODocument(FJenisPekerjaan.TABLE);
	}

	public JenisPekerjaan(ODocument doc) {
		super();
		setDoc(doc);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		doc.field(FJenisPekerjaan.CODE, code, OType.STRING);
		this.code = code;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		doc.field(FJenisPekerjaan.NAMA, nama, OType.STRING);
		this.nama = nama;
	}

	public ODocument getDoc() {
		return doc;
	}

	public void setDoc(ODocument doc) {
		this.code = doc.field(FJenisPekerjaan.CODE);
		this.nama = doc.field(FJenisPekerjaan.NAMA);
		this.doc = doc;
	}

	@Override
	public String toString() {
		return nama;
	}
	
	
}
