package com.mlm.comp.model;

import org.basic.comp.listener.ODocumentToStringNodeInterface;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;

import com.global.App;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PaketToStringNode implements ODocumentToStringNodeInterface{

	private ODocument o;
	private MutableTreeTableNode node;

	public PaketToStringNode(ODocument o, MutableTreeTableNode node) {
		super();
		this.o = o;
		this.node=node;
		
	}
	

	@Override
	public ODocument getO() {
		return o;
	}

	public void setO(ODocument o) {
		this.o = o;
	}

	@Override
	public String toString() {
		if (App.getPaketDao().isTrueChildThis(o)) {
			return App.getPaketDao().getNama(o);
		}else if(App.getPpDao().isTrueChildThis(o)){
			return App.getPpDao().pelangganToString(null, o)+" ("+node.getChildCount()+")";
		}
		return "";
	}

	public MutableTreeTableNode getNode() {
		return node;
	}

	public void setNode(MutableTreeTableNode node) {
		this.node = node;
	}
	
	
	

}
