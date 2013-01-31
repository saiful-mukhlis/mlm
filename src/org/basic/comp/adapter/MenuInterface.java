package org.basic.comp.adapter;

import org.basic.comp.listener.MasterInterface;
import org.basic.comp.listener.WidgetInterface;

import javax.swing.*;

public interface MenuInterface extends WidgetInterface{
	public JMenuBar getMenu();
	public void setWindow(WindowInterfaces window);
	public WindowInterfaces getWindow();
	public void setMasterSelected(MasterInterface master);
}
