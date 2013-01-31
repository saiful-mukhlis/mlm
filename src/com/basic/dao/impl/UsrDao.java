package com.basic.dao.impl;

import com.basic.db.FGrp;
import com.basic.db.FJenisPekerjaan;
import com.basic.db.FUsr;
import com.basic.entity.Grp;
import com.basic.entity.JenisPekerjaan;
import com.basic.entity.Usr;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.dao.abst.DaoAbstract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author toyib
 *
 */
public class UsrDao extends DaoAbstract {

	public UsrDao() {
		super(FUsr.TABLE);
	}

//	public UsrDao setCode(ODocument o, String code) {
//		o.field(FUsr.CODE, code);
//		return this;
//	}
//
//	public UsrDao setCode(ODocument o, TextField code) {
//		setCode(o, code.getText());
//		return this;
//	}
//
//	public String getCode(ODocument o) {
//		String tmp = o.field(FUsr.CODE);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vCode(ODatabaseDocumentTx db, TextField code) {
//		if (!(code.getText().trim().equals("") || code.getText().trim()
//				.equalsIgnoreCase("Auto"))) {
//			long tmp = getCountByColumn(db, FUsr.CODE, code.getText());
//			if (tmp > 0) {
//				App.showErrorDataSudahAda(db, LUsr.CODE);
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public boolean vCode(ODatabaseDocumentTx db, TextField code, ODocument model) {
//		if (!code.getText().equalsIgnoreCase((String) model.field(FUsr.CODE))) {
//			long tmp = getCountByColumn(db, FUsr.CODE, code.getText());
//			if (tmp > 0) {
//				App.showErrorDataSudahAda(db, LUsr.CODE);
//				return false;
//			}
//			if (code.getText().trim().equals("")) {
//				App.showErrorFieldEmpty(db, LUsr.CODE);
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public UsrDao setUsername(ODocument o, String username) {
//		o.field(FUsr.USERNAME, username);
//		return this;
//	}
//
//	public UsrDao setUsername(ODocument o, TextField username) {
//		setUsername(o, username.getText());
//		return this;
//	}
//
//	public String getUsername(ODocument o) {
//		String tmp = o.field(FUsr.USERNAME);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vUsername(ODatabaseDocumentTx db, TextField username) {
//		if (!validate(db, username, LUsr.USERNAME)) {
//			return false;
//		}
//			long tmp=App.getUsrDao().getCountByColumn(db, FUsr.USERNAME, username.getText());
//			if (tmp>0) {
//				App.showErrorDataSudahAda(db, LUsr.USERNAME);
//				return false;
//			}
//		return true;
//	}
//
//	public boolean vUsername(ODatabaseDocumentTx db, TextField username,
//			ODocument model) {
//		if (!validate(db, username, LUsr.USERNAME)) {
//			return false;
//		}
//		if (!username.getText().equalsIgnoreCase((String) model.field(FUsr.USERNAME))) {
//			long tmp=App.getUsrDao().getCountByColumn(db, FUsr.USERNAME, username.getText());
//			if (tmp>0) {
//				App.showErrorDataSudahAda(db, LUsr.USERNAME);
//				return false;
//			}
//			if (username.getText().trim().equals("")) {
//				App.showErrorFieldEmpty(db, LUsr.USERNAME);
//				return false;
//			}
//		}
//		return true;
//	}
//
//	public UsrDao setPassword(ODocument o, String password) {
//		o.field(FUsr.PASSWORD, password);
//		return this;
//	}
//
//	public UsrDao setPassword(ODocument o, TextField password) {
//		setPassword(o, password.getText());
//		return this;
//	}
//
//	public String getPassword(ODocument o) {
//		String tmp = o.field(FUsr.PASSWORD);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vPassword(ODatabaseDocumentTx db, PasswordField password, PasswordField ulang) {
//		
//		if (!validate(db, password, LUsr.PASSWORD)) {
//			return false;
//		}
//		if (!validate(db, ulang, LUsr.ULANG)) {
//			return false;
//		}
//		
//		if (!validate(db, password, ulang, LUsr.ULANG)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vPassword(ODatabaseDocumentTx db, PasswordField password,PasswordField ulang,
//			ODocument model) {
//		
//		if (password.getPassword().length!=0) {
//			if (!validate(db, password, ulang, LUsr.ULANG)) {
//				return false;
//			}
//		}
//		
//		return true;
//	}
//
//	public UsrDao setGrp(ODocument o, int grp) {
//		o.field(FUsr.GRP, grp, OType.INTEGER);
//		return this;
//	}
//
//	public UsrDao setGrp(ODocument o, TextField grp) {
//		Integer tmp = 0;
//		try {
//			tmp = Integer.parseInt(grp.getText());
//		} catch (Exception e) {
//		}
//		setGrp(o, tmp);
//		return this;
//	}
//
//
//	public ODocument getGrp(ODocument o) {
//		if (o.isLazyLoad()) {
//			return o.field(FUsr.GRP);
//		}else{
//			ORecordId id=o.field(FUsr.GRP);
//			if (id!=null) {
//				ODatabaseDocumentTx db = App.getDbd();
//				ODatabaseRecordThreadLocal.INSTANCE.set(db);
//				ODocument tmp= getOneByRid(db, id.toString());
//				db.close();
//				return tmp;
//			}
//			return null;
//		}
//	}
//	
//	public ODocument getGrp(ODatabaseDocumentTx db,ODocument o) {
//		if (o.isLazyLoad()) {
//			return o.field(FUsr.GRP);
//		}else{
//			ORecordId id=o.field(FUsr.GRP);
//			if (id!=null) {
//				ODocument tmp= getOneByRid(db, id.toString());
//				return tmp;
//			}
//			return null;
//		}
//	}
//
//	public String grpToString(ODocument o) {
//		ODocument tmp = getGrp(o);
//		if (tmp == null) {
//			return "";
//		}
//		return App.getGrpDao().getName(tmp);
//	}
//
//	public UsrDao setNama(ODocument o, String nama) {
//		o.field(FUsr.NAMA, nama);
//		return this;
//	}
//
//	public UsrDao setNama(ODocument o, TextField nama) {
//		setNama(o, nama.getText());
//		return this;
//	}
//
//	public String getNama(ODocument o) {
//		String tmp = o.field(FUsr.NAMA);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vNama(ODatabaseDocumentTx db, TextField nama) {
//		if (!validate(db, nama, LUsr.NAMA)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vNama(ODatabaseDocumentTx db, TextField nama, ODocument model) {
//		if (!validate(db, nama, LUsr.NAMA)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setAlamat(ODocument o, String alamat) {
//		o.field(FUsr.ALAMAT, alamat);
//		return this;
//	}
//
//	public UsrDao setAlamat(ODocument o, TextArea alamat) {
//		setAlamat(o, alamat.getText());
//		return this;
//	}
//
//	public String getAlamat(ODocument o) {
//		String tmp = o.field(FUsr.ALAMAT);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vAlamat(ODatabaseDocumentTx db, TextField alamat) {
//		if (!validate(db, alamat, LUsr.ALAMAT)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vAlamat(ODatabaseDocumentTx db, TextField alamat,
//			ODocument model) {
//		if (!validate(db, alamat, LUsr.ALAMAT)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setKota(ODocument o, String kota) {
//		o.field(FUsr.KOTA, kota);
//		return this;
//	}
//
//	public UsrDao setKota(ODocument o, TextField kota) {
//		setKota(o, kota.getText());
//		return this;
//	}
//
//	public String getKota(ODocument o) {
//		String tmp = o.field(FUsr.KOTA);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vKota(ODatabaseDocumentTx db, TextField kota) {
//		if (!validate(db, kota, LUsr.KOTA)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vKota(ODatabaseDocumentTx db, TextField kota, ODocument model) {
//		if (!validate(db, kota, LUsr.KOTA)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setNoIdentitas(ODocument o, String noIdentitas) {
//		o.field(FUsr.NO_IDENTITAS, noIdentitas);
//		return this;
//	}
//
//	public UsrDao setNoIdentitas(ODocument o, TextField noIdentitas) {
//		setNoIdentitas(o, noIdentitas.getText());
//		return this;
//	}
//
//	public String getNoIdentitas(ODocument o) {
//		String tmp = o.field(FUsr.NO_IDENTITAS);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vNoIdentitas(ODatabaseDocumentTx db, TextField noIdentitas) {
//		if (!validate(db, noIdentitas, LUsr.NO_IDENTITAS)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vNoIdentitas(ODatabaseDocumentTx db, TextField noIdentitas,
//			ODocument model) {
//		if (!validate(db, noIdentitas, LUsr.NO_IDENTITAS)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setJenisIdentitas(ODocument o, String jenisIdentitas) {
//		o.field(FUsr.JENIS_IDENTITAS, jenisIdentitas);
//		return this;
//	}
//
//	public UsrDao setJenisIdentitas(ODocument o, TextField jenisIdentitas) {
//		setJenisIdentitas(o, jenisIdentitas.getText());
//		return this;
//	}
//
//	public String getJenisIdentitas(ODocument o) {
//		String tmp = o.field(FUsr.JENIS_IDENTITAS);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vJenisIdentitas(ODatabaseDocumentTx db,
//			TextField jenisIdentitas) {
//		if (!validate(db, jenisIdentitas, LUsr.JENIS_IDENTITAS)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vJenisIdentitas(ODatabaseDocumentTx db,
//			TextField jenisIdentitas, ODocument model) {
//		if (!validate(db, jenisIdentitas, LUsr.JENIS_IDENTITAS)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setKotaLahir(ODocument o, String kotaLahir) {
//		o.field(FUsr.KOTA_LAHIR, kotaLahir);
//		return this;
//	}
//
//	public UsrDao setKotaLahir(ODocument o, TextField kotaLahir) {
//		setKotaLahir(o, kotaLahir.getText());
//		return this;
//	}
//
//	public String getKotaLahir(ODocument o) {
//		String tmp = o.field(FUsr.KOTA_LAHIR);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vKotaLahir(ODatabaseDocumentTx db, TextField kotaLahir) {
//		if (!validate(db, kotaLahir, LUsr.KOTA_LAHIR)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vKotaLahir(ODatabaseDocumentTx db, TextField kotaLahir,
//			ODocument model) {
//		if (!validate(db, kotaLahir, LUsr.KOTA_LAHIR)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setTglLahir(ODocument o, Date tglLahir) {
//		o.field(FUsr.TGL_LAHIR, tglLahir, OType.DATE);
//		return this;
//	}
//
//	public UsrDao setTglLahir(ODocument o, DatePicker tglLahir) {
//		setTglLahir(o, tglLahir.getDate());
//		return this;
//	}
//	
//	public Date getTglLahir(ODocument o){
//		return o.field(FUsr.TGL_LAHIR);
//	}
//
//	public String tglLahirToString(ODocument o) {
//		Date tmp = getTglLahir(o);
//		if (tmp == null) {
//			return "";
//		}
//		return App.dateFormat.format(tmp);
//	}
//
//	public UsrDao setJenisKelamin(ODocument o, int jenisKelamin) {
//		o.field(FUsr.JENIS_KELAMIN, jenisKelamin, OType.INTEGER);
//		return this;
//	}
//
//	public UsrDao setJenisKelamin(ODocument o, TextField jenisKelamin) {
//		int tmp=0;
//		try {
//			tmp=Integer.parseInt(jenisKelamin.getText());
//		} catch (Exception e) {
//		}
//		setJenisKelamin(o, tmp);
//		return this;
//	}
//
//	public String jenisKelaminToString(ODocument o) {
//		Integer tmp = o.field(FUsr.JENIS_KELAMIN);
//		if (tmp == null || tmp==0) {
//			return "";
//		}
//		String[] tmp2=getJenisKelaminData();
//		return tmp2[tmp];
//	}
//	
//	public Integer getJenisKelamin(ODocument o) {
//		Integer tmp = o.field(FUsr.JENIS_KELAMIN);
//		if (tmp == null) {
//			tmp = 0;
//		}
//		return tmp;
//	}
//
//	public boolean vJenisKelamin(ODatabaseDocumentTx db, TextField jenisKelamin) {
//		if (!validate(db, jenisKelamin, LUsr.JENIS_KELAMIN)) {
//			return false;
//		}
//		try {
//			Integer tmp=Integer.parseInt(jenisKelamin.getText());
//		} catch (Exception e) {
//			App.showErrorNotValid(db, LUsr.JENIS_KELAMIN);
//			jenisKelamin.setText("0");
//			jenisKelamin.requestFocus();
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vJenisKelamin(ODatabaseDocumentTx db, TextField jenisKelamin, ODocument model) {
//		return vJenisKelamin(db, jenisKelamin);
//	}
//
//	public UsrDao setNoTelp(ODocument o, String noTelp) {
//		o.field(FUsr.NO_TELP, noTelp);
//		return this;
//	}
//
//	public UsrDao setNoTelp(ODocument o, TextField noTelp) {
//		setNoTelp(o, noTelp.getText());
//		return this;
//	}
//
//	public String getNoTelp(ODocument o) {
//		String tmp = o.field(FUsr.NO_TELP);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vNoTelp(ODatabaseDocumentTx db, TextField noTelp) {
//		if (!validate(db, noTelp, LUsr.NO_TELP)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vNoTelp(ODatabaseDocumentTx db, TextField noTelp,
//			ODocument model) {
//		if (!validate(db, noTelp, LUsr.NO_TELP)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setNoHp1(ODocument o, String noHp1) {
//		o.field(FUsr.NO_HP1, noHp1);
//		return this;
//	}
//
//	public UsrDao setNoHp1(ODocument o, TextField noHp1) {
//		setNoHp1(o, noHp1.getText());
//		return this;
//	}
//
//	public String getNoHp1(ODocument o) {
//		String tmp = o.field(FUsr.NO_HP1);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vNoHp1(ODatabaseDocumentTx db, TextField noHp1) {
//		if (!validate(db, noHp1, LUsr.NO_HP1)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vNoHp1(ODatabaseDocumentTx db, TextField noHp1,
//			ODocument model) {
//		if (!validate(db, noHp1, LUsr.NO_HP1)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setNoHp2(ODocument o, String noHp2) {
//		o.field(FUsr.NO_HP2, noHp2);
//		return this;
//	}
//
//	public UsrDao setNoHp2(ODocument o, TextField noHp2) {
//		setNoHp2(o, noHp2.getText());
//		return this;
//	}
//
//	public String getNoHp2(ODocument o) {
//		String tmp = o.field(FUsr.NO_HP2);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vNoHp2(ODatabaseDocumentTx db, TextField noHp2) {
//		if (!validate(db, noHp2, LUsr.NO_HP2)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vNoHp2(ODatabaseDocumentTx db, TextField noHp2,
//			ODocument model) {
//		if (!validate(db, noHp2, LUsr.NO_HP2)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setPinBb(ODocument o, String pinBb) {
//		o.field(FUsr.PIN_BB, pinBb);
//		return this;
//	}
//
//	public UsrDao setPinBb(ODocument o, TextField pinBb) {
//		setPinBb(o, pinBb.getText());
//		return this;
//	}
//
//	public String getPinBb(ODocument o) {
//		String tmp = o.field(FUsr.PIN_BB);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vPinBb(ODatabaseDocumentTx db, TextField pinBb) {
//		if (!validate(db, pinBb, LUsr.PIN_BB)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vPinBb(ODatabaseDocumentTx db, TextField pinBb,
//			ODocument model) {
//		if (!validate(db, pinBb, LUsr.PIN_BB)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setTglMasuk(ODocument o, Date tglMasuk) {
//		o.field(FUsr.TGL_MASUK, tglMasuk, OType.DATE);
//		return this;
//	}
//
//	public UsrDao setTglMasuk(ODocument o, DatePicker tglMasuk) {
//		setTglMasuk(o, tglMasuk.getDate());
//		return this;
//	}
//
//	public Date getTglMasuk(ODocument o){
//		return o.field(FUsr.TGL_MASUK);
//	}
//	
//	public String tglMasukToString(ODocument o) {
//		Date tmp = o.field(FUsr.TGL_MASUK);
//		if (tmp == null) {
//			return "";
//		}
//		return App.dateFormat.format(tmp);
//	}
//
//	public UsrDao setGaji(ODocument o, BigDecimal gaji) {
//		o.field(FUsr.GAJI, gaji, OType.DECIMAL);
//		return this;
//	}
//
//	public UsrDao setGaji(ODocument o, TextFieldAmount gaji) {
//		
//		if (!gaji.getText().trim().equalsIgnoreCase("")) {
//			//hilangkan koma
//			String tmp0=CharMatcher.is(',').removeFrom(gaji.getText());
//			// hanya boloeh angka dan satu titik
//			if (tmp0.matches("[0-9]*\\.?[0-9]*")) {
//				BigDecimal tmp=new BigDecimal(tmp0);
//				setGaji(o, tmp);
//			}
//		}
//		return this;
//	}
//
//	public BigDecimal getGaji(ODocument o) {
//		BigDecimal tmp = o.field(FUsr.GAJI);
//		return tmp;
//	}
//	
//	public String gajiToString(ODocument o) {
//		BigDecimal tmp = getGaji(o);
//		if (tmp==null) {
//			return "";
//		}
//		BigDecimal tmp2=tmp.setScale(2, RoundingMode.HALF_UP); 
//		return App.paymentFormat2.format(tmp2.doubleValue());
//	}
//	
////	public String getGaji(ODocument o, DecimalFormat format) {
////		return format.format(getGaji(o));
////	}
//
//	public boolean vGaji(ODatabaseDocumentTx db, TextField gaji) {
//		if (!validate(db, gaji, LUsr.GAJI)) {
//			return false;
//		}
//		try {
//			Double tmp = Double.parseDouble(gaji.getText());
//		} catch (Exception e) {
//			App.showErrorNotValid(db, LUsr.GAJI);
//			gaji.setText("0");
//			gaji.requestFocus();
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vGaji(ODatabaseDocumentTx db, TextField gaji, ODocument model) {
//		return vGaji(db, gaji);
//	}
//
//	public UsrDao setJenisPekerjaan(ODocument o, ODocument jenisPekerjaan) {
//		o.field(FUsr.JENIS_PEKERJAAN, jenisPekerjaan, OType.LINK);
//		return this;
//	}
//
//
//	public ODocument getJenisPekerjaan(ODocument o) {
//		if (o.isLazyLoad()) {
//			return o.field(FUsr.JENIS_PEKERJAAN);
//		}else{
//			ORecordId id=o.field(FUsr.JENIS_PEKERJAAN);
//			if (id!=null) {
//				ODatabaseDocumentTx db = App.getDbd();
//				ODatabaseRecordThreadLocal.INSTANCE.set(db);
//				ODocument tmp= getOneByRid(db, id.toString());
//				db.close();
//				return tmp;
//			}
//			return null;
//		}
//	}
//	
//	public ODocument getJenisPekerjaan(ODatabaseDocumentTx db,ODocument o) {
//		if (o.isLazyLoad()) {
//			return o.field(FUsr.JENIS_PEKERJAAN);
//		}else{
//			ORecordId id=o.field(FUsr.JENIS_PEKERJAAN);
//			if (id!=null) {
//				ODocument tmp= getOneByRid(db, id.toString());
//				return tmp;
//			}
//			return null;
//		}
//	}
//
//	public String jenisPekerjaanToString(ODocument o) {
//		ODocument tmp = getJenisPekerjaan(o);
//		if (tmp == null) {
//			return "";
//		}
//		return App.getJenisPekerjaanDao().getNama(tmp);
//	}
//
//
//
//	public UsrDao setPendidikanTerakhir(ODocument o, String pendidikanTerakhir) {
//		o.field(FUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir);
//		return this;
//	}
//
//	public UsrDao setPendidikanTerakhir(ODocument o,
//			TextField pendidikanTerakhir) {
//		setPendidikanTerakhir(o, pendidikanTerakhir.getText());
//		return this;
//	}
//
//	public String getPendidikanTerakhir(ODocument o) {
//		String tmp = o.field(FUsr.PENDIDIKAN_TERAKHIR);
//		if (tmp == null) {
//			tmp = "";
//		}
//		return tmp;
//	}
//
//	public boolean vPendidikanTerakhir(ODatabaseDocumentTx db,
//			TextField pendidikanTerakhir) {
//		if (!validate(db, pendidikanTerakhir, LUsr.PENDIDIKAN_TERAKHIR)) {
//			return false;
//		}
//		return true;
//	}
//
//	public boolean vPendidikanTerakhir(ODatabaseDocumentTx db,
//			TextField pendidikanTerakhir, ODocument model) {
//		if (!validate(db, pendidikanTerakhir, LUsr.PENDIDIKAN_TERAKHIR)) {
//			return false;
//		}
//		return true;
//	}
//
//	public UsrDao setStatus(ODocument o, int status) {
//		o.field(FUsr.STATUS, status, OType.INTEGER);
//		return this;
//	}
//
//	public UsrDao setStatus(ODocument o, TextField status) {
//		Integer tmp = 0;
//		try {
//			tmp = Integer.parseInt(status.getText());
//		} catch (Exception e) {
//		}
//		setStatus(o, tmp);
//		return this;
//	}
//
//	public String statusToString(ODocument o) {
//		Integer tmp = o.field(FUsr.STATUS);
//		if (tmp == null) {
//			return "";
//		}
//		String[] tmp2 = getStatusData();
//		return tmp2[tmp];
//	}
//
//	public Integer getStatus(ODocument o) {
//		Integer tmp = o.field(FUsr.STATUS);
//		if (tmp == null) {
//			return 0;
//		}
//		return tmp;
//	}

	// public String getCode(ODocument o){
	// String tmp=o.field(Usr.CODE);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getUsername(ODocument o){
	// String tmp=o.field(Usr.USERNAME);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getPassword(ODocument o){
	// String tmp=o.field(Usr.PASSWORD);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getGroup(ODocument o){
	// return o.field(Usr.GROUP);
	// }
	//
	// public String getNama(ODocument o){
	// String tmp=o.field(Usr.NAMA);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getAlamat(ODocument o){
	// String tmp=o.field(Usr.ALAMAT);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getKota(ODocument o){
	// String tmp=o.field(Usr.KOTA);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getNoIdentitas(ODocument o){
	// String tmp=o.field(Usr.NO_IDENTITAS);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getJenisIdentitas(ODocument o){
	// String tmp=o.field(Usr.JENIS_IDENTITAS);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getJenisKelamin(ODocument o){
	// Integer tmp=o.field(Usr.JENIS_KELAMIN);
	// if (tmp==null || tmp==0) {
	// return "";
	// }
	// String[] jk=getJenisKelaminData();
	// return jk[tmp];
	// }
	//
	// public int getJenisKelaminIndex(ODocument o){
	// Integer tmp=o.field(Usr.JENIS_KELAMIN);
	// if (tmp==null) {
	// return 0;
	// }
	// return tmp;
	// }
	//
	// public String getKotaLahir(ODocument o){
	// String tmp=o.field(Usr.KOTA_LAHIR);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getTglLahir(ODocument o){
	// Date tmp=o.field(Usr.TGL_LAHIR);
	// if (tmp==null) {
	// return "";
	// }
	// return App.dateFormat.format(tmp);
	// }
	//
	//
	// public String getNoTelp(ODocument o){
	// String tmp=o.field(Usr.NO_TELP);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getNoHp1(ODocument o){
	// String tmp=o.field(Usr.NO_HP1);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getNoHp2(ODocument o){
	// String tmp=o.field(Usr.NO_HP2);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getPinBb(ODocument o){
	// String tmp=o.field(Usr.PIN_BB);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public ODocument getLogdb(ODocument o){
	// return o.field(Usr.LOGDB);
	// }
	//
	// public String getTglMasuk(ODocument o){
	// Date tmp=o.field(Usr.TGL_MASUK);
	// if (tmp==null) {
	// return "";
	// }
	// return App.dateFormat.format(tmp);
	// }
	//
	// public String getGaji(ODocument o){
	// String tmp=o.field(Usr.GAJI);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public ODocumentToString getJenisPekerjaan(ODocument tmpjenisPekejaan){
	// // ODocument tmp=o.field(Usr.JENIS_PEKERJAAN);
	// if (tmpjenisPekejaan==null) {
	// return null;
	// }
	// ODocumentToString tmp2=new ODocumentToString(App.getJenisPekerjaanDao(),
	// tmpjenisPekejaan);
	// return tmp2;
	// }
	//
	// public String getPendidikanTerakhir(ODocument o){
	// String tmp=o.field(Usr.PENDIDIKAN_TERAKHIR);
	// if (tmp==null) {
	// tmp="";
	// }
	// return tmp;
	// }
	//
	// public String getStatus(ODocument o){
	// Integer tmp=o.field(Usr.STATUS);
	// if (tmp==null || tmp==0) {
	// return "";
	// }
	// String [] d=getStatusData();
	// return d[tmp];
	// }
	//
	// public int getStatusIndex(ODocument o){
	// Integer tmp=o.field(Usr.STATUS);
	// if (tmp==null) {
	// return 0;
	// }
	// return tmp;
	// }
	//
	//
	//
	//
	//
	//
	// public UsrDao setCode(ODocument o, String code){
	// o.field(Usr.CODE, code);
	// return this;
	// }
	//
	// public UsrDao setUsername(ODocument o, String username){
	// o.field(Usr.USERNAME, username);
	// return this;
	// }
	//
	// public UsrDao setPassword(ODocument o, String password){
	// o.field(Usr.PASSWORD, password);
	// return this;
	// }
	//
	public UsrDao setGrp(ODocument o, ODocument grp) {
		o.field(FUsr.GRP, grp, OType.LINK);
		return this;
	}
	//
	// public UsrDao setNama(ODocument o, String nama){
	// o.field(Usr.NAMA, nama);
	// return this;
	// }
	//
	// public UsrDao setAlamat(ODocument o, String alamat){
	// o.field(Usr.ALAMAT, alamat);
	// return this;
	// }
	//
	// public UsrDao setKota(ODocument o, String kota){
	// o.field(Usr.KOTA, kota);
	// return this;
	// }
	//
	// public UsrDao setNoIdentitas(ODocument o, String noIdentitas){
	// o.field(Usr.NO_IDENTITAS, noIdentitas);
	// return this;
	// }
	//
	// public UsrDao setJenisIdentitas(ODocument o, String jenisIdentitas){
	// o.field(Usr.JENIS_IDENTITAS, jenisIdentitas);
	// return this;
	// }
	//
	// public UsrDao setJenisKelamin(ODocument o, int jenisKelamin){
	// o.field(Usr.JENIS_KELAMIN, jenisKelamin, OType.INTEGER);
	// return this;
	// }
	//
	// public UsrDao setKotaLahir(ODocument o, String kotaLahir){
	// o.field(Usr.KOTA_LAHIR, kotaLahir);
	// return this;
	// }
	//
	// public UsrDao setTglLahir(ODocument o, Date tglLahir){
	// o.field(Usr.TGL_LAHIR, tglLahir, OType.DATE);
	// return this;
	// }
	//
	//
	// public UsrDao setNoTelp(ODocument o, String noTelp){
	// o.field(Usr.NO_TELP, noTelp);
	// return this;
	// }
	//
	// public UsrDao setNoHp1(ODocument o, String noHp1){
	// o.field(Usr.NO_HP1, noHp1);
	// return this;
	// }
	//
	// public UsrDao setNoHp2(ODocument o, String noHp2){
	// o.field(Usr.NO_HP2, noHp2);
	// return this;
	// }
	//
	// public UsrDao setPinBb(ODocument o, String pinBb){
	// o.field(Usr.PIN_BB, pinBb);
	// return this;
	// }
	//
//	 public UsrDao setLogdb(ODocument o, ODocument logdb){
//	 o.field(Usr.LOGDB, logdb, OType.EMBEDDED);
//	 return this;
//	 }
	//
	// public UsrDao setTglMasuk(ODocument o, Date tglMasuk){
	// o.field(Usr.TGL_MASUK, tglMasuk, OType.DATE);
	// return this;
	// }
	//
	// public UsrDao setGaji(ODocument o, String gaji){
	// o.field(Usr.GAJI, gaji);
	// return this;
	// }
	//
	// public UsrDao setJenisPekerjaan(ODocument o, ODocument jenisPekerjaan){
	// o.field(Usr.JENIS_PEKERJAAN, jenisPekerjaan, OType.LINK);
	// return this;
	// }
	//
	// public UsrDao setPendidikanTerakhir(ODocument o, String
	// pendidikanTerakhir){
	// o.field(Usr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir);
	// return this;
	// }
	//
	// public UsrDao setStatus(ODocument o, int status){
	// o.field(Usr.STATUS, status, OType.INTEGER);
	// return this;
	// }

	public String[] getStatusData() {
		String a1 = App.getT("Pilih Status Pegawai");
		String ta = App.getT("Tidak Aktif");
		String a = App.getT("Aktif");
		String[] stringStatus = { a1, ta, a };
		return stringStatus;
	}

	public String[] getJenisKelaminData() {
		String a = App.getT("Pilih Jenis Kelamin");
		String l = App.getT("Laki - laki");
		String p = App.getT("Perempuan");
		String[] jenisKelamin = { a, l, p };
		return jenisKelamin;
	}

	public Object[] getGrpData(ODatabaseDocumentTx db) {
		GrpDao grpDao = App.getGrpDao();
		List<ODocument> grps = grpDao.getAllAsc(db, FGrp.NAME);
		
		List<Object> grps2=new ArrayList<>(); 
		Grp g=new Grp();
		g.setName(App.getT("Pilih Hak Akses"));
		grps2.add(g);
		for (ODocument o : grps) {
			grps2.add(new Grp(o));
		}
		

		return grps2.toArray();
	}

	public Object[] getJenisPekerjaanData(ODatabaseDocumentTx db) {
		JenisPekerjaanDao dao = App.getJenisPekerjaanDao();
		List<ODocument> jps = dao.getAllAsc(db, FJenisPekerjaan.NAMA);
		List<Object> jps2=new ArrayList<>();
		JenisPekerjaan j=new JenisPekerjaan();
		j.setNama(App.getT("Pilih Jenis Pekerjaan"));
		jps2.add(j);
		for (ODocument o : jps) {
			jps2.add(new JenisPekerjaan(o));
		}
		
		return jps2.toArray();
	}

	// public ODocument factoryModel( String username,
	// String passwordBeforMd5, String nikName, byte status, ODocument grp,
	// ODocument logdb) {
	// ODocument doc = new ODocument(getClassName());
	// doc.field(this.username, username);
	//
	// try {
	// doc.field(this.password, new UtilAccount().md5(passwordBeforMd5));
	// } catch (Exception e) {
	// App.printErr(e);
	// }
	// doc.field(this.nikName, nikName);
	// doc.field(this.status, status);
	// doc.field(this.grp, grp, OType.LINK);
	// doc.field(this.logdb, logdb, OType.EMBEDDED);
	// return doc;
	//
	// }
	
	public UsrDao setCreateBy(ODocument o, ODocument usr){
		if (usr!=null) {
			o.field(FUsr.CREATE_BY, usr, OType.LINK);
		}
		return this;
	}
	public UsrDao setCreateAt(ODocument o, Date createAt){
		if (createAt!=null) {
			o.field(FUsr.CREATE_AT, createAt , OType.DATETIME);
		}
		return this;
	}
	
	public UsrDao setUpdateBy(ODocument o, ODocument usr){
		if (usr!=null) {
			o.field(FUsr.UPDATE_BY, usr, OType.LINK);
		}
		return this;
	}
	public UsrDao setUpdateAt(ODocument o, Date updateAt){
		if (updateAt!=null) {
			o.field(FUsr.UPDATE_AT, updateAt , OType.DATETIME);
		}
		return this;
	}

	/**
	 * di run saat setelah pembuatan database, sebelum pembuatan group
	 * @param db
	 */
	public void factoryModelFirst(ODatabaseDocumentTx db) {
		Usr usr=new Usr();
		usr.setNama("Administrator");
		usr.setUsername("admin");
		usr.setPassword("admin");
		usr.setCreateAt(new Date());
		
		save(db, usr.getDoc());
		
		usr.setCreateBy(usr.getDoc());
		
		save(db, usr.getDoc());
		
	}


//	@Override
//	public List<ODocument> getAllSearch(ODatabaseDocumentTx db, ODocument o) {
//		List<Object> values=new ArrayList<>();
//		StringBuilder sql=new StringBuilder("select * from ");
//		sql.append(getClassName());
//		sql.append(" where ");
//		
//		String tmp=getCode(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.CODE);
//			sql.append(" = ? ");
//			values.add(tmp);
//		}
//		
//		tmp=getUsername(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.USERNAME);
//			sql.append(" = ? ");
//			values.add("%"+tmp+"%");
//		}
//		
//		tmp=getGrp(o);
//		if (tmp!=null) {
//			sql.append(Usr.GRP);
//			sql.append(" = ? ");
//			values.add(tmp);
//		}
//		
//		tmp=getNama(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.NAMA);
//			sql.append(" = ? ");
//			values.add("%"+tmp+"%");
//		}
//		
//		
//		if (values.size()==0) {
//			return new ArrayList<>();
//		}
//		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
//		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(values.toArray());
//		return result;
//	}
//	
//	@Override
//	public List<ODocument> getAllSearch(ODatabaseDocumentTx db, ODocument o, int start, int end) {
//		List<Object> values=new ArrayList<>();
//		StringBuilder sql=new StringBuilder("select * from ");
//		sql.append(getClassName());
//		sql.append(" where ");
//		
//		String tmp=getCode(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.CODE);
//			sql.append(" = ? ");
//			values.add(tmp);
//		}
//		
//		tmp=getUsername(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.USERNAME);
//			sql.append(" like ? ");
//			values.add("%"+tmp+"%");
//		}
//		
//		tmp=getGrp(o);
//		if (tmp!=null) {
//			sql.append(Usr.GRP);
//			sql.append(" = ? ");
//			values.add(tmp);
//		}
//		
//		tmp=getNama(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.NAMA);
//			sql.append(" like ? ");
//			values.add("%"+tmp+"%");
//		}
//		sql.append(" skip "+start+" limit "+end);
//		
//		if (values.size()==0) {
//			return new ArrayList<>();
//		}
//		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
//		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(values.toArray());
//		return result;
//	}
//	
//	
//	@Override
//	public long getCountSearch(ODatabaseDocumentTx db, ODocument o) {
//		StringBuilder sql=new StringBuilder("select count(*) as x from ");
//		sql.append(getClassName());
//		sql.append(" where "); 
//		List<Object> values=new ArrayList<>();
//		String tmp=getCode(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.CODE);
//			sql.append(" = ? ");
//			values.add(tmp);
//		}
//		
//		tmp=getUsername(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.USERNAME);
//			sql.append(" = ? ");
//			values.add("%"+tmp+"%");
//		}
//		
//		tmp=getGrp(o);
//		if (tmp!=null) {
//			sql.append(Usr.GRP);
//			sql.append(" = ? ");
//			values.add(tmp);
//		}
//		
//		tmp=getNama(o);
//		if (!tmp.equalsIgnoreCase("")) {
//			sql.append(Usr.NAMA);
//			sql.append(" = ? ");
//			values.add("%"+tmp+"%");
//		}
//		
//		
//		if (values.size()==0) {
//			return 0;
//		}
//		App.info(sql.toString());
//		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
//		List<ODocument> result = db.command(query).execute(values.toArray());
//		try {
//			long count = Long.parseLong(result.get(0).field("x").toString());
//			return count;
//		} catch (Exception e) {
//			App.printErr(e);
//			return 0;
//		}
//	}
	
	
	

}
