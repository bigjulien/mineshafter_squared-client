package com.mineshaftersquared.resources;

import java.awt.Dimension;

import com.mineshaftersquared.MinecraftLauncher;

public class GameEntry {
	public static void main(String[] args) {
		new MinecraftLauncher(new String[] { args[0], args[1] }, false, false).run();
	}
}
