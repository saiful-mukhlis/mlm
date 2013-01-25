package com.basic.view.detail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JSplitPane;

import org.basic.comp.abst.DetailAbs;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextArea;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;
import org.basic.comp.base.TreeHakAkses;

import com.basic.entity.Grp;
import com.basic.entity.JenisPekerjaan;
import com.basic.entity.Usr;
import com.basic.lang.LGrp;
import com.basic.lang.LUsr;
import com.basic.table.UsrTForGrp;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.mlm.comp.base.TreeHakAksesMlm;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class GrpDetail extends DetailAbs{
	protected TextField code;
	protected TextField name;
	protected TextArea note;
	protected ScrollPane snote;
	
	protected TextField createBy;
	protected TextField updateBy;
	protected TextField createAt;
	protected TextField updateAt;
	
	
	protected TreeHakAkses tree;
	protected UsrTForGrp usrt;
	protected JSplitPane splitPane;
	
	protected String title;
	@Override
	public void init() {
		super.init();
		
		tree=new TreeHakAksesMlm();
		usrt=new UsrTForGrp();
		
		code = new TextField();
		name = new TextField();
		note = new TextArea();
		snote = new ScrollPane(note);
		
		createBy = new TextField();
		updateBy = new TextField();
		createAt = new TextField();
		updateAt = new TextField();
	}
	@Override
	public void build(ODatabaseDocumentTx db) {
		init();
		title=App.getT("Detail Data Pegawai");
		label.setText(title);
		
		
		
		code.setBackground(App.whiteSmoke);
		name.setBackground(App.whiteSmoke);
		note.setBackground(App.whiteSmoke);
		snote.setBackground(App.whiteSmoke);
		
		createBy.setBackground(App.whiteSmoke);
		updateBy.setBackground(App.whiteSmoke);
		createAt.setBackground(App.whiteSmoke);
		updateAt.setBackground(App.whiteSmoke);
		
		
		code.setEditable(false);
		name.setEditable(false);
		note.setEditable(false);
		createAt.setEditable(false);
		createBy.setEditable(false);
		updateAt.setEditable(false);
		updateBy.setEditable(false);
		
		
		
		
		
		
		
		StringBuilder col=new StringBuilder();
		StringBuilder row=new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("20px,");
		col.append("r:p,10px,f:0px:g,");
		col.append("10px,");
		
		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		
		FormLayout l = new FormLayout(col.toString(),row.toString());

		l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		//append(String i8n, Component c, int x, int y, int w)
		b.append(LGrp.CODE, code, 2, 2, 1);
		b.append(LGrp.NAME, name, 2, 4, 1);
		
		b.append(LGrp.NOTE, snote, 2, 6, 1, 3);
		
		b.append(LGrp.CREATE_BY, createBy, 6, 2, 1);
		b.append(LGrp.CREATE_AT, createAt, 6, 4, 1);
		b.append(LGrp.UPDATE_BY, updateBy, 6, 6, 1);
		b.append(LGrp.UPDATE_AT, updateAt, 6, 8, 1);
		
		
		StringBuilder c=new StringBuilder();
		StringBuilder r=new StringBuilder();
		c.append("10px,f:0px:g,10px,");
		
		r.append("3dlu,");
		r.append("p,3dlu,");
		r.append("p,3dlu,");
		FormLayout layout=new FormLayout(c.toString(), r.toString());
		FormBuilder builder=new FormBuilder(layout);
		
		tree.build(db);
		usrt.build(db);
		tree.getPanel().setBorder(App.borderBlackAtasBawahKiri);
		usrt.getPanel().setBorder(App.borderBlackAtasBawahKanan);
		splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tree.getPanel(), usrt.getPanel());
		splitPane.setDividerSize(20);
		Double tmp=App.getW()*0.25;
		splitPane.setDividerLocation(tmp.intValue());
//		splitPane.setOneTouchExpandable(false);
		splitPane.setOpaque(false);
		splitPane.setBorder(null);
//		splitPane.setBackground(Color.red);
		builder.append(b.getPanel(), 2, 2);
		builder.append(splitPane, 2, 4);
		
		super.build(db);
		
	}
	
	public void load(Object o){
		if (o==null) {
			label.setText(title);
			code.setText("");
			name.setText("");
			note.setText("");
			createBy.setText("");
			createAt.setText("");
			updateBy.setText("");
			updateAt.setText("");
			usrt.getTableModel().setModels(new ArrayList<>());
		}
		else if (o instanceof Grp) {
			Grp g=(Grp) o;
			label.setText(title+" "+g.getName());
			code.setText(g.getCode());
			name.setText(g.getName());
			note.setText(g.getNote());
			createBy.setText(g.getCreateBy());
			Date createAtTmp=g.getCreateAt();
			if (createAtTmp==null) {
				createAt.setText("");
			}else{
				createAt.setText(App.dateFormat.format(createAtTmp));
			}
			updateBy.setText(g.getUpdateBy());
			Date updateAtTmp=g.getUpdateAt();
			if (updateAtTmp==null) {
				updateAt.setText("");
			}else{
				updateAt.setText(App.dateFormat.format(updateAtTmp));
			}
			List<Usr> usrs=g.getUsrs();
			usrt.getTableModel().setModels(usrs);
		}
	}
	@Override
	public void changeObj(Object o) {
		load(o);
	}
	
	
}
