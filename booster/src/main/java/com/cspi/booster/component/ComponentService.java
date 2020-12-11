package com.cspi.booster.component;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

public interface ComponentService {

	public void createComponent(Document document);
	public void createComponent(List<String> list);
	public void updateComponent(Component component);
	public Map<String, Component> getComponentList() ;
	public void clear();
}
