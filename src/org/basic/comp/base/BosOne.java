package org.basic.comp.base;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class BosOne {
	private String loc = "";

	public void seta(ODatabaseDocumentTx db) {
//		ODocument o=App.getBosDao().getOne(db, FBos.ID, 2);
//		
//		if (o != null) {
//			int jmlStatus=App.getBosDao().getJml(o);
//			String namaKode=App.getBosDao().getName(o);
//			if (jmlStatus != 0) {
//				Preferences userPref = Preferences.userRoot();
//				String x = userPref.get("ortptnk", "x");
//				
//				if (namaKode != null || !namaKode.equalsIgnoreCase("")) {
//					if (!x.equalsIgnoreCase(namaKode)) {
//						App.getBosDao().setJml(o, 0);
//						App.getBosDao().setName(o, "");
//						o.save();
//					}
//				}
//			} else {
//				ODocument o1=App.getBosDao().getOne(db, FBos.ID, 1);
//				if (o1 != null) {
//					int jumlahLogin=App.getBosDao().getJml(o1);
//					setLoc(" [Trial Version after " + jumlahLogin + " Login]");
//				}else{
//					App.getBosDao().factoryFirst(db);
//					setLoc(" [Trial Version after 20 Login]");
//				}
//			}
//
//			if (namaKode == null || namaKode.equalsIgnoreCase("")) {
//
//				App.getBosDao().setName(o, App.getBosDao().creatRendom());
//				App.getBosDao().setJml(o, 0);
//				o.save();
//
//			}
//		} else {
//			
//			App.getBosDao().factoryFirst(db);
//			setLoc(" [Trial Version after 20 Login]");
//
//		}
		
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
}
