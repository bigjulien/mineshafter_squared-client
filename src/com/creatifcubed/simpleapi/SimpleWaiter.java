package com.creatifcubed.simpleapi;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.mineshaftersquared.GameUpdaterProxy;
import com.mineshaftersquared.MinecraftLauncher;

public class SimpleWaiter implements Runnable {
	private String title;
	private Runnable task;
	private JFrame parent;
	
	public SimpleWaiter(String title, Runnable task, JFrame parent) {
		this.title = title;
		this.task = task;
		this.parent = parent;
	}
	
	public void run() {
		final JDialog dialog = new JDialog(this.parent, this.title, true);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				SimpleWaiter.this.task.run();
				dialog.dispose();
			}
		});
		t.start();
		JPanel panel = new JPanel();
		JProgressBar progress = new JProgressBar();
		progress.setIndeterminate(true);
		panel.add(progress);
		dialog.setContentPane(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose(); // safe measure
	}
}
