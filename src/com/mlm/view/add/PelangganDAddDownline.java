package com.mlm.view.add;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;

import org.basic.comp.abst.DialogDefault;
import org.basic.comp.abst.FormBuilder;
import org.basic.comp.base.ComboBox;
import org.basic.comp.base.DatePicker;
import org.basic.comp.base.ScrollPane;
import org.basic.comp.base.TextField;
import org.basic.comp.base.TextFieldAmount;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import com.basic.lang.LPelanggan;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.mlm.comp.impl.master.PelangganM;
import com.mlm.dao.impl.PpDao;
import com.mlm.db.FPaket;
import com.mlm.db.FPelanggan;
import com.mlm.db.FPp;
import com.mlm.lang.LPp;
import com.mlm.table.model.PaketTMS;
import com.mlm.table.model.PelangganTMS;
import com.mlm.table.model.PpTMS;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;

public class PelangganDAddDownline extends DialogDefault {
	
	protected ODocument ppPelanggan;
	
	protected TextField code;
	protected DatePicker tglDaftar;
	protected TextField downline;
	protected TextField paket;
	protected TextFieldAmount harga;
	protected TextFieldAmount bayar;
	protected TextFieldAmount kembali;
	protected ComboBox status;

	// protected ODocumentToString [] statusModel ;

	@Override
	public void actionReset() {
		code.setText("Auto");
		downline.setText("");
//		paket.setText("");
//		harga.setText("");
		tglDaftar.setDate(new Date());
		bayar.setText("");
		kembali.setText("");
		status.setSelectedIndex(0);
		requestDefaultFocus();
	}

	@Override
	public void requestDefaultFocus() {
		downline.requestFocus();
	}

