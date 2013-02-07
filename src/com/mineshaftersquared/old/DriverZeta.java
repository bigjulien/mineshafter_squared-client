package com.mineshaftersquared.old;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.mineshaftersquared.gui.panels.FeedbackTabPanel;
import com.mineshaftersquared.gui.panels.IndexTabPanel;
import com.mineshaftersquared.gui.panels.InfoTabPanel;
import com.mineshaftersquared.gui.panels.SettingsTabPanel;

public class DriverZeta {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Mineshafter Squared ");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(new JLabel("hi"));
		
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		new Thread(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Mineshafter Squared ");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel contentPane = new JPanel(new BorderLayout());
				contentPane.add(new JLabel("2"));
				
				frame.setContentPane(contentPane);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setVisible(true);
			}
		}).start();
	}
}
