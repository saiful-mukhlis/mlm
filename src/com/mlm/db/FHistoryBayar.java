package com.mlm.db;

import com.basic.annotation.db.Type;
import com.orientechnologies.orient.core.metadata.schema.OType;

public class FHistoryBayar {
	public static final String TABLE="HistoryBayar";
	//public static final String CODE="code";
	@Type(t=OType.DATE)
	public static final String TGL_BAYAR="tglBayar";
	@Type(t=OType.DECIMAL)
	public static final String JML_BAYAR="jmlBayar";
	@Type(t=OType.DECIMAL)
	public static final String JML_SISA="jmlSisa";

}
