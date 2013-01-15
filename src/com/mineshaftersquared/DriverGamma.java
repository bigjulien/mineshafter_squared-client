package com.mineshaftersquared;

import java.util.LinkedList;
import java.util.List;

public class DriverGamma {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			List<String> processString = new LinkedList<String>();
			processString.add("java");
			processString.add("-Xmx2048m");
			
			//processString.add("-Dsun.java2d.noddraw=true");
			//processString.add("-Dsun.java2d.d3d=false");
			//processString.add("-Dsun.java2d.opengl=false");
			//processString.add("-Dsun.java2d.pmoffscreen=false");
	        
			processString.add("-classpath");
			processString.add(MinecraftLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			processString.add("com.mineshaftersquared.DriverBeta");
			processString.add("abc");
			processString.add("def");
			
			ProcessBuilder processBuilder = new ProcessBuilder(processString);
			Process process = processBuilder.start();
			System.out.println("process is null " + (process == null));
			for (String str : processString) {
				System.out.print(" " + str);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
