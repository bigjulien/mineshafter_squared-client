package com.mineshaftersquared;

import java.applet.Applet;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import com.mineshaftersquared.resources.MS2Container;
import com.mineshaftersquared.resources.MS2Frame;
import com.mineshaftersquared.resources.Utils;

public class MinecraftLauncher extends Applet implements Runnable {
	private String[] credentials; // username and sessionID
	private boolean compatMode;
	private boolean maximize;
	private int pathFind;
	
	public static final int PATH_AUTODETECT = 0;
	public static final int PATH_DEFAULTMC = 1;
	public static final int PATH_LOCAL = 2;
	
	public MinecraftLauncher(String[] credentials) {
		this.credentials = credentials;
		this.compatMode = false;
		this.maximize = false;
		this.pathFind = PATH_AUTODETECT;
	}
	
	public void setCompat(boolean b) {
		this.compatMode = b;
	}
	
	public void setMaximize(boolean b) {
		this.maximize = b;
	}
	
	public void setPathfind(int type) {
		this.pathFind = type;
	}
	//public MinecraftLauncher() {};
	
	public void run() {
		String cwd = System.getProperty("user.dir");
		switch (this.pathFind) {
			case PATH_DEFAULTMC:
				cwd = Utils.getDefaultMCPath();
				break;
			case PATH_LOCAL:
				// do nothing
				break;
			default:
				if (new File("bin/minecraft.jar").exists()) {
					// do nothing
				} else {
					cwd = Utils.getDefaultMCPath();
				}
				break;
		}
		//System.out.println("src: " + cwd);
		
		try {
			String[] jarFiles = new String[] {
				"minecraft.jar", "lwjgl.jar", "lwjgl_util.jar", "jinput.jar"
			};
			
			URL[] urls = new URL[jarFiles.length];
			File binDir = new File(cwd, "bin");
			
			for (int i = 0; i < urls.length; i++) {
				File f = new File(binDir, jarFiles[i]);
				urls[i] = f.toURI().toURL();
				System.out.println("Loading URL: " + urls[i].toString());
			}
			System.out.println("Loading natives");
			String nativesDir = new File(binDir, "natives").toString();
			
			System.setProperty("org.lwjgl.librarypath", nativesDir);
			System.setProperty("net.java.games.input.librarypath", nativesDir);
			
			//System.setProperty("user.home", new File(cwd).getParent());
			URLClassLoader cl = new URLClassLoader(urls);
			
			System.setProperty("minecraft.applet.TargetDirectory", cwd);
			
			if (System.getProperty("user.dir") != null) {
				System.out.println("here");
				URLClassLoader gameupdater = new URLClassLoader(new URL[] { new File(cwd, "minecraft.jar").toURI().toURL()});
				Class game = gameupdater.loadClass("net.minecraft.GameUpdater");
				Constructor constructor = game.getConstructor(String.class, String.class, Boolean.TYPE);
				Runnable g = (Runnable) constructor.newInstance(Utils.getMCVersion(new File(new File(cwd, "bin"), "minecraft.jar")), "minecraft.jar", false);
				
				String path = cwd + "\\bin\\";
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
				f.set(null, cl);
				System.out.println("here");
				
				Method m = game.getMethod("createApplet");
				Applet a = (Applet) m.invoke(g);
				System.out.println("a is null " + (a == null));
				MS2Frame frame = new MS2Frame();
				frame.setVisible(true);
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("username", this.credentials[0]);
				parameters.put("sessionid", this.credentials[1]);
				parameters.put("demo", "false");
				parameters.put("server", "false");
				parameters.put("stand-alone", "true");
				MS2Container container = new MS2Container(parameters, a);
				frame.start(container);
				return;
			}
			
			if (this.compatMode) {
				Class mc = cl.loadClass("net.minecraft.client.Minecraft");
				System.out.println("Launching in compatibility mode");
				mc.getMethod("main", String[].class).invoke(null, (Object) this.credentials);
			} else {
				try {
					System.out.println("Launching with applet wrapper");
					Class AppletWrapperClass = cl.loadClass("net.minecraft.client.MinecraftApplet");
					Applet app = (Applet) AppletWrapperClass.newInstance();
					MS2Frame frame = new MS2Frame();
					frame.setVisible(true);
					Map<String, String> parameters = new HashMap<String, String>();
					parameters.put("username", this.credentials[0]);
					parameters.put("sessionid", this.credentials[1]);
					parameters.put("demo", "false");
					parameters.put("server", "false");
					parameters.put("stand-alone", "true");
					MS2Container container = new MS2Container(parameters, app);
					frame.start(container);
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(-1);
					System.out.println("Launching in compatibility mode");
					Class mc = cl.loadClass("net.minecraft.client.Minecraft");
					mc.getMethod("main", String[].class).invoke(null, (Object) this.credentials);
				}
			}
			
			//cl.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
