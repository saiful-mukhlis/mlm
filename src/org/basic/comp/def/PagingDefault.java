package org.basic.comp.def;

import com.basic.icon.IconBase;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import org.basic.comp.abst.PagingAbstract;
import org.basic.comp.adapter.ParentPagingInterface;

import javax.swing.*;
import java.awt.*;

public class PagingDefault extends PagingAbstract{
	@Override
	public void initComponent() {
		super.initComponent();
		setPanelPaging(panelPaging, reloadButton, firstButton, backButton,
				curentTextFild, pageLabel, nextButton, endButton,
				jumlahRowLabel);
	}

	public PagingDefault(ParentPagingInterface tableModel) {
		super();
		super.tableModel=tableModel;
		super.tableModel.setPaging(this);
		initComponent();
	}
	public void setPanelPaging(JPanel panelPaging, JButton reloadButton,
			JButton firstButton, JButton backButton,
			JTextField curentTextFild, JLabel pageLabel, JButton nextButton,
			JButton endButton, JLabel jumlahRowLabel) {
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
		builder.append(jumlahRowLabel);
		JPanel p = builder.getPanel();
		panelPaging.setLayout(new BorderLayout(0, 0));
		panelPaging.add(p, BorderLayout.CENTER);
	}

}
