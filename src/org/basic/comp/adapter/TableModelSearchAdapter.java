package org.basic.comp.adapter;


import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.List;

public interface TableModelSearchAdapter extends TableModel {
	public boolean isInstace(Object model);
	public void addObjModel(Object model);
	public void editObjModel(Object model);
	public void load();
	public void reload();
	
	public void setPaging(PagingInterface paging);
	public void setDefaultLebar(JTable table);
	public List getModel();
	public void fireTableDataChanged();
	public void setSearching(boolean isSearch);
	public boolean isSearching();
	public void setTextSearch(String teksSearch);
}
