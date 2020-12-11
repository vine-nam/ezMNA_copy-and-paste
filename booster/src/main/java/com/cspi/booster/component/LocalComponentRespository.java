package com.cspi.booster.component;

import java.util.HashMap;
import java.util.Map;

public class LocalComponentRespository implements ComponentRepository {

	private static Map<String, Component> store = new HashMap();

	// create, update
	@Override
	public void save(Component component) {
		if (store.get(component.getComponentName()) != null) {
			store.put(component.getComponentName(), new Component(component.getComponentName(),
					store.get(component.getComponentName()).getProcessName(), component.getIsAutoStart()));
		} else {
			store.put(component.getComponentName(), component);
		}
	}

	public Component findByComponentName(String ComponentName) {
		return store.get(ComponentName);
	}

	@Override
	public Map<String, Component> getComponentList() {
		return store;
	}

	@Override
	public void clear() {
		store.clear();
	}
}
