package com.basic.entity;

import com.basic.db.FBos;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Bos {
	private String id;
	private String name;
	private int jml;

	private ODocument doc;

	public Bos() {
		super();
		doc = new ODocument(FBos.TABLE);
	}

	public Bos(ODocument doc) {
		super();
		setDoc(doc);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		doc.field(FBos.ID, id, OType.STRING);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		doc.field(FBos.NAME, name, OType.STRING);
		this.name = name;
	}

	public int getJml() {
		return jml;
	}

	public void setJml(int jml) {
		doc.field(FBos.JML, jml, OType.INTEGER);
		this.jml = jml;
	}

	public ODocument getDoc() {
		return doc;
	}

	public void setDoc(ODocument doc) {
		this.id = doc.field(FBos.ID);
		this.name = doc.field(FBos.NAME);
		this.jml = doc.field(FBos.JML);
		this.doc = doc;
	}

}
