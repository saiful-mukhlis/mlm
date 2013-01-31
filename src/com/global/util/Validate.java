package com.global.util;

import com.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.dao.adapter.DaoInterface;

import javax.swing.*;
import java.util.Arrays;

public class Validate {
	public static boolean validateStringEmpty(JTextField f, String nf) {
		if (f.getText().trim().equalsIgnoreCase("")) {
			Err.showErrEmptyField(nf);
			f.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validateNoSelected(JComboBox c,String nf) {
		if (c.getSelectedItem()==null || c.getSelectedIndex()==0) {
			Err.showErrEmptySelect(nf);
			c.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validatePassword(JPasswordField f, JPasswordField f2, String nf, String nf2) {
		if (f.getPassword().length > 0) {
			if (!(f.getPassword().length == f2.getPassword().length && Arrays.equals(f.getPassword(), f2.getPassword()))) {
				Err.showErrNf("%s tidak sesuai dengan %s", nf, nf2);
				f2.requestFocus();
				return false;
			}
		}else{
			Err.showErrEmptyField(nf);
			f.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validateUnique(ODatabaseDocumentTx db, DaoInterface dao,JTextField f, String nf, String uniqueField) {
		boolean close=false;
		if (db==null) {
			close=true;
			db=App.getDbdLocal();
		}
		long tmp = dao.getCountByColumn(db, uniqueField, f.getText().trim());
		if (tmp > 0) {
			Err.showErrDataSudahAda(nf);
			f.requestFocus();
			if (close) {
				db.close();
			}
			return false;
		}
		if (close) {
			db.close();
		}
		return true;
	}
	

	public static boolean validateStringEmpty(JTextArea f,String nf) {
		if (f.getText().trim().equalsIgnoreCase("")) {
			Err.showErrEmptyField(nf);
			f.requestFocus();
			return false;
		}
		return true;
	}
}

