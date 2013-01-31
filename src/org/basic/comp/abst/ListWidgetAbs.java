package org.basic.comp.abst;

import javax.swing.*;

public class ListWidgetAbs implements ListWidget {
protected JPanel panel;

@Override
public JPanel getPanel() {
	return panel;
}

@Override
public void setPanel(JPanel panel) {
	this.panel = panel;
}


}
