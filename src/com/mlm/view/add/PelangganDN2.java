package com.mlm.view.add;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.model.ODocumentToString;

import com.basic.lang.LPelanggan;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.mlm.comp.impl.master.PelangganM;
import com.mlm.dao.impl.PelangganDao;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganDN2 extends DialogDefault {
	
	protected ODocument model;
	
	protected List<JPanel> panels=new ArrayList<>();
	protected int indexNow;
	protected int indexDefault;
	protected int indexNext;
	protected int indexBack;
	protected int indexMax;
	
	
	protected JLabel code_2;
	protected JLabel namaToko_2;
	protected JLabel namaPemilik_2;
	protected JLabel jenisIdentitas_2;
	protected JLabel noIdentitas_2;
	protected JLabel alamat_2;
	protected JLabel kota_2;
	protected JLabel noTelp_2;
	protected JPanel panel_2;
	
	protected JButton tamahPaket;
	protected List<JButton> tambahDownlines;
	protected List<TextField> namaPakets;
	
	protected JButton pelangganBaru;
	
	public void createPanel1(){
		
		tamahPaket=new JButton(App.getT("Tambah Paket"));
		pelangganBaru=new JButton(App.getT("Pelanggan Baru"));
		
		code_2=new JLabel();
		namaToko_2=new JLabel();
		namaPemilik_2=new JLabel();
		jenisIdentitas_2=new JLabel();
		noIdentitas_2=new JLabel();
		alamat_2=new JLabel();
		kota_2=new JLabel();
		noTelp_2=new JLabel();
		
//		code_2.setEditable(false);
//		namaToko_2.setEditable(false);
//		namaPemilik_2.setEditable(false);
//		jenisIdentitas_2.setEditable(false);
//		noIdentitas_2.setEditable(false);
//		alamat_2.setEditable(false);
//		kota_2.setEditable(false);
//		noTelp_2.setEditable(false);
//		
//		code_2.setEditable(false);
//		code_2.setBackground(App.whiteSmoke);
//		namaToko_2.setBackground(App.whiteSmoke);
//		namaPemilik_2.setBackground(App.whiteSmoke);
//		jenisIdentitas_2.setBackground(App.whiteSmoke);
//		noIdentitas_2.setBackground(App.whiteSmoke);
//		alamat_2.setBackground(App.whiteSmoke);
//		salamat_2.setBackground(App.whiteSmoke);
//		kota_2.setBackground(App.whiteSmoke);
//		noTelp_2.setBackground(App.whiteSmoke);
		
		
		StringBuilder col_1=new StringBuilder();
		StringBuilder row_1=new StringBuilder();
		
		col_1.append("3px,");
		col_1.append("r:p,10px,f:200px:g,");
		col_1.append("3px,");
		
		row_1.append("3dlu,");
		row_1.append("p,3dlu,");
		row_1.append("p,3dlu,");
		row_1.append("p,3dlu,");
		row_1.append("p,3dlu,");
		row_1.append("p,3dlu,");
		row_1.append("p,3dlu,");
		row_1.append("p,3dlu,");
		row_1.append("p,3dlu,");
		
		FormLayout l_1 = new FormLayout(col_1.toString(),row_1.toString());

		l_1.setColumnGroups(new int[][] { { 2, 4 } });
		FormBuilder b_1 = new FormBuilder(l_1);
		//append(String i8n, Component c, int x, int y, int w)
		b_1.append(LPelanggan.CODE, code_2, 2, 2, 1);
		b_1.append(LPelanggan.NAMA_TOKO, namaToko_2, 2, 4, 1);
		b_1.append(LPelanggan.NAMA_PEMILIK, namaPemilik_2, 2, 6, 1);
		b_1.append(LPelanggan.JENIS_IDENTITAS, jenisIdentitas_2, 2, 8, 1);
		b_1.append(LPelanggan.NO_IDENTITAS, noIdentitas_2, 2, 10, 1);
		b_1.append(LPelanggan.ALAMAT, alamat_2, 2, 12, 1);
		b_1.append(LPelanggan.KOTA, kota_2, 2, 14, 1);
		b_1.append(LPelanggan.NO_TELP, noTelp_2, 2, 16, 1);
		
		
		StringBuilder col_2=new StringBuilder();
		StringBuilder row_2=new StringBuilder();
		
		col_2.append("30px,");
		col_2.append("f:max(1dlu;p):g,10px,f:160px,10px,f:160px,");
		col_2.append("30px,");
		
		row_2.append("3dlu,");
		row_2.append("p,3dlu,");

		
		FormLayout l_2 = new FormLayout(col_2.toString(),row_2.toString());

		//l_2.setColumnGroups(new int[][] { { 2, 4 } });
		FormBuilder b_2 = new FormBuilder(l_2);
		b_2.append( tamahPaket, 4, 2);
		b_2.append( pelangganBaru, 6, 2);
		
		
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("3px,");
		col.append("r:p,");
		col.append("3px,");
		col.append("r:p,");
		col.append("3px,");
		col.append("r:p,");
		col.append("3px,");
		
		row.append("3dlu,");
		row.append("p,3dlu,");

		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		l.setColumnGroups(new int[][] { { 2, 6 } });
		FormBuilder b = new FormBuilder(l);
		
		JPanel p=new JPanel(new BorderLayout());
		p.add(b_1.getPanel(), BorderLayout.CENTER);
		p.add(b_2.getPanel(), BorderLayout.SOUTH);
		
		JSeparator s=new JSeparator(JSeparator.VERTICAL);
		b.append( p, 2, 2);
		b.append( s, 4, 1, 1,3);
		//b.append( b_2.getPanel(), 6, 2);
		panel_2=b.getPanel();
		
		
	}
	
	
	
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
	
	
	
	
	
	protected JButton next;
	protected JButton back;
	protected JButton finish;
	protected JButton cancel;
	
	
	
	@Override
	public void actionReset() {
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
		requestDefaultFocus();
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
		labelTitle.setText("Tambah Pelanggan");
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
		PelangganDao d=App.getPelangganDao();
		model=new ODocument(d.getClassName());
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
		
		
		d.save(db, model);
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		PelangganDao d=App.getPelangganDao();
		if (!d.vCode(db, code)) {
			return false;
		}
		if (!d.vNamaToko(db, namaToko)) {
			return false;
		}
		
		return true;
	}



	@Override
	public void load(ODocument model) {
		this.model=model;
	}
	
	
	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
		createPanel1();
		buildPanel();
		pane.setViewportView(panel_2);
		setFocusEnter();
	}
	
	
	
}
