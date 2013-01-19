package com.mineshaftersquared;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
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

import com.creatifcubed.simpleapi.ISimpleSettings;
import com.creatifcubed.simpleapi.Platform;
import com.creatifcubed.simpleapi.SimpleXMLSettings;
import com.creatifcubed.simpleapi.SimpleUtils;
import com.mineshaftersquared.resources.MS2Frame;

public class MineshafterSquaredGUI implements Runnable {
	
	private String[] args;
	private ISimpleSettings settings;
	
	private static int DEFAULT_WIDTH = 854;
	private static int DEFAULT_HEIGHT = 480;
	
	public static void main(String[] args) {
		(new MineshafterSquaredGUI(args)).run();
		System.out.println("hi");
	}
	
	public MineshafterSquaredGUI(String[] args) {
		this.args = args;
		new File("ms2-resources").mkdir();
		this.settings = new SimpleXMLSettings("ms2-resources/settings.xml");
	}
	
	public void run() {
		JFrame frame = new JFrame("Mineshafter Squared");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel contentPane = new JPanel(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Main", buildMainPage());
		//tabbedPane.add("Versions", buildVersionsPage());
		tabbedPane.add("Settings", buildSettingsPage());
		//tabbedPane.add("News", buildNewsPage());
		tabbedPane.add("Info", buildInfoPage());
		contentPane.add(tabbedPane);
		
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		System.out.println(SimpleUtils.getRam());
	}
	
	private JComponent buildMainPage() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel infoPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String javaVersion = System.getProperty("java.version");
		String os = Platform.getPlatform().toString();
		infoPane.add(new JLabel(String.format("<html><ul><li>Java Version: %s</li><li>Detected OS: %s</li><li>Proxying on port: %s</li><li>Total Ram: %s</ul></html>", javaVersion, os, "9001", (double) (SimpleUtils.getRam() * 100 / 1024 / 1024 / 1024) / 100 + "GB")));
		infoPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Info"));
		panel.add(infoPane, BorderLayout.NORTH);
		
		JPanel loginPane = new JPanel(new GridLayout(0, 2));
		JLabel usernameLabel = new JLabel("Username");
		final JTextField usernameField = new JTextField(this.settings.getString("username"), 20);
		JLabel passwordLabel = new JLabel("Password");
		final JTextField passwordField = new JPasswordField(this.settings.getString("password"), 20);
		final JCheckBox rememberMe = new JCheckBox("Remember me?", true);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rememberMe.isSelected()) {
					MineshafterSquaredGUI.this.settings.put("username", usernameField.getText());
					MineshafterSquaredGUI.this.settings.put("password", passwordField.getText());
					MineshafterSquaredGUI.this.settings.save();
				}
			}
			
		});
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
		launchClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				goLauncher();
			}
		});
		launchClientPane.add(launchClientButton);
		JPanel launchServerPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton launchServerButton = new JButton("Launch Server");
		String settingsServerName = this.settings.getString("server.name");
		if (settingsServerName == null) {
			settingsServerName = "minecraft_server";
		}
		final JTextField serverName = new JTextField(settingsServerName, 20);
		final JCheckBox rememberServer = new JCheckBox("Set as default?", true);
		launchServerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (rememberServer.isSelected()) {
					String serverFileName = serverName.getText();
					System.out.println(serverFileName);
					MineshafterSquaredGUI.this.settings.put("server.name", serverFileName);
					MineshafterSquaredGUI.this.settings.save();
					MineshafterSquaredGUI.this.goServer();
				}
			}
		});
		JLabel serverNameExtension = new JLabel(".jar");
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
	
	private JComponent buildSettingsPage() {
		final JPanel panel = new JPanel(new GridLayout(0, 1));
		
		JPanel memoryPane = new JPanel(new GridLayout(0, 2));
		memoryPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Ram"));
		final JLabel minMemoryLabel = new JLabel("Minimum Memory");
		long x = (long) Math.floor((double) SimpleUtils.getRam() / 1024 / 1024 / 1024) * 1024;
		final JSlider minMemorySlider = new JSlider(0, (int) x);
		minMemorySlider.setValue(this.settings.getInt("runtime.ram.min", 1024) - 1); // -1 to automatically update label
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
		maxMemorySlider.setValue(this.settings.getInt("runtime.ram.max", 1024) - 1); // -1 to automatically update label
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
					settings.put("runtime.ram.max", Integer.toString(maxMemorySlider.getValue()));
					settings.put("runtime.ram.min", Integer.toString(minMemorySlider.getValue()));
					settings.save();
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
	
	private void goLauncher() {
		String max = this.settings.getString("runtime.ram.max");
		String min = this.settings.getString("runtime.ram.min");
		System.out.println("max is null " + (max == null ?  "true" : max));
		System.out.println("min is null " + (min == null ?  "true" : min));
		
		//MinecraftLauncher launcher = new MinecraftLauncher(/*args*/ new String[] { "Adrian", "a"}, new Dimension(854, 500), false, false);
		//launcher.run();
		
		try {
			String str = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			List<String> arr = new ArrayList<String>();
			arr.add("java");
			if (max != null) {
				arr.add("-Xmx" + max + "m");
			}
			//arr.add("-Dsun.java2d.noddraw=true");
			//arr.add("-Dsun.java2d.d3d=false");
			//arr.add("-Dsun.java2d.opengl=false");
			//arr.add("-Dsun.java2d.pmoffscreen=false");

			arr.add("-classpath");
	        arr.add(str);
	        arr.add("com.mineshaftersquared.resources.GameEntry");
	        arr.add("Adrian");
	        arr.add("a");
	        ProcessBuilder builder = new ProcessBuilder(arr);
	        System.out.println("here");
	        //builder.redirectOutput();
	        builder.redirectErrorStream(true);
	        builder.start();
	        for (String a : arr) {
	        	System.out.print(a + " ");
	        }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void goServer() {
		String max = this.settings.getString("runtime.ram.max");
		String min = this.settings.getString("runtime.ram.min");
		System.out.println("max is " + (max == null ?  "null" : max));
		System.out.println("min is " + (min == null ?  "null" : min));
		
		//MinecraftLauncher launcher = new MinecraftLauncher(/*args*/ new String[] { "Adrian", "a"}, new Dimension(854, 500), false, false);
		//launcher.run();
		
		try {
			String str = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			List<String> arr = new ArrayList<String>();
			arr.add("java");
			if (max != null) {
				arr.add("-Xmx" + max + "m");
				//arr.add("-Xms" + max + "m");
			}
			if (min != null) {
				arr.add("-Xms" + min + "m");
			}

			arr.add("-jar");
	        String server = this.settings.getString("server.name");
	        if (server == null) {
	        	server = "minecraft_server";
	        }
	        server += ".jar";
	        if (!(new File(server).exists())) {
	        	JOptionPane.showMessageDialog(null, "Server file {" + server + "} does not exist");
	        }
	        arr.add(server);
	        ProcessBuilder builder = new ProcessBuilder(arr);
	        builder.redirectOutput();
	        builder.redirectError();
	        builder.start();
	        for (String a : arr) {
	        	System.out.print(a + " ");
	        }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
