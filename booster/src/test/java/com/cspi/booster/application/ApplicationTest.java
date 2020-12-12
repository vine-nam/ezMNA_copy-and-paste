package com.cspi.booster.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.cspi.booster.file.FileService;

public class ApplicationTest {

	@Test
	void createComponent() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		String pathName = "module.bwm";
		Document document = (Document) new FileService(pathName).read();
		ApplicationService applicationService = new ApplicationService();
		applicationService.createApplication(document);
		String applicationName = applicationService.getApplication().getName();
		System.out.println(applicationName);
		assertNotNull(applicationName);
	}
}
