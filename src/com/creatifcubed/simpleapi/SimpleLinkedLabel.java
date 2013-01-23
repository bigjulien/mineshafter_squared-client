package com.creatifcubed.simpleapi;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

import javax.swing.JLabel;

public class SimpleLinkedLabel extends JLabel {
	private String url;
	
	public SimpleLinkedLabel(String text, String url) {
		super(String.format("<html><a href=\"%s\">%s</a></html>", url, text));
		this.url = url;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI(SimpleLinkedLabel.this.url));
					} catch (Exception ignore) {
						//
					}
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				SimpleLinkedLabel.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				SimpleLinkedLabel.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				//
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				//
			}
			
		});
	}
	
	
	public String getURL() {
		return this.url;
	}
}
