package com.mlm.entity;

import com.mlm.db.FHistoryBayar;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

import java.math.BigDecimal;
import java.util.Date;

public class HistoryBayar {
	private Date tglBayar;
	private BigDecimal jmlBayar;
	private BigDecimal jmlSisa;

	private ODocument doc;

	
	
	public HistoryBayar(ODocument doc) {
		super();
		setDoc(doc);
	}

	public Date getTglBayar() {
		return tglBayar;
	}

	public void setTglBayar(Date tglBayar) {
		doc.field(FHistoryBayar.TGL_BAYAR, tglBayar, OType.DATETIME);
		this.tglBayar = tglBayar;
	}

	public BigDecimal getJmlBayar() {
		return jmlBayar;
	}

	public void setJmlBayar(BigDecimal jmlBayar) {
		doc.field(FHistoryBayar.JML_BAYAR, jmlBayar, OType.DECIMAL);
		this.jmlBayar = jmlBayar;
	}

	public BigDecimal getJmlSisa() {
		return jmlSisa;
	}

	public void setJmlSisa(BigDecimal jmlSisa) {
		doc.field(FHistoryBayar.JML_SISA, jmlSisa, OType.DECIMAL);
		this.jmlSisa = jmlSisa;
	}

	public ODocument getDoc() {
		return doc;
	}
	
	


	public void setDoc(ODocument doc) {
		this.tglBayar = doc.field(FHistoryBayar.TGL_BAYAR);
		this.jmlBayar = doc.field(FHistoryBayar.JML_BAYAR);
		this.jmlSisa = doc.field(FHistoryBayar.JML_SISA);
		this.doc = doc;
	}

}
