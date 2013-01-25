package com.mlm.db;

import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class FPaket {
	public static final String TABLE="Paket";
	public static final String CODE="code";

	
	public static final String NAMA="nama";
	public static final String KET="ket";
	@Type(t=OType.DECIMAL)
	public static final String HARGA="harga";
	@Type(t=OType.INTEGER)
	public static final String DOWNLINE="downline";
	@Type(t=OType.INTEGER)
	public static final String WAKTU="waktu";
	@Type(t=OType.INTEGER)
	public static final String STATUS="status";
	@Type(t=OType.INTEGER)
	public static final String TOTAL_PELANGGAN="totalPelanggan";
	
	@Type(t=OType.LINKSET)
	public static final String DOWNLINES="downlines";
}
