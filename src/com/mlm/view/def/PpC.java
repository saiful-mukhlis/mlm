package com.mlm.view.def;

import com.basic.lang.LPaket;
import com.basic.lang.LPelanggan;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.mlm.dao.impl.PaketDao;
import com.mlm.dao.impl.PelangganDao;
import com.mlm.dao.impl.PpDao;
import com.mlm.db.FPelanggan;
import com.mlm.db.FPp;
import com.mlm.lang.LPp;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;

public class PpC extends DetailAbstract {

	protected TextField code;
	protected TextField namaToko;
	protected TextField namaPemilik;
	protected TextField jenisIdentitas;
	protected TextField noIdentitas;
	protected TextArea alamat;
	protected ScrollPane salamat;
	protected TextField kota;
	protected TextField noTelp;
	protected TextField noFax;
	protected TextField noHp1;
	protected TextField noHp2;
	protected TextField pinBb1;
	protected TextField pinBb2;
	protected TextField status;
	
	
	protected TextField pcode;
	protected TextField pnama;
	protected TextArea pket;
	protected ScrollPane spket;
	protected TextField pharga;
	protected TextField pwaktu;
	
	protected TextField ppcode;
	protected TextField pptglDaftar;
	protected TextField pptglLunas;
	protected TextField pptglAktif;
	protected TextField ppStatus;
	
	

	public void init(ODatabaseDocumentTx db) {
		lebar = 0.002;
		dao = App.getGrpDao();
		
		initComponent(db);
	}


	public void setColorView() {
		code.setBackground(App.whiteSmoke);
		namaToko.setBackground(App.whiteSmoke);
		namaPemilik.setBackground(App.whiteSmoke);
		jenisIdentitas.setBackground(App.whiteSmoke);
		noIdentitas.setBackground(App.whiteSmoke);
		alamat.setBackground(App.whiteSmoke);
		kota.setBackground(App.whiteSmoke);
		noTelp.setBackground(App.whiteSmoke);
		noFax.setBackground(App.whiteSmoke);
		noHp1.setBackground(App.whiteSmoke);
		noHp2.setBackground(App.whiteSmoke);
		pinBb1.setBackground(App.whiteSmoke);
		pinBb2.setBackground(App.whiteSmoke);
		status.setBackground(App.whiteSmoke);
		
		pcode.setBackground(App.whiteSmoke);
		pnama.setBackground(App.whiteSmoke);
		pket.setBackground(App.whiteSmoke);
		spket.setBackground(App.whiteSmoke);
		pharga.setBackground(App.whiteSmoke);
		pwaktu.setBackground(App.whiteSmoke);
		
		ppcode.setBackground(App.whiteSmoke);
		pptglDaftar.setBackground(App.whiteSmoke);
		pptglLunas.setBackground(App.whiteSmoke);
		pptglAktif.setBackground(App.whiteSmoke);
		ppStatus.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		code.setEditable(false);
		namaToko.setEditable(false);
		namaPemilik.setEditable(false);
		jenisIdentitas.setEditable(false);
		noIdentitas.setEditable(false);
		alamat.setEditable(false);
		kota.setEditable(false);
		noTelp.setEditable(false);
		noFax.setEditable(false);
		noHp1.setEditable(false);
		noHp2.setEditable(false);
		pinBb1.setEditable(false);
		pinBb2.setEditable(false);
		status.setEditable(false);
		
		pcode.setEditable(false);
		pnama.setEditable(false);
		pket.setEditable(false);
		pharga.setEditable(false);
		pwaktu.setEditable(false);
		
		ppcode.setEditable(false);
		pptglDaftar.setEditable(false);
		pptglLunas.setEditable(false);
		pptglAktif.setEditable(false);
		ppStatus.setEditable(false);
		
	}

	
	

	@Override
	public void load(ODocument model) {
		if (model!=null && model.field("@class").equals(FPp.TABLE)) {
			PpDao d = App.getPpDao();
			PelangganDao dp = App.getPelangganDao();
			ODocument pelangganTmp=d.getPelanggan(null, model);
			code.setText(dp.getCode(pelangganTmp));
			namaToko.setText(dp.getNamaToko(pelangganTmp));
			namaPemilik.setText(dp.getNamaPemilik(pelangganTmp));
			jenisIdentitas.setText(dp.getJenisIdentitas(pelangganTmp));
			noIdentitas.setText(dp.getNoIdentitas(pelangganTmp));
			alamat.setText(dp.getAlamat(pelangganTmp));
			kota.setText(dp.getKota(pelangganTmp));
			noTelp.setText(dp.getNoTelp(pelangganTmp));
			noFax.setText(dp.getNoFax(pelangganTmp));
			noHp1.setText(dp.getNoHp1(pelangganTmp));
			noHp2.setText(dp.getNoHp2(pelangganTmp));
			pinBb1.setText(dp.getPinBb1(pelangganTmp));
			pinBb2.setText(dp.getPinBb2(pelangganTmp));
			status.setText(dp.statusToString(null, pelangganTmp));
			
			
			ODocument o=d.getPaket(null, model);
			PaketDao da=App.getPaketDao();
			pcode.setText(da.getCode(o));
			pnama.setText(da.getNama(o));
			pket.setText(da.getKet(o));
			pharga.setText(da.hargaToString(o));
			pwaktu.setText(da.waktuToString(o));
			
			ppcode.setText(d.getCode(o));
			pptglDaftar.setText(d.tglDaftarToString(model));
			pptglLunas.setText(d.tglLunasToString(model));
			pptglAktif.setText(d.tglAktifToString(model));
			ppStatus.setText(d.statusToString(model));
			
		}else if (model!=null && model.field("@class").equals(FPelanggan.TABLE)) {
			PelangganDao d = App.getPelangganDao();
			code.setText(d.getCode(model));
			namaToko.setText(d.getNamaToko(model));
			namaPemilik.setText(d.getNamaPemilik(model));
			jenisIdentitas.setText(d.getJenisIdentitas(model));
			noIdentitas.setText(d.getNoIdentitas(model));
			alamat.setText(d.getAlamat(model));
			kota.setText(d.getKota(model));
			noTelp.setText(d.getNoTelp(model));
			noFax.setText(d.getNoFax(model));
			noHp1.setText(d.getNoHp1(model));
			noHp2.setText(d.getNoHp2(model));
			pinBb1.setText(d.getPinBb1(model));
			pinBb2.setText(d.getPinBb2(model));
			status.setText(d.statusToString(null, model));
			
			pcode.setText("");
			pnama.setText("");
			pket.setText("");
			pharga.setText("");
			pwaktu.setText("");
			
			ppcode.setText("");
			pptglDaftar.setText("");
			pptglLunas.setText("");
			pptglAktif.setText("");
			ppStatus.setText("");
			
		}else {
			code.setText("");
			namaToko.setText("");
			namaPemilik.setText("");
			jenisIdentitas.setText("");
			noIdentitas.setText("");
			alamat.setText("");
			kota.setText("");
			noTelp.setText("");
			noFax.setText("");
			noHp1.setText("");
			noHp2.setText("");
			pinBb1.setText("");
			pinBb2.setText("");
			status.setText("");
			
			pcode.setText("");
			pnama.setText("");
			pket.setText("");
			pharga.setText("");
			pwaktu.setText("");
			
			ppcode.setText("");
			pptglDaftar.setText("");
			pptglLunas.setText("");
			pptglAktif.setText("");
			ppStatus.setText("");
		}
	}



