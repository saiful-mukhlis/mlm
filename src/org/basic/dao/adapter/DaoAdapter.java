package org.basic.dao.adapter;

import java.util.HashSet;
import java.util.List;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.ORecordLazyList;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class DaoAdapter implements DaoInterface {

	@Override
	public ODocument save(ODatabaseDocumentTx db, ODocument model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAll(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAll(ODatabaseDocumentTx db, long start, long end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom,
			Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom,
			String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom,
			Object value, long start, long end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom,
			String value, long start, long end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value,
			String kolom2, Object value2, String operator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value,
			String kolom2, Object vallue2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCount(ODatabaseDocumentTx db, String sql, String as) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCountByColumn(ODatabaseDocumentTx db, String colom,
			String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCountByColumn(ODatabaseDocumentTx db, String colom,
			Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCountByColumn(ODatabaseDocumentTx db, String colom,
			String value, String colom2, String value2, String operator) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printAll(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub

	}

	@Override
	public long delByColoumn(ODatabaseDocumentTx db, String colom, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long truncetClass(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long truncetRecord(ODatabaseDocumentTx db, String rid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteAll(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ODocument delete(ODatabaseDocumentTx db, ODocument o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNameFielsToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllSearch(ODatabaseDocumentTx db, ODocument o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCountSearch(ODatabaseDocumentTx db, ODocument o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ODocument> getCountAllSearch(ODatabaseDocumentTx db, ODocument o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllSearch(ODatabaseDocumentTx db, ODocument o,
			long tmp, long jumlahPerHalaman) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ODocument update(ODatabaseDocumentTx db, ODocument model) {
		// TODO Auto-generated method stub
		model.save();
		return model;
	}

	@Override
	public ODocument update(ODatabaseDocumentTx db, ODocument model,
			String jsonOld) {
		model.save();
		return model;
	}

	@Override
	public List<ODocument> getLinkList(ODatabaseDocumentTx db, String linklist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashSet<ODocument> getLinkSet(ODatabaseDocumentTx db, ORecordLazyList linkset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String col, String value, long tmp, long jumlahPerHalaman) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db,
			String col, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCountByColumnLike(ODatabaseDocumentTx db, String col,
			String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getFormatCode() {
		return "00000";
	}

	@Override
	public boolean isUseFormatCode() {
		return true;
	}

	@Override
	public boolean isTrueChildThis(ODocument o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ODocument> getAllByColumnLikeCollection(ODatabaseDocumentTx db,
			String col, String kolomAnak, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ODocument> getAllByColumnLikeCollection(ODatabaseDocumentTx db,
			String kolom, String kolomAnak, String value, long start, long end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCountByColumnLikeCollection(ODatabaseDocumentTx db,
			String col, String col2, String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
