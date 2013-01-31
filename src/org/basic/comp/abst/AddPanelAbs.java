package org.basic.comp.abst;

import com.basic.lang.LApp;
import com.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import org.basic.comp.base.GradientPanel;
import org.basic.comp.base.ScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddPanelAbs implements AddPanel {
	protected JPanel panel;
	protected boolean builded = false;
	protected Master master;

	protected double lebar;
	protected Icon icon;
	protected JLabel labelTitle;
	protected JLabel labelNote;
	protected JPanel panelForm;
	protected GradientPanel panelTitle;
	protected JPanel panelButton;
	protected ScrollPane pane;

	protected List<WidgetAddObj> widgetAddObjs;

	protected JButton save;
	protected JButton reset;
	protected JButton cancel;

	protected Object model;

	@Override
	public void init() {
		
		icon = master.getIcon32();
		labelTitle = new JLabel();
		labelNote = new JLabel();
		panelTitle = new GradientPanel(new BorderLayout());

		save = new JButton(LApp.SAVE);
		reset = new JButton(LApp.RESET);
		cancel = new JButton(LApp.CANCEL);
		widgetAddObjs = new ArrayList<>();
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		builded = true;

		labelTitle.setFont(App.getFontTitleDialog());
		labelTitle.setText(master.getTitle());
		labelNote.setText(App.getT("Tambah Data"));

		// create panel title
		panelTitle.setBackground(new Color(240, 255, 255));
		panelTitle.setForeground(Color.WHITE);
		StringBuilder c = new StringBuilder();
		c.append("30px,");
		c.append("20px,");
		c.append("f:0px:g,10px,");

		StringBuilder r = new StringBuilder();
		r.append("10dlu,");
		r.append("p,1dlu,");
		r.append("p,4dlu,");

		FormLayout l = new FormLayout(c.toString(), r.toString());
		FormBuilder b = new FormBuilder(l);

		b.append(labelTitle, 2, 2, 2, 1);
		b.append(labelNote, 3, 4);

		if (icon != null) {
			StringBuilder c2 = new StringBuilder();
			c2.append("1px,");
			c2.append("f:0px:g,");
			c2.append("p,30px,");

			StringBuilder r2 = new StringBuilder();
			r2.append("5dlu,");
			r2.append("p,4dlu,");
			// r2.append("c:0px:g,");

			FormLayout l2 = new FormLayout(c2.toString(), r2.toString());
			FormBuilder b2 = new FormBuilder(l2);

			JLabel labelIcon = new JLabel(icon);
			b2.append(labelIcon, 3, 2);

			StringBuilder c3 = new StringBuilder();
			c3.append("1px,");
			c3.append("f:0px:g,");
			c3.append("p,");
			c3.append("1px,");

			StringBuilder r3 = new StringBuilder();
			r3.append("1dlu,");
			r3.append("p,");
			r3.append("1dlu,");

			FormLayout l3 = new FormLayout(c3.toString(), r3.toString());
			FormBuilder b3 = new FormBuilder(l3);

			b3.append(b.getPanel(), 2, 2);
			b3.append(b2.getPanel(), 3, 2);
			b.getPanel().setOpaque(false);
			b2.getPanel().setOpaque(false);
			b3.getPanel().setOpaque(false);
			panelTitle.add(b3.getPanel(), BorderLayout.CENTER);

		} else {
			b.getPanel().setOpaque(false);
			panelTitle.add(b.getPanel(), BorderLayout.CENTER);
		}
		panelTitle.add(new JSeparator(), BorderLayout.SOUTH);

		// build button
		StringBuilder cc2 = new StringBuilder();
		cc2.append("30px,");
		cc2.append("d:g,10px,");
		cc2.append("f:80px,10px,");// 4
		cc2.append("f:80px,10px,");// 6
		cc2.append("f:80px,30px,");// 7

		StringBuilder r2 = new StringBuilder();
		r2.append("5dlu,");
		r2.append("p,");
		r2.append("15dlu,");

		FormLayout l2 = new FormLayout(cc2.toString(), r2.toString());
		FormBuilder b2 = new FormBuilder(l2);

		b2.append(save, 4, 2);
		b2.append(reset, 6, 2);
		b2.append(cancel, 8, 2);
		panelButton = b2.getPanel();

		// button action
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionSave();
			}
		});
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionReset();
			}
		});
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				actionCancel();
			}
		});

		// xx
		panel = new JPanel(new BorderLayout());
		pane = new ScrollPane(panelForm);
		pane.setBorder(null);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(panelTitle, BorderLayout.NORTH);
		panel.add(panelButton, BorderLayout.SOUTH);

		actionReset();
	}

	public void actionReset() {
	}

	public void actionCancel() {
		dispose(panel);
	}

	public void actionSave() {
		ODatabaseDocumentTx db = App.getDbd();
		ODatabaseRecordThreadLocal.INSTANCE.set(db);
		if (beforeSave(db)) {
			try {
				save(db);
				afterSave();
			} catch (Exception e) {
				App.printErr(e);
			} finally {
				db.close();
			}

		}
	}

	public void save(ODatabaseDocumentTx db) {

	}

	public void afterSave() {
		for (WidgetAddObj w : widgetAddObjs) {
			w.addObj(model);
		}
		dispose(panel);
	}

	public boolean beforeSave(ODatabaseDocumentTx db) {
		return validate(db);
	}

	public boolean validate(ODatabaseDocumentTx db) {
		return false;
	}

	public void dispose(Object o) {
		if (o instanceof java.awt.Window) {
			((java.awt.Window) o).dispose();
		} else {
			if (o instanceof Component) {
				dispose(((Component) o).getParent());
			}
		}
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	@Override
	public boolean isBuilded() {
		return builded;
	}

	@Override
	public void setBuilded(boolean builded) {
		this.builded = builded;
	}

	@Override
	public Master getMaster() {
		return master;
	}

	@Override
	public void setMaster(Master master) {
		this.master = master;
	}

	@Override
	public void requestDefaultFocus() {
		
	}

}
