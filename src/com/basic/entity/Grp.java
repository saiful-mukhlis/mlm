package com.basic.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.basic.db.FGrp;
import com.basic.db.FUsr;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.db.record.ORecordLazyList;
import com.orientechnologies.orient.core.db.record.ORecordLazySet;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.type.tree.OMVRBTreeRIDSet;

public class Grp {
	private String code;
	private String name;
	private String note;
	private String key;
	
	private String createBy;
	private String updateBy;
	private Date createAt;
	private Date updateAt;

	private ODocument doc;

	private List<Usr> usrs;
	private List<ODocument> usrDocs;
	
	public Grp() {
		doc = new ODocument(FGrp.TABLE);
	}

	
	/**
	 * set doc ini tidak men set users
	 * @param doc
	 */
	public Grp(ODocument doc) {
		super();
		setDoc(doc);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		doc.field(FGrp.CODE, code, OType.STRING);
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String nama) {
		doc.field(FGrp.NAME, nama, OType.STRING);
		this.name = nama;
	}

	public ODocument getDoc() {
		return doc;
	}

	public void setDoc(ODocument doc) {
		this.code = doc.field(FGrp.CODE);
		this.name = doc.field(FGrp.NAME);
		this.note = doc.field(FGrp.NOTE);
		
		ODocument tmp=doc.field(FGrp.CREATE_BY);
		if (tmp!=null) {
			this.createBy=tmp.field(FGrp.NAME);
		}
		
		ODocument tmp2=doc.field(FGrp.UPDATE_BY);
		if (tmp2!=null) {
			this.updateBy=tmp2.field(FGrp.NAME);
		}
		
		this.createAt=doc.field(FGrp.CREATE_AT);
		this.updateAt=doc.field(FGrp.UPDATE_AT);
		
//		this.usrDocs=doc.field(FGrp.USRS);
//		
//		this.usrs=new ArrayList<>();
//		for (ODocument o : usrDocs) {
//			this.usrs.add(new Usr(o));
//		}
		
		this.doc = doc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		doc.field(FGrp.KEY, key, OType.STRING);
		this.key = key;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		doc.field(FGrp.NOTE, note, OType.STRING);
		this.note = note;
	}
	
	
	public String getCreateBy() {
		return createBy;
	}

	
	public void setCreateBy(ODocument createBy) {
		doc.field(FGrp.CREATE_BY, createBy, OType.LINK);
		this.createBy = createBy.field(FGrp.NAME);
	}

	public String getUpdateBy() {
		return updateBy;
	}

	
	public void setUpdateBy(ODocument updateBy) {
		doc.field(FGrp.UPDATE_BY, updateBy, OType.LINK);
		this.updateBy = updateBy.field(FGrp.NAME);
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		doc.field(FGrp.CREATE_AT, createAt, OType.DATETIME);
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		doc.field(FGrp.UPDATE_AT, updateAt, OType.DATETIME);
		this.updateAt = updateAt;
	}

	public List<Usr> getUsrs() {
		if (usrs==null) {
			ODatabaseDocumentTx db=App.getDbdLocal();
			this.usrDocs=App.getUsrDao().getAllByColumn(db, FUsr.GRP, getDoc().getIdentity());
			db.close();
			this.usrs=new ArrayList<>();
			for (ODocument o : usrDocs) {
				this.usrs.add(new Usr(o));
			}
		}
		return usrs;
	}
	public List<Usr> getUsrs(boolean update) {
		if (usrs==null || update) {
			ODatabaseDocumentTx db=App.getDbdLocal();
			this.usrDocs=App.getUsrDao().getAllByColumn(db, FUsr.GRP, getDoc().getIdentity());
			db.close();
			this.usrs=new ArrayList<>();
			for (ODocument o : usrDocs) {
				this.usrs.add(new Usr(o));
			}
		}
		return usrs;
	}


	public List<ODocument> getUsrDocs() {
		return usrDocs;
	}

	public void setUsrDocs(List<ODocument> usrDocs) {
		List<Usr> usrs=new ArrayList<>();
		for (ODocument o : usrDocs) {
			usrs.add(new Usr(o));
		}
		doc.field(FGrp.USRS, usrDocs, OType.LINKSET);
		this.usrs = usrs;
		this.usrDocs = usrDocs;
	}
	
}
