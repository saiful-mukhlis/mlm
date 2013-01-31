package org.basic.comp.model;


import com.basic.db.FGrp;
import com.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;

public class HakAksesTreeModel extends DefaultMutableTreeTableNode {

	
	
	public HakAksesTreeModel(String nama, int key, ODocument groupa) {
		super(nama);
		group=groupa;
		this.nama = nama;
		this.kode="x"+key+"x";
		if (group!=null) {
			String data=group.field(FGrp.KEY);
			if (data!=null) {
				if (data.indexOf(kode)==-1) {
					this.aktif=false;
				}else{
					this.aktif=true;
				}
			}
		}
	}

	private String nama;
	private String kode;
	private boolean aktif;
//	private int key;
	private ODocument group;

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int column) {
		if (column == 0) {
			return nama;
		} else if (column == 1) {
			return aktif;
		}
		return super.getValueAt(column);
	}

	@Override
	public boolean isEditable(int column) {
		if (column == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int column) {
		if (column == 1) {
			if (group != null) {
				ODatabaseDocumentTx db = App.getDbd();
				ODatabaseRecordThreadLocal.INSTANCE.set(db);
				try {

					if (aValue instanceof Boolean) {
						aktif = (boolean) aValue;
						if (aktif) {
							String data = group.field(FGrp.KEY);
							if (data != null) {
								if (data.indexOf(kode) == -1) {
									data = data + kode;
									group.field(FGrp.KEY, data);
									group.save();
								}
							} else {
								data = "";
								if (data.indexOf(kode) == -1) {
									data = data + kode;
									group.field(FGrp.KEY, data);
									group.save();
								}
							}
						} else {
							String data = group.field(FGrp.KEY);
							if (data != null) {
								if (data.indexOf(kode) != -1) {
									data = data.replaceAll(kode, "");
									group.field(FGrp.KEY, data);
									group.save();
								}
							}
						}

						if ((Boolean) aValue) {
							TreeTableNode p = getParent();
							if (p instanceof HakAksesTreeModel) {
								HakAksesTreeModel parent = (HakAksesTreeModel) p;
								if (parent != null) {
									parent.setAktif((boolean) aValue);
									String kode = ((HakAksesTreeModel) parent).getKode();
									String data = group.field(FGrp.KEY);
									if (data != null) {
										if (data.indexOf(kode) == -1) {
											data = data + kode;
											group.field(FGrp.KEY, data);
											group.save();
										}
									} else {
										data = "";
										if (data.indexOf(kode) == -1) {
											data = data + kode;
											group.field(FGrp.KEY, data);
											group.save();
										}
									}

								}
							}

						}

						if (!(Boolean) aValue) {
							int jmlAnak = getChildCount();
							for (int i = 0; i < jmlAnak; i++) {
								TreeTableNode c = getChildAt(i);
								if (c instanceof HakAksesTreeModel) {
									HakAksesTreeModel ch = (HakAksesTreeModel) c;
									ch.setAktif((boolean) aValue);

									String kode = ch.getKode();
									String data = group.field(FGrp.KEY);
									if (data != null) {
										if (data.indexOf(kode) != -1) {
											data = data.replaceAll(kode, "");
											group.field(FGrp.KEY, data);
											group.save();
										}
									}
								}

							}

						}

					}

				} catch (Exception e) {
					App.printErr(e);
				} finally {
					db.close();
				}

				super.setValueAt(aValue, column);

			}
		}

	}



	@Override
	public String toString() {
		return nama;
	}

	public boolean isAktif() {
		return aktif;
	}

	public void setAktif(boolean aktif) {
		this.aktif = aktif;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

}
