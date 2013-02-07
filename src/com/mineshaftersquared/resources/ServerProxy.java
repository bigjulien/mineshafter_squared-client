package com.mineshaftersquared.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.creatifcubed.simpleapi.SimpleVersion;
import com.mineshaftersquared.proxy.MineProxy;

public class ServerProxy {
	
	public static void main(String[] args) {
		try {
			String serverJar = "minecraft_server.jar";
			String authserver = "mineshaftersquared.com";
			SimpleVersion version = null;
			Integer maxRam = null;
			Integer minRam = null;
			if (args.length > 0) {
				serverJar = args[0];
			}
			if (args.length > 1) {
				authserver = args[1];
			}
			if (args.length > 2) {
				try {
					version = new SimpleVersion(args[2]);
				} catch (Exception ignore) {
					//
				}
			}
			if (args.length > 3) {
				try {
					maxRam = Integer.parseInt(args[3]);
				} catch (Exception ignore) {
					//
				}
			}
			if (args.length > 4) {
				try {
					minRam = Integer.parseInt(args[4]);
				} catch (Exception ignore) {
					//
				}
			}
			System.out.println("here");
			
			MineProxy proxy = new MineProxy(version, authserver); // create proxy
			proxy.start(); // launch proxy
			
			System.setProperty("http.proxyHost", "127.0.0.1");
			System.setProperty("http.proxyPort", String.valueOf(proxy.getPort()));
			System.setProperty("java.net.preferIPv4Stack", "true");
			
			List<String> arr = new ArrayList<String>();
			arr.add("java");
			if (maxRam != null) {
				arr.add("-Xmx" + maxRam + "m");
				//arr.add("-Xms" + max + "m");
			}
			if (minRam != null) {
				arr.add("-Xms" + minRam + "m");
			}
			
	
			arr.add("-Dhttp.proxyHost=127.0.0.1");
			arr.add("-Dhttp.proxyPort=" + proxy.getPort());
	
			arr.add("-jar");
	        String server = serverJar;
	        if (!(new File(server).exists())) {
	        	JOptionPane.showMessageDialog(null, "Server file {" + server + "} does not exist");
	        }
	        arr.add(server);
	        ProcessBuilder builder = new ProcessBuilder(arr);
	        builder.redirectOutput();
	        builder.redirectError();
	        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
	        Process p = builder.start();
	        for (String a : arr) {
	        	System.out.print(a + " ");
	        }
	        p.waitFor();
	        System.out.println("Done");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//System.out.println("hmmmmm");
		System.exit(0);
	}
}
