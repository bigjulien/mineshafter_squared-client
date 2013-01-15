package com.mineshaftersquared.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

import com.creatifcubed.simpleapi.Platform;

public class Utils {

	/**
     * Given a file, attempts to extract the Minecraft version from it.
     *
     * @param file to parse
     * @return the version
     */
    public static String getMCVersion(File file) {
        String prefix = "Minecraft Minecraft ";
        Pattern magic = Pattern.compile("(" + prefix + "(\\w|\\.)+(?=\01\00))");
        String version = "Unknown";
        JarFile jar = null;
        try {
            jar = new JarFile(file);
            ZipEntry entry = jar.getEntry("net/minecraft/client/Minecraft.class");
            if (entry != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(jar.getInputStream(entry)));
                String line;
                while ((line = br.readLine()) != null) {
                    Matcher matcher = magic.matcher(line);
                    if (matcher.find()) {
                        version = matcher.group().substring(prefix.length());
                        break;
                    }
                }
                br.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	try {
        		jar.close();
        	} catch (Exception ex) {
        		// do nothing
        	}
        }
        return version;
    }
    
    public static String getDefaultMCPath() {
    	String base = System.getProperty("user.home", ".") + "/";
    	String extension = null;
    	switch (Platform.getPlatform()) {
    	case WINDOWS:
    		String appdata = System.getenv("APPDATA");
    		extension = (appdata == null ? base : appdata) + "/.minecraft";
    		break;
    	case MAC:
    		extension = base + "Library/Application Support/minecraft";
    		break;
    	case UNIX:
    		extension = base + ".minecraft";
    		break;
    	default:
    		extension = base + "minecraft";
    		break;
    	}
    	return extension;
    }
}
