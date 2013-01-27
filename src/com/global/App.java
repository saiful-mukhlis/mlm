package com.global;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.basic.comp.base.NumberRenderer;

import com.basic.comp.impl.NamaBulan;
//import com.basic.dao.impl.AgamaDao;
import com.basic.dao.impl.BosDao;
import com.basic.dao.impl.GrpDao;
import com.basic.dao.impl.JenisPekerjaanDao;
import com.basic.dao.impl.NumberIdDao;
import com.basic.dao.impl.UsrDao;
import com.basic.lang.Lang;
import com.mlm.dao.impl.PaketDao;
import com.mlm.dao.impl.PelangganDao;
import com.mlm.dao.impl.PpDao;
import com.mlm.dao.impl.StatusPelangganDao;
import com.mlm.managedb.StartDbMlm;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.exception.OStorageException;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * @author toyib Class ini di gunalan untuk variable yang di access secara
 *         global
 */
public class App {
	private App() {
	}
	
	private static Double w;
	private static Double h;

	public static Double getW() {
		if (w == null) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			w = screenSize.getWidth();
		}
		return w;
	}
	public static Double getH() {
		if (h == null) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			h = screenSize.getHeight();
		}
		return h;
	}
	
	
	public static DateFormat timeFormat=new SimpleDateFormat("HH:mm");
	public static DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
	public static DateFormat dateTimeFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	public static DecimalFormat paymentFormat=new DecimalFormat("###,###,##0.##");
	public static DecimalFormat paymentFormat0=new DecimalFormat("#########.##");
	public static DecimalFormat paymentFormat1=new DecimalFormat("###,###,##0.0");
	public static DecimalFormat paymentFormat2=new DecimalFormat("###,###,##0.00");
	public static NumberRenderer tablePayment=new NumberRenderer(App.paymentFormat2);
	public static Color whiteSmoke = new Color(253, 253, 253);
	
	public static Color selected = UIManager.getDefaults().getColor("List.selectionBackground");//new Color(0, 136, 204);
	public static Color aqua = new Color(0, 255, 255);
	public static Color gainsboro = new Color(220, 220, 220);
	public static Color blacksmoot = new Color(176, 176, 176);
	
	public static Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(App.gainsboro, 1),
            BorderFactory.createEmptyBorder(3, 3, 3,3 ));
	public static Border borderSelected = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(App.selected, 1),
            BorderFactory.createEmptyBorder(3, 3, 3,3 ));
	public static Border borderWhite = BorderFactory.createLineBorder(Color.WHITE);
	public static Border borderBlack = BorderFactory.createLineBorder(App.blacksmoot);
	public static Border borderBlackBottom5555 = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, App.blacksmoot), BorderFactory.createEmptyBorder(5, 5, 5, 5));
	public static Border borderBlackBottom3000 = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, App.blacksmoot), BorderFactory.createEmptyBorder(3, 0, 0, 0));
	
	public static Border borderBlackKananKiri = BorderFactory.createMatteBorder(0, 1, 0, 1, App.blacksmoot);
	public static Border borderBlackAtasBawahKiri = BorderFactory.createMatteBorder(1, 1, 1, 0, App.blacksmoot);
	public static Border borderBlackAtasBawahKanan = BorderFactory.createMatteBorder(1, 0, 1, 1, App.blacksmoot);
	
