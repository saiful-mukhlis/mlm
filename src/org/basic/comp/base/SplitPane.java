package org.basic.comp.base;

import javax.swing.*;
import java.awt.*;
public class SplitPane extends JSplitPane {

	public SplitPane(int newOrientation, Component newLeftComponent,
			Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		setDividerSize(10);
		setOneTouchExpandable(false);
		setBorder(null);
		
	}


}
