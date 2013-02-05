package org.basic.comp.abst;

import com.basic.comp.impl.action.ExitAction;
import com.basic.lang.LApp;
import com.basic.lang.LWindow;
import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import org.basic.comp.base.PanelBottom;
import org.basic.comp.listener.WidgetInterface;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManagerListener;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.mydoggy.event.ContentManagerEvent;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.noos.xing.mydoggy.plaf.ui.cmp.ExtendedTableLayout;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WindowAbstract implements WindowInterfaces {
	protected WidgetInterface toolbar;
	protected MenuInterfaces menu; 
	protected WidgetInterface panelButton;
	protected ToolWindowManager toolWindowManager;
	protected JFrame frame;
	protected PanelBottom panelBottom;
	protected JProgressBar progressBar;
	protected boolean useProgressBar;
	protected List widgetProtects;
	protected List<Master> masters;
	protected String idMasterOpen;
	protected Master masterOpen;

	protected HashMap<String, Master> mapMaster = new HashMap<>();

	protected AddContentAction welcomeAca;
	protected ViewContextAction welcomeVca;

	protected JPanel welcomeComponent;
	
	protected List<WidgetPrivilege> widgetPrivileges=new ArrayList<>();

	public void init() {
		toolWindowManager = new MyDoggyToolWindowManager();
		((MyDoggyToolWindowManager)toolWindowManager).getMainContainer().setBackground(Color.WHITE);
		
		frame = new JFrame();
		
		panelBottom = new PanelBottom();
		
		// aksi
		ExitAction exit=new ExitAction();
		App.getActions().put("exit", exit);
		
//		masters=new ArrayList<>();
	}

	public void build(ODatabaseDocumentTx db) {

		menu.build(db);
		toolbar.build(db);
		panelBottom.build();



		// toolWindowManager
		for (ToolWindow window : toolWindowManager.getToolWindows()) {
			window.setAvailable(true);
		}

		toolWindowManager.getContentManager().addContentManagerListener(
				new ContentManagerListener() {

					@Override
					public void contentSelected(ContentManagerEvent arg0) {
						idMasterOpen = arg0.getContent().getId();
						if (masterOpen != null) {
							masterOpen.requestDefaultSelected();
						}

					}

					@Override
					public void contentRemoved(ContentManagerEvent arg0) {
						if (arg0.getContent().getId().equals(idMasterOpen)) {
							idMasterOpen = "";
						}
						if (toolWindowManager.getContentManager()
								.getContentCount() == 0) {
							App.getActions().get(LApp.SHOW_WELCOME).actionPerformed(null);
						}
					}

					@Override
					public void contentAdded(ContentManagerEvent arg0) {
						idMasterOpen = arg0.getContent().getId();
						masterOpen=mapMaster.get(idMasterOpen);
						System.out.println(idMasterOpen);
						if (toolWindowManager.getContentManager()
								.getContentCount() > 1) {
							try {
								Content c = toolWindowManager
										.getContentManager()
										.getContentByComponent(welcomeComponent);
								toolWindowManager.getContentManager()
										.removeContent(c);
							} catch (Exception e) {
								App.printErr(e);
							}

						}

					}
				});

		// frame
		frame.setTitle(LWindow.TITLE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setIconImage(App.getImage(LApp.iconApp16).getImage());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menu.getMenuBar());
		
		
		// build tool window manager
		JPanel p = new JPanel();
		p.setLayout(new ExtendedTableLayout(new double[][] { { 0, -1, 0 },
				{ 0, -1, 0 } }));
		p.add((Component) toolWindowManager, "1,1,");
		
		frame.add(p, BorderLayout.CENTER);

		// frame.getContentPane().add(toolbar.getPanel(), BorderLayout.SOUTH);
		JPanel p2=new JPanel(new BorderLayout());
		p2.add(new JSeparator(), BorderLayout.NORTH);
		p2.add(panelBottom.getPanel(), BorderLayout.CENTER);
		frame.getContentPane().add(p2, BorderLayout.SOUTH);
	}

	

	

	@Override
	public String getIdMasterOpen() {
		return idMasterOpen;
	}

	@Override
	public void setIdMasterOpen(String idMasterOpen) {
		this.idMasterOpen = idMasterOpen;
	}

	

	public HashMap<String, Master> getMapMaster() {
		return mapMaster;
	}

	public void setMapMaster(HashMap<String, Master> mapMaster) {
		this.mapMaster = mapMaster;
	}


	
	

}
