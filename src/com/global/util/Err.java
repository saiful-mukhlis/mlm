package com.global.util;

import com.global.App;

import javax.swing.*;

public class Err {
	public static void show(String err) {
		JOptionPane.showMessageDialog(null, err);
	}

	public static void showT(String err) {
		show(App.getT(err));
	}

	public static String f(String f, String p) {
		return String.format(App.getT(f), p);
	}

	public static String f(String f, Object... p) {
		return String.format(App.getT(f), p);
	}

	public static void showErrNf(String err, Object nf) {
		show(f(err, nf));
	}

	public static void showErrNf(String err, Object... nf) {
		show(f(err, nf));
	}

	public static void showErrEmptySelect(String nf) {
		show( f("%s belum di pilih", nf));
	}

	public static void showErrEmptyField(String nf) {
		show( f("$s belum di isi", nf));
	}

	public static void showErrNotValid(String nf) {
		show( f("%s tidak valid", nf));
	}

	public static void showErrNilaiHarusDiAtasNol(String nf) {
		show( f("%s harus lebih besar dari nol", nf));
	}

	public static void showSaveOk() {
		showT("Data berhasil di simpan");
	}
	
	public static void showErrUsernameTidakTerdaftar() {
		showT("Username tidak terdaftar");
	}
	
	public static void showErrMasaTrialSudahHabis() {
		showT("Masa Trial Sudah Habis");
	}

	public static void showErrDataSudahAda(String nf) {
		show( f("%s sudah terdaftar", nf));
	}

	public static void showErrTmp(String err) {
		show(err);
	};
}
