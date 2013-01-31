package com.global;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Focus {
	public static void enter(JTextComponent sebelum,
			final JTextComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
					sesudah.selectAll();
				}
			}
		});
	}

	public static void enter(JTextComponent sebelum,
			final JComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
				}
			}
		});
	}

	public static void enter(JComponent sebelum,
			final JComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					sesudah.requestFocus();
				}
			}
		});
	}
}
