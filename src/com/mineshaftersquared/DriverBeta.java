package com.mineshaftersquared;

import java.awt.Dimension;

import com.mineshaftersquared.resources.Utils;

public class DriverBeta {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Utils.getDefaultMCPath());
		MinecraftLauncher launcher = new MinecraftLauncher(args, new Dimension(854, 500), false, false);
		launcher.run();
	}

}
