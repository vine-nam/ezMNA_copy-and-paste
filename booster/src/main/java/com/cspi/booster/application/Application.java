package com.cspi.booster.application;

public class Application {

	private String name;
	private String version;
	
	public Application(String name, String version) {
		this.name = name;
		this.version = version;
	}
	
	public Application() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
}
