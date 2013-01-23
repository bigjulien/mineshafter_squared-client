package com.mineshaftersquared.gui.panels;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class AbstractTabPanel extends JPanel {
	public AbstractTabPanel() {
		super(new GridLayout(0, 1));
	}
	
	public AbstractTabPanel(LayoutManager layout) {
		super(layout);
	}
}
