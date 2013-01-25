package com.mlm.comp.model;

import org.basic.comp.model.HakAksesTreeModel;
import org.basic.comp.model.HakAksesTreeModel;

import com.global.DataUser;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class HakAksesModelMlm extends HakAksesModel {

	public HakAksesModelMlm(ODocument group) {
		super(group);
	}

//	public HakAkses buildHakakses() {
//		HakAkses top = new HakAkses("Root", DataUser.XROOT);
//
//		// Hak Akses===============================================
//
//		HakAkses perent = new HakAkses("Hak Akses", DataUser.XHAK_AKSES_VIEW,
//				group);
//		top.add(perent);
//
//		HakAkses anak = new HakAkses("Tambah Hak Akses",
//				DataUser.XHAK_AKSES_ADD, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Edit Hak Akses", DataUser.XHAK_AKSES_EDIT, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Hapus Hak Akses", DataUser.XHAK_AKSES_HAPUS, group);
//		perent.add(anak);
//
//		// Pegawai
//		perent = new HakAkses("Pegawai", DataUser.XUSR_VIEW, group);
//		top.add(perent);
//
//		anak = new HakAkses("Tambah Pegawai", DataUser.XUSR_ADD, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Edit Pegawai", DataUser.XUSR_EDIT, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Hapus Pegawai", DataUser.XUSR_DEL, group);
//		perent.add(anak);
//
//		// Jenis Pekerjaan
//		perent = new HakAkses("Type Master", DataUser.JENIS_VIEW, group);
//		top.add(perent);
//
//		anak = new HakAkses("Tambah Jenis Pekerjaan",
//				DataUser.JENIS_PEKERJAAN_ADD, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Edit Jenis Pekerjaan",
//				DataUser.JENIS_PEKERJAAN_EDIT, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Hapus Jenis Pekerjaan",
//				DataUser.JENIS_PEKERJAAN_DEL, group);
//		perent.add(anak);
//
//		// Paket
//		perent = new HakAkses("Paket", DataUser.PAKET_VIEW, group);
//		top.add(perent);
//
//		anak = new HakAkses("Tambah Paket", DataUser.PAKET_ADD, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Edit Paket", DataUser.PAKET_EDIT, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Hapus Paket", DataUser.PAKET_DEL, group);
//		perent.add(anak);
//
//		// Paket
//		perent = new HakAkses("Pelanggan", DataUser.PELANGGAN_VIEW, group);
//		top.add(perent);
//
//		anak = new HakAkses("Tambah Pelanggan", DataUser.PELANGGAN_ADD, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Edit Pelanggan", DataUser.PELANGGAN_EDIT, group);
//		perent.add(anak);
//
//		anak = new HakAkses("Hapus Pelanggan", DataUser.PELANGGAN_DEL, group);
//		perent.add(anak);
//
//		return top;
//
//	}
}
