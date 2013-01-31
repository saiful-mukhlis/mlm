package com.mlm.managedb;

import com.basic.db.*;
import com.global.App;
import com.mlm.db.FPaket;
import com.mlm.db.FPelanggan;
import com.mlm.db.FPp;
import com.mlm.db.FStatusPelanggan;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class StartDbMlm {

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
		OClass paket = db.getMetadata().getSchema().getClass(FPaket.TABLE);
		OClass pelanggan = db.getMetadata().getSchema()
				.getClass(FPelanggan.TABLE);
		OClass pp = db.getMetadata().getSchema()
				.getClass(FPp.TABLE);
		OClass statusPelanggan = db.getMetadata().getSchema()
				.getClass(FStatusPelanggan.TABLE);


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
//		terjadi duplicate index
//		grp.createProperty(FGrp.USRS, OType.LINKSET);
		grp.createProperty(FGrp.CREATE_BY, OType.LINK, usr);
		grp.createProperty(FGrp.UPDATE_BY, OType.LINK, usr);


		// tabel Usr
		usr.createProperty(FUsr.USERNAME, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		usr.createProperty(FUsr.CODE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		usr.createProperty(FUsr.GRP, OType.LINK, grp);
		usr.createProperty(FUsr.CREATE_BY, OType.LINK, usr);
		usr.createProperty(FUsr.UPDATE_BY, OType.LINK, usr);

		// tabel Paket
		paket.createProperty(FPaket.CODE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		paket.createProperty(FPaket.NAMA, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		paket.createProperty(FPaket.DOWNLINES, OType.LINKLIST, pelanggan);

		// tabel Pelanggan
		pelanggan.createProperty(FPelanggan.CODE, OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		pelanggan.createProperty(FPelanggan.NAMA_TOKO, OType.STRING)
				.createIndex(OClass.INDEX_TYPE.UNIQUE);
		pelanggan.createProperty(FPelanggan.STATUS, OType.LINK, statusPelanggan);
		pelanggan.createProperty(FPelanggan.PAKETS, OType.LINKLIST, pp);
		
		// tabel Pp
				pp.createProperty(FPp.CODE, OType.STRING).createIndex(
						OClass.INDEX_TYPE.UNIQUE);
				pp.createProperty(FPp.PELANGGAN, OType.LINK, pelanggan);
				pp.createProperty(FPp.UP_LINE, OType.LINK, pelanggan);
				pp.createProperty(FPp.PAKET, OType.LINK, paket);
				pp.createProperty(FPp.DOWNLINES, OType.LINKLIST, pelanggan);

		// tabel Status Pelanggan
		statusPelanggan.createProperty(FStatusPelanggan.CODE, OType.STRING)
				.createIndex(OClass.INDEX_TYPE.UNIQUE);
		statusPelanggan.createProperty(FStatusPelanggan.NAMA, OType.STRING)
				.createIndex(OClass.INDEX_TYPE.UNIQUE);

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
