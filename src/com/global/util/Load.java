package com.global.util;

import java.util.ArrayList;
import java.util.List;

import org.basic.comp.abst.Paging;
import org.basic.dao.adapter.DaoInterface;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Load {
	// modelDocs tidak boleh null
	public static List<ODocument> load(ODatabaseDocumentTx db, Paging paging,
			DaoInterface dao, List<ODocument> modelDocs) {
		if (paging != null) {
			long count = dao.getCount(db);
			paging.setCount(count);
			if (paging.getPanel().isVisible()) {
				long countStart = paging.getCountStart();
				long countPerPage = paging.getCountPerPage();
				modelDocs = dao.getAll(db, countStart, countPerPage);
			} else {
				modelDocs = dao.getAll(db);
			}
		}
		
		if (modelDocs == null) {
			modelDocs = new ArrayList<>();
		}
		return modelDocs;
	}

	// modelDoc, column, value tidak boleh null
	public static void loadSimpleSearchLike(ODatabaseDocumentTx db,
			Paging paging, DaoInterface dao, List<ODocument> modelDocs,
			String column, String value) {
		if (paging != null) {
			long count = dao.getCountByColumnLike(db, column, value);
			paging.setCount(count);
			if (paging.getPanel().isVisible()) {
				long countStart = paging.getCountStart();
				long countPerPage = paging.getCountPerPage();
				modelDocs = dao.getAllByColumnLike(db, column, value,
						countStart, countPerPage);
			} else {
				modelDocs = dao.getAllByColumnLike(db, column, value);
			}
		}
		if (modelDocs == null) {
			modelDocs = new ArrayList<>();
		}
	}

	public static void loadSimpleSearchLikeValueLowerCase(
			ODatabaseDocumentTx db, Paging paging, DaoInterface dao,
			List<ODocument> modelDocs, String column, String value) {
		value = value.toLowerCase();
		loadSimpleSearchLike(db, paging, dao, modelDocs, column, value);
	}

	// modelDoc, column, value tidak boleh null
	public static void loadSimpleSearchLikePersenValuePersen(
			ODatabaseDocumentTx db, Paging paging, DaoInterface dao,
			List<ODocument> modelDocs, String column, String value) {
		value = "%" + value.toLowerCase() + "%";
		loadSimpleSearchLike(db, paging, dao, modelDocs, column, value);
	}

	// modelDoc, column, value tidak boleh null
	public static void loadSimpleSearchLikePersenValue(ODatabaseDocumentTx db,
			Paging paging, DaoInterface dao, List<ODocument> modelDocs,
			String column, String value) {
		value = "%" + value.toLowerCase();
		loadSimpleSearchLike(db, paging, dao, modelDocs, column, value);
	}

	// modelDoc, column, value tidak boleh null
	public static void loadSimpleSearchLikeValuePersen(ODatabaseDocumentTx db,
			Paging paging, DaoInterface dao, List<ODocument> modelDocs,
			String column, String value) {
		value = value.toLowerCase() + "%";
		loadSimpleSearchLike(db, paging, dao, modelDocs, column, value);
	}
	
	
	
	
	


		// modelDoc, column, value tidak boleh null
		public static void loadCollectionSimpleSearchLike(ODatabaseDocumentTx db,
				Paging paging, DaoInterface dao, List<ODocument> modelDocs,
				String column, String column2, String value) {
			if (paging != null) {
				long count = dao.getCountByColumnLikeCollection(db, column, column2, value);
				paging.setCount(count);
				if (paging.getPanel().isVisible()) {
					long countStart = paging.getCountStart();
					long countPerPage = paging.getCountPerPage();
					modelDocs = dao.getAllByColumnLikeCollection(db, column, column2, value,
							countStart, countPerPage);
				} else {
					modelDocs = dao.getAllByColumnLikeCollection(db, column, column2, value);
				}
			}
			if (modelDocs == null) {
				modelDocs = new ArrayList<>();
			}
		}

		public static void loadCollectionSimpleSearchLikeValueLowerCase(
				ODatabaseDocumentTx db, Paging paging, DaoInterface dao,
				List<ODocument> modelDocs, String column,String column2, String value) {
			value = value.toLowerCase();
			loadCollectionSimpleSearchLike(db, paging, dao, modelDocs, column, column2, value);
		}

		// modelDoc, column, value tidak boleh null
		public static void loadCollectionSimpleSearchLikePersenValuePersen(
				ODatabaseDocumentTx db, Paging paging, DaoInterface dao,
				List<ODocument> modelDocs, String column,String column2, String value) {
			value = "%" + value.toLowerCase() + "%";
			loadCollectionSimpleSearchLike(db, paging, dao, modelDocs, column, column2, value);
		}

		// modelDoc, column, value tidak boleh null
		public static void loadCollectionSimpleSearchLikePersenValue(ODatabaseDocumentTx db,
				Paging paging, DaoInterface dao, List<ODocument> modelDocs,
				String column,String column2, String value) {
			value = "%" + value.toLowerCase();
			loadCollectionSimpleSearchLike(db, paging, dao, modelDocs, column, column2, value);
		}

		// modelDoc, column, value tidak boleh null
		public static void loadCollectionSimpleSearchLikeValuePersen(ODatabaseDocumentTx db,
				Paging paging, DaoInterface dao, List<ODocument> modelDocs,
				String column,String column2, String value) {
			value = value.toLowerCase() + "%";
			loadCollectionSimpleSearchLike(db, paging, dao, modelDocs, column, column2, value);
		}
}
