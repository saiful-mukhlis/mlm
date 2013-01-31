package org.basic.comp.listener;

import com.orientechnologies.orient.core.record.impl.ODocument;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;

public interface ODocumentToStringNodeInterface {
	public ODocument getO();

	public void setO(ODocument userObject);
	public MutableTreeTableNode getNode();
	public void setNode(MutableTreeTableNode node);
}
