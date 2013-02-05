package org.basic.comp.abst;

import java.util.HashMap;

public interface Window {

	String getIdMasterOpen();

	void setIdMasterOpen(String idMasterOpen);

	HashMap<String, Master> getMapMaster();

}