package com.bmb.test;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

import com.basic.icon.IconBase;
import com.basic.view.screen.SplashScreen;
import com.global.App;
import com.jgoodies.looks.FontPolicies;
import com.jgoodies.looks.FontPolicy;
import com.jgoodies.looks.FontSet;
import com.jgoodies.looks.FontSets;
import com.jgoodies.looks.Fonts;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.mlm.comp.impl.WindowMlm;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class Main {
	public static void main(String[] args) {
		m1();
	}

	public static void m1() {
		long t1 = System.currentTimeMillis();
		try {
			UIManager
					.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");

			UIManager.put("Tree.leafIcon", IconBase.TREEP);
			UIManager.put("Tree.openIcon", IconBase.TREEO);
			UIManager.put("Tree.closedIcon", IconBase.TREEA);

			UIManager.put("TaskPaneContainer.useGradient", Boolean.FALSE);
			UIManager.put("TaskPaneContainer.background", Color.WHITE);

			UIManager.put("TaskPane.titleForeground", Color.WHITE);
			UIManager.put("TaskPane.titleOver", Color.LIGHT_GRAY.darker());
			UIManager.put("TaskPane.background", Color.WHITE);

			UIManager.put("TaskPane.titleBackgroundGradientStart", Color.BLACK);
			UIManager.put("TaskPane.titleBackgroundGradientEnd", Color.BLACK);

			UIManager.put("SplitPaneDivider.border", App.borderBlackKananKiri);
			UIManager.put("SplitPaneDivider.draggingColor", App.aqua);

			UIManager.put("SplitPane.background", Color.WHITE);

			UIDefaults ui = UIManager.getLookAndFeelDefaults();

			ui.put("PopupMenuSeparator.background", Color.RED);

			ui.put("Menu.background", Color.WHITE);

			ui.put("OptionPane.background", Color.WHITE);
			ui.put("MenuBar.opaque", true);

			ui.put("Panel.background", Color.WHITE);
			ui.put("RootPane.background", Color.WHITE);
			ui.put("DesktopPane.background", Color.WHITE);

			ui.put("Menu.opaque", true);
			ui.put("Menu.foreground", Color.BLACK);

			ui.put("TabbedPane.background", Color.WHITE);

			UIManager.put("jgoodies.popupDropShadowEnabled", Boolean.TRUE);
			Options.setSelectOnFocusGainEnabled(true);

			System.setProperty("Windows.controlFont", "Segoe UI-plain-15");

			Font controlFont = Fonts.WINDOWS_VISTA_96DPI_NORMAL;
			FontSet fontSet = FontSets.createDefaultFontSet(controlFont);
			FontPolicy fontPolicy = FontPolicies.createFixedPolicy(fontSet);
			WindowsLookAndFeel.setFontPolicy(fontPolicy);
			WindowsLookAndFeel.setFontPolicy(FontPolicies
					.getLooks1xWindowsPolicy());

		} catch (Exception e) {
			App.printErr(e);
		}

		try {

			SplashScreen splash = new SplashScreen();
			splash.setVisible(true);
			splash.getBar().setValue(30);
			WindowMlm w = new WindowMlm();
			splash.getBar().setValue(40);
			w.setProgressBar(splash.getBar());
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			splash.getBar().setValue(70);
			w.build(db);
			
			w.getFrame().setVisible(true);

			db.close();
			splash.setVisible(false);
			splash.dispose();

		} catch (Exception e) {
			App.showErrSementara(e.getMessage());
			App.printErr(e);
			System.exit(0);
		}
		long t2 = System.currentTimeMillis();
		long t3 = t2 - t1;
		App.showErrSementara(t2 + "-" + t1 + "=" + t3);
	}

}
