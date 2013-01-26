package com.creatifcubed.simpleapi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class SimpleRequest {
	
	private URLConnection connection;
	
	public SimpleRequest(URL url) {
		try {
			this.connection = (HttpURLConnection) url.openConnection();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public byte[] doGet() {
		//this.connection.setreque
		
		return null;
	}
}
