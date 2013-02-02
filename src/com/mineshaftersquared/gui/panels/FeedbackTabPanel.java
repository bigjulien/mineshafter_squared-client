package com.mineshaftersquared.gui.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.creatifcubed.simpleapi.SimpleRequest;

public class FeedbackTabPanel extends AbstractTabPanel {
	
	public FeedbackTabPanel() {
		JPanel formPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		
		formPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Submit Feedback"));
		
		JLabel about = new JLabel("<html>Please submit feedback here."
				+ "<br />If you are having problems, make sure you run from"
				+ "<br />terminal or cmd (using scripts provided) and send"
				+ "<br />us the output. Thanks!</html>");
		
		final JTextArea content = new JTextArea(10, 40);
		content.setLineWrap(true);
		content.setWrapStyleWord(true);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JButton submit = new JButton("Submit");
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					new SimpleRequest(new URL("http://ms2.creatifcubed.com/resources/feedback.php")).doPost(content.getText().getBytes());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Thanks for your input! Keep updated on the website.");
			}
		});
		
		c.gridx = 0;
		
		c.gridy = 0;
		formPane.add(about, c);
		c.gridy = 1;
		formPane.add(new JScrollPane(content), c);
		c.gridy = 2;
		formPane.add(submit, c);
		this.add(formPane);
	}
}
