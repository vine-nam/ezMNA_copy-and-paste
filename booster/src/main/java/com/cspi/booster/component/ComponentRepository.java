package com.cspi.booster.component;

import java.util.Map;

public interface ComponentRepository {

	void save(Component component);

	Map<String, Component> getComponentList();
	
	void clear();
}
