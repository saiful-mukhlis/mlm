package org.basic.comp.base;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
	/* the default image to use */
	String imageFile = "/image/wt.jpg";

	public ImagePanel() {
		super();
	}

	public ImagePanel(String image) {
		super();
		this.imageFile = image;
	}

	public ImagePanel(LayoutManager layout) {
		super(layout);
	}

	public void paintComponent(Graphics g) {
		/* create image icon to get image */
		ImageIcon imageicon = new ImageIcon(getClass().getResource(imageFile));
		Image image = imageicon.getImage();

		/* Draw image on the panel */
		super.paintComponent(g);

		if (image != null)
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}
