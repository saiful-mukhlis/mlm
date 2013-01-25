package com.basic.table.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.basic.comp.abst.TableModelDefault;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import com.basic.dao.impl.GrpDao;
import com.basic.dao.impl.UsrDao;
import com.basic.db.FGrp;
import com.basic.db.FJenisPekerjaan;
import com.basic.db.FUsr;
import com.basic.lang.LUsr;
import com.basic.lang.LWindow;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrTM extends TableModelDefault {

	public UsrTM(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;
	protected final int USERNAME = 3;
	protected final int GROUP = 4;
	protected final int ALAMAT = 5;
	protected final int KOTA = 6;
	protected final int NO_IDENTITAS = 7;
	protected final int JENIS_IDENTITAS = 8;
	protected final int KOTA_LAHIR = 9;
	protected final int TGL_LAHIR = 10;
	protected final int JENIS_KELAMIN = 11;
	protected final int NO_TELP = 12;
	protected final int NO_HP1 = 13;
	protected final int NO_HP2 = 14;
	protected final int PIN_BB = 15;
	protected final int TGL_MASUK = 16;
	protected final int GAJI = 17;
	protected final int JENIS_PEKERJAAN = 18;
	protected final int PENDIDIKAN_TERAKHIR = 19;
	protected final int STATUS = 20;

	public void initNamaKolom(ODatabaseDocumentTx db) {
		namaKolom = new String[21];
		namaKolom[NO] = LUsr.NO;
		namaKolom[CODE] = LUsr.CODE;
		namaKolom[NAMA] = LUsr.NAMA;
		namaKolom[USERNAME] = LUsr.USERNAME;
		namaKolom[GROUP] = LUsr.GRP;
		namaKolom[ALAMAT] = LUsr.ALAMAT;
		namaKolom[KOTA] = LUsr.KOTA;
		namaKolom[NO_IDENTITAS] = LUsr.NO_IDENTITAS;
		namaKolom[JENIS_IDENTITAS] = LUsr.JENIS_IDENTITAS;
		namaKolom[KOTA_LAHIR] = LUsr.KOTA_LAHIR;
		namaKolom[TGL_LAHIR] = LUsr.TGL_LAHIR;
		namaKolom[JENIS_KELAMIN] = LUsr.JENIS_KELAMIN;
		namaKolom[NO_TELP] = LUsr.NO_TELP;
		namaKolom[NO_HP1] = LUsr.NO_HP1;
		namaKolom[NO_HP2] = LUsr.NO_HP2;
		namaKolom[PIN_BB] = LUsr.PIN_BB;
		namaKolom[TGL_MASUK] = LUsr.TGL_MASUK;
		namaKolom[GAJI] = LUsr.GAJI;
		namaKolom[JENIS_PEKERJAAN] = LUsr.JENIS_PEKERJAAN;
		namaKolom[PENDIDIKAN_TERAKHIR] = LUsr.PENDIDIKAN_TERAKHIR;
		namaKolom[STATUS] = LUsr.STATUS;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m=model.get(rowIndex);
		UsrDao d=App.getUsrDao();
		if (columnIndex == NO) {
			return getNo(rowIndex);
		} else if (columnIndex == CODE) {
			return d.getCode(m);
		} else if (columnIndex == USERNAME) {
			return d.getUsername(m);
		} else if (columnIndex == GROUP) {
			return d.getGrp(m);
		}

		else if (columnIndex == NAMA) {
			return d.getNama(m);
		} else if (columnIndex == ALAMAT) {
			return d.getAlamat(m);
		} else if (columnIndex == KOTA) {
			return d.getKota(m);
		} else if (columnIndex == JENIS_IDENTITAS) {
			return d.getJenisIdentitas(m);
		} else if (columnIndex == NO_IDENTITAS) {
			return d.getNoIdentitas(m);
		} else if (columnIndex == KOTA_LAHIR) {
			return d.getKotaLahir(m);
		} else if (columnIndex == TGL_LAHIR) {
			return d.getTglLahir(m);
		} else if (columnIndex == JENIS_KELAMIN) {
			return d.getJenisKelamin(m);
		} else if (columnIndex == NO_TELP) {
			return d.getNoTelp(m);
		} else if (columnIndex == NO_HP1) {
			return d.getNoHp1(m);
		} else if (columnIndex == NO_HP2) {
			return d.getNoHp2(m);
		} else if (columnIndex == PIN_BB) {
			return d.getPinBb(m);
		} else if (columnIndex == TGL_MASUK) {
			return d.getTglMasuk(m);
		} else if (columnIndex == GAJI) {
			return d.gajiToString(m);
		} else if (columnIndex == JENIS_PEKERJAAN) {
			return d.jenisKelaminToString(m);
		} else if (columnIndex == PENDIDIKAN_TERAKHIR) {
			return d.getPendidikanTerakhir(m);
		} else if (columnIndex == STATUS) {
			return d.statusToString(m);
		}

		else {
			return null;
		}
	}


	@Override
	public void initDao() {
		dao = App.getUsrDao();
	}

	protected List<ODocument> model2;
	protected List<ODocument> model3;

	
	
	public void actionSearchOneField(String col, String value){
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		loadJumlahDataSearchLike(db, col, value);
		loadDataModelSearchLike(db, col, value);
		db.close();
		fireTableDataChanged();
		if (getTable() != null) {
			getTable().selected();
		}
	}
	
	public void actionSearchOneField(String col,String col2, String value){
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		loadJumlahDataSearchLikeCollection(db, col,col2, value);
		loadDataModelSearchLikeCollection(db, col,col2, value);
		db.close();
		fireTableDataChanged();
		if (getTable() != null) {
			getTable().selected();
		}
	}

	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	protected String colSearch = FUsr.NAMA;
	protected JPopupMenu menuItemSearch;

	public TextFieldSearch getFieldSearch() {
		if (fieldSearch == null) {
			fieldSearch = new TextFieldSearch();
			fieldSearch = new TextFieldSearch();
			fieldSearch.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					actionSearch();
				}
			});
		}
		return fieldSearch;
	}

	public SplitButton getItemSearch() {
		if (itemSearch == null) {
			itemSearch = new SplitButton(LWindow.KET_SEARCH + LUsr.NAMA);
			itemSearch.setBackground(Color.WHITE);

			menuItemSearch = new JPopupMenu();

			JMenuItem item = new JMenuItem(LWindow.KET_SEARCH + LUsr.CODE);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.CODE);
					colSearch = FUsr.CODE;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.USERNAME);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.USERNAME);
					colSearch = FUsr.USERNAME;
				}
			});
			menuItemSearch.add(item);
