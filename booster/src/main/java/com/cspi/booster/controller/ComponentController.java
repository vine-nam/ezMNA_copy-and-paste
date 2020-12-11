package com.cspi.booster.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;

import com.cspi.booster.application.Application;
import com.cspi.booster.application.ApplicationService;
import com.cspi.booster.component.Component;
import com.cspi.booster.component.ComponentService;
import com.cspi.booster.file.FileService;

@Controller
public class ComponentController {

	private final ComponentService componentService;
	private ApplicationService applicationService;
	
	@Autowired
	public ComponentController(ComponentService componentService) {
		this.componentService = componentService;
	}
	
	@PostMapping("/component/file")
	public String create(@RequestParam("files") MultipartFile file, RedirectAttributes redirectAttributes) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Document document = null;
		componentService.clear();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(file.getInputStream());
			//document = (Document) new FileService(file).read();
			componentService.createComponent(document);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/";
		}

		Map<String, Component> componentList = componentService.getComponentList();

		for (String key : componentList.keySet()) {
			Component component = componentList.get(key);
			System.out.println(component.getComponentName() + " : " + component.getProcessName() + " : "
					+ component.getIsAutoStart());
		}
		
		applicationService = new ApplicationService(document);
		
		return "redirect:/componentList";
	}
	
	@PostMapping("/component/update")
	public String update(Component component) {
		componentService.updateComponent(component);

		System.out.println(component.getComponentName() + " : " + component.getProcessName() + " : "
				+ component.getIsAutoStart());
		
		return "redirect:/componentList";
	}

	@GetMapping("/componentList")
	public String list(Model model) {
		Map<String, Component> componentList = componentService.getComponentList();
		Application appplicationInfo = new Application(applicationService.getApplicationName(), "1.0");
		model.addAttribute("components", componentList);
		model.addAttribute("appplicationInfo", appplicationInfo);
		return "component/componentList";
	}
}