	public void initComponent(ODatabaseDocumentTx db) {
		code = new TextField();
		downline = new TextField();
		paket = new TextField();
		paket.setEditable(false);
		harga = new TextFieldAmount();
		harga.setEditable(false);
		kembali = new TextFieldAmount();
		kembali.setEditable(false);
		tglDaftar = new DatePicker();
		bayar = new TextFieldAmount();
		// statusModel = App. getPelangganDao().getStatusData(db);
		status = new ComboBox(App.getPpDao().getStatusData());
		
		bayar.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if (oDocumentPaket!=null) {
					BigDecimal tmp2=App.getPaketDao().getHarga(oDocumentPaket);
					if (tmp2!=null) {
						BigDecimal tmp1=TextFieldAmount.getValue(bayar);
						int c=tmp1.compareTo(tmp2);
						if (c==0) {
							kembali.setText("0.00");
						}else if(c==-1){
							// belum lunas
							kembali.setText("0.00");
							status.setSelectedIndex(1);
							status.setEditable(false);
						}else if(c==1){
							BigDecimal tmp3=tmp1.add(tmp2.negate());
							kembali.setText(App.paymentFormat2.format(tmp3));
						}
					}
					
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void setFocusEnter() {
		setFocusEnter(code, downline);
		setFocusEnter(downline, paket);
//		setFocusEnter(paket, harga);
//		setFocusEnter(harga, tglDaftar);
		setFocusEnter(paket, bayar);
		setFocusEnter(bayar, status);
		setFocusEnter(status, save);
	}

	@Override
	public void init(ODatabaseDocumentTx db) {
		icon = PelangganM.ICON_48;
		super.init(db);
		labelTitle.setText("Daftar Paket");
		labelNote.setText("Isi data dengan benar");
		initComponent(db);
		buildForm(db);
		
		
		initPopupPp(db);
//		initPopup(db);
	}

	public void buildForm(ODatabaseDocumentTx db) {
		initComponent(db);

		StringBuilder col = new StringBuilder();
		StringBuilder row = new StringBuilder();
		col.append("10px,");
		col.append("r:p,10px,f:80px:g,10px,");
		col.append("r:p,10px,f:80px:g,30px,");
		col.append("r:p,10px,f:80px:g,10px,");
		col.append("r:p,10px,f:80px:g,10px,");
		col.append("10px,");

		row.append("3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");
		row.append("p,3dlu,");

		FormLayout l = new FormLayout(col.toString(), row.toString());

		// l.setColumnGroups(new int[][] { { 4, 8 } });
		FormBuilder b = new FormBuilder(l);

		// append(String i8n, Component c, int x, int y, int w)
		b.append(LPp.CODE, code, 2, 2, 5);
		b.append(LPp.TGL_DAFTAR, tglDaftar, 10, 2, 5);
		b.append(LPp.DOWNLINE, downline, 2, 4, 5);
		b.append(LPp.NAMA_PAKET, paket, 10, 4, 5);
		b.append(LPp.HARGA, harga, 10, 6, 5);
		b.append(LPp.BAYAR, bayar, 2, 6, 5);
		b.append(LPp.STATUS, status, 2, 8, 5);
		b.append(LPp.KEMBALI, kembali, 10, 8, 5);

		panelForm = b.getPanel();

	}

	
	
	
	
	
	
	protected ODocument oDocumentPelanggan;
	protected JXTable tPopupPelanggan;
	protected PelangganTMS tModelPelanggan;
	protected ScrollPane scrolPanePopupPp;

	private JPopupMenu popupPelanggan;

	public void initPopupPp(ODatabaseDocumentTx db) {
		tModelPelanggan = new PelangganTMS(db);
		tPopupPelanggan = new JXTable(tModelPelanggan);
		tPopupPelanggan.setHighlighters(HighlighterFactory.createAlternateStriping());
		tPopupPelanggan.setShowHorizontalLines(false);
		tPopupPelanggan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		tPopupPelanggan.setTableHeader(null);

		popupPelanggan = new JPopupMenu();
		popupPelanggan.setBackground(Color.WHITE);

		scrolPanePopupPp = new ScrollPane(tPopupPelanggan);
		scrolPanePopupPp.setBorder(BorderFactory.createLineBorder(App.selected));

		popupPelanggan.setBorder(BorderFactory.createEmptyBorder());
		popupPelanggan.add(scrolPanePopupPp);

		downline.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (oDocumentPelanggan != null) {
						String pel = downline.getText();
						if (pel.equalsIgnoreCase("")) {
							oDocumentPelanggan = null;
						} else {
							String[] tmp = pel.split(" | ");
							if (tmp.length < 2) {
								oDocumentPelanggan = null;
							} else {
								String codep = oDocumentPelanggan.field(FPelanggan.CODE);
								if (codep.equalsIgnoreCase(tmp[0].trim())) {
									downline.requestFocus();
								} else {
									oDocumentPelanggan = null;
									showPopupPelanggan(e);
								}
							}
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (tPopupPelanggan.isVisible()) {
						tPopupPelanggan.requestFocus();
						if (tPopupPelanggan.getRowCount() > 0) {
							tPopupPelanggan.getSelectionModel().setSelectionInterval(0,
									0);
						}
					}

				} else {

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (oDocumentPelanggan == null) {
					showPopupPelanggan(e);
				} else {
					String pel = downline.getText();
					String[] tmp = pel.split(" | ");
					if (tmp.length < 2) {
						oDocumentPelanggan = null;
						showPopupPelanggan(e);
					}
				}
			}

		});

		tPopupPelanggan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// App.info(tPopup.getSelectedRow()+"y");
				// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"y");
				String tmp1 = e.getKeyChar() + "";
				if (tmp1.matches("[a-zA-Z0-9_ ]")) {
					String tmp = downline.getText() + e.getKeyChar();
					downline.setText(tmp);
					tModelPelanggan.setTextSearch(downline.getText().toLowerCase());
					ODatabaseDocumentTx db = App.getDbd();
					ODatabaseRecordThreadLocal.INSTANCE.set(db);
					tModelPelanggan.reload(db);
					db.close();
					oDocumentPelanggan = null;
					downline.requestFocus();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// App.info(tPopup.getSelectedRow()+"x");
						// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"x");
						if (tPopupPelanggan.getSelectedRow() != -1) {
							// App.info(tPopup.getSelectedRow()+"");
							// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"");
							ODocument o = (ODocument) tModelPelanggan.getModels().get(
									tPopupPelanggan.convertRowIndexToModel(tPopupPelanggan
											.getSelectedRow()));
							if (o != null) {
								String codex = App.getPelangganDao().getCode(o);
								String namex = App.getPelangganDao().getNamaToko(o);
								downline.setText(codex + " | " + namex);
								oDocumentPelanggan = o;
								
//								oDocumentPaket=App.getPpDao().getPaket(null, o);
//								String codex2 = App.getPaketDao().getCode(oDocumentPaket);
//								String namex2 = App.getPaketDao().getNama(oDocumentPaket);
//								paket.setText(codex2 + " | " + namex2);
//								
//								paket.setEditable(false);
//								BigDecimal b=App.getPaketDao().getHarga(oDocumentPaket);
//								if (b!=null) {
//									String h=App.paymentFormat2.format(b);
//									harga.setText(h);
//								}else{
//									harga.setText("0,00");
//								}
								
							} else {
								oDocumentPelanggan = null;
//								paket.setEditable(true);
							}

						}
						popupPelanggan.setVisible(false);
						downline.requestFocus();
					}

				}

			}

		});

		downline.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (oDocumentPelanggan != null) {
					String pel = downline.getText();
					if (pel.equalsIgnoreCase("")) {
						oDocumentPelanggan = null;
//						paket.setEditable(true);
					} else {
						String[] tmp = pel.split(" | ");
						if (tmp.length == 0) {
							oDocumentPelanggan = null;
//							paket.setEditable(true);
						} else {
							String codep = App.getPaketDao().getCode(oDocumentPelanggan);
							if (!codep.equalsIgnoreCase(tmp[0].trim())) {
								oDocumentPelanggan = null;
								downline.setText("");
//								paket.setEditable(true);
							}
						}
					}
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// harga.setValue(DataUser.getProduct().field(ProductDao.harga));
			}
		});
	}

	public void showPopupPelanggan(KeyEvent e) {

		String tmp = downline.getText() + e.getKeyChar();
		tmp = tmp.trim();
		if (tmp.equalsIgnoreCase("")) {
			tmp = null;
		}
		tModelPelanggan.setTextSearch(tmp);
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		tModelPelanggan.reload(db);
		db.close();
		Dimension d1 = scrolPanePopupPp.getPreferredSize();
		Dimension d2 = downline.getSize();// .getPreferredSize();
		d1.width = d2.width;
		d1.height = 150;
		scrolPanePopupPp.setPreferredSize(d1);
		tPopupPelanggan.getColumnModel().getColumn(0).setPreferredWidth(10);
		tPopupPelanggan.getColumnModel().getColumn(1).setPreferredWidth(300);

		tPopupPelanggan.packAll();
		popupPelanggan.show(downline, 0, 20);
		downline.requestFocus();
	}
	
	
	protected ODocument oDocumentPaket;
