package com.basic.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.dao.abst.DaoAbstract;

import com.basic.db.FGrp;
import com.basic.db.FUsr;
import com.basic.entity.Grp;
import com.basic.entity.Usr;
import com.basic.lang.LGrp;
import com.global.App;
import com.global.DataUser;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;

public class GrpDao extends DaoAbstract {

	public GrpDao() {
		super(FGrp.TABLE);
	}

	public GrpDao setCode(ODocument o, String code) {
		o.field(FGrp.CODE, code);
		return this;
	}

	public GrpDao setCode(ODocument o, TextField code) {
		setCode(o, code.getText());
		return this;
	}

	public String getCode(ODocument o) {
		String tmp = o.field(FGrp.CODE);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public boolean vCode(ODatabaseDocumentTx db, TextField code) {
		if (!(code.getText().trim().equals("") || code.getText().trim()
				.equalsIgnoreCase("Auto"))) {
			long tmp = getCountByColumn(db, FGrp.CODE, code.getText());
			if (tmp > 0) {
				App.showErrorDataSudahAda(db, LGrp.CODE);
				return false;
			}
		}
		return true;
	}

	public boolean vCode(ODatabaseDocumentTx db, TextField code, ODocument model) {
		if (!code.getText().equalsIgnoreCase((String) model.field(FGrp.CODE))) {
			long tmp = getCountByColumn(db, FGrp.CODE, code.getText());
			if (tmp > 0) {
				App.showErrorDataSudahAda(db, LGrp.CODE);
				return false;
			}
			if (code.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LGrp.CODE);
				return false;
			}
		}
		return true;
	}

	public GrpDao setName(ODocument o, String name) {
		o.field(FGrp.NAME, name);
		return this;
	}

	public GrpDao setName(ODocument o, TextField name) {
		setName(o, name.getText());
		return this;
	}

