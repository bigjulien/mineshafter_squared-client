package com.mineshaftersquared.resources;

import java.awt.Dimension;

import com.creatifcubed.simpleapi.SimpleVersion;
import com.mineshaftersquared.MinecraftLauncher;
import com.mineshaftersquared.proxy.MineProxy;

public class GameEntry {
	public static void main(String[] args) {
		String username = "";
		String sessionId = "";
		int pathFind = MinecraftLauncher.PATH_AUTODETECT;
		SimpleVersion version = null;
		String authserver = "mineshaftersquared.com";
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
		if (args.length > 3) {
			authserver = args[3];
		}
		if (args.length > 4) {
			try {
				version = new SimpleVersion(args[4]);
			} catch (Exception ignore) {
				//
			}
		}
		
		MineProxy proxy = new MineProxy(version, authserver); // create proxy
		proxy.start(); // launch proxy

		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", String.valueOf(proxy.getPort()));
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		System.setProperty("minecraft.applet.WrapperClass", "com.mineshaftersquared.resources.MS2Container");
		MinecraftLauncher m = new MinecraftLauncher(new String[] { username, sessionId });
		m.setPathfind(pathFind);
		m.setCompat(false);
		m.run();
	}
}
