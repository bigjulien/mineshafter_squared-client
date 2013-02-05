package com.mineshaftersquared.gui.panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.creatifcubed.simpleapi.SimpleUtils;
import com.mineshaftersquared.MineshafterSquaredGUI;

public class SettingsTabPanel extends AbstractTabPanel {
	
	public static final int RAM_STEP_SIZE = 256;
	
	public SettingsTabPanel(final MineshafterSquaredGUI gui) {
		JPanel memoryPane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		int row = 0;
		//c.weightx = 0.01;
		//c.weighty = 0.01;
		memoryPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Ram"));
		
		
		final JLabel minMemoryLabel = new JLabel("Minimum Memory");
		int maxRam = (int) (Math.floor((double) SimpleUtils.getRam() / 1024 / 1024 / 1024) * 1024);
		c.gridx = 0;
		c.gridy = row++;
		memoryPane.add(new JLabel("You have " + maxRam + " MBs of ram. Set to 0 ram to ignore setting."), c);
		
		SpinnerNumberModel minMemorySpinnerModel = new SpinnerNumberModel(constrain(gui.settings.getInt("runtime.ram.min", 1024).intValue(), 0, maxRam), 0, maxRam, RAM_STEP_SIZE);
		final JSpinner minMemorySpinner = new JSpinner(minMemorySpinnerModel);
		c.gridx = 0;
		c.gridy = row++;
		memoryPane.add(minMemoryLabel, c);
		//memoryPane.add(minMemorySlider);
		c.gridx = 1;
		memoryPane.add(new JLabel("-Xms"), c);
		c.gridx = 2;
		memoryPane.add(minMemorySpinner, c);
		c.gridx = 3;
		memoryPane.add(new JLabel("m"), c);
		final JLabel maxMemoryLabel = new JLabel("Maximum Memory");
		SpinnerNumberModel maxMemorySpinnerModel = new SpinnerNumberModel(gui.settings.getInt("runtime.ram.max").intValue(), 0, maxRam, RAM_STEP_SIZE);
		final JSpinner maxMemorySpinner = new JSpinner(maxMemorySpinnerModel);
		c.gridx = 0;
		c.gridy = row++;
		memoryPane.add(maxMemoryLabel, c);
		c.gridx = 1;
		memoryPane.add(new JLabel("-Xmx"), c);
		c.gridx = 2;
		memoryPane.add(maxMemorySpinner, c);
		c.gridx = 3;
		memoryPane.add(new JLabel("m"), c);
		
		JButton memorySaveButton = new JButton("Save");
		memorySaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				List<String> errorMessages = new LinkedList<String>();
				int min = ((Number) minMemorySpinner.getValue()).intValue();
				int max = ((Number) maxMemorySpinner.getValue()).intValue();
				if (min > max && max != 0) {
					errorMessages.add("Max memory must be greater or equal to intial memory");
				}
				if (errorMessages.size() == 0) {
					gui.settings.put("runtime.ram.max", max);
					gui.settings.put("runtime.ram.min", min);
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
		//memoryPane.add(new JPanel());
		c.gridx = 0;
		c.gridy = row++;
		memoryPane.add(memorySaveButtonWrapper, c);
		
		this.add(memoryPane);
	}
	
	private static int constrain(int value, int min, int max) {
		return Math.min(max, Math.max(min, value));
	}
}
