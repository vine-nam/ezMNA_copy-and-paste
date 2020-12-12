package com.cspi.booster.controller;

import java.util.Map;

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
	private final ApplicationService applicationService;
	
	@Autowired
	public ComponentController(ComponentService componentService, ApplicationService applicationService) {
		this.componentService = componentService;
		this.applicationService = applicationService;
	}
	
	@PostMapping("/component/file")
	public String create(@RequestParam("files") MultipartFile file, RedirectAttributes redirectAttributes) {
		componentService.clear();
		try {
			Document document = (Document) new FileService().readXml(file);
			componentService.createComponent(document);
			applicationService.createApplication(document);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/";
		}
		
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
		Application appplicationInfo = applicationService.getApplication();
		model.addAttribute("components", componentList);
		model.addAttribute("appplicationInfo", appplicationInfo);
		return "component/componentList";
	}
}
