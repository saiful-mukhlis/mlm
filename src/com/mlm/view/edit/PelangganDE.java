package com.mlm.view.edit;

import com.basic.lang.LPelanggan;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.mlm.comp.impl.master.PelangganM;
import com.mlm.dao.impl.PelangganDao;
import com.mlm.db.FPelanggan;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.abst.DialogEditDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.model.ODocumentToString;

public class PelangganDE  extends DialogEditDefault {
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
	protected ComboBox status;
	protected ODocumentToString [] statusModel ;
	
	
	


	@Override
	public void load(ODocument model) {
		this.model=model;
		if (model==null) {
			code.setText("Auto");
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
			status.setSelectedIndex(0);
			status.setSelectedIndex(0);
		}else if (model.field("@class").equals(FPelanggan.TABLE)) {
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
			
			
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			ODocument tmp1=d.getStatus(db,model);
			if (tmp1!=null) {
				for (ODocumentToString os : statusModel) {
					ODocument o=os.getO();
					if (o!=null) {
						if (o.getIdentity().equals(tmp1.getIdentity())) {
							status.setSelectedItem(os);
							break;
						}
					}
				}
			}
			
			db.close();
			
		}
	}

	@Override
	public void actionReset() {
		actionReload();
	}

	
	
	@Override
	public void requestDefaultFocus() {
		namaToko.requestFocus();
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
		statusModel = App. getPelangganDao().getStatusData(db);
		status =new ComboBox(statusModel );
		
	}
	
	
	
	
	@Override
	public void setFocusEnter() {
		setFocusEnter(code, namaToko);
		setFocusEnter(namaToko, namaPemilik);
		setFocusEnter(namaPemilik, jenisIdentitas);
		setFocusEnter(jenisIdentitas, noIdentitas);
		setFocusEnter(noIdentitas, alamat);
		setFocusEnter(alamat, kota);
		setFocusEnter(kota, noTelp);
		setFocusEnter(noTelp, noFax);
		setFocusEnter(noFax, noHp1);
		setFocusEnter(noHp1, noHp2);
		setFocusEnter(noHp2, pinBb1);
		setFocusEnter(pinBb1, pinBb2 );
		setFocusEnter(pinBb2, status);
		setFocusEnter(status, save);
		
		
	}


	@Override
	public void init(ODatabaseDocumentTx db) {
		icon=PelangganM.ICON_48;
		super.init(db);
		labelTitle.setText("Edit Pelanggan");
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
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LPelanggan.CODE, code, 2, 2, 1);
		b.append(LPelanggan.NAMA_TOKO, namaToko, 2, 4, 1);
		b.append(LPelanggan.NAMA_PEMILIK, namaPemilik, 2, 6, 1);
		b.append(LPelanggan.JENIS_IDENTITAS, jenisIdentitas, 2, 8, 1);
		b.append(LPelanggan.NO_IDENTITAS, noIdentitas, 2, 10, 1);
		b.append(LPelanggan.ALAMAT, salamat, 2, 12, 1);
		b.append(LPelanggan.KOTA, kota, 2, 14, 1);
		b.append(LPelanggan.NO_TELP, noTelp, 2, 16, 1);
		b.append(LPelanggan.NO_FAX, noFax, 2, 18, 1);
		b.append(LPelanggan.NO_HP1, noHp1, 2, 20, 1);
		b.append(LPelanggan.NO_HP2, noHp2, 2, 22, 1);
		b.append(LPelanggan.PIN_BB1, pinBb1, 2, 24, 1);
		b.append(LPelanggan.PIN_BB2, pinBb2, 2, 26, 1);
		b.append(LPelanggan.STATUS, status, 2, 28, 1);
		
		panelForm=b.getPanel();

	}


	@Override
	public void save(ODatabaseDocumentTx db) {
		String jsonOld=model.toJSON();
		PelangganDao d=App.getPelangganDao();
		d.setCode(model,code);
		d.setNamaToko(model,namaToko);
		d.setNamaPemilik(model,namaPemilik);
		d.setJenisIdentitas(model,jenisIdentitas);
		d.setNoIdentitas(model,noIdentitas);
		d.setAlamat(model,alamat);
		d.setKota(model,kota);
		d.setNoTelp(model,noTelp);
		d.setNoFax(model,noFax);
		d.setNoHp1(model,noHp1);
		d.setNoHp2(model,noHp2);
		d.setPinBb1(model,pinBb1);
		d.setPinBb2(model,pinBb2);
		d.setStatus(model, ((ODocumentToString)status.getSelectedItem()).getO());
		
		
		d.update(db, model, jsonOld);
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		PelangganDao d=App.getPelangganDao();
		if (!d.vCode(db, code, model)) {
			return false;
		}
		if (!d.vNamaToko(db, namaToko, model)) {
			return false;
		}
		
		return true;
	}
	
}