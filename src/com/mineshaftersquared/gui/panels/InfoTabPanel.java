package com.mineshaftersquared.gui.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.creatifcubed.simpleapi.SimpleLinkedLabel;
import com.creatifcubed.simpleapi.SimpleSelectableLabel;

public class InfoTabPanel extends AbstractTabPanel {
	public InfoTabPanel() {
		//JPanel infoPane = new JPanel(new GridLayout(0, 2));
		JPanel infoPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		infoPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "About"));
		c.anchor = GridBagConstraints.WEST;
		c.ipadx = 40;
		c.ipady = 20;
		
		c.gridx = 0;
		
		c.gridy = 0;
		infoPane.add(new JLabel("Website"), c);
		c.gridy = 1;
		infoPane.add(new JLabel("Launcher Source"), c);
		c.gridy = 2;
		infoPane.add(new JLabel("Main dude"), c);
		c.gridy = 3;
		infoPane.add(new JLabel("Launcher"), c);
		c.gridy = 4;
		infoPane.add(new JLabel("Forum Design"), c);
		
		c.gridx = 1;
		c.gridy = 0;
		infoPane.add(new SimpleLinkedLabel("mineshaftersquared.com", "http://mineshaftersquared.com"), c);
		c.gridy = 1;
		infoPane.add(new SimpleLinkedLabel("Github", "https://github.com/Raekye/mineshafter_squared-client"), c);
		c.gridy = 2;
		infoPane.add(new SimpleLinkedLabel("Ryan", "http://www.kayoticlabs.com/"), c);
		c.gridy = 3;
		infoPane.add(new SimpleLinkedLabel("Adrian", "http://creatifcubed.com"), c);
		//infoPane.add(new JLabel("Adrian"), c);
		c.gridy = 4;
		infoPane.add(new JLabel("Rodney"), c);
		
		c.gridx = 2;
		c.gridy = 1;
		infoPane.add(new SimpleSelectableLabel("github.com/Raekye/mineshafter_squared-client"), c);
		c.gridy = 2;
		infoPane.add(new SimpleSelectableLabel("kayoticlabs.com"), c);
		c.gridy = 3;
		infoPane.add(new SimpleSelectableLabel("creatifcubed.com"), c);
		//infoPane.add(new JLabel("Adrian"), c);
		
		this.add(infoPane);
		
		JPanel usagePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String usage = "<ul>"
				+ "<li>This launcher is portable</li>"
				+ "<li>If you choose 'Default MC' it will download/load Minecraft from the default location</li>"
				+ "<li>If you choose 'local', it will play in the current directory</li>"
				+ "<li>If you choose 'Autodetect', it will first look for Minecraft in the local directory, and fall back to the default location</li>"
				+ "<li>It uses Kyaotic Sully's cool skin and texture system!</li>"
				+ "<li>Allows launching with custom ram</li>"
				+ "<li>It works with Forge!</li>"
				+ "<li>Hope you enjoy it!</li>"
				+ "</ul>";
		usagePane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Usage"));
		usagePane.add(new JLabel(String.format("<html><div style=\"width: %dpx;\">%s</div>", 400, usage)));
		this.add(usagePane);
	}
}