	public void initComponent(ODatabaseDocumentTx db) {
		code = new TextField();
		namaToko = new TextField();
		namaPemilik = new TextField();
		jenisIdentitas = new TextField();
		noIdentitas = new TextField();
		alamat = new TextArea();
		salamat=new ScrollPane(alamat);
		kota = new TextField();
		noTelp = new TextField();
		noFax = new TextField();
		noHp1 = new TextField();
		noHp2 = new TextField();
		pinBb1 = new TextField();
		pinBb2 = new TextField();
		status = new TextField();
		
		pcode = new TextField();
		pnama = new TextField();
		pket = new TextArea();
		spket = new ScrollPane(pket);
		pharga = new TextField();
		pwaktu = new TextField();
		
		ppcode = new TextField();
		pptglDaftar = new TextField();
		pptglLunas = new TextField();
		pptglAktif = new TextField();
		ppStatus = new TextField();
		
	}

	public void buildForm(ODatabaseDocumentTx db) {

		//Double tmp = App.getW() * lebar;// 0.51
		
		
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("10px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("10px,");

		
		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		builder = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		builder.addSeparator(App.getT("Profil Pelanggan"), 2, 2, 7);
		builder.append(LPelanggan.CODE_PELANGGAN, code, 2, 4, 1);
		builder.append(LPelanggan.NAMA_TOKO, namaToko, 2, 6, 1);
		builder.append(LPelanggan.NAMA_PEMILIK, namaPemilik, 6, 6, 1);
		builder.append(LPelanggan.JENIS_IDENTITAS, jenisIdentitas, 2, 8, 1);
		builder.append(LPelanggan.NO_IDENTITAS, noIdentitas, 6, 8, 1);
		builder.append(LPelanggan.ALAMAT, salamat, 2, 10, 1, 7);
		builder.append(LPelanggan.KOTA, kota, 2, 18, 1);
		builder.append(LPelanggan.NO_TELP, noTelp, 6, 10, 1);
		builder.append(LPelanggan.NO_FAX, noFax, 6, 12, 1);
		builder.append(LPelanggan.NO_HP1, noHp1, 6, 14, 1);
		builder.append(LPelanggan.NO_HP2, noHp2, 6, 16, 1);
		builder.append(LPelanggan.PIN_BB1, pinBb1, 6, 18, 1);
		builder.append(LPelanggan.PIN_BB2, pinBb2, 6, 20, 1);
		builder.append(LPelanggan.STATUS, status, 2, 20, 1);
		
		
		
		
		builder.addSeparator(App.getT("Data Paket"), 2, 22, 7);
		builder.append(LPp.NO_DAFTAR, ppcode, 2, 24, 1);
		builder.append(LPaket.CODE_PAKET, pcode, 6, 24, 1);
		builder.append(LPaket.NAMA_PAKET, pnama, 2, 26, 1);
		builder.append(LPaket.HARGA, pharga, 6, 26, 1);
		builder.append(LPaket.KET, spket, 2, 28, 1, 5);
		builder.append(LPaket.WAKTU, pwaktu, 6, 28, 1);
		
		builder.append(LPp.TGL_DAFTAR, pptglDaftar , 6, 30, 1);
		builder.append(LPp.TGL_LUNAS, pptglLunas, 6, 32, 1);
		builder.append(LPp.STATUS, ppStatus, 2, 34, 1);
		builder.append(LPp.TGL_AKTIF, pptglAktif, 6, 34, 1);
		
		
		
		
		
		
		StringBuilder c=new StringBuilder();
		StringBuilder r=new StringBuilder();
		c.append("10px,f:0px:g,10px,");
		
		r.append("3dlu,");
		r.append("p,3dlu,");
		r.append("p,3dlu,");
		layout=new FormLayout(c.toString(), r.toString());

	}

	@Override
	public void requestDefaultFocus() {
		namaToko.requestFocus();
	}

}