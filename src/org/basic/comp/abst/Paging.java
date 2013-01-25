package org.basic.comp.abst;

import javax.swing.JPanel;

public interface Paging {

	// di sini setting visible sekalian
	void setCount(long count);

	JPanel getPanel();

	// bila minis maka return 0
	long getCountStart();

	long getCountPerPage();

}
