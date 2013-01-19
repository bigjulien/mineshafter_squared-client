package com.mineshaftersquared;

import java.io.File;

import com.creatifcubed.simpleapi.SimpleSettings;

public class MS2LauncherEntry {
	public static void main(String[] args) {
		new File("ms2-resources").mkdir();
		SimpleSettings settings = new SimpleSettings("ms2-resources/settings.xml");
		String max = settings.get("runtime.ram.max");
		
		
		if (max != null) {
			
		}
	}
}
