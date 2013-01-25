package com.basic.view.add;



import java.util.Date;

import org.basic.comp.abst.AddPanelAbs;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.DatePicker;
import org.basic.comp.base.PasswordField;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;
import com.basic.dao.impl.UsrDao;
import com.basic.db.FUsr;
import com.basic.entity.Grp;
import com.basic.entity.JenisPekerjaan;
import com.basic.entity.Usr;
import com.basic.lang.LGrp;
import com.basic.lang.LUsr;
import com.global.App;
import com.global.DataUser;
import com.global.Focus;
import com.global.util.Account;
import com.global.util.Validate;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;



public class UsrDN extends AddPanelAbs {
	protected TextField code;
	protected TextField username;
	protected TextField nama;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField noIdentitas;
	protected TextField jenisIdentitas;
	protected TextField kotaLahir;
	protected TextField noTelp;
	protected TextField noHp1;
	protected TextField noHp2;
	protected TextField pinBb;
	protected TextFieldAmount gaji;
	protected TextField pendidikanTerakhir;
	protected PasswordField password;
	protected PasswordField ulang;
	protected DatePicker tglLahir;
	protected DatePicker tglMasuk;
	protected ComboBox jenisKelamin;
	protected ComboBox jenisPekerjaan;
	protected ComboBox status;
	protected ComboBox grp;
	
	@Override
	public void actionReset() {
		code.setText("Auto");
		username.setText("");
		grp.setSelectedIndex(0);
		nama.setText("");
		alamat.setText("");
		
		kota.setText("");
		noIdentitas.setText("");
		jenisIdentitas.setText("");
		kotaLahir.setText("");
		tglLahir.setDate(new Date());
		jenisKelamin.setSelectedIndex(0);
		noTelp.setText("");
		noHp1.setText("");
		noHp2.setText("");
		pinBb.setText("");
		tglMasuk.setDate(new Date());
		gaji.setText("");
		jenisPekerjaan.setSelectedIndex(0);
		pendidikanTerakhir.setText("");
		status.setSelectedIndex(0);
		requestDefaultFocus();
	}

	
	
	public void requestDefaultFocus() {
		username.requestFocus();
	}



	public void init() {
		super.init();
		labelTitle.setText(App.getT("Tambah Pegawai"));
		code=new TextField();
		username=new TextField();
		nama=new TextField();
		alamat=new TextArea();
		salamat=new ScrollPane(alamat);
		
		kota=new TextField();
		noIdentitas=new TextField();
		jenisIdentitas=new TextField();
		kotaLahir=new TextField();
		noTelp=new TextField();
		noHp1=new TextField();
		noHp2=new TextField();
		pinBb=new TextField();
		gaji=new TextFieldAmount();
		pendidikanTerakhir=new TextField();
		
		password=new PasswordField();
		ulang=new PasswordField();
		tglLahir=new DatePicker();
		tglMasuk=new DatePicker();

		
		
	}
	
	
	
	
	
