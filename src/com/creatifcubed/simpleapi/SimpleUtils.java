package com.creatifcubed.simpleapi;

public  class SimpleUtils {
	private SimpleUtils() {
		
	}
	
	public static long getRam() {
		return ((com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
	}
}
