package com.cspi.booster.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.cspi.booster.file.FileService;

public class ApplicationTest {

	@Test
	void createComponent() throws ParserConfigurationException, SAXException, IOException {
		String pathName = "E:\\Workspace\\bwce\\CN_HA01-test\\CN_HA01\\META-INF\\module.bwm";
		Document document = (Document) new FileService(pathName).read();
		ApplicationService applicationService = new ApplicationService(document);
		
		String applicationName = applicationService.getApplicationName();
		System.out.println(applicationName);
		assertNotNull(applicationName);
	}
}
