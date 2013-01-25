package org.basic.comp.abst;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.basic.icon.IconBase;
import com.global.App;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class PagingAbs implements Paging{

	protected TableModel tableModel;

	protected JPanel panel;
	protected JButton reloadButton;
	protected JButton firstButton;
	protected JButton backButton;
	protected JTextField curentTextFild;
	protected JLabel pageLabel;
	protected JButton nextButton;
	protected JButton endButton;
	protected JLabel countRowLabel;

	protected long count = (long) 0;
	protected long curentPage = 1;
	protected long countPage = 1;
	protected long countPerPage = 50;

	public void init() {

		panel = new JPanel(new BorderLayout());
		reloadButton = new JButton();
		firstButton = new JButton();
		backButton = new JButton();
		curentTextFild = new JTextField(5);
		pageLabel = new JLabel();
		nextButton = new JButton();
		endButton = new JButton();
		countRowLabel = new JLabel();

		reloadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db = App.getDbdLocal();
				tableModel.reload(db);
				db.close();
			}
		});
		nextButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db = App.getDbdLocal();
				next(db);
				db.close();
			}
		});
		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db = App.getDbdLocal();
				prev(db);
				db.close();
			}
		});
		firstButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db = App.getDbdLocal();
				first(db);
				db.close();
			}
		});
		endButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db = App.getDbdLocal();
				last(db);
				db.close();
			}
		});
		curentTextFild.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db = App.getDbdLocal();
				go(db);
				db.close();
			}
		});

	}

	public void build() {
		init();

		FormLayout layout = new FormLayout(
				"p:g, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, p:g");
		layout.setColumnGroups(new int[][] { { 3, 5, 7, 13, 15 } });
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		// builder.setDefaultDialogBorder();

		reloadButton.setIcon(IconBase.RELOAD);
		reloadButton.setContentAreaFilled(false);
		reloadButton.setBorder(null);
		firstButton.setIcon(IconBase.FIRST);
		firstButton.setContentAreaFilled(false);
		firstButton.setBorder(null);
		backButton.setIcon(IconBase.BACK);
		backButton.setContentAreaFilled(false);
		backButton.setBorder(null);
		nextButton.setIcon(IconBase.NEXT);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorder(null);
		endButton.setIcon(IconBase.END);
		endButton.setContentAreaFilled(false);
		endButton.setBorder(null);

		builder.nextColumn();
		builder.nextColumn();
		builder.append(reloadButton);
		builder.append(firstButton);
		builder.append(backButton);
		builder.append(curentTextFild);
		builder.append(pageLabel);
		builder.append(nextButton);
		builder.append(endButton);
		builder.append(countRowLabel);
		JPanel p = builder.getPanel();
		panel.add(p, BorderLayout.CENTER);
	}

	public void loadFirst(ODatabaseDocumentTx db) {
		countPage = (long) (count / countPerPage);
		if (count % countPerPage != 0) {
			countPage++;
		}
		last(db);
		reset();
	}

	public void reset() {
		curentTextFild.setText(curentPage + "");
		pageLabel.setText("  from " + (countPage) + " page  ");
		countRowLabel.setText(" Total " + count + " row");
	}

	public void setFistPaging() {
		reset();
		backButton.setEnabled(false);
		firstButton.setEnabled(false);
		if (countPage <= 1) {
			nextButton.setEnabled(false);
			endButton.setEnabled(false);
			curentTextFild.setEditable(false);
		} else {
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
			curentTextFild.setEditable(true);
		}

	}

	public void next(ODatabaseDocumentTx db) {
		curentPage++;
		if (curentPage == countPage) {
			nextButton.setEnabled(false);
			endButton.setEnabled(false);
		}
		backButton.setEnabled(true);
		firstButton.setEnabled(true);
		curentTextFild.setText(curentPage + "");
		tableModel.reload(db);
	}

	public void prev(ODatabaseDocumentTx db) {
		curentPage--;
		if (curentPage == 1) {
			backButton.setEnabled(false);
			firstButton.setEnabled(false);
		}
		nextButton.setEnabled(true);
		endButton.setEnabled(true);
		curentTextFild.setText(curentPage + "");
		tableModel.reload(db);
	}

	public void first(ODatabaseDocumentTx db) {
		curentPage = 1;
		backButton.setEnabled(false);
		firstButton.setEnabled(false);
		if (countPage > 1) {
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
		}
		curentTextFild.setText(curentPage + "");
		tableModel.reload(db);
	}

	public void last(ODatabaseDocumentTx db) {
		curentPage = countPage;
		backButton.setEnabled(true);
		firstButton.setEnabled(true);
		nextButton.setEnabled(false);
		endButton.setEnabled(false);
		curentTextFild.setText(curentPage + "");
		tableModel.reload(db);
	}

	public void go(ODatabaseDocumentTx db) {
		long tmp = curentPage;
		try {
			curentPage = Long.parseLong(curentTextFild.getText());
		} catch (Exception e) {
		}
		if (curentPage == 1 || curentPage > (countPage)) {
			curentPage = tmp;
		} else {
			// curentHalaman--;
		}
		if (curentPage == 1) {
			first(db);
		} else if (curentPage == countPage) {
			last(db);
		} else {
			backButton.setEnabled(true);
			firstButton.setEnabled(true);
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
			tableModel.reload(db);
		}

	}


	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getReloadButton() {
		return reloadButton;
	}

	public void setReloadButton(JButton reloadButton) {
		this.reloadButton = reloadButton;
	}

	public JButton getFirstButton() {
		return firstButton;
	}

	public void setFirstButton(JButton firstButton) {
		this.firstButton = firstButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JTextField getCurentTextFild() {
		return curentTextFild;
	}

	public void setCurentTextFild(JTextField curentTextFild) {
		this.curentTextFild = curentTextFild;
	}

	public JLabel getPageLabel() {
		return pageLabel;
	}

	public void setPageLabel(JLabel pageLabel) {
		this.pageLabel = pageLabel;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}

	public JButton getEndButton() {
		return endButton;
	}

	public void setEndButton(JButton endButton) {
		this.endButton = endButton;
	}

	public JLabel getCountRowLabel() {
		return countRowLabel;
	}

	public void setCountRowLabel(JLabel countRowLabel) {
		this.countRowLabel = countRowLabel;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		if (count<countPerPage) {
			panel.setVisible(false);
		}else{
			panel.setVisible(true);
		}
		this.count = count;
	}

	public long getCurentPage() {
		if (curentPage <= 0) {
			curentPage = 1;
		}
		return curentPage;
	}

	public void setCurentPage(int curentPage) {
		this.curentPage = curentPage;
	}

	public long getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public long getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	@Override
	public long getCountStart() {
		long i=(curentPage-1)*countPerPage;
		if (i<0) {
			i=0;
		}
		return i;
	}

}
