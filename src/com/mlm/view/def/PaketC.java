package com.mlm.view.def;


import com.basic.lang.LPaket;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.mlm.dao.impl.PaketDao;
import com.mlm.db.FPaket;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.abst.DetailAbstract;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;

public class PaketC extends DetailAbstract {

	protected TextField code;
	protected TextField nama;
	protected TextArea ket;
	protected ScrollPane sket;
	protected TextFieldAmount harga;
	protected TextField downline;
	protected TextField waktu;
	protected TextField status;
	protected TextField totalPelanggan;
	
	

	public void init(ODatabaseDocumentTx db) {
		lebar = 0.002;
		dao = App.getGrpDao();
		
		initComponent(db);
	}


	public void setColorView() {
		code.setBackground(App.whiteSmoke);
		nama.setBackground(App.whiteSmoke);
		ket.setBackground(App.whiteSmoke);
		sket.setBackground(App.whiteSmoke);
		harga.setBackground(App.whiteSmoke);
		downline.setBackground(App.whiteSmoke);
		waktu.setBackground(App.whiteSmoke);
		status.setBackground(App.whiteSmoke);
		totalPelanggan.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		code.setEditable(false);
		nama.setEditable(false);
		ket.setEditable(false);
		harga.setEditable(false);
		downline.setEditable(false);
		waktu.setEditable(false);
		status.setEditable(false);
		totalPelanggan.setEditable(false);
	}

	
	

	@Override
	public void load(ODocument model) {
		 if (model!=null && model.field("@class").equals(FPaket.TABLE)) {
			PaketDao d = App.getPaketDao();
			code.setText(d.getCode(model));
			nama.setText(d.getNama(model));
			ket.setText(d.getKet(model));
			harga.setText(d.hargaToString(model));
			downline.setText(d.downlineToString(model));
			waktu.setText(d.waktuToString(model));
			status.setText(d.statusToString(model));
			totalPelanggan.setText(d.totalPelangganToString(model));
		}else {
			code.setText("");
			nama.setText("");
			ket.setText("");
			harga.setText("");
			downline.setText("");
			waktu.setText("");
			status.setText("");
			totalPelanggan.setText("");
		}
	}



	public void initComponent(ODatabaseDocumentTx db) {
		code = new TextField();
		nama = new TextField();
		ket = new TextArea();
		sket = new  ScrollPane(ket);
		harga = new TextFieldAmount();
		downline = new TextField();
		waktu = new TextField();
		status = new TextField();
		totalPelanggan = new TextField();
		
	}

	public void buildForm(ODatabaseDocumentTx db) {

		//Double tmp = App.getW() * lebar;// 0.51
		
		
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("10px,");

		
		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("f:40dlu,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		builder = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		builder.append(LPaket.CODE, code, 2, 2, 1);
		builder.append(LPaket.NAMA, nama, 2, 4, 1);
		builder.append(LPaket.KET, sket, 2, 6, 1);
		builder.append(LPaket.HARGA, harga, 2, 8, 1);
		builder.append(LPaket.DOWNLINE, downline, 2, 10, 1);
		builder.append(LPaket.WAKTU_BLN, waktu, 2, 12, 1);
		builder.append(LPaket.STATUS, status, 2, 14, 1);
		builder.append(LPaket.TOTAL_PELANGGAN, totalPelanggan, 2, 16, 1);
		
		
		
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
		nama.requestFocus();
	}

}
