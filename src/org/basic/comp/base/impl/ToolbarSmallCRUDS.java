package org.basic.comp.base.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.basic.comp.abst.FactorySearch;
import org.basic.comp.abst.Master;
import org.basic.comp.abst.ToolbarSmall;
import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

import com.basic.icon.IconBase;
import com.basic.lang.LActions;
import com.basic.lang.LWindow;
import com.global.App;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class ToolbarSmallCRUDS implements ToolbarSmall {
	protected JPanel panel;
	protected Master master;
	protected JToolBar toolBar;

	protected JButton reload;
	protected JButton add;
	protected JButton edit;
	protected JButton del;
	protected SplitButton print;

	protected JMenuItem menuItemPrint;
	protected JMenuItem menuItemPrintPreview;
	protected JMenuItem menuItemPdf;
	protected JMenuItem menuItemExcel;
	protected JMenuItem menuItemWord;

	protected TextFieldSearch fieldSearch;
	protected SplitButton itemSearch;
	
	protected FactorySearch factorySearch;

	public void load(Object model) {
		if (model == null) {
			setFalseAll();
		} else {
			setStateByHakAkses();
		}
	}

	public void setFalseAll() {
		add.setEnabled(master.isAdd());
		edit.setEnabled(false);
		del.setEnabled(false);
	}

	public void setStateByHakAkses() {
		add.setEnabled(master.isAdd());
		edit.setEnabled(master.isEdit());
		del.setEnabled(master.isDel());
	}

	public ToolbarSmallCRUDS() {
		super();
	}

	public void build(ODatabaseDocumentTx db) {
		init();

		reload.setText(LWindow.RELOAD);
		add.setText(LWindow.ADD);
		edit.setText(LWindow.EDIT);
		print.setText(LWindow.PRINT);
		del.setText(LWindow.DELETE);

		menuItemExcel.setIcon(IconBase.EXCEL);
		menuItemPdf.setIcon(IconBase.PDF);
		menuItemPrint.setIcon(IconBase.PRINT);
		menuItemPrintPreview.setIcon(IconBase.PRINT_PREVIEW);
		menuItemWord.setIcon(IconBase.WORD);

		JPopupMenu menuPrint = new JPopupMenu();
		menuPrint.add(menuItemPrint);
		menuPrint.add(menuItemPrintPreview);
		menuPrint.add(menuItemPdf);
		menuPrint.add(menuItemExcel);
		menuPrint.add(menuItemWord);

		print.setDropDownMenu(menuPrint);

		StringBuilder col = new StringBuilder();
		col.append("1px,");//
		col.append("f:p,10px,");//
		col.append("p,10px,");//
		col.append("c:p:g,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		col.append("p,10px,");//
		FormLayout layout = new FormLayout(col.toString(), "p,3dlu");
		toolBar.setLayout(layout);
		toolBar.setBackground(Color.WHITE);

		JLabel label = new JLabel(master.getTitleToolBar());
		label.setFont(App.getFontTitleToolbar());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		CellConstraints cc = new CellConstraints();
		if (fieldSearch != null) {
			toolBar.add(fieldSearch, cc.xy(2, 1));
		}
		if (itemSearch != null) {
			toolBar.add(itemSearch, cc.xy(4, 1));
		}
		toolBar.add(label, cc.xy(6, 1));
		toolBar.add(reload, cc.xy(10, 1));
		// add(view, cc.xy(10, 1));
		toolBar.add(add, cc.xy(12, 1));
		toolBar.add(edit, cc.xy(14, 1));
		toolBar.add(del, cc.xy(16, 1));
		toolBar.add(print, cc.xy(18, 1));

		toolBar.setBorder(App.borderBlackBottom3000);
		
		panel.setLayout(new BorderLayout());
		panel.add(toolBar, BorderLayout.CENTER);

		// set action component
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionAdd();
			}
		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionEdit();
			}
		});

		reload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionReload();
			}
		});

		del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.actionDel();

			}
		});

		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (print.getIcon().equals(IconBase.PRINT)) {
					master.actionPrint();
				} else if (print.getIcon().equals(IconBase.PRINT_PREVIEW)) {
					master.actionPrintPreview();
				} else if (print.getIcon().equals(IconBase.PDF)) {
					master.actionPdf();
				} else if (print.getIcon().equals(IconBase.EXCEL)) {
					master.actionExcel();
				} else if (print.getIcon().equals(IconBase.WORD)) {
					master.actionWord();
				}

			}
		});

		menuItemPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPrint();
				print.setIcon(IconBase.PRINT);
			}
		});

		menuItemPrintPreview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPrintPreview();
				print.setIcon(IconBase.PRINT_PREVIEW);
				print.setText(LActions.PRINT_PREVIEW);
			}
		});

		menuItemPdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionPdf();
				print.setIcon(IconBase.PDF);
				print.setText(LActions.PDF);
			}
		});

		menuItemExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionExcel();
				print.setIcon(IconBase.EXCEL);
				print.setText(LActions.EXCEL);
			}
		});

		menuItemWord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.actionWord();
				print.setIcon(IconBase.WORD);
				print.setText(LActions.WORD);
			}
		});

		// state awal
		edit.setEnabled(false);
		del.setEnabled(false);
	}

	public void init() {
		toolBar = new JToolBar();
		panel =new JPanel();
		reload = new JButton(IconBase.RELOAD);
		add = new JButton(IconBase.ADD);
		edit = new JButton(IconBase.EDIT);
		del = new JButton(IconBase.DEL);
		print = new SplitButton(IconBase.PRINT);

		menuItemPrint = new JMenuItem(LActions.PRINT);
		menuItemPrintPreview = new JMenuItem(LActions.PRINT_PREVIEW);
		menuItemPdf = new JMenuItem(LActions.PDF);
		menuItemExcel = new JMenuItem(LActions.EXCEL);
		menuItemWord = new JMenuItem(LActions.WORD);

		reload.setBackground(Color.WHITE);
		add.setBackground(Color.WHITE);
		edit.setBackground(Color.WHITE);
		del.setBackground(Color.WHITE);
		print.setBackground(Color.WHITE);
		
		if (factorySearch!=null) {
			fieldSearch=factorySearch.getFieldSearch();
			itemSearch=factorySearch.getItemSearch();
		}

	}

	public JButton getReload() {
		return reload;
	}

	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

	public JButton getEdit() {
		return edit;
	}

	public void setEdit(JButton edit) {
		this.edit = edit;
	}

	public JButton getDel() {
		return del;
	}

	public void setDel(JButton del) {
		this.del = del;
	}

	public TextFieldSearch getFieldSearch() {
		return fieldSearch;
	}

	public void setFieldSearch(TextFieldSearch fieldSearch) {
		this.fieldSearch = fieldSearch;
	}

	public SplitButton getItemSearch() {
		return itemSearch;
	}

	public void setItemSearch(SplitButton itemSearch) {
		this.itemSearch = itemSearch;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

	public FactorySearch getFactorySearch() {
		return factorySearch;
	}

	public void setFactorySearch(FactorySearch factorySearch) {
		this.factorySearch = factorySearch;
	}
	
	

}
