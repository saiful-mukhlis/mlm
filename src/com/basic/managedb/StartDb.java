package com.basic.managedb;

import com.basic.db.*;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class StartDb {

	/**
	 * membuat schema,index dan relasi pada database di jalankan setelah
	 * database di buat, jadi hanya sekali saja
	 */
	public void createSchemaDb() {
		OObjectDatabaseTx db = App.getDb();

		OClass bos = db.getMetadata().getSchema().getClass(FBos.TABLE);
		OClass jenisPekerjaan = db.getMetadata().getSchema()
				.getClass(FJenisPekerjaan.TABLE);
		OClass numberId = db.getMetadata().getSchema().getClass(FNumberId.TABLE);

		OClass grp = db.getMetadata().getSchema().getClass(FGrp.TABLE);
		OClass usr = db.getMetadata().getSchema().getClass(FUsr.TABLE);


		// table Jenis Pekerjaan
		jenisPekerjaan.createProperty(FJenisPekerjaan.CODE, OType.STRING)
				.createIndex(OClass.INDEX_TYPE.UNIQUE);
		jenisPekerjaan.createProperty(FJenisPekerjaan.NAMA, OType.STRING)
				.createIndex(OClass.INDEX_TYPE.UNIQUE);
		// tabel NumberId
		numberId.createProperty(FNumberId.NAMA_TABLE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);

		// table Grp
		grp.createProperty(FGrp.CODE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		grp.createProperty(FGrp.NAME, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		grp.createProperty(FGrp.USRS, OType.LINKLIST);
		grp.createProperty(FGrp.CREATE_BY, OType.LINK, usr);
		grp.createProperty(FGrp.UPDATE_BY, OType.LINK, usr);
		
		
		

		// tabel Usr
		usr.createProperty(FUsr.USERNAME, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		usr.createProperty(FUsr.CODE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		usr.createProperty("grp", OType.LINK, grp);
		usr.createProperty(FUsr.CREATE_BY, OType.LINK, usr);
		usr.createProperty(FUsr.UPDATE_BY, OType.LINK, usr);

		db.getMetadata().getSchema().save();

		db.close();

		ODatabaseDocumentTx dbd = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(dbd);
		App.getUsrDao().factoryModelFirst(dbd);
		App.getGrpDao().factoryModelFirst(dbd);
		App.getJenisPekerjaanDao().createFirst(dbd);
		dbd.close();

	}

}
