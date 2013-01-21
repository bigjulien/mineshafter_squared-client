package com.mineshaftersquared.resources;

import java.awt.Dimension;

import com.mineshaftersquared.MinecraftLauncher;

public class GameEntry {
	public static void main(String[] args) {
		String username = "";
		String sessionId = "";
		int pathFind = MinecraftLauncher.PATH_AUTODETECT;
		if (args.length > 0) {
			username = args[0];
		}
		if (args.length > 1) {
			sessionId = args[1];
		}
		if (args.length > 2) {
			try {
				pathFind = Integer.parseInt(args[2]);
			} catch (NumberFormatException ignore) {
				//
			}
		}
		MinecraftLauncher m = new MinecraftLauncher(new String[] { username, sessionId });
		m.setPathfind(pathFind);
		m.run();
	}
}
