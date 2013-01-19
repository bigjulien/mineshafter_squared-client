package com.mineshaftersquared.old;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

public class DriverAlpha {
	public static void main(String[] args) {
		//new MinecraftLauncher(new String[] { "adrian", "123"}, new Dimension(854, 600), true, false).run();
		/*ProcessBuilder pb = new ProcessBuilder("java", "-Xmx1024m", "-Xms1024m",
			    "-DTOOLS_DIR=/home/IM/work/dist", "-Daoi=whole", 
			    "-jar", "/home/IM/work/dist/idt_tools.jar");*/
		File f = new File("C:/Users/adrian/appdata/roaming/.minecraft/bin/minecraft.jar");
		System.out.println(f.exists());
		ProcessBuilder pb2 = new ProcessBuilder("java", "-jar", "C:/Users/adrian/appdata/roaming/.minecraft/bin/minecraft.jar", "adrian", "123");
		pb2.environment().put("APPDATA", new File(System.getenv("APPDATA")).getAbsolutePath());
		System.out.println(new File(System.getenv("APPDATA")).getAbsolutePath());
		System.setProperty("org.lwjgl.librarypath", "C:/Users/adrian/appdata/roaming/.minecraft/bin/natives");
        System.setProperty("net.java.games.input.librarypath", "C:/Users/adrian/appdata/roaming/.minecraft/bin/natives");
        System.setProperty("minecraft.applet.TargetDirectory", System.getProperty("user.dir"));
		try {
			Process p = pb2.start();
			System.out.println(p == null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
