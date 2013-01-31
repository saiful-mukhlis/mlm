package org.basic.comp.adapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

import javax.swing.*;

public interface PagingInterface {
public void loadFirst(ODatabaseDocumentTx db);
public void setCurentHalaman(int curentHalaman);
public int getCurentHalaman();
public void setJumlahPerHalaman(int jumlahPerHalaman) ;
public int getJumlahPerHalaman();
public void setJumlahHalaman(int jumlahHalaman);
public int getJumlahHalaman();
public void setJumlahData(long jumlahData);
public Long getJumlahData();
public JPanel getPanelPaging();
}