//	protected JXTable tPopupPaket;
//	protected PaketTMS tModelPaket;
//	protected ScrollPane scrolPanePopupPaket;
//
//	private JPopupMenu popup;

//	public void initPopup(ODatabaseDocumentTx db) {
//		tModelPaket = new PaketTMS(db);
//		tPopupPaket = new JXTable(tModelPaket);
//		tPopupPaket.setHighlighters(HighlighterFactory.createAlternateStriping());
//		tPopupPaket.setShowHorizontalLines(false);
//		tPopupPaket.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//		tPopupPaket.setTableHeader(null);
//
//		popup = new JPopupMenu();
//		popup.setBackground(Color.WHITE);
//
//		scrolPanePopupPaket = new ScrollPane(tPopupPaket);
//		scrolPanePopupPaket.setBorder(BorderFactory.createLineBorder(App.selected));
//
//		popup.setBorder(BorderFactory.createEmptyBorder());
//		popup.add(scrolPanePopupPaket);
//
//		paket.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					if (oDocumentPaket != null) {
//						String pel = paket.getText();
//						if (pel.equalsIgnoreCase("")) {
//							oDocumentPaket = null;
//						} else {
//							String[] tmp = pel.split(" | ");
//							if (tmp.length < 2) {
//								oDocumentPaket = null;
//							} else {
//								String codep = oDocumentPaket.field(Paket.CODE);
//								if (codep.equalsIgnoreCase(tmp[0].trim())) {
//									paket.requestFocus();
//								} else {
//									oDocumentPaket = null;
//									showPopupPaket(e);
//								}
//							}
//						}
//					}
//				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//					if (tPopupPaket.isVisible()) {
//						tPopupPaket.requestFocus();
//						if (tPopupPaket.getRowCount() > 0) {
//							tPopupPaket.getSelectionModel().setSelectionInterval(0,
//									0);
//						}
//					}
//
//				} else {
//
//				}
//
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//			}
//
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if (oDocumentPaket == null) {
//					showPopupPaket(e);
//				} else {
//					String pel = paket.getText();
//					String[] tmp = pel.split(" | ");
//					if (tmp.length < 2) {
//						oDocumentPaket = null;
//						showPopupPaket(e);
//					}
//				}
//			}
//
//		});
//
//		tPopupPaket.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// App.info(tPopup.getSelectedRow()+"y");
//				// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"y");
//				String tmp1 = e.getKeyChar() + "";
//				if (tmp1.matches("[a-zA-Z0-9_ ]")) {
//					String tmp = paket.getText() + e.getKeyChar();
//					paket.setText(tmp);
//					tModelPaket.setTextSearch(paket.getText().toLowerCase());
//					ODatabaseDocumentTx db = App.getDbd();
//					ODatabaseRecordThreadLocal.INSTANCE.set(db);
//					tModelPaket.reload(db);
//					db.close();
//					oDocumentPaket = null;
//					paket.requestFocus();
//				} else {
//					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//						// App.info(tPopup.getSelectedRow()+"x");
//						// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"x");
//						if (tPopupPaket.getSelectedRow() != -1) {
//							// App.info(tPopup.getSelectedRow()+"");
//							// App.info(tPopup.convertRowIndexToModel(tPopup.getSelectedRow())+"");
//							ODocument o = (ODocument) tModelPaket.getModels().get(
//									tPopupPaket.convertRowIndexToModel(tPopupPaket
//											.getSelectedRow()));
//							if (o != null) {
//								String codex = App.getPaketDao().getCode(o);
//								String namex = App.getPaketDao().getNama(o);
//								paket.setText(codex + " | " + namex);
//								oDocumentPaket = o;
//							} else {
//								oDocumentPaket = null;
//							}
//
//						}
//						popup.setVisible(false);
//						paket.requestFocus();
//					}
//
//				}
//
//			}
//
//		});
//
//		paket.addFocusListener(new FocusListener() {
//
//			@Override
//			public void focusLost(FocusEvent e) {
//				if (oDocumentPaket != null) {
//					String pel = paket.getText();
//					if (pel.equalsIgnoreCase("")) {
//						oDocumentPaket = null;
//					} else {
//						String[] tmp = pel.split(" | ");
//						if (tmp.length == 0) {
//							oDocumentPaket = null;
//						} else {
//							String codep = App.getPaketDao().getCode(oDocumentPaket);
//							if (!codep.equalsIgnoreCase(tmp[0].trim())) {
//								oDocumentPaket = null;
//								paket.setText("");
//							}else {
//								BigDecimal b=App.getPaketDao().getHarga(oDocumentPaket);
//								if (b!=null) {
//									String h=App.paymentFormat2.format(b);
//									harga.setText(h);
//								}else{
//									harga.setText("0,00");
//								}
//								
//							}
//						}
//					}
//				}
//
//			}
//
//			@Override
//			public void focusGained(FocusEvent e) {
//				// harga.setValue(DataUser.getProduct().field(ProductDao.harga));
//			}
//		});
//	}
//
//	public void showPopupPaket(KeyEvent e) {
//
//		String tmp = paket.getText() + e.getKeyChar();
//		tmp = tmp.trim();
//		if (tmp.equalsIgnoreCase("")) {
//			tmp = null;
//		}
//		tModelPaket.setTextSearch(tmp);
//		ODatabaseDocumentTx db = App.getDbd();
//		ODatabaseRecordThreadLocal.INSTANCE.set(db);
//		tModelPaket.reload(db);
//		db.close();
//		Dimension d1 = scrolPanePopupPaket.getPreferredSize();
//		Dimension d2 = paket.getSize();// .getPreferredSize();
//		d1.width = d2.width;
//		d1.height = 150;
//		scrolPanePopupPaket.setPreferredSize(d1);
//		tPopupPaket.getColumnModel().getColumn(0).setPreferredWidth(10);
//		tPopupPaket.getColumnModel().getColumn(1).setPreferredWidth(300);
//
//		tPopupPaket.packAll();
//		popup.show(paket, 0, 20);
//		paket.requestFocus();
//	}
	
	
	
	
	
	
	@Override
	public void save(ODatabaseDocumentTx db) {
		PpDao d=App.getPpDao();
		model=new ODocument(d.getClassName());
		d.setCode(model,code);
		d.setPaket(model,oDocumentPaket);
		d.setUpLine(model, ppPelanggan);
		d.setBayar(model, bayar);
		d.setPelanggan(model, oDocumentPelanggan);
		d.setTglDaftar(model, tglDaftar);
		

		BigDecimal tmp2=App.getPaketDao().getHarga(oDocumentPaket);
		if (tmp2!=null) {
			BigDecimal tmp1=TextFieldAmount.getValue(bayar);
			int c=tmp1.compareTo(tmp2);
			if (c==0 || c==1) {
				//lunas
				d.setTglLunas(model, d.getTglDaftar(model));
				d.setHistoryBayar(db, model, d.getTglDaftar(model), tmp2, new BigDecimal(0));
			}else if(c==-1){
				// belum lunas
				d.setHistoryBayar(db, model, d.getTglDaftar(model), tmp1, tmp2.add(tmp1.negate()));
			}
		}
		
		
		
		int ind=status.getSelectedIndex();
		if (ind>1) {
			d.setTglAktif(model, d.getTglDaftar(model));
		}
		d.setStatus(model, ind);
		
		
		try{
			  db.begin(TXTYPE.OPTIMISTIC);
			  d.save(db, model);
				
				List<ODocument> listTmp=App.getPelangganDao().getPakets(db, oDocumentPelanggan);
				listTmp.add(model);
				App.getPelangganDao().setPakets(oDocumentPelanggan, listTmp);
				App.getPelangganDao().update(db, oDocumentPelanggan);
				
				if (ppPelanggan!=null) {
					List<ODocument> listTmp2=App.getPpDao().getDownlines(db, ppPelanggan);
					ODocument tmpPaket=App.getPpDao().getPaket(db, ppPelanggan);
//					int jmlDownline=App.getPaketDao().getDownline(tmpPaket);
//					if (listTmp2.size()<jmlDownline || jmlDownline==0) {
						listTmp2.add(model);
//						App.getPpDao().setJmlDownline(ppPelanggan, listTmp2.size());
						App.getPpDao().setDownlines(ppPelanggan, listTmp2);
						App.getPpDao().recursivePenambahanDownline(db, ppPelanggan);
//						App.getPpDao().update(db, ppPelanggan);
//					}
					
					
				}
				
				int tmpTotalPelanggan=App.getPaketDao().getTotalPelanggan(oDocumentPaket);
				tmpTotalPelanggan++;
				App.getPaketDao().setTotalPelanggan(oDocumentPaket, tmpTotalPelanggan);
				App.getPaketDao().update(db, oDocumentPaket);
				
			  db.commit();
			}catch( Exception e ){
			  db.rollback();
			}

		
		
		
	}

	@Override
	public boolean validate(ODatabaseDocumentTx db) {
		PpDao d = App.getPpDao();
		if (!d.vCode(db, code)) {
			return false;
		}
//		if (oDocumentPelanggan==null) {
//			App.showErrorEmptySelect(db, LPp.NAMA_PELANGGAN);
//			return false;
//		}
		if (oDocumentPaket==null) {
			App.showErrorEmptySelect(db, LPp.NAMA_PAKET);
			return false;
		}
		
		if (ppPelanggan!=null) {
			//cek apakah melebihi kuota
			int tmp=App.getPaketDao().getDownline(oDocumentPaket);
			int tmp2=App.getPpDao().getJmlDownline(ppPelanggan);
			if (tmp<=tmp2) {
				App.showErrorJumlahKuotaSudahPenuh(db, App.getPpDao().pelangganToString(db, ppPelanggan), App.getPpDao().paketToString(db, ppPelanggan));
				return false;
			}
					
		}
		

		return true;
	}

	@Override
	public void load(ODocument model) {
		if (model.field("@class").equals(FPelanggan.TABLE)) {
			ppPelanggan=model;
		}else if (model.field("@class").equals(FPp.TABLE)) {
			oDocumentPelanggan=model;
			ppPelanggan=model;
			
			oDocumentPaket=App.getPpDao().getPaket(null, model);


			String codex2 = App.getPaketDao().getCode(oDocumentPaket);
			String namex2 = App.getPaketDao().getNama(oDocumentPaket);
			paket.setText(codex2 + " | " + namex2);
			
			paket.setEditable(false);
			BigDecimal b=App.getPaketDao().getHarga(oDocumentPaket);
			if (b!=null) {
				String h=App.paymentFormat2.format(b);
				harga.setText(h);
			}else{
				harga.setText("0,00");
			}
			
		
		}
	}

	

}
