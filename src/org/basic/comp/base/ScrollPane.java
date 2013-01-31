package org.basic.comp.base;

import com.global.App;

import javax.swing.*;
import java.awt.*;

public class ScrollPane extends JScrollPane {

	public ScrollPane() {
		super();
		setFirst();
	}
	
	public void setFirst(){
		setBackground(Color.WHITE);
		setBorder(App.border);
	}

	public ScrollPane(Component view) {
		super(view);
		setFirst();
		if (view instanceof TextArea) {
			((TextArea) view).setPane(this);
		}
	}

}
