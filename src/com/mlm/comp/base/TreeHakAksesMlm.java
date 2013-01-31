package com.mlm.comp.base;

import com.global.DataUser;
import org.basic.comp.base.TreeHakAkses;
import org.basic.comp.model.HakAksesTreeModel;

public class TreeHakAksesMlm extends TreeHakAkses{
	public HakAksesTreeModel buildHakakses() {
	HakAksesTreeModel top = new HakAksesTreeModel("Root", DataUser.XROOT, null);

	// Hak Akses===============================================

	HakAksesTreeModel perent = new HakAksesTreeModel("Hak Akses", DataUser.XHAK_AKSES_VIEW,
			group);
	top.add(perent);

	HakAksesTreeModel anak = new HakAksesTreeModel("Tambah Hak Akses",
			DataUser.XHAK_AKSES_ADD, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Edit Hak Akses", DataUser.XHAK_AKSES_EDIT, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Hapus Hak Akses", DataUser.XHAK_AKSES_HAPUS, group);
	perent.add(anak);

	// Pegawai
	perent = new HakAksesTreeModel("Pegawai", DataUser.XUSR_VIEW, group);
	top.add(perent);

	anak = new HakAksesTreeModel("Tambah Pegawai", DataUser.XUSR_ADD, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Edit Pegawai", DataUser.XUSR_EDIT, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Hapus Pegawai", DataUser.XUSR_DEL, group);
	perent.add(anak);

	// Jenis Pekerjaan
	perent = new HakAksesTreeModel("Type Master", DataUser.JENIS_VIEW, group);
	top.add(perent);

	anak = new HakAksesTreeModel("Tambah Jenis Pekerjaan",
			DataUser.JENIS_PEKERJAAN_ADD, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Edit Jenis Pekerjaan",
			DataUser.JENIS_PEKERJAAN_EDIT, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Hapus Jenis Pekerjaan",
			DataUser.JENIS_PEKERJAAN_DEL, group);
	perent.add(anak);

	// Paket
	perent = new HakAksesTreeModel("Paket", DataUser.PAKET_VIEW, group);
	top.add(perent);

	anak = new HakAksesTreeModel("Tambah Paket", DataUser.PAKET_ADD, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Edit Paket", DataUser.PAKET_EDIT, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Hapus Paket", DataUser.PAKET_DEL, group);
	perent.add(anak);

	// Paket
	perent = new HakAksesTreeModel("Pelanggan", DataUser.PELANGGAN_VIEW, group);
	top.add(perent);

	anak = new HakAksesTreeModel("Tambah Pelanggan", DataUser.PELANGGAN_ADD, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Edit Pelanggan", DataUser.PELANGGAN_EDIT, group);
	perent.add(anak);

	anak = new HakAksesTreeModel("Hapus Pelanggan", DataUser.PELANGGAN_DEL, group);
	perent.add(anak);

	return top;

}
}
