package com.mlm.view.add;

import com.basic.lang.LPaket;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.dao.impl.PaketDao;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.*;



public class PaketDN extends DialogDefault {
	protected TextField code;
	protected TextField nama;
	protected TextArea ket;
	protected ScrollPane sket;
	protected TextFieldAmount harga;
	protected TextField downline;
	protected TextField waktu;
	protected ComboBox status;
	
	
	
	@Override
	public void actionReset() {
		code.setText("Auto");
		nama.setText("");
		ket.setText("");
		harga.setText("");
		downline.setText("");
		waktu.setText("");
		status.setSelectedIndex(0);
		requestDefaultFocus();
	}

	
	
	@Override
	public void requestDefaultFocus() {
		nama.requestFocus();
	}



	public void initComponent(ODatabaseDocumentTx db) {
		code = new TextField();
		nama = new TextField();
		ket = new TextArea();
		sket = new  ScrollPane(ket);
		harga = new TextFieldAmount();
		downline = new TextField();
		waktu = new TextField();
		status=new ComboBox(App.getPaketDao().getStatusData());
		
	}
	
	
	
	
	@Override
	public void setFocusEnter() {
		setFocusEnter(code, nama);
		setFocusEnter(nama, ket);
		setFocusEnter(ket, harga);
		setFocusEnter(harga, downline);
		setFocusEnter(downline, waktu);
		setFocusEnter(waktu, status);
		setFocusEnter(status, save);
	}


	@Override
	public void init(ODatabaseDocumentTx db) {
		icon=PaketM.ICON_48;
		super.init(db);
		labelTitle.setText("Tambah Paket");
		labelNote.setText("Isi data dengan benar");
		initComponent(db);
		buildForm(db);
	}

	public void buildForm(ODatabaseDocumentTx db) {
		initComponent(db);
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("30px,");
		col.append("r:p,10px,f:400px:g,");
		col.append("30px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("f:40dlu:g,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LPaket.CODE, code, 2, 2, 1);
		b.append(LPaket.NAMA, nama, 2, 4, 1);
		b.append(LPaket.KET, sket, 2, 6, 1);
		b.append(LPaket.HARGA, harga, 2, 8, 1);
		b.append(LPaket.DOWNLINE, downline, 2, 10, 1);
		b.append(LPaket.WAKTU_BLN, waktu, 2,12 , 1);
		b.append(LPaket.STATUS, status, 2, 14, 1);
		
		panelForm=b.getPanel();

	}


	@Override
	public void save(ODatabaseDocumentTx db) {
		PaketDao d=App.getPaketDao();
		model=new ODocument(d.getClassName());
		d.setCode(model, code);
		d.setNama(model, nama);
		d.setKet(model, ket);
		d.setHarga(model, harga);
		d.setDownline(model, downline);
		d.setWaktu(model, waktu);
		d.setStatus(model, status.getSelectedIndex());
		d.save(db, model);
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		PaketDao d=App.getPaketDao();
		if (!d.vCode(db, code)) {
			return false;
		}
		if (!d.vNama(db, nama)) {
			return false;
		}
		if (!d.vDownline(db, downline)) {
			return false;
		}
		
		return true;
	}
	
}