//	public static BorderLayout borderLayout;
//	public static BorderLayout getBorderLayout(){
//		if (borderLayout==null) {
//			borderLayout=new  BorderLayout();
//		}
//		return borderLayout;
//	}
	
	public static Font fontTitleToolbar;
	public static Font getFontTitleToolbar(){
		if (fontTitleToolbar==null) {
			JLabel l=new JLabel();
			Font f = l.getFont();
			fontTitleToolbar = new Font(f.getName(), Font.PLAIN, f.getSize() + 2);
		}
		return fontTitleToolbar;
	}
	
	public static Font fontTitleDialog;
	public static Font getFontTitleDialog(){
		if (fontTitleDialog==null) {
			JLabel l=new JLabel();
			Font f = l.getFont();
			fontTitleDialog = new Font(f.getName(), Font.BOLD, f.getSize());
		}
		return fontTitleDialog;
	}
	
	public static String [] bln={"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", 
			"September", "Oktober", "November", "Desember" };
	
	public static NamaBulan[] getBulan(){
		NamaBulan  nb[]= new NamaBulan[12];
		for (int i = 0; i < 12; i++) {
			nb[i]=new NamaBulan(i, bln[i]);
		}
		return nb;
	}

	public static void showDialog(Object o, JPanel panel){
		JDialog d = new JDialog(getWindow(o), ModalityType.APPLICATION_MODAL);
		d.getContentPane().add(panel);
		d.pack();
		d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - d
				.getPreferredSize().width) / 2, (Toolkit.getDefaultToolkit()
				.getScreenSize().height - d.getPreferredSize().height) / 2);
		d.setVisible(true);
	}
	
	
	public static Window getWindow(Object o){
		if (o instanceof Window) {
			return ((Window) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}
	
	
	private static Lang lang;

	public static Lang getLang() {
		if (lang == null) {
			lang = new Lang();
		}
		return lang;
	}
	
	public static String getT(String category, String text){
		return getLang().getText("com.bmb.app.lang."+category, text);
	}
	
	public static String getT(String text){
		return getLang().getText("com.basic.lang.app", text);
	}
	
//	private static AgamaDao agamaDao;
//
//	public static AgamaDao getAgamaDao() {
//		if (agamaDao == null) {
//			agamaDao = new AgamaDao();
//		}
//		return agamaDao;
//	}
	
	
	
	
	private static BosDao bosDao;

	public static BosDao getBosDao() {
		if (bosDao == null) {
			bosDao = new BosDao();
		}
		return bosDao;
	}
	
	
	
	
	private static UsrDao usrDao;

	public static UsrDao getUsrDao() {
		if (usrDao == null) {
			usrDao = new UsrDao();
		}
		return usrDao;
	}
	
	
	
	
	
	private static GrpDao grpDao;

	public static GrpDao getGrpDao() {
		if (grpDao == null) {
			grpDao = new GrpDao();
		}
		return grpDao;
	}
	
	
	private static NumberIdDao numberIdDao;

	public static NumberIdDao getNumberIdDao() {
		if (numberIdDao == null) {
			numberIdDao = new NumberIdDao();
		}
		return numberIdDao;
	}
	
	private static JenisPekerjaanDao jenisPekerjaanDao;

	public static JenisPekerjaanDao getJenisPekerjaanDao() {
		if (jenisPekerjaanDao == null) {
			jenisPekerjaanDao = new JenisPekerjaanDao();
		}
		return jenisPekerjaanDao;
	}
	
	
	
	

					/**
					 * di gunakan untuk one registri pada class db
					 */
	private static boolean firstRegisterClasses = true;

	/**
	 * mendapatkan database dari pool, bila databse belum tersedia maka akan
	 * dibuatkan database baru
	 * 
	 * @return OObjectDatabaseTx
	 */
	public static OObjectDatabaseTx getDb() {
		//String url = "local:c://test/db";
		String url = "local:db/db";
		String user = "admin";
		String pwd = "admin";

		String packDb = "com.basic.entity";
		try {
			OObjectDatabaseTx db = OObjectDatabasePool.global().acquire(url,
					user, pwd);

			if (firstRegisterClasses) {
				// try {
				// new SchemaDb().createDbIfNotExist(url);
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				db.getEntityManager().registerEntityClasses(packDb);
				firstRegisterClasses = false;
			}
			return db;
		} catch (OStorageException e) {
			App.printErr(e);
			OObjectDatabaseTx db = new OObjectDatabaseTx(url).create();
			db.getEntityManager().registerEntityClasses(packDb);
			//new StartDb().createSchemaDb();
			new StartDbMlm().createSchemaDb();
			return db;
		}
	}

	/**
	 * @return ODatabaseDocumentTx
	 */
	public static ODatabaseDocumentTx getDbd() {
		//String url = "local:c://test/db";
		String url = "local:db/db";
		String user = "admin";
		String pwd = "admin";

		String packDb = "com.bmb.app.entity";
		String packDb2 = "com.mlm.entity";

		try {
			ODatabaseDocumentTx dbd = ODatabaseDocumentPool.global().acquire(
					url, user, pwd);
			return dbd;
		} catch (OStorageException e) {
			e.printStackTrace();
			OObjectDatabaseTx db = new OObjectDatabaseTx(url).create();
			db.getEntityManager().registerEntityClasses(packDb);
			db.getEntityManager().registerEntityClasses(packDb2);
//			new StartDb().createSchemaDb();
			new StartDbMlm().createSchemaDb();
			ODatabaseDocumentTx dbd = ODatabaseDocumentPool.global().acquire(
					url, user, pwd);
			return dbd;
		}

	}
	
	public static ODatabaseDocumentTx getDbdLocal(){
		ODatabaseDocumentTx dbd=getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(dbd);
		return dbd;
	}
	
//	public static Icon getIcon(ODatabaseDocumentTx db, String nameIcon){
//			return new ImageIcon(App.class.getResource(App.getT(db, nameIcon)));
//	}
//	public static ImageIcon getImage(ODatabaseDocumentTx db, String nameIcon){
//		return new ImageIcon(App.class.getResource(App.getT(db, nameIcon)));
//}
	
	public static Icon getIcon(String nameIcon){
		//App.info(nameIcon);
		return new ImageIcon(App.class.getResource(nameIcon));
}
public static ImageIcon getImage( String nameIcon){
	return new ImageIcon(App.class.getResource(nameIcon));
}


public static void showErrorEmptySelect(ODatabaseDocumentTx db, String namaField){
	JOptionPane.showMessageDialog(null,namaField+" belum di pilih");
}	

	public static void showErrorFieldEmpty(ODatabaseDocumentTx db, String namaField){
		JOptionPane.showMessageDialog(null,namaField+" belum di isi");
	}
	public static void showErrorNotValid(ODatabaseDocumentTx db, String namaField){
		JOptionPane.showMessageDialog(null, namaField+" tidak valid");
	}
	public static void showErrorNilaiHarusDiAtasNol(ODatabaseDocumentTx db, String namaField){
		JOptionPane.showMessageDialog(null, namaField+" harus lebih besar dari nol");
	}
	public static void showSaveOk(){
		JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
	}
	public static void showErrorJumlahTidakFalid(){
		JOptionPane.showMessageDialog(null, "Jumlah tidak valid. Jumlah tidak boleh kurang dari 1.");
	}
	public static void showPelangganTidakDapatDiHapus(){
		JOptionPane.showMessageDialog(null, "Pelanggan masih punya Piutang.\nData tidak dapat di Hapus.");
	}
	public static void showErrorPasswordTidakSamadenganKonfirmasi(){
		JOptionPane.showMessageDialog(null, "Password dan Ketik Ulang tidak sesuai");
	}
//	public static void showErrorUsernameTidakTerdaftar(){
//		JOptionPane.showMessageDialog(null, "Username tidak terdaftar");
//	}
	public static void showErrorPasswordSalah(){
		JOptionPane.showMessageDialog(null, "Silahkan ulangi, password yang Anda inputkan tidak sesuai.");
	}
	public static void showErrorSN(){
		JOptionPane.showMessageDialog(null, "Kode Aktifasi yang Anda masukkan tidak sesuai.");
	}
	public static void showErrorDataSudahAda(ODatabaseDocumentTx db, String namaField){
		JOptionPane.showMessageDialog(null, namaField+" sudah terdaftar");
	}
	
	public static void showErrSementara(String x){
		JOptionPane.showMessageDialog(null, x);
	}
	public static void printErr(Exception e) {
		e.printStackTrace();
	}

	public static void info(String note) {
		System.out.println(note);
	}
	
	
	
	
	
	public static void showErrorJumlahKuotaSudahPenuh(ODatabaseDocumentTx db, String namaPelanggan, String Paket){
		JOptionPane.showMessageDialog(null,"Target Downline Pelanggan "+namaPelanggan+" dengan paket "+Paket+" sudah terpenuhi");
	}	
	
	
	
	
	
	
	private static PaketDao paketDao;

	public static PaketDao getPaketDao() {
		if (paketDao == null) {
			paketDao = new PaketDao();
		}
		return paketDao;
	}
	
	private static PelangganDao pelangganDao;

	public static PelangganDao getPelangganDao() {
		if (pelangganDao == null) {
			pelangganDao = new PelangganDao();
		}
		return pelangganDao;
	}

	private static StatusPelangganDao statusPelangganDao;

	public static StatusPelangganDao getStatusPelangganDao() {
		if (statusPelangganDao == null) {
			statusPelangganDao = new StatusPelangganDao();
		}
		return statusPelangganDao;
	}
	
	private static PpDao ppDao;

	public static PpDao getPpDao() {
		if (ppDao == null) {
			ppDao = new PpDao();
		}
		return ppDao;
	}
}
