package com.creatifcubed.simpleapi;

public interface ISimpleSettings {
	public void load(String path);
	public void load();
	public void save();
	public void save(String path);
	public void setPath(String path);
	public String getPath();
	
	public String getString(String key);
	public String getString(String key, String def);
	
	public void put(String key, Object o);
	
	public Integer getInt(String key);
	public Integer getInt(String key, Integer def);
	
	public Double getDouble(String key);
	public Double getDouble(String key, Double def);
}
