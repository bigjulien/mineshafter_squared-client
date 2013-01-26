package com.mineshaftersquared.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.creatifcubed.simpleapi.Platform;
import com.creatifcubed.simpleapi.SimpleUtils;
import com.mineshaftersquared.MinecraftLauncher;
import com.mineshaftersquared.MineshafterSquaredGUI;

public class IndexTabPanel extends AbstractTabPanel {
	
	private JLabel infoLabel;
	
	public IndexTabPanel(final MineshafterSquaredGUI gui) {
		
		JPanel infoPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.infoLabel =new JLabel();
		infoPane.add(this.infoLabel);
		infoPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Info"));
		this.add(infoPane, BorderLayout.NORTH);
		
		JPanel loginPane = new JPanel(new GridLayout(0, 2));
		JLabel usernameLabel = new JLabel("Username");
		final JTextField usernameField = new JTextField(gui.settings.getString("username"), 20);
		gui.username = usernameField;
		JLabel passwordLabel = new JLabel("Password");
		final JTextField passwordField = new JPasswordField(gui.settings.getString("password"), 20);
		final JCheckBox rememberMe = new JCheckBox("Remember me?", true);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (rememberMe.isSelected()) {
					gui.settings.put("username", usernameField.getText());
					gui.settings.put("password", passwordField.getText());
					gui.settings.save();
				}
			}
			
		});
		JLabel sessionIDDescription = new JLabel("Session ID");
		JLabel sessionID = new JLabel("Not logged in");
		gui.sessionId = sessionID;
		
		loginPane.add(usernameLabel);
		loginPane.add(usernameField);
		loginPane.add(passwordLabel);
		loginPane.add(passwordField);
		loginPane.add(rememberMe);
		loginPane.add(loginButton);
		loginPane.add(sessionIDDescription);
		loginPane.add(sessionID);
		
		loginPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Login"));
		this.add(loginPane, BorderLayout.CENTER);
		
		JPanel launchPane = new JPanel(new BorderLayout());
		
		JPanel launchClientPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton launchClientButton = new JButton("Launch Client");
		launchClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gui.goLauncher();
			}
		});
		int savedPathfind = gui.settings.getInt("gui.launch.pathfind", MinecraftLauncher.PATH_AUTODETECT);
		ButtonGroup pathFindOptions = new ButtonGroup();
		JRadioButton autodetect = new JRadioButton("Autodetect", savedPathfind == MinecraftLauncher.PATH_AUTODETECT);
		autodetect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gui.settings.put("gui.launch.pathfind", MinecraftLauncher.PATH_AUTODETECT);
				gui.settings.save();
			}
		});
		JRadioButton local = new JRadioButton("Local", savedPathfind == MinecraftLauncher.PATH_LOCAL);
		local.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gui.settings.put("gui.launch.pathfind", MinecraftLauncher.PATH_LOCAL);
				gui.settings.save();
			}
		});
		JRadioButton defaultMC = new JRadioButton("Default MC", savedPathfind == MinecraftLauncher.PATH_DEFAULTMC);
		defaultMC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gui.settings.put("gui.launch.pathfind", MinecraftLauncher.PATH_DEFAULTMC);
				gui.settings.save();
			}
		});
		JButton downloadMCButton = new JButton("Download");
		downloadMCButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				gui.doDownload();
			}
		});
		pathFindOptions.add(autodetect);
		pathFindOptions.add(local);
		pathFindOptions.add(defaultMC);
		
		launchClientPane.add(launchClientButton);
		launchClientPane.add(autodetect);
		launchClientPane.add(local);
		launchClientPane.add(defaultMC);
		launchClientPane.add(downloadMCButton);
		JPanel launchServerPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton launchServerButton = new JButton("Launch Server");
		String settingsServerName = gui.settings.getString("server.name");
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
					gui.settings.put("server.name", serverFileName);
					gui.settings.save();
					gui.goServer();
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
		
		this.add(launchPane, BorderLayout.SOUTH);
	}
	
	public void updateInfo(int proxyPort) {
		String javaVersion = System.getProperty("java.version");
		String os = Platform.getPlatform().toString();
		this.infoLabel.setText(String.format("<html><ul><li>Java Version: %s</li><li>Detected OS: %s</li><li>Proxying on port: %d</li><li>Total Ram: %s</ul></html>", javaVersion, os, proxyPort, (double) (SimpleUtils.getRam() * 100 / 1024 / 1024 / 1024) / 100 + "GB"));
	}
}
