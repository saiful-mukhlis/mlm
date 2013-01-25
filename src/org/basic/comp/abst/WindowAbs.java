package org.basic.comp.abst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;

import org.basic.comp.base.LoginDialog;
import org.basic.comp.base.PanelBottom;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManager;
import org.noos.xing.mydoggy.ContentManagerListener;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.mydoggy.event.ContentManagerEvent;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.noos.xing.mydoggy.plaf.ui.cmp.ExtendedTableLayout;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;
import org.noos.xing.yasaf.plaf.view.MapViewContext;
import org.noos.xing.yasaf.view.ViewContext;

import com.basic.comp.impl.master.UsrMaster;
import com.basic.icon.IconBase;
import com.basic.lang.LApp;
import com.basic.lang.LWindow;
import com.global.App;
import com.global.DataUser;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class WindowAbs implements Window {
	protected Toolbar toolbar;
	protected Menu menu;
	protected ToolWindowManager toolWindowManager;
	protected ViewContext viewContext;
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

	@Override
	public void init() {
		toolWindowManager = new MyDoggyToolWindowManager();
		((MyDoggyToolWindowManager)toolWindowManager).getMainContainer().setBackground(Color.WHITE);
		frame = new JFrame();
		panelBottom = new PanelBottom();
		viewContext=new MapViewContext();
		
//		masters=new ArrayList<>();
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		menu.setWindow(this);
		toolbar.setWindow(this);

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
							showWelcome();
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
	public void actionPrint() {

	}

	@Override
	public void actionPdf() {

	}

	@Override
	public void actionAdd() {

	}

	@Override
	public void actionEdit() {

	}

	@Override
	public void actionDel() {

	}

	@Override
	public void actionReload() {

	}

	@Override
	public void actionReg() {

	}

	@Override
	public void actionAbout() {

	}

	@Override
	public void actionExit() {

	}

	@Override
	public void actionClose() {

	}

	@Override
	public void actionPrintPreview() {

	}

	@Override
	public void actionWord() {

	}

	@Override
	public void actionExcel() {

	}

	@Override
	public void showWelcome() {
		welcomeAca.actionPerformed(null);
//		viewContext.put(UsrMaster.ID_MASTER, null);
	}

	// GETER AND SETTER

	@Override
	public Toolbar getToolbar() {
		return toolbar;
	}

	@Override
	public void setToolbar(Toolbar toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public Menu getMenu() {
		return menu;
	}

	@Override
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public ToolWindowManager getToolWindowManager() {
		return toolWindowManager;
	}

	@Override
	public void setToolWindowManager(ToolWindowManager toolWindowManager) {
		this.toolWindowManager = toolWindowManager;
	}

	@Override
	public ViewContext getViewContext() {
		return viewContext;
	}

	@Override
	public void setViewContext(ViewContext viewContext) {
		this.viewContext = viewContext;
	}

	@Override
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public PanelBottom getPanelBottom() {
		return panelBottom;
	}

	@Override
	public void setPanelBottom(PanelBottom panelBottom) {
		this.panelBottom = panelBottom;
	}

	@Override
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	@Override
	public void setProgressBar(JProgressBar progressBar) {
		this.useProgressBar = true;
		this.progressBar = progressBar;
	}

	@Override
	public boolean isUseProgressBar() {
		return useProgressBar;
	}

	@Override
	public void setUseProgressBar(boolean useProgressBar) {
		this.useProgressBar = useProgressBar;
	}

	@Override
	public List getWidgetProtects() {
		return widgetProtects;
	}

	@Override
	public void setWidgetProtects(List widgetProtects) {
		this.widgetProtects = widgetProtects;
	}

//	@Override
//	public List getMasters() {
//		return masters;
//	}
//
//	@Override
//	public void setMasters(List masters) {
//		this.masters = masters;
//	}

	@Override
	public String getIdMasterOpen() {
		return idMasterOpen;
	}

	@Override
	public void setIdMasterOpen(String idMasterOpen) {
		this.idMasterOpen = idMasterOpen;
	}

	@Override
	public void actionLogin() {
		if (DataUser.getUsr() == null) {
			LoginDialog form = new LoginDialog();
			form.build();
			App.showDialog(frame, form.getPanel());
		} else {
			//logout
			DataUser.setUsr(null);
			DataUser.setGrp(null);
			toolWindowManager.getContentManager().removeAllContents();
			
		}
		DataUser.setAkses();
		changePrivilege();
	}
	
	public void changePrivilege(){
		for (WidgetPrivilege w : widgetPrivileges) {
			if (w!=null) {
				w.changePrivilege();
			}
		}
	}

	public HashMap<String, Master> getMapMaster() {
		return mapMaster;
	}

	public void setMapMaster(HashMap<String, Master> mapMaster) {
		this.mapMaster = mapMaster;
	}
	
	

}
