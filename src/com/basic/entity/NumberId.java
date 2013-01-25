package com.basic.entity;

import com.basic.db.FNumberId;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class NumberId {
	private String namaTable;
	private Long start;
	private long end;
	private int increment;
	private long numberNow;
	private String prefix;
	private String format;
	private boolean useFormat;

	private ODocument doc;
	

	public NumberId(ODocument doc) {
		super();
		setDoc(doc);
	}

	public String getNamaTable() {
		return namaTable;
	}

	public void setNamaTable(String namaTable) {
		doc.field(FNumberId.NAMA_TABLE, namaTable, OType.STRING);
		this.namaTable = namaTable;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		doc.field(FNumberId.START, start, OType.LONG);
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		doc.field(FNumberId.END, end, OType.LONG);
		this.end = end;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		doc.field(FNumberId.INCREMENT, increment, OType.INTEGER);
		this.increment = increment;
	}

	public long getNumberNow() {
		return numberNow;
	}

	public void setNumberNow(long numberNow) {
		doc.field(FNumberId.NUMBER_NOW, numberNow, OType.LONG);
		this.numberNow = numberNow;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		doc.field(FNumberId.PREFIX, prefix, OType.STRING);
		this.prefix = prefix;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		doc.field(FNumberId.FORMAT, format, OType.STRING);
		this.format = format;
	}

	public boolean isUseFormat() {
		return useFormat;
	}

	public void setUseFormat(boolean useFormat) {
		doc.field(FNumberId.USE_FORMAT, useFormat, OType.BOOLEAN);
		this.useFormat = useFormat;
	}

	public ODocument getDoc() {
		return doc;
	}

	

	public void setDoc(ODocument doc) {
		this.namaTable = doc.field(FNumberId.NAMA_TABLE);
		this.start = doc.field(FNumberId.START);
		this.end = doc.field(FNumberId.END);
		this.increment = doc.field(FNumberId.INCREMENT);
		this.numberNow = doc.field(FNumberId.NUMBER_NOW);
		this.prefix = doc.field(FNumberId.PREFIX);
		this.format = doc.field(FNumberId.FORMAT);
		this.useFormat = doc.field(FNumberId.USE_FORMAT);
		this.doc = doc;
	}

}
