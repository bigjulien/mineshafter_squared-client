package com.mineshaftersquared.old;

import java.io.File;

import com.mineshaftersquared.resources.Utils;

public class DriverEpsilon {
	public static void main(String[] args) {

		String cwd = System.getProperty("user.dir");
		if (new File("bin/minecraft.jar").exists()) {
			// do nothing
		} else {
			cwd = Utils.getDefaultMCPath();
		}
		System.out.println("src: " + cwd);
	}
}
