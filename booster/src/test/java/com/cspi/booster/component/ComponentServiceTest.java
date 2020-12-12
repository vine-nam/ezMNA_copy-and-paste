package com.cspi.booster.component;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.cspi.booster.file.FileService;

public class ComponentServiceTest {

	ComponentRepository componentRepository = new LocalComponentRespository();
	
	@Test
	@Disabled
	void createComponent() throws ParserConfigurationException, SAXException, IOException {
		String pathName = "module.bwm";
		Document document = (Document) new FileService(pathName).read();
		ComponentService componentService = new ComponentServiceImpl(componentRepository);
		
		componentService.createComponent(document);
		assertTrue(componentService.getComponentList().size()>0);
	}
	
	@Test
	@Disabled
	void createComponentText() throws ParserConfigurationException, SAXException, IOException {
		String pathName = "test.txt";
		List list = (List) new FileService(pathName).read();
		ComponentService componentService = new ComponentServiceImpl(componentRepository);
		
		componentService.createComponent(list);
		assertTrue(componentService.getComponentList().size()>0);
	}
	
	@Test
	void createComponentBoth() throws ParserConfigurationException, SAXException, IOException {
		String pathName = "module.bwm";
		Document document = (Document) new FileService(pathName).read();
		ComponentService componentService = new ComponentServiceImpl(componentRepository);
		
		componentService.createComponent(document);
		assertTrue(componentService.getComponentList().size()>0);
		int size = componentService.getComponentList().size();
		
		pathName = "test.txt";
		List list = (List) new FileService(pathName).read();
		
		componentService.createComponent(list);
		assertTrue(componentService.getComponentList().size()>0);
		assertTrue(componentService.getComponentList().size()==size);
		for (String key : componentService.getComponentList().keySet()) {
			Component component = componentService.getComponentList().get(key);
			System.out.println(component.getComponentName() + " : " + component.getProcessName() + " : " + component.getIsAutoStart());
		}
	}
	
}