	public String getName(ODocument o) {
		String tmp = o.field(FGrp.NAME);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public boolean vName(ODatabaseDocumentTx db, TextField name) {
		if (!validate(db, name, LGrp.NAME)) {
			return false;
		}
		long tmp = App.getGrpDao().getCountByColumn(db, FGrp.NAME,
				name.getText());
		if (tmp > 0) {
			App.showErrorDataSudahAda(db, LGrp.NAME);
			return false;
		}
		return true;
	}

	public boolean vName(ODatabaseDocumentTx db, TextField name, ODocument model) {
		if (!validate(db, name, LGrp.NAME)) {
			return false;
		}
		if (!name.getText().equalsIgnoreCase((String) model.field(FGrp.NAME))) {
			long tmp = App.getUsrDao().getCountByColumn(db, FGrp.NAME,
					name.getText());
			if (tmp > 0) {
				App.showErrorDataSudahAda(db, LGrp.NAME);
				return false;
			}
			if (name.getText().trim().equals("")) {
				App.showErrorFieldEmpty(db, LGrp.NAME);
				return false;
			}
		}
		return true;
	}

	public GrpDao setNote(ODocument o, String note) {
		o.field(FGrp.NOTE, note);
		return this;
	}

	public GrpDao setNote(ODocument o, TextArea note) {
		setNote(o, note.getText());
		return this;
	}

	public String getNote(ODocument o) {
		String tmp = o.field(FGrp.NOTE);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public boolean vNote(ODatabaseDocumentTx db, TextField note) {
		if (!validate(db, note, LGrp.NOTE)) {
			return false;
		}
		return true;
	}

	public boolean vNote(ODatabaseDocumentTx db, TextField note, ODocument model) {
		if (!validate(db, note, LGrp.NOTE)) {
			return false;
		}
		return true;
	}

	public GrpDao setKey(ODocument o, String key) {
		o.field(FGrp.KEY, key);
		return this;
	}

	public GrpDao setKey(ODocument o, TextField key) {
		setKey(o, key.getText());
		return this;
	}

	public String getKey(ODocument o) {
		String tmp = o.field(FGrp.KEY);
		if (tmp == null) {
			tmp = "";
		}
		return tmp;
	}

	public GrpDao setUsrs(ODocument o, HashSet<ODocument> users) {
		if (users == null) {
			users = new HashSet<>();
		}
		o.field(FGrp.USRS, users, OType.LINKSET);
		return this;
	}
	
	public List<ODocument> getUsrs(ODocument o){
		List<ODocument> tmp=o.field(FGrp.USRS);
		if (tmp==null) {
			tmp=new ArrayList<>();
		}
		return tmp;
	}

	public GrpDao setCreateBy(ODocument o, ODocument usr) {
		if (usr != null) {
			o.field(FGrp.CREATE_BY, usr, OType.LINK);
		}
		return this;
	}
	
	public String getCreateAt(ODocument o){
		Date tmp=o.field(FGrp.CREATE_AT);
		if (tmp==null) {
			return "";
		}
		return App.dateTimeFormat.format(tmp);
	}
	
	public String getUpdateAt(ODocument o){
		Date tmp=o.field(FGrp.UPDATE_AT);
		if (tmp==null) {
			return "";
		}
		return App.dateTimeFormat.format(tmp);
	}
	
	public ODocument getCreateBy(ODocument o){
		if (o.isLazyLoad()) {
			return o.field(FGrp.CREATE_BY);
		}else{
			ORecordId id=o.field(FGrp.CREATE_BY);
			if (id!=null) {
				ODatabaseDocumentTx db = App.getDbd();
				ODatabaseRecordThreadLocal.INSTANCE.set(db);
				ODocument tmp= getOneByRid(db, id.toString());
				db.close();
				return tmp;
			}
			return null;
		}
	}
	
//	public String createByToString(ODocument o){
//		ODocument tmp=getCreateBy(o);
//		if (tmp==null) {
//			return "";
//		}
//		return App.getUsrDao().getNama(tmp);
//	}
	
	
	
	public ODocument getUpdateBy(ODocument o){
		if (o.isLazyLoad()) {
			return o.field(FGrp.UPDATE_BY);
		}else{
			ORecordId id=o.field(FGrp.UPDATE_BY);
			if (id!=null) {
				ODatabaseDocumentTx db = App.getDbd();
				ODatabaseRecordThreadLocal.INSTANCE.set(db);
				ODocument tmp= getOneByRid(db, id.toString());
				db.close();
				return tmp;
			}
			return null;
		}
	}
	
//	public String updateByToString(ODocument o){
//		ODocument tmp=getUpdateBy(o);
//		if (tmp==null) {
//			return "";
//		}
//		return App.getUsrDao().getNama(tmp);
//	}
	
	
	public ODocument getCreateBy2(ODocument o){
		String json=o.field(FGrp.CREATE_BY2);
		if (json==null || json.equalsIgnoreCase("")) {
			return null;
		}
		ODocument tmp=new ODocument(getClassName());
		tmp.fromJSON(json);
		return tmp;
	}
	
	public ODocument getUpdateBy2(ODocument o){
		String json=o.field(FGrp.UPDATE_BY2);
		if (json==null || json.equalsIgnoreCase("")) {
			return null;
		}
		ODocument tmp=new ODocument(getClassName());
		tmp.fromJSON(json);
		return tmp;
	}

	public GrpDao setCreateAt(ODocument o, Date createAt) {
		if (createAt != null) {
			o.field(FGrp.CREATE_AT, createAt, OType.DATETIME);
		}
		return this;
	}

	public GrpDao setUpdateBy(ODocument o, ODocument usr) {
		if (usr != null) {
			o.field(FGrp.UPDATE_BY, usr, OType.LINK);
		}
		return this;
	}

	public GrpDao setUpdateAt(ODocument o, Date updateAt) {
		if (updateAt != null) {
			o.field(FGrp.UPDATE_AT, updateAt, OType.DATETIME);
		}
		return this;
	}

	// public ODocument factoryModel(String code, String name, String note) {
	// ODocument doc = factoryModel(name, code, note, (String) DataUser
	// .getUsr().field(Usr.USERNAME), new Date());
	// doc.field(Grp.CODE, code);
	// doc.field(Grp.NAME, name);
	// doc.field(Grp.NOTE, note);
	// return doc;
	// }

	// public ODocument factoryModel(String name, String code, String note,
	// String createdBy, Date createdAt) {
	// ODocument doc = new ODocument(getClassName());
	// doc.field(Grp.NAME, name);
	// doc.field(Grp.CODE, code);
	// doc.field(Grp.NOTE, note);
	// ODocument logdb = new ODocument(Logdb.TABLE);
	// logdb.field(Logdb.CREATE_BY, createdBy);
	// logdb.field(Logdb.CREATE_AT, createdAt, OType.DATE);
	// doc.field(Grp.LOGDB, logdb, OType.EMBEDDED);
	// return doc;
	//
	// }

	public void factoryModelFirst(ODatabaseDocumentTx db) {
		if (getCount(db) == 0) {

			ODocument usrDoc = App.getUsrDao().getOne(db, FUsr.USERNAME, "admin");
			if (usrDoc == null) {
				App.getUsrDao().factoryModelFirst(db);
				usrDoc = App.getUsrDao().getOne(db, FUsr.USERNAME, "admin");
			}

			Usr usr=new Usr(usrDoc);
			
			Grp grp=new Grp();
			grp.setName("Admin");
			grp.setNote("Hak akses untuk Super User");
			StringBuffer tmp = new StringBuffer();
			for (int i = 1; i < 50; i++) {
				tmp.append("x" + i + "x");
			}
			grp.setKey(tmp.toString());
			
			grp.setCreateAt(new Date());
			grp.setCreateBy(usrDoc);
			
			List<Usr> usrs=new ArrayList<>();
			usrs.add(usr);

			grp.setUsrs(usrs);
			

			try {
				db.begin(TXTYPE.OPTIMISTIC);

				save(db, grp.getDoc());
				usr.setGrp(grp);
				App.getUsrDao().save(db, usr.getDoc());
				db.commit();

			} catch (Exception e) {
				e.printStackTrace();
				db.rollback();
			}

		}
	}

	public long modelIsExist(ODatabaseDocumentTx db, String name, String code) {
		return getCountByColumn(db, "name", name, "code", code, "or");
	}

	public boolean beforeDelete(ODatabaseDocumentTx db, ODocument o) {
		App.getUsrDao().delByColoumn(db, FUsr.GRP, o.getIdentity());
		return true;
	}

	@Override
	public String getNameFielsToString() {
		return FGrp.NAME;
	}

	@Override
	public ODocument update(ODatabaseDocumentTx db, ODocument model,
			String jsonOld) {
		
		model.save();

		
		return model;
	}

	@Override
	public ODocument save(ODatabaseDocumentTx db, ODocument model) {
		super.save(db, model);
		
		return model;
	}
	
	
	
	

}
