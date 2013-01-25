package com.mlm.db;

import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class FPelanggan {
	public static final String TABLE="Pelanggan";
	public static final String CODE="code";

	
	public static final String NAMA_TOKO="namaToko";
	public static final String NAMA_PEMILIK="namaPemilik";
	public static final String JENIS_IDENTITAS = "jenisIdentitas";
	public static final String NO_IDENTITAS = "noIdentitas";
	public static final String ALAMAT="alamat";
	public static final String KOTA="kota";
	public static final String NO_TELP="noTelp";
	public static final String NO_FAX="noFax";
	public static final String NO_HP1="noHp1";
	public static final String NO_HP2="noHp2";
	public static final String PIN_BB1="pinBb1";
	public static final String PIN_BB2="pinBb2";
	@Type(t=OType.LINK)
	public static final String STATUS="status";
	
	public static final String PAKETS="pakets";
	
}
