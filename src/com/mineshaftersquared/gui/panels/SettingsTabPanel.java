package com.mineshaftersquared.gui.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.creatifcubed.simpleapi.SimpleUtils;
import com.mineshaftersquared.MineshafterSquaredGUI;

public class SettingsTabPanel extends AbstractTabPanel {
	
	public SettingsTabPanel(final MineshafterSquaredGUI gui) {
		JPanel memoryPane = new JPanel(new GridLayout(0, 2));
		memoryPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Ram"));
		final JLabel minMemoryLabel = new JLabel("Minimum Memory");
		long x = (long) Math.floor((double) SimpleUtils.getRam() / 1024 / 1024 / 1024) * 1024;
		final JSlider minMemorySlider = new JSlider(0, (int) x);
		minMemorySlider.setValue(gui.settings.getInt("runtime.ram.min", 1024) - 1); // -1 to automatically update label
		minMemorySlider.setMajorTickSpacing(1024);
		minMemorySlider.setMinorTickSpacing(512);
		minMemorySlider.setPaintTicks(true);
		minMemorySlider.setPaintLabels(true);
		minMemorySlider.setSnapToTicks(true);
		minMemorySlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				minMemoryLabel.setText("Minimum memory: -Xms" + ((JSlider) event.getSource()).getValue() + "m");
			}
		});
		memoryPane.add(minMemoryLabel);
		memoryPane.add(minMemorySlider);
		final JLabel maxMemoryLabel = new JLabel("Maximum Memory");
		final JSlider maxMemorySlider = new JSlider(0, (int) x);
		maxMemorySlider.setValue(gui.settings.getInt("runtime.ram.max", 1024) - 1); // -1 to automatically update label
		maxMemorySlider.setMajorTickSpacing(1024);
		maxMemorySlider.setMinorTickSpacing(512);
		maxMemorySlider.setPaintTicks(true);
		maxMemorySlider.setPaintLabels(true);
		maxMemorySlider.setSnapToTicks(true);
		maxMemorySlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				maxMemoryLabel.setText("Maximum memory: -Xmx" + ((JSlider) event.getSource()).getValue() + "m");
			}
		});
		memoryPane.add(maxMemoryLabel);
		memoryPane.add(maxMemorySlider);
		
		JButton memorySaveButton = new JButton("Save");
		memorySaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				List<String> errorMessages = new LinkedList<String>();
				if (minMemorySlider.getValue() <= 0) {
					errorMessages.add("Initial memory must be greater than 0");
				}
				if (minMemorySlider.getValue() > maxMemorySlider.getValue()) {
					errorMessages.add("Max memory must be greater or equal to intial memory");
				}
				if (errorMessages.size() == 0) {
					gui.settings.put("runtime.ram.max", Integer.toString(maxMemorySlider.getValue()));
					gui.settings.put("runtime.ram.min", Integer.toString(minMemorySlider.getValue()));
					gui.settings.save();
				} else {
					String bin = "";
					for (String message : errorMessages) {
						bin += message + "\n";
					}
					JOptionPane.showMessageDialog(null, bin);
				}
			}
		});
		JPanel memorySaveButtonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		memorySaveButtonWrapper.add(memorySaveButton);
		memoryPane.add(new JPanel());
		memoryPane.add(memorySaveButtonWrapper);
		
		this.add(memoryPane);
	}
}
