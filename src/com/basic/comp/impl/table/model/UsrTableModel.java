package com.basic.comp.impl.table.model;

import com.basic.db.FGrp;
import com.basic.db.FJenisPekerjaan;
import com.basic.db.FUsr;
import com.basic.entity.Usr;
import com.basic.lang.LUsr;
import com.basic.lang.LWindow;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.abst.FactorySearch;
import org.basic.comp.abst.TableModelAbs;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class UsrTableModel extends TableModelAbs implements FactorySearch{
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

	public UsrTableModel() {
		super();
	}

	@Override
	public void init() {
		super.init();
		
		dao=App.getUsrDao();
		
		nameColumn = new String[21];
		nameColumn[NO] = LUsr.NO;
		nameColumn[CODE] = LUsr.CODE;
		nameColumn[NAMA] = LUsr.NAMA;
		nameColumn[USERNAME] = LUsr.USERNAME;
		nameColumn[GROUP] = LUsr.GRP;
		nameColumn[ALAMAT] = LUsr.ALAMAT;
		nameColumn[KOTA] = LUsr.KOTA;
		nameColumn[NO_IDENTITAS] = LUsr.NO_IDENTITAS;
		nameColumn[JENIS_IDENTITAS] = LUsr.JENIS_IDENTITAS;
		nameColumn[KOTA_LAHIR] = LUsr.KOTA_LAHIR;
		nameColumn[TGL_LAHIR] = LUsr.TGL_LAHIR;
		nameColumn[JENIS_KELAMIN] = LUsr.JENIS_KELAMIN;
		nameColumn[NO_TELP] = LUsr.NO_TELP;
		nameColumn[NO_HP1] = LUsr.NO_HP1;
		nameColumn[NO_HP2] = LUsr.NO_HP2;
		nameColumn[PIN_BB] = LUsr.PIN_BB;
		nameColumn[TGL_MASUK] = LUsr.TGL_MASUK;
		nameColumn[GAJI] = LUsr.GAJI;
		nameColumn[JENIS_PEKERJAAN] = LUsr.JENIS_PEKERJAAN;
		nameColumn[PENDIDIKAN_TERAKHIR] = LUsr.PENDIDIKAN_TERAKHIR;
		nameColumn[STATUS] = LUsr.STATUS;

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object m = models.get(rowIndex);
		if (m instanceof Usr) {
			Usr u = (Usr) m;
			if (columnIndex == NO) {
				return getNo(rowIndex);
			} else if (columnIndex == CODE) {
				return u.getCode();
			} else if (columnIndex == USERNAME) {
				return u.getUsername();
			} else if (columnIndex == GROUP) {
				return u.getGrp();
			} else if (columnIndex == NAMA) {
				return u.getNama();
			} else if (columnIndex == ALAMAT) {
				return u.getAlamat();
			} else if (columnIndex == KOTA) {
				return u.getKota();
			} else if (columnIndex == JENIS_IDENTITAS) {
				return u.getJenisIdentitas();
			} else if (columnIndex == NO_IDENTITAS) {
				return u.getNoIdentitas();
			} else if (columnIndex == KOTA_LAHIR) {
				return u.getKotaLahir();
			} else if (columnIndex == TGL_LAHIR) {
				Date d = u.getTglLahir();
				if (d == null) {
					return "";
				} else {
					return App.dateFormat.format(d);
				}
			} else if (columnIndex == JENIS_KELAMIN) {
				int i = u.getJenisKelamin();
				if (i == 0) {
					return "";
				} else {
					String[] jenisKelaminData = App.getUsrDao()
							.getJenisKelaminData();
					return jenisKelaminData[i];
				}
			} else if (columnIndex == NO_TELP) {
				return u.getNoTelp();
			} else if (columnIndex == NO_HP1) {
				return u.getNoHp1();
			} else if (columnIndex == NO_HP2) {
				return u.getNoHp2();
			} else if (columnIndex == PIN_BB) {
				return u.getPinBb();
			} else if (columnIndex == TGL_MASUK) {
				return u.getTglMasuk();
			} else if (columnIndex == GAJI) {
				return u.getGaji();
			} else if (columnIndex == JENIS_PEKERJAAN) {
				return u.getJenisPekerjaan();
			} else if (columnIndex == PENDIDIKAN_TERAKHIR) {
				return u.getPendidikanTerakhir();
			} else if (columnIndex == STATUS) {
				int i = u.getStatus();
				if (i == 0) {
					return "";
				} else {
					String[] statusData = App.getUsrDao().getStatusData();
					return statusData[i];
				}
			}
		}

		return null;
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
	
	
	
	public void actionSearchOneField(String col, String value){
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		loadSearchLike(db, col, value);
		db.close();
		fireTableDataChanged();
//		if (getTable() != null) {
//			getTable().selected();
//		}
	}
	
	public void actionSearchOneField(String col,String col2, String value){
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		loadCollectionSearchLike(db, col, col2, value);
		db.close();
		fireTableDataChanged();
		// if (getTable() != null) {
		// getTable().selected();
		//	}
	}
	
	public void convertDocsToObjects() {
		models=new ArrayList<>();
		for (ODocument o : modelDocs) {
			Usr u=new Usr(o);
			models.add(u);
		}
	}

}
