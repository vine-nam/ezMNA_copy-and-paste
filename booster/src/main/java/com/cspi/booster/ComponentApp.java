package com.cspi.booster;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.cspi.booster.application.Application;
import com.cspi.booster.application.ApplicationService;
import com.cspi.booster.component.Component;
import com.cspi.booster.component.ComponentService;
import com.cspi.booster.component.ComponentServiceImpl;
import com.cspi.booster.component.LocalComponentRespository;
import com.cspi.booster.file.FileService;

public class ComponentApp {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("Enter File Path: ");
		String pathName = "module.bwm"; //myObj.nextLine(); 
		
		Document document = (Document) new FileService(pathName).read();
		ComponentService componentService = new ComponentServiceImpl(new LocalComponentRespository());

		componentService.createComponent(document);

		Map<String, Component> componentList = componentService.getComponentList();

		for (String key : componentList.keySet()) {
			Component component = componentList.get(key);
			System.out.println(component.getComponentName() + " : " + component.getProcessName() + " : "
					+ component.getIsAutoStart());
		}

		// -Dbw.application.disable.autostart.AP_FT01.application.1.0.ComponentRDD_MISMST_000=true
		// -Dbw.application.disable.autostart.애플리케이션이름.버전.컴포넌트이름=true
		ApplicationService applicationService = new ApplicationService();
		applicationService.createApplication(document);
		Application application = applicationService.getApplication();
		String applicationName = application.getName();
		String applicationVersion = application.getVersion();
		boolean viewAll = true;
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : componentList.keySet()) {
			Component component = componentList.get(key);
			
			if(!viewAll && component.getIsAutoStart()) { 
				continue;
			}
			stringBuilder.append(String.format("-Dbw.application.disable.autostart.%s.application.%s.%s=%s\n",
					applicationName, applicationVersion, component.getComponentName(), !component.getIsAutoStart()));
		}
		System.out.println(stringBuilder.toString());
		//new FileService("test.txt").write(stringBuilder.toString());
		
		//update
		System.out.println("Enter Component Name: ");
		String componentName = "ComponentInitializeBaseInfo"; //myObj.nextLine();
		System.out.println("Enter true or false for autoStart: ");
		String autoStart = "false"; //myObj.nextLine(); 
		Component component = new Component(componentName, Boolean.valueOf(autoStart));
		componentService.updateComponent(component);

		Map<String, Component> componentList2 = componentService.getComponentList();
		StringBuilder stringBuilder2 = new StringBuilder();
		for (String key : componentList2.keySet()) {
			Component component2 = componentList2.get(key);
			
			if(!viewAll && component2.getIsAutoStart()) { 
				continue;
			}
			stringBuilder2.append(String.format("-Dbw.application.disable.autostart.%s.application.%s.%s=%s\n",
					applicationName, applicationVersion, component2.getComponentName(), !component2.getIsAutoStart()));
		}
		System.out.println(stringBuilder2.toString());
	}
}
