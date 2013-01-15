package com.mineshaftersquared;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.creatifcubed.simpleapi.Platform;
import com.creatifcubed.simpleapi.SimpleUtils;

public class MineshafterSquaredGUI implements Runnable {
	
	private String[] args;
	
	private static int DEFAULT_WIDTH = 854;
	private static int DEFAULT_HEIGHT = 480;
	
	public static void main(String[] args) {
		(new MineshafterSquaredGUI(args)).run();
		System.out.println("hi");
	}
	
	public MineshafterSquaredGUI(String[] args) {
		this.args = args;
	}
	
	public void run() {
		JFrame frame = new JFrame("Mineshafter Squared");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel contentPane = new JPanel(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Main", buildMainPage());
		tabbedPane.add("Versions", buildVersionsPage());
		tabbedPane.add("Settings", buildSettingsPage());
		tabbedPane.add("News", buildNewsPage());
		tabbedPane.add("Info", buildInfoPage());
		contentPane.add(tabbedPane);
		
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		System.out.println(SimpleUtils.getRam());
	}
	
	private static JComponent buildMainPage() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel infoPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String javaVersion = System.getProperty("java.version");
		String os = Platform.getPlatform().toString();
		infoPane.add(new JLabel(String.format("<html><ul><li>Java Version: %s</li><li>Detected OS: %s</li><li>Proxying on port: %s</li><li>Total Ram: %s</ul></html>", javaVersion, os, "9001", (double) (SimpleUtils.getRam() * 100 / 1024 / 1024 / 1024) / 100 + "GB")));
		infoPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Info"));
		panel.add(infoPane, BorderLayout.NORTH);
		
		JPanel loginPane = new JPanel(new GridLayout(0, 2));
		JLabel usernameLabel = new JLabel("Username");
		JTextField usernameField = new JTextField("READ FILE", 20);
		JLabel passwordLabel = new JLabel("Password");
		JTextField passwordField = new JPasswordField("READ FILE", 20);
		JCheckBox rememberMe = new JCheckBox("Remember me?", true);
		JButton loginButton = new JButton("Login");
		JLabel sessionIDDescription = new JLabel("Session ID");
		JLabel sessionID = new JLabel("Not logged in");
		
		loginPane.add(usernameLabel);
		loginPane.add(usernameField);
		loginPane.add(passwordLabel);
		loginPane.add(passwordField);
		loginPane.add(rememberMe);
		loginPane.add(loginButton);
		loginPane.add(sessionIDDescription);
		loginPane.add(sessionID);
		
		loginPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Login"));
		panel.add(loginPane, BorderLayout.CENTER);
		
		JPanel launchPane = new JPanel(new BorderLayout());
		
		JPanel launchClientPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton launchClientButton = new JButton("Launch Client");
		launchClientPane.add(launchClientButton);
		JPanel launchServerPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton launchServerButton = new JButton("Launch Server");
		final JTextField serverName = new JTextField("READ FILE", 20);
		launchServerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String serverFileName = serverName.getText() + ".jar";
				System.out.println(serverFileName);
			}
		});
		JLabel serverNameExtension = new JLabel(".jar");
		JCheckBox rememberServer = new JCheckBox("Set as default?", true);
		launchServerPane.add(launchServerButton);
		launchServerPane.add(serverName);
		launchServerPane.add(serverNameExtension);
		launchServerPane.add(rememberServer);
		
		launchPane.add(launchClientPane, BorderLayout.NORTH);
		launchPane.add(launchServerPane, BorderLayout.SOUTH);
		launchPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Launch"));
		
		panel.add(launchPane, BorderLayout.SOUTH);
		
		return panel;
	}

	private static JComponent buildVersionsPage() {
		JPanel panel = new JPanel();
		
		
		
		return panel;
	}
	
	private static JComponent buildSettingsPage() {
		final JPanel panel = new JPanel(new GridLayout(0, 1));
		
		JPanel memoryPane = new JPanel(new GridLayout(0, 2));
		memoryPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Ram"));
		final JLabel minMemoryLabel = new JLabel("Minimum Memory");
		long x = (long) Math.floor((double) SimpleUtils.getRam() / 1024 / 1024 / 1024) * 1024;
		final JSlider minMemorySlider = new JSlider(0, (int) x);
		minMemorySlider.setValue(300); // READ VALUE
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
		maxMemorySlider.setValue(300); // READ VALUE
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
					// save
				} else {
					String bin = "";
					for (String message : errorMessages) {
						bin += message + "\n";
					}
					JOptionPane.showMessageDialog(panel, bin);
				}
			}
		});
		JPanel memorySaveButtonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		memorySaveButtonWrapper.add(memorySaveButton);
		memoryPane.add(new JPanel());
		memoryPane.add(memorySaveButtonWrapper);
		
		panel.add(memoryPane);
		
		return panel;
	}
	
	private static JComponent buildNewsPage() {
		JPanel panel = new JPanel();
		
		
		
		return panel;
	}
	
	private static JComponent buildInfoPage() {
		JPanel panel = new JPanel();
		
		
		
		return panel;
	}
	
	private static File[] listAllMinecraftsIn(File dir) {
		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File f, String name) {
				return f.isFile() && Pattern.compile("[a-zA-Z0-9_.-]*.jar").matcher(name).matches();
			}
		});
	}
}
