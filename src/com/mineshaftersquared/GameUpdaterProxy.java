package com.mineshaftersquared;

import java.applet.Applet;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;

import com.mineshaftersquared.resources.MS2Container;
import com.mineshaftersquared.resources.MS2Frame;
import com.mineshaftersquared.resources.Utils;

public class GameUpdaterProxy {
	private String dir;
	
	public GameUpdaterProxy(String dir) {
		this.dir = dir;
	}
	
	public Applet update() {
		try {
			if (!hasOfficialLauncher()) {
				downloadOfficialLauncher();
			}
			new File(this.dir, "bin").mkdir();
			System.out.println("this dir " + this.dir);
			URLClassLoader gameupdater = new URLClassLoader(new URL[] { new File(this.dir, "minecraft.jar").toURI().toURL()});
			Class game = gameupdater.loadClass("net.minecraft.GameUpdater");
			Constructor constructor = game.getConstructor(String.class, String.class, Boolean.TYPE);
			File oldMc = new File(new File(this.dir, "bin"), "minecraft.jar");
			String oldVersion = "-1";
			if (oldMc.exists()) {
				oldVersion = Utils.getMCVersion(oldMc);
			}
			System.out.println("THIS DIR: " + this.dir);
			Runnable g = (Runnable) constructor.newInstance(oldVersion, "minecraft.jar", false);
			
			String path = this.dir + "/bin/";
			Method foo = game.getDeclaredMethod("downloadJars", String.class);
			Method bar = game.getDeclaredMethod("extractJars", String.class);
			Method baz = game.getDeclaredMethod("extractNatives", String.class);
			
			foo.setAccessible(true);
			bar.setAccessible(true);
			baz.setAccessible(true);
			Method binky = game.getDeclaredMethod("loadJarURLs");
			binky.setAccessible(true);
			binky.invoke(g);
			
			foo.invoke(g, path);
			bar.invoke(g, path);
			baz.invoke(g, path);
			
			Field f = g.getClass().getDeclaredField("classLoader");
			f.setAccessible(true);
			f.set(null, new MinecraftLauncher(null).getClassLoader());
			System.out.println("done downloading");
			
			/*Method m = game.getMethod("createApplet");
			Applet a = (Applet) m.invoke(g);
			return a;*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void downloadOfficialLauncher() {
		try {
		    URL loc = new URL("https://s3.amazonaws.com/MinecraftDownload/launcher/minecraft.jar");
		    ReadableByteChannel rbc = Channels.newChannel(loc.openStream());
		    FileOutputStream fos = new FileOutputStream("minecraft.jar");
		    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean hasOfficialLauncher() {
		return new File("minecraft.jar").exists();
	}
}
