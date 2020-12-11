package com.cspi.booster.component;

public class Component {
	private String componentName;
	private String processName;
	private Boolean isAutoStart;

	public Component(String componentName, String processName, Boolean isAutoStart) {
		this.componentName = componentName;
		this.processName = processName;
		this.isAutoStart = isAutoStart;
	}
	
	public Component(String componentName, Boolean isAutoStart) {
		this.componentName = componentName;
		this.isAutoStart = isAutoStart;
	}
	
	public Component() {}

	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Boolean getIsAutoStart() {
		return isAutoStart;
	}
	public void setIsAutoStart(Boolean isAutoStart) {
		this.isAutoStart = isAutoStart;
	}
	
}
