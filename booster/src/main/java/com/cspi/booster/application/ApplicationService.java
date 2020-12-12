package com.cspi.booster.application;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ApplicationService {
	
	ApplicationRepository applicationRepository = new ApplicationRepository();

	public void createApplication(Document document) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/composite";
		Node result = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
		String appName = result.getAttributes().getNamedItem("name").getNodeValue();
		Application application = new Application(appName);
		applicationRepository.save(application);
	}

	public Application getApplication() {
		return applicationRepository.application;
	}

}