//			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.PASSWORD);
//			item.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
//					itemSearch.setText(LWindow.KET_SEARCH + LUsr.PASSWORD);
//					colSearch = LUsr.PASSWORD;
//				}
//			});
//			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.GRP);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.GRP);
					colSearch = FUsr.GRP;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.NAMA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.NAMA);
					colSearch = FUsr.NAMA;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.ALAMAT);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.ALAMAT);
					colSearch = FUsr.ALAMAT;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.KOTA);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.KOTA);
					colSearch = FUsr.KOTA;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.NO_IDENTITAS);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.NO_IDENTITAS);
					colSearch = FUsr.NO_IDENTITAS;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.JENIS_IDENTITAS);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH
							+ LUsr.JENIS_IDENTITAS);
					colSearch = FUsr.JENIS_IDENTITAS;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.KOTA_LAHIR);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.KOTA_LAHIR);
					colSearch = FUsr.KOTA_LAHIR;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.TGL_LAHIR);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.TGL_LAHIR);
					colSearch = FUsr.TGL_LAHIR;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.JENIS_KELAMIN);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.JENIS_KELAMIN);
					colSearch = FUsr.JENIS_KELAMIN;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.NO_TELP);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.NO_TELP);
					colSearch = FUsr.NO_TELP;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.NO_HP1);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.NO_HP1);
					colSearch = FUsr.NO_HP1;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.NO_HP2);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.NO_HP2);
					colSearch = FUsr.NO_HP2;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.PIN_BB);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.PIN_BB);
					colSearch = FUsr.PIN_BB;
				}
			});
			menuItemSearch.add(item);

			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.TGL_MASUK);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.TGL_MASUK);
					colSearch = FUsr.TGL_MASUK;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.GAJI);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.GAJI);
					colSearch = FUsr.GAJI;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.JENIS_PEKERJAAN);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH
							+ LUsr.JENIS_PEKERJAAN);
					colSearch = FUsr.JENIS_PEKERJAAN;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.PENDIDIKAN_TERAKHIR);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH
							+ LUsr.PENDIDIKAN_TERAKHIR);
					colSearch = FUsr.PENDIDIKAN_TERAKHIR;
				}
			});
			menuItemSearch.add(item);
			item = new JMenuItem(LWindow.KET_SEARCH + LUsr.STATUS);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					itemSearch.setText(LWindow.KET_SEARCH + LUsr.STATUS);
					colSearch = FUsr.STATUS;
				}
			});
			menuItemSearch.add(item);
			itemSearch.setDropDownMenu(menuItemSearch);
			
			itemSearch.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					actionSearch();
				}
			});

		}
		return itemSearch;
	}
	
	public void actionSearch(){

		String tmp=fieldSearch.getText();
		if (!tmp.trim().equalsIgnoreCase("")) {
			if (colSearch.equals(FUsr.GRP)) {
				actionSearchOneField(colSearch,FGrp.NAME, tmp);
			}
			else if (colSearch.equals(FUsr.JENIS_PEKERJAAN)) {
				actionSearchOneField(colSearch,FJenisPekerjaan.NAMA, tmp);
			}
			else if (colSearch.equals(FUsr.STATUS)) {
				String[] tmps=App.getUsrDao().getStatusData();
				int i=0;
				for (String s : tmps) {
					if (s.equalsIgnoreCase(tmp)) {
						tmp=i+"";
					}
					i++;
				}
				actionSearchOneField(colSearch, tmp);
			}
			else{
				actionSearchOneField(colSearch, tmp);
			}
			
		}else{
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set(db);
			reload(db);
			db.close();
		}
	
	}
}
