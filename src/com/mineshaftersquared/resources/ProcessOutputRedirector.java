package com.mineshaftersquared.resources;

import java.io.*;

public class ProcessOutputRedirector implements Runnable {
	
	private Process process;
	private String log;
	
	public ProcessOutputRedirector(Process process, String log) {
		this.process = process;
		this.log = log;
	}
	
	@Override
	public void run() {
		/*String s;
        BufferedReader stdout = new BufferedReader (
            new InputStreamReader(p.getInputStream()));
        while ((s = stdout.readLine()) != null) {
            System.out.println(s);
        }*/
		BufferedReader stdout = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
		while (true) {
			try {
				String str = stdout.readLine();
				if (str == null) {
					System.out.println("{ProcessOutputRedirector: " + this.log + " is done}");
					break;
				}
				System.out.println(this.log + ": " + str);
			} catch (Exception ignore) {
				//
			}
		}
	}
}
