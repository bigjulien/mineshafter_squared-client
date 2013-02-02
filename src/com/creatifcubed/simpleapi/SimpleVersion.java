package com.creatifcubed.simpleapi;

public class SimpleVersion {
	private int major;
	private int minor;
	private int fix;
	
	public SimpleVersion(String verString) {
		String[] verArray = verString.split("[.]");

		this.major = Integer.parseInt(verArray[0]);
		this.minor = Integer.parseInt(verArray[1]);
		this.fix = Integer.parseInt(verArray[2]);
	}
	
	public SimpleVersion(int major, int minor, int fix) {
		this.major = major;
		this.minor = minor;
		this.fix = fix;
	}
	
	public boolean shouldUpdateTo(SimpleVersion newerVersion) {
		if (newerVersion.major > this.major) {
			return true;
		}
		if (newerVersion.minor > this.minor) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return major + "." + minor + "." + fix;
	}
}