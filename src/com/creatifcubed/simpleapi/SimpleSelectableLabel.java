package com.creatifcubed.simpleapi;

import java.awt.Cursor;

import javax.swing.JTextField;

public class SimpleSelectableLabel extends JTextField {

	public SimpleSelectableLabel(String text) {
		super(text);
		this.setEditable(false);
		this.setBackground(null);
		this.setBorder(null);
		this.setCursor(new Cursor(Cursor.TEXT_CURSOR));
	}
}
