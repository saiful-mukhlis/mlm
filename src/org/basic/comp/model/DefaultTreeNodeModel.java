package org.basic.comp.model;

import com.orientechnologies.orient.core.record.impl.ODocument;
import org.basic.comp.listener.ODocumentToStringNodeInterface;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;

public class DefaultTreeNodeModel extends DefaultMutableTreeTableNode {

	protected ODocumentToStringNodeInterface documentToString;
	protected int columnCount = 1;

	public DefaultTreeNodeModel(ODocument userObject) {
		super(userObject);
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}


	public ODocumentToStringNodeInterface getDocumentToString() {
		return documentToString;
	}

	public void setDocumentToString(ODocumentToStringNodeInterface documentToString) {
		this.documentToString = documentToString;
	}
	
	public boolean isEditable(int column) {
		return false;
	}

}
