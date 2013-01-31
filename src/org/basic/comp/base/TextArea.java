package org.basic.comp.base;

import com.global.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2850291374364481785L;

	public TextArea() {
		super();
		setBackground(Color.WHITE);
		setCaretColor(App.selected);
		setLineWrap(true);
		addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (pane!=null) {
					pane.setBorder(App.border);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (pane!=null) {
					pane.setBorder(App.borderSelected);
				}
			}
		});
		addMouseListener(new MouseAdapter() {

			
			@Override
			public void mouseExited(MouseEvent mouseevent) {
				// TODO Auto-generated method stub
				if (!isFocusOwner()) {
					if (pane!=null) {
						pane.setBorder(App.border);
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent mouseevent) {
				if (pane!=null) {
					pane.setBorder(App.borderSelected);
				}
			}
		
		});
	}
	
	public JScrollPane getPane() {
		return pane;
	}

	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}

	private JScrollPane pane;

}
