package com.basic.dao.impl;


import com.basic.db.FBos;
import com.basic.entity.Bos;
import com.global.util.Err;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.dao.abst.DaoAbstract;

import java.util.Random;
import java.util.prefs.Preferences;

public class BosDao extends DaoAbstract {

	public BosDao() {
		super(FBos.TABLE);
	}

//	public ODocument factoryModel(int id, String name, int jml) {
//		ODocument o = new ODocument(getClassName());
//		setId(o, id);
//		setName(o, name);
//		setJml(o, jml);
//		return o;
//	}
//
//	public ODocument factoryModelUpdate(ODocument o,int id, String name, int jml) {
//		setId(o, id);
//		setName(o, name);
//		setJml(o, jml);
//		return o;
//	}
//
	public void factoryFirst(ODatabaseDocumentTx db){
		Bos bos=new Bos();
		bos.setId("1");
		bos.setName("jmllog");
		bos.setJml(2000);
		save(db, bos.getDoc());
		
		bos=new Bos();
		bos.setId("2");
		bos.setName(creatRendom());
		bos.setJml(0);
		save(db, bos.getDoc());
	}
//	
//	public String getName(ODocument o) {
//		return o.field(FBos.NAME);
//	}
//
//	public ODocument setName(ODocument o,String name) {
//		o.field(FBos.NAME, name);
//		return o;
//	}
//
//	public int getJml(ODocument o) {
//		return o.field(FBos.JML);
//	}
//
//	public ODocument setJml(ODocument o, int jml) {
//		o.field(FBos.JML, jml, OType.INTEGER);
//		return o;
//	}
//
//	public int getId(ODocument o) {
//		return o.field(FBos.ID);
//	}
//
//	public ODocument setId(ODocument o, int id) {
//		o.field(FBos.ID, id, OType.INTEGER);
//		return o;
//	}
	
	public boolean check(ODatabaseDocumentTx db){
		ODocument o=getOne(db, FBos.ID, 2);
		if (o==null) {
			factoryFirst(db);
			//baru pertama jadi belum reg
			return false;
		}
		Bos bos=new Bos(o);
		if (bos.getJml()==1) {
			//sudah registrasi
			Preferences userPref = Preferences.userRoot();
			String x=userPref.get("ortptnk", "x");
			if (!x.equalsIgnoreCase(bos.getName())) {
				if (bos.getJml()==1) {
					bos.setName(creatRendom());
					bos.setJml(0);
					save(db, bos.getDoc());
				}
				//di hack
				return false;
			}
			return true;
		}
		return false;
	}
	
	public String creatRendom(){
		Random r = new Random();

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZOIUYTR";
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			tmp.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return tmp.toString();
	}
	
	
	/**
	 * Ini di gunkan untuk mengurangi jumlah login
	 * @param db
	 */
	public void minus(ODatabaseDocumentTx db){
		ODocument o=getOne(db, FBos.ID, 2);
		if (o!=null) {
			Bos bos=new Bos(o);
			if (bos.getJml()==0) {
				//belum reg
				ODocument o1=getOne(db, FBos.ID, 1);
				if (o1!=null) {
					Bos bos1=new Bos(o1);
					if (bos1.getJml()<0) {
						Err.showErrMasaTrialSudahHabis();
						System.exit(0);
					}
					int jml=bos1.getJml();
					jml=jml-1;
					bos1.setJml(jml);
					save(db, bos1.getDoc());
				}else{
					factoryFirst(db);
					db.close();
					System.exit(0);
				}
			}else{
				//System.exit(1);
			}
		}else{
			factoryFirst(db);
			db.close();
			System.exit(0);
		}
	}
}
