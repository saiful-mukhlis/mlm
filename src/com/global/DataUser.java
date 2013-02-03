package com.global;


import org.basic.comp.base.LoginDialog;

import com.basic.comp.impl.master.GrpMaster;
import com.basic.comp.impl.master.JenisPekerjaanMaster;
import com.basic.comp.impl.master.UsrMaster;
import com.basic.db.FGrp;
import com.basic.lang.LApp;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.comp.impl.master.PelangganM;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Class ini untuk menampung session dari semua setting dari user
 * 
 * @author toyib
 */
public class DataUser {
	
	public static boolean ROOT=true;
	
	public static int XROOT=0;
	public static int XHAK_AKSES_VIEW=1;
	public static int XHAK_AKSES_ADD=2;
	public static int XHAK_AKSES_EDIT=3;
	public static int XHAK_AKSES_HAPUS=4;
	
	
	public static int XUSR_VIEW=5;
	public static int XUSR_EDIT=6;
	public static int XUSR_ADD=7;
	public static int XUSR_DEL=8;
	

	
	public static int JENIS_VIEW=13;
	public static int JENIS_PEKERJAAN_EDIT=14;
	public static int JENIS_PEKERJAAN_ADD=15;
	public static int JENIS_PEKERJAAN_DEL=16;
	
	
	public static int PAKET_VIEW=17;
	public static int PAKET_EDIT=18;
	public static int PAKET_ADD=19;
	public static int PAKET_DEL=20;
	
	public static int PELANGGAN_VIEW=21;
	public static int PELANGGAN_EDIT=22;
	public static int PELANGGAN_ADD=23;
	public static int PELANGGAN_DEL=24;
	
	

	
	
	
	public static boolean getAkses(int id) {
		if (getGrp() != null && getGrp().field("@class").equals(App.getGrpDao().getClassName())) {
			String kunci = getGrp().field(FGrp.KEY);
			if (kunci!=null&&kunci.indexOf("x" + id + "x") != -1) {
				return true;
			}
		}
		return false;

	}
	
	public static void setAkses(){
		
		ROOT=getAkses(XROOT);
		GrpMaster.VIEW=getAkses(XHAK_AKSES_VIEW);
		GrpMaster.ADD=getAkses(XHAK_AKSES_ADD);
		GrpMaster.EDIT=getAkses(XHAK_AKSES_EDIT);
		GrpMaster.DEL=getAkses(XHAK_AKSES_HAPUS);
		
		UsrMaster.VIEW=getAkses(XUSR_VIEW);
		UsrMaster.EDIT=getAkses(XUSR_EDIT);
		UsrMaster.ADD=getAkses(XUSR_ADD);
		UsrMaster.DEL=getAkses(XUSR_DEL);
		

		JenisPekerjaanMaster.VIEW=getAkses(JENIS_VIEW);
		JenisPekerjaanMaster.EDIT=getAkses(JENIS_PEKERJAAN_EDIT);
		JenisPekerjaanMaster.ADD=getAkses(JENIS_PEKERJAAN_ADD);
		JenisPekerjaanMaster.DEL=getAkses(JENIS_PEKERJAAN_DEL);
		
		PaketM.VIEW=getAkses(PAKET_VIEW);
		PaketM.EDIT=getAkses(PAKET_EDIT);
		PaketM.ADD=getAkses(PAKET_ADD);
		PaketM.DEL=getAkses(PAKET_DEL);
		
		PelangganM.VIEW=getAkses(PELANGGAN_VIEW);
		PelangganM.EDIT=getAkses(PELANGGAN_EDIT);
		PelangganM.ADD=getAkses(PELANGGAN_ADD);
		PelangganM.DEL=getAkses(PELANGGAN_DEL);
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static ODocument grp;

	public static ODocument getGrp() {
		return grp;
	}
	
	
	/**
	 * User yang login
	 */
	private static ODocument usr;

	/**
	 * Menggambil data user yang login
	 * 
	 * @return User
	 */
	public static synchronized ODocument getUsr() {
		while (usr==null) {
			LoginDialog form = new LoginDialog();
			form.build();
			App.showDialog(null, form.getPanel());
		}
		return usr;
	}
	
	public static synchronized ODocument setUsr(ODocument usra) {
		usr=usra;
		return usr;
	}
	public static synchronized ODocument setGrp(ODocument grpa) {
		grp=grpa;
		return grp;
	}

	
}
