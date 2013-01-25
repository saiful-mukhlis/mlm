package com.basic.view.edit;



import java.util.Date;

import org.basic.comp.abst.EditPanelAbs;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import com.basic.dao.impl.GrpDao;
import com.basic.db.FGrp;
import com.basic.entity.Grp;
import com.basic.lang.LGrp;
import com.global.App;
import com.global.DataUser;
import com.global.Focus;
import com.global.util.Validate;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;



public class GrpDEdit extends EditPanelAbs {
	protected TextField code;
	protected TextField name;
	protected TextArea note;
	protected ScrollPane snote;
	
	
	@Override
	public void actionReset() {
		if (model!=null) {
			if (model instanceof Grp) {
				Grp g=(Grp) model;
				
				code.setText(g.getCode());
				name.setText(g.getName());
				note.setText(g.getNote());
			}
		}
		
		requestDefaultFocus();
	}

	
	
	public void requestDefaultFocus() {
		name.requestFocus();
	}



	public void init() {
		super.init();
		labelTitle.setText(App.getT("Edit Pegawai"));
		code = new TextField();
		name = new TextField();
		note = new TextArea();
		snote = new ScrollPane(note);
		
	}
	
	
	
	
	
	@Override
	public void build(ODatabaseDocumentTx db) {
		init();
		
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
		if (model instanceof Grp) {
			GrpDao d=App.getGrpDao();
			
			Grp u = (Grp) model;
			u.setCode(code.getText().trim());
			u.setName(name.getText().trim());
			u.setNote(note.getText().trim());
			
			u.setUpdateAt(new Date());
			u.setUpdateBy(DataUser.getUsr());
			
			d.update(db, u.getDoc());
		}
		
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		
		if (model instanceof Grp) {
			GrpDao d=App.getGrpDao();
			
			Grp u = (Grp) model;
			
			if (!Validate.validateStringEmpty(code, LGrp.CODE)) {
				return false;
			}
			
			if (!code.getText().trim().equalsIgnoreCase(u.getCode())) {
				if (!Validate.validateUnique(db, d, code, LGrp.CODE, FGrp.CODE)) {
					return false;
				}
			}
			
			
			if (!Validate.validateStringEmpty(name, LGrp.NAME)) {
				return false;
			}
			
			if (!name.getText().trim().equalsIgnoreCase(u.getName())) {
				if (!Validate.validateUnique(db, d, name, LGrp.NAME, FGrp.NAME)) {
					return false;
				}
			}
			
			
			
			return true;
		}else{
			return false;
		}
	}
	
}
