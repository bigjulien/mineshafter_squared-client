package com.mineshaftersquared.old;

import java.io.File;
import java.io.IOException;

import com.mineshaftersquared.resources.Utils;

public class DriverEpsilon {
	public static void main(String[] args) {

		String cwd = System.getProperty("user.dir");
		File f = new File("bin/minecraft.jar");
		if (f.exists()) {
			// do nothing
			System.out.println("version: " + Utils.getMCVersion(f));
		} else {
			cwd = Utils.getDefaultMCPath();
		}
		System.out.println("src: " + cwd);
		try {
			Runtime.getRuntime().exec(new String[] {"explorer", cwd});
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("hi");
		File f1 = new File("hi");
	}
}
