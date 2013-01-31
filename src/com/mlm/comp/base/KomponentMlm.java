package com.mlm.comp.base;

import com.basic.comp.impl.master.GrpM;
import com.basic.comp.impl.master.JenisPekerjaanM;
import com.basic.comp.impl.master.UsrM;
import com.global.App;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.comp.impl.master.PelangganM;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.adapter.WindowInterfaces;
import org.basic.comp.base.Komponent;
import org.basic.comp.base.Window;

import javax.swing.*;


public class KomponentMlm extends Komponent{
	
	public KomponentMlm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KomponentMlm(WindowInterfaces window, String typeMaster,
			String title, char shortCut, Icon icon16, Icon icon32) {
		super(window, typeMaster, title, shortCut, icon16, icon32);
		// TODO Auto-generated constructor stub
	}

	public Komponent createComponent(Komponent komponent){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
	    String type=komponent.getTypeMaster();
		if (type.equals(Window.PEGAWAI)) {
			komponent.setWidgetTop(new UsrM());
		}else if (type.equals(Window.HAK_AKSES)) {
			komponent.setWidgetTop(new GrpM());
		}
		else if (type.equals(Window.JENIS_PEKERJAAN)) {
			komponent.setWidgetTop(new JenisPekerjaanM());
		}
		else if (type.equals(WindowMlm.PAKET)) {
			komponent.setWidgetTop(new PaketM());
		}
		else if (type.equals(WindowMlm.PELANGGAN)) {
			komponent.setWidgetTop(new PelangganM());
		}
		
		
		if (komponent.getWidgetTop()!=null) {
			//komponent.getWidgetTop().getChangeStateActions().add(window.getMenu());
			komponent.getWidgetTop().build(db);
			komponent.setComponent(komponent.getWidgetTop().getPanel());
		}
		db.close();
		return komponent;
	}
}
