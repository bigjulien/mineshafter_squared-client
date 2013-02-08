package com.mineshaftersquared.old;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.util.*;

import com.mineshaftersquared.resources.MS2Frame;

public class DriverDelta {
	public static void main(String[] args) {
		try {
			String str = MS2Frame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			List<String> arr = new ArrayList<String>();
			arr.add("java");
			arr.add("-Xmx1024m");
			arr.add("-Dsun.java2d.noddraw=true");
			arr.add("-Dsun.java2d.d3d=false");
			arr.add("-Dsun.java2d.opengl=false");
			arr.add("-Dsun.java2d.pmoffscreen=false");

			arr.add("-classpath");
	        arr.add(str);
	        arr.add("com.mineshaftersquared.DriverBeta");
	        ProcessBuilder builder = new ProcessBuilder(arr);
	        System.out.println("here");
	        //builder.redirectOutput();
	        //builder.redirectError();
	        builder.start();
	        for (String a : arr) {
	        	System.out.print(a + " ");
	        }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
