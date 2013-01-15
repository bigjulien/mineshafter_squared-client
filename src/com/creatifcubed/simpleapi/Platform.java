package com.creatifcubed.simpleapi;

public enum Platform {
	WINDOWS("Windows"),
	MAC("Mac"),
	UNIX("Unix"),
	SOLARIS("Solaris"),
	UNKNOWN("Unknown");
	
	private String representation;
	
	private Platform(String representation) {
		this.representation = representation;
	}
	
	@Override
	public String toString() {
		return this.representation;
	}
	
	public static Platform getPlatform() {
		
		String os = System.getProperty("os.name").toLowerCase();
		
		if (os.indexOf("win") != -1) {
			return Platform.WINDOWS;
		} else if (os.indexOf("mac") != -1) {
			return Platform.MAC;
		} else if (os.indexOf("nix") != -1 || os.indexOf("nux") != -1 || os.indexOf("aix") != -1) {
			return Platform.UNIX;
		} else if (os.indexOf("sunos") != -1) {
			return Platform.SOLARIS;
		}
		
		return Platform.UNKNOWN;
	}
}
