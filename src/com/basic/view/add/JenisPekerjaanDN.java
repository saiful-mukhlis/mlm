package com.basic.view.add;


import com.basic.dao.impl.GrpDao;
import com.basic.db.FGrp;
import com.basic.entity.Grp;
import com.basic.lang.LGrp;
import com.basic.lang.LUsr;
import com.global.App;
import com.global.DataUser;
import com.global.Focus;
import com.global.util.Validate;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.abst.AddPanelAbs;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;

import java.util.Date;



public class JenisPekerjaanDN extends AddPanelAbs {
	protected TextField code;
	protected TextField name;
	protected TextArea note;
	
	@Override
	public void actionReset() {
		code.setText("Auto");
		name.setText("");
		note.setText("");
		requestDefaultFocus();
	}

	
	
	@Override
	public void requestDefaultFocus() {
		name.requestFocus();
	}



	public void init() {
		super.init();
		code = new TextField();
		name = new TextField();
		note = new TextArea();
		
	}
	
	
	
	
	@Override
	public void build(ODatabaseDocumentTx db) {
		

		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("30px,");
		col.append("r:p,10px,f:400px:g,");
		col.append("30px,");
		
		row.append("15dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("f:40dlu:g,15dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		//l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LGrp.CODE, code, 2, 2, 1);
		b.append(LGrp.NAME, name, 2, 4, 1);
		
		b.append(LGrp.NOTE, snote, 2, 6, 1);
		
		
		panelForm=b.getPanel();
		
		Focus.enter(code, name);
		Focus.enter(name, note);
		Focus.enter(note, save);
		Focus.enter(save, code);
		super.build(db);
	}








	@Override
	public void save(ODatabaseDocumentTx db) {
		GrpDao d=App.getGrpDao();
		
		Grp g=new Grp();
		g.setCode(code.getText());
		g.setName(name.getText());
		g.setNote(note.getText());
		g.setCreateAt(new Date());
		g.setCreateBy(DataUser.getUsr());
		
		
		
		d.save(db, g.getDoc());
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		GrpDao d=App.getGrpDao();
		
		if (!Validate.validateStringEmpty(code, LUsr.CODE)) {
			return false;
		}
		
		if (!Validate.validateStringEmpty(name, LUsr.NAMA)) {
			return false;
		}
		
		if (!Validate.validateUnique(db, d, code, LGrp.CODE, FGrp.CODE)) {
			return false;
		}
		
		if (!Validate.validateUnique(db, d, code, LGrp.NAME, FGrp.NAME)) {
			return false;
		}
		
		return true;
	}
	
}
