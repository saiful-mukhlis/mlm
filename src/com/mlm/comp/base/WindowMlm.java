package com.mlm.comp.base;

import com.basic.comp.impl.master.GrpM;
import com.basic.comp.impl.master.JenisPekerjaanM;
import com.basic.comp.impl.master.UsrM;
import com.basic.lang.LApp;
import com.basic.lang.LWindow;
import com.global.App;
import com.mlm.comp.impl.master.PaketM;
import com.mlm.comp.impl.master.PelangganM;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.base.ImagePanel;
import org.basic.comp.base.Window;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import javax.swing.*;
import java.awt.*;


public class WindowMlm extends Window{
	public static String PAKET=PaketM.class.getName();
	public static String PELANGGAN=PelangganM.class.getName();
	
	@Override
	public void buildMaster(ODatabaseDocumentTx db) {
		
		KomponentMlm pegawai=new KomponentMlm(this, PEGAWAI, UsrM.TITLE, 'P', UsrM.ICON_16, UsrM.ICON_32);
		KomponentMlm hakAkses=new KomponentMlm(this, HAK_AKSES, LWindow.HAK_AKSES, 'H', GrpM.ICON_16, GrpM.ICON_32);
		KomponentMlm jenisPekerjaan=new KomponentMlm(this, JENIS_PEKERJAAN, LWindow.JENIS_PEKERJAAN, 'H', JenisPekerjaanM.ICON_16, JenisPekerjaanM.ICON_32);
		KomponentMlm paket=new KomponentMlm(this, PAKET, LWindow.PAKET, 'H', PaketM.ICON_16, PaketM.ICON_32);
		KomponentMlm pelanggan=new KomponentMlm(this, PELANGGAN, LWindow.PELANGGAN, 'H', PelangganM.ICON_16, PelangganM.ICON_32);
		
		komponentMaps.put(PEGAWAI, pegawai);
		komponentMaps.put(HAK_AKSES, hakAkses);
		komponentMaps.put(JENIS_PEKERJAAN, jenisPekerjaan);
		komponentMaps.put(PAKET, paket);
		komponentMaps.put(PELANGGAN, pelanggan);
		
		komponents.add(pegawai);
		komponents.add(hakAkses);
		komponents.add(jenisPekerjaan);
		komponents.add(paket);
		komponents.add(pelanggan);
		
		
		
		for (Object komponent : getKomponents()) {
			cangeHakAkses.add(((KomponentMlm) komponent).getHakAksesListener());
		}
		
		
		JPanel panel = new ImagePanel(
				new FlowLayout(FlowLayout.CENTER, 50, 180));
		welcomeComponent = panel;

		
		windowAca = factoryContentAction(db, "Welcome", App.getIcon(LApp.iconApp16),
				welcomeComponent, (int) 'W');
		
	}
	
	@Override
	public void buildActions(ODatabaseDocumentTx db) {
		for (Object komponent : getKomponents()) {
			((KomponentMlm) komponent).setView(db, ((KomponentMlm) komponent).getTypeMaster(), myDoggySetContext);
		}
		
		windowVca = new ViewContextAction(
				"Welcome", App.getIcon(LApp.iconApp16),
				myDoggySetContext, ImagePanel.class);
		
	}
	
	@Override
	public void initMenu(ODatabaseDocumentTx db) {
		menu=new MenuMlm();
		menu.setWindow(this);
		menu.build(db);
		cangeHakAkses.add(menu);
		frame.setJMenuBar(menu.getMenu());
	}
	
}
