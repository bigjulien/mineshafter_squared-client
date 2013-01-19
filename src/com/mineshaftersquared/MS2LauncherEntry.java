package com.mineshaftersquared;

import java.io.File;

import com.creatifcubed.simpleapi.SimpleXMLSettings;

public class MS2LauncherEntry {
	public static void main(String[] args) {
		new File("ms2-resources").mkdir();
		SimpleXMLSettings settings = new SimpleXMLSettings("ms2-resources/settings.xml");
		String max = settings.getString("runtime.ram.max");
		
		
		if (max != null) {
			
		}
	}
}
