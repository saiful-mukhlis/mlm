package com.basic.view.detail;

import com.basic.entity.JenisPekerjaan;
import com.basic.lang.LJenisPekerjaan;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.abst.DetailAbs;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextField;

public class JenisPekerjaanDetail extends DetailAbs {
	protected TextField code;
	protected TextField nama;

	protected String title;

	@Override
	public void init() {
		super.init();
		code = new TextField();
		nama = new TextField();
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init();
		title = App.getT("Detail Data Jenis Pekerjaan");
		label.setText(title);

		code.setBackground(App.whiteSmoke);
		nama.setBackground(App.whiteSmoke);

		code.setEditable(false);
		nama.setEditable(false);

		FormLayout layout = new FormLayout(
				"r:p,   	10px,   	f:max(50px;p):g,  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,   p,3dlu,   p, 10dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		FormBuilder builder = new FormBuilder(layout, true);

		builder.append(LJenisPekerjaan.CODE, code, 1, 1, 5);
		builder.append(LJenisPekerjaan.NAMA, nama, 1, 3, 5);

		panelForm = builder.getPanel();
		scrollPane = new ScrollPane(panelForm);

		super.build(db);

	}

	public void load(Object o) {
		if (o == null) {
			label.setText(title);
			code.setText("");
			nama.setText("");
		} else if (o instanceof JenisPekerjaan) {
			JenisPekerjaan u = (JenisPekerjaan) o;
			code.setText(u.getCode());
			label.setText(title + " " + u.getNama());
			nama.setText(u.getNama());
		}
	}

	@Override
	public void changeObj(Object o) {
		load(o);
	}

}
