package com.basic.view.detail;

import com.basic.entity.Grp;
import com.basic.entity.JenisPekerjaan;
import com.basic.entity.Usr;
import com.basic.lang.LUsr;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.abst.DetailAbs;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;

import java.math.BigDecimal;
import java.util.Date;

public class UsrDetail extends DetailAbs{
	protected TextField code;
	protected TextField username;
	protected TextField grp;
	protected TextField nama;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField noIdentitas;
	protected TextField jenisIdentitas;
	protected TextField kotaLahir;
	protected TextField tglLahir;
	protected TextField jenisKelamin;
	protected TextField noTelp;
	protected TextField noHp1;
	protected TextField noHp2;
	protected TextField pinBb;
	protected TextField tglMasuk;
	protected TextFieldAmount gaji;
	protected TextField jenisPekerjaan;
	protected TextField pendidikanTerakhir;
	protected TextField status;
	
	protected String title;
	@Override
	public void init() {
		super.init();
		
		
		
		code=new TextField();
		username=new TextField();
		grp=new TextField();
		nama=new TextField();
		alamat=new TextArea();
		salamat=new ScrollPane(alamat);
		
		kota=new TextField();
		noIdentitas=new TextField();
		jenisIdentitas=new TextField();
		kotaLahir=new TextField();
		tglLahir=new TextField();
		jenisKelamin=new TextField();
		noTelp=new TextField();
		noHp1=new TextField();
		noHp2=new TextField();
		pinBb=new TextField();
		tglMasuk=new TextField();
		gaji=new TextFieldAmount();
		jenisPekerjaan=new TextField();
		pendidikanTerakhir=new TextField();
		status=new TextField();
	}
	@Override
	public void build(ODatabaseDocumentTx db) {
		init();
		title=App.getT("Detail Data Pegawai");
		label.setText(title);
		
		
		code.setBackground(App.whiteSmoke);
		username.setBackground(App.whiteSmoke);
		grp.setBackground(App.whiteSmoke);
		nama.setBackground(App.whiteSmoke);
		alamat.setBackground(App.whiteSmoke);
		salamat.setBackground(App.whiteSmoke);
		
		kota.setBackground(App.whiteSmoke);
		noIdentitas.setBackground(App.whiteSmoke);
		jenisIdentitas.setBackground(App.whiteSmoke);
		kotaLahir.setBackground(App.whiteSmoke);
		tglLahir.setBackground(App.whiteSmoke);
		jenisKelamin.setBackground(App.whiteSmoke);
		noTelp.setBackground(App.whiteSmoke);
		noHp1.setBackground(App.whiteSmoke);
		noHp2.setBackground(App.whiteSmoke);
		pinBb.setBackground(App.whiteSmoke);
		tglMasuk.setBackground(App.whiteSmoke);
		gaji.setBackground(App.whiteSmoke);
		jenisPekerjaan.setBackground(App.whiteSmoke);
		pendidikanTerakhir.setBackground(App.whiteSmoke);
		status.setBackground(App.whiteSmoke);
		
		
		code.setEditable(false);
		username.setEditable(false);
		grp.setEditable(false);
		nama.setEditable(false);
		alamat.setEditable(false);
		
		kota.setEditable(false);
		noIdentitas.setEditable(false);
		jenisIdentitas.setEditable(false);
		kotaLahir.setEditable(false);
		tglLahir.setEditable(false);
		jenisKelamin.setEditable(false);
		noTelp.setEditable(false);
		noHp1.setEditable(false);
		noHp2.setEditable(false);
		pinBb.setEditable(false);
		tglMasuk.setEditable(false);
		gaji.setEditable(false);
		jenisPekerjaan.setEditable(false);
		pendidikanTerakhir.setEditable(false);
		status.setEditable(false);
		
		
		
		
		
		
		
		FormLayout layout = new FormLayout(
				//"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px," +
						"r:p,3dlu,	f:max(50px;p):g,3dlu,	f:max(50px;p):g,3dlu,	10dlu,	r:p,3dlu,	f:max(50px;p):g,3dlu,	r:p,3dlu,	f:max(50px;p):g,3dlu,",
				

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  2dlu,   t:30dlu,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,10dlu");

		 layout.setColumnGroups(new int[][] { { 3,5,10,14 } });
		 FormBuilder builder = new FormBuilder(layout, true);

		builder.append( LUsr.CODE, code, 1, 1, 1);
		builder.append( LUsr.USERNAME, username, 1, 3, 3);
		
		builder.append( LUsr.GRP, grp, 1, 5, 3);
		builder.append( LUsr.STATUS, status, 1, 7, 3);

		builder.append( LUsr.TGL_MASUK, tglMasuk, 1, 12, 3);
		builder.append( LUsr.GAJI, gaji, 1, 14, 3);
		builder.append( LUsr.JENIS_PEKERJAAN, jenisPekerjaan, 1, 16, 3);
		
		
		
		
		builder.append( LUsr.NAMA, nama, 8, 1, 5);
		builder.append( LUsr.JENIS_IDENTITAS, jenisIdentitas, 8, 3, 1);
		builder.append( LUsr.NO_IDENTITAS, noIdentitas, 12, 3, 1);
		builder.append( LUsr.KOTA_LAHIR, kotaLahir, 8, 5, 1);
		builder.append( LUsr.TGL_LAHIR, tglLahir , 12, 5, 1);
		builder.append( LUsr.JENIS_KELAMIN, jenisKelamin, 8, 7, 1);
		builder.appendTa( LUsr.ALAMAT, salamat, 8, 9, 5, 4);
		builder.append( LUsr.KOTA, kota, 8, 14, 5);
		builder.append( LUsr.NO_TELP, noTelp, 8, 16, 1);
		builder.append( LUsr.PIN_BB, pinBb, 12, 16, 1);
		builder.append( LUsr.NO_HP1, noHp1, 8, 18, 1);
		builder.append( LUsr.NO_HP2, noHp2, 12, 18, 1);
		builder.append( LUsr.PENDIDIKAN_TERAKHIR, pendidikanTerakhir, 8, 20, 5);
		
		panelForm=builder.getPanel();
		scrollPane=new ScrollPane(panelForm); 
		
		super.build(db);
		
	}
	
	public void load(Object o){
		if (o==null) {
			label.setText(title);
			code.setText("");
			username.setText("");
			grp.setText("");
			nama.setText("");
			alamat.setText("");
			
			kota.setText("");
			noIdentitas.setText("");
			jenisIdentitas.setText("");
			kotaLahir.setText("");
			tglLahir.setText("");
			jenisKelamin.setText("");
			noTelp.setText("");
			noHp1.setText("");
			noHp2.setText("");
			pinBb.setText("");
			tglMasuk.setText("");
			gaji.setText("");
			jenisPekerjaan.setText("");
			pendidikanTerakhir.setText("");
			status.setText("");
		}
		else if (o instanceof Usr) {
			Usr u=(Usr) o;
			code.setText(u.getCode());
			username.setText(u.getUsername());
			Grp g=u.getGrp();
			if (g!=null) {
				grp.setText(g.getName());
			}else{
				grp.setText("");
			}
			label.setText(title+" "+u.getNama());
			nama.setText(u.getNama());
			alamat.setText(u.getAlamat());
			
			kota.setText(u.getKota());
			noIdentitas.setText(u.getNoIdentitas());
			jenisIdentitas.setText(u.getJenisIdentitas());
			kotaLahir.setText(u.getKotaLahir());
			Date d=u.getTglLahir();
			if (d!=null) {
				tglLahir.setText(App.dateFormat.format(d));
			}else{
				tglLahir.setText("");
			}
			int jenisKelaminIndex=u.getJenisKelamin();
			if (jenisKelaminIndex==0) {
				jenisKelamin.setText("");
			}else{
				String [] jenisKelaminData=App.getUsrDao().getJenisKelaminData();
				jenisKelamin.setText(jenisKelaminData[jenisKelaminIndex]);
			}
			noTelp.setText(u.getNoTelp());
			noHp1.setText(u.getNoHp1());
			noHp2.setText(u.getNoHp2());
			pinBb.setText(u.getPinBb());
			Date tglMasukDate=u.getTglMasuk();
			if (tglMasukDate==null) {
				tglMasuk.setText("");
			}else{
				tglMasuk.setText(App.dateFormat.format(tglMasukDate));
			}
			
			BigDecimal gajibBigDecimal=u.getGaji();
			if (gajibBigDecimal==null) {
				gaji.setText("");
			}else{
				gaji.setText(App.paymentFormat2.format(gajibBigDecimal.doubleValue()));
			}
			JenisPekerjaan jp=u.getJenisPekerjaan();
			if (jp==null) {
				jenisPekerjaan.setText("");
			}else{
				jenisPekerjaan.setText(jp.getNama());
			}
			pendidikanTerakhir.setText(u.getPendidikanTerakhir());
			int statusIndex=u.getStatus();
			if (statusIndex==0) {
				status.setText("");
			}else{
				String[] statusData=App.getUsrDao().getStatusData();
				status.setText(statusData[statusIndex]);
			}
		}
	}
	@Override
	public void changeObj(Object o) {
		load(o);
	}
	
	
}
