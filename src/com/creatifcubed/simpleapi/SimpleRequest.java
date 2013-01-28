package com.creatifcubed.simpleapi;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import com.mineshaftersquared.resources.Streams;

public class SimpleRequest {
	
	private HttpURLConnection connection;
	
	public SimpleRequest(URL url) {
		this.connection = null;
		try {
			this.connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public byte[] doGet() {
		//this.connection.setreque
		if (this.connection == null) {
			return null;
		}
		try {
			this.connection.setRequestMethod("GET");
			BufferedInputStream in = new BufferedInputStream(this.connection.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Streams.pipeStreams(in, out);
			
			return out.toByteArray();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public byte[] doPost(byte[] data) {
		if (this.connection == null) {
			return null;
		}
		
		try {
			this.connection.setRequestMethod("POST");
			this.connection.setDoOutput(true);
			this.connection.setRequestProperty("Content-length", Integer.toString(data.length));
			this.connection.getOutputStream().write(data);
			BufferedInputStream in = new BufferedInputStream(this.connection.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Streams.pipeStreams(in, out);
			return out.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public boolean canRequest() {
		return this.connection != null;
	}
}