	@Override
	public void build(ODatabaseDocumentTx db) {
		init();
		jenisKelamin=new ComboBox(App.getUsrDao().getJenisKelaminData());
		status=new ComboBox(App.getUsrDao().getStatusData());
		grp=new ComboBox(App.getUsrDao().getGrpData(db));
		jenisPekerjaan=new ComboBox(App.getUsrDao().getJenisPekerjaanData(db));
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		col.append("r:p,10px,f:100px:g,");
		col.append("30px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		//row.append("f:40dlu:g,15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LGrp.CODE, code, 2, 2, 1);
		b.append( LUsr.USERNAME, username, 2, 4, 5);
		
		b.append( LUsr.PASSWORD, password, 2, 6, 5);
		b.append( LUsr.ULANG, ulang, 2, 8, 5);
		
		b.append( LUsr.GRP, grp, 2, 10, 3);
		b.append( LUsr.STATUS, status, 2, 12, 3);

		b.append( LUsr.TGL_MASUK, tglMasuk, 2, 16, 1);
		b.append( LUsr.GAJI, gaji, 2, 18, 5);
		b.append( LUsr.JENIS_PEKERJAAN, jenisPekerjaan, 2, 20, 3);
		
		
		
		
		b.append( LUsr.NAMA, nama, 10, 2, 5);
		b.append( LUsr.JENIS_IDENTITAS, jenisIdentitas, 10, 4, 1);
		b.append( LUsr.NO_IDENTITAS, noIdentitas, 14, 4, 1);
		b.append( LUsr.KOTA_LAHIR, kotaLahir, 10, 6, 1);
		b.append( LUsr.TGL_LAHIR, tglLahir , 14, 6, 1);
		b.append( LUsr.JENIS_KELAMIN, jenisKelamin, 10, 8, 2);
		b.append( LUsr.ALAMAT, salamat, 10, 10, 5, 5);
		b.append( LUsr.KOTA, kota, 10, 16, 5);
		b.append( LUsr.NO_TELP, noTelp, 10, 18, 1);
		b.append( LUsr.PIN_BB, pinBb, 14, 18, 1);
		b.append( LUsr.NO_HP1, noHp1, 10, 20, 1);
		b.append( LUsr.NO_HP2, noHp2, 14, 20, 1);
		b.append( LUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir, 10, 22, 5);
		
		
		panelForm=b.getPanel();
		
		
		
		Focus.enter(code, username);
		Focus.enter(username, password);
		Focus.enter(password, ulang);
		Focus.enter(ulang, grp);
//		Focus.enter(status, grp);
		Focus.enter(grp, status);
		Focus.enter(status, nama);

		Focus.enter(nama, jenisIdentitas);
		Focus.enter(jenisIdentitas, noIdentitas);
		Focus.enter(noIdentitas, kotaLahir);
		Focus.enter(kotaLahir, tglLahir.getEditor());
		Focus.enter(tglLahir.getEditor(), jenisKelamin);
		Focus.enter(jenisKelamin, alamat);
		Focus.enter(alamat, kota);
		Focus.enter(kota, noTelp);
		Focus.enter(noTelp, pinBb);
		Focus.enter(pinBb, noHp1);
		Focus.enter(noHp1, noHp2);
		Focus.enter(noHp2, pendidikanTerakhir);
		Focus.enter(pendidikanTerakhir, tglMasuk.getEditor());

		Focus.enter(gaji, jenisPekerjaan);
		Focus.enter(jenisPekerjaan, save);
		
//		Focus.enter(save, reset);
		Focus.enter(reset, code);
		
		
		super.build(db);
	}



	@Override
	public void save(ODatabaseDocumentTx db) {
		UsrDao d=App.getUsrDao();
		
		Usr u = new Usr();
		u.setCode(code.getText().trim());
		u.setUsername(username.getText().trim());
		u.setGrp((Grp) grp.getSelectedItem());
		u.setNama(nama.getText().trim());
		u.setAlamat(alamat.getText().trim());
		u.setKota(kota.getText().trim());
		u.setNoIdentitas(noIdentitas.getText().trim());
		u.setJenisIdentitas(jenisIdentitas.getText().trim());
		u.setKotaLahir(kotaLahir.getText().trim());
		u.setTglLahir(tglLahir.getDate());
		u.setJenisKelamin(jenisKelamin.getSelectedIndex());
		u.setNoTelp(noTelp.getText().trim());
		u.setNoHp1(noHp1.getText().trim());
		u.setNoHp2(noHp2.getText().trim());
		u.setPinBb(pinBb.getText().trim());
		u.setTglMasuk(tglMasuk.getDate());
		u.setGaji(TextFieldAmount.getValue(gaji));
		u.setJenisPekerjaan((JenisPekerjaan) jenisPekerjaan.getSelectedItem());
		u.setPendidikanTerakhir(pendidikanTerakhir.getText().trim());
		u.setStatus(status.getSelectedIndex());
		try {
			u.setPassword(Account.md5(new String(password.getPassword())));
		} catch (Exception e) {
			App.printErr(e);
		}
		
		u.setCreateAt(new Date());
		u.setCreateBy(DataUser.getUsr());
		
		d.save(db, u.getDoc());
		
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		
		UsrDao d=App.getUsrDao();
		
		if (!Validate.validateUnique(db, d, code, LUsr.CODE, FUsr.CODE)) {
			return false;
		}
		
		if (!Validate.validateStringEmpty(code, LUsr.CODE)) {
			return false;
		}
		
		if (!Validate.validateStringEmpty(username, LUsr.USERNAME)) {
			return false;
		}
		
		if (!Validate.validateStringEmpty(nama, LUsr.NAMA)) {
			return false;
		}
		
		if (!Validate.validatePassword(password, ulang, LUsr.PASSWORD, LUsr.ULANG)) {
			return false;
		}
		
		if (!Validate.validateNoSelected(grp, LUsr.GRP)) {
			return false;
		}
		
		if (!Validate.validateNoSelected(status, LUsr.STATUS)) {
			return false;
		}
		
		
		return true;
		
		
	}
	
}
