package com.mlm.db;

import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class FPp {
	public static final String TABLE="Pp";
	public static final String CODE="code";

	@Type(t=OType.LINK)
	public static final String PELANGGAN="pelanggan";
	@Type(t=OType.LINK)
	public static final String PAKET="paket";
	@Type(t=OType.DECIMAL)
	public static final String BAYAR="bayar";
	@Type(t=OType.LINK)
	public static final String UP_LINE="upLine";
	@Type(t=OType.LINKLIST)
	public static final String DOWNLINES="downlines";
	@Type(t=OType.INTEGER)
	public static final String JML_DOWNLINE="jmlDownline";
	@Type(t=OType.DATE)
	public static final String TGL_DAFTAR="tglDaftar";
	@Type(t=OType.DATE)
	public static final String TGL_LUNAS="tglLunas";
	@Type(t=OType.DATE)
	public static final String TGL_AKTIF="tglAktif";
	@Type(t=OType.INTEGER)
	public static final String STATUS="status";
	@Type(t=OType.EMBEDDEDLIST)
	public static final String HISTORY_BAYARS="historyBayars";

}
