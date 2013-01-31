package org.basic.dao.adapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.ORecordLazyList;
import com.orientechnologies.orient.core.db.record.ORecordLazySet;
import com.orientechnologies.orient.core.record.impl.ODocument;

import java.util.HashSet;
import java.util.List;

public interface DaoInterface {
	public ODocument save(ODatabaseDocumentTx db, ODocument model);
	public ODocument update(ODatabaseDocumentTx db, ODocument model);
	public ODocument update(ODatabaseDocumentTx db, ODocument model, String jsonOld);
	public ODocument delete(ODatabaseDocumentTx db,ODocument o);
	public List<ODocument> getAll(ODatabaseDocumentTx db);
	public List<ODocument> getAll(ODatabaseDocumentTx db, long start, long end);
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, Object value);
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, String value);
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, Object value, long countStart, long countPerPage);
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, String value, long start, long end);
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, String value);
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value, String kolom2, Object value2, String operator);
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value, String kolom2, Object vallue2);
	public long getCount(ODatabaseDocumentTx db);
	public long getCount(ODatabaseDocumentTx db, String sql, String as);
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, String value);
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, Object value);
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, String value, String colom2, String value2, String operator);
	public void printAll(ODatabaseDocumentTx db);
	public long delByColoumn(ODatabaseDocumentTx db, String colom, String value);
	public long truncetClass(ODatabaseDocumentTx db);
	public long truncetRecord(ODatabaseDocumentTx db, String rid);
	public long deleteAll(ODatabaseDocumentTx db);
	public String getClassName();
	public String getNameFielsToString();
	
	
	public List<ODocument> getAllByColumnLikeCollection(ODatabaseDocumentTx db,
			String col, String kolomAnak, String value);
	public List<ODocument> getAllByColumnLikeCollection(ODatabaseDocumentTx db,
			String kolom, String kolomAnak, String value, long start, long end);
	
	
	
	public List<ODocument> getLinkList(ODatabaseDocumentTx db, String linklist);
	public HashSet<ODocument> getLinkSet(ODatabaseDocumentTx db, ORecordLazyList linkset);
	public long getCountByColumnLikeCollection(ODatabaseDocumentTx db,
			String col, String col2, String string);
	
	
	
	/**
	 * Untuk mendapatkan List ODocument dengan parameter ODocument 
	 * yang akan di ubah menjadi attribut pencarian
	 * @param db
	 * @param o
	 * @return
	 */
	public List<ODocument> getAllSearch(ODatabaseDocumentTx db, ODocument o);
	public long getCountSearch(ODatabaseDocumentTx db, ODocument o);
	public List<ODocument> getCountAllSearch(ODatabaseDocumentTx db, ODocument o);
	public List<ODocument> getAllSearch(ODatabaseDocumentTx db, ODocument o,
			long tmp, long jumlahPerHalaman);
	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String col, String value, long tmp, long jumlahPerHalaman);
	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String col, String value);
	public long getCountByColumnLike(ODatabaseDocumentTx db, String col,
			String value);
	public String getFormatCode();
	public boolean isUseFormatCode();
	public boolean isTrueChildThis(ODocument o);
	List<ODocument> getLinkList(ODatabaseDocumentTx db, ORecordLazyList linklist);
	List<ODocument> getLinkList(ODatabaseDocumentTx db, ORecordLazySet linklist);

	
}
