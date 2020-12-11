package com.cspi.booster.application;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ApplicationService {

	Document document;
	
	public ApplicationService(Document document) {
		this.document = document;
	}

	public String getApplicationName() {
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/composite";
		try {
			Node result = (Node) xpath.evaluate(expression, document, XPathConstants.NODE);
			return result.getAttributes().getNamedItem("name").getNodeValue();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}

}
