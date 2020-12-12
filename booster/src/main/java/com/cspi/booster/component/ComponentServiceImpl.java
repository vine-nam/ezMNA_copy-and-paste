package com.cspi.booster.component;

import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ComponentServiceImpl implements ComponentService{

	ComponentRepository componentRepository;

	public ComponentServiceImpl(ComponentRepository componentRepository) {
		this.componentRepository = componentRepository;
	}
	
	public void createComponent(Document document) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		//xpath.setNamespaceContext(namespaceContext);
		String expression = "/composite/component/implementation";
		try {
			NodeList result = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);
			for (int i = 0; i < result.getLength(); i++) {
				
				String componentName = result.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue();
				String processName = result.item(i).getAttributes().getNamedItem("processName").getNodeValue();
				String autoValue;
				if(result.item(i).getAttributes().getNamedItem("autoStart") != null)  {
					autoValue = result.item(i).getAttributes().getNamedItem("autoStart").getNodeValue();
				} else {
					autoValue = "true";	
				}
				Component component = new Component(componentName, processName, Boolean.valueOf(autoValue));
				componentRepository.save(component);
				
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	public void createComponent(List<String> list) {
		for (String data : list) {
			String[] str = data.split("\\.");
			
			String componentName = str[8].split("=")[0];
			String autoValue = str[8].split("=")[1];
			Component component = new Component(componentName, Boolean.valueOf(autoValue));
			componentRepository.save(component);
		}
	}

	public void updateComponent(Component component) {
		componentRepository.save(component);
	}
	
	
	public Map<String, Component> getComponentList() {
		return componentRepository.getComponentList();
	}

	@Override
	public void clear() {
		componentRepository.clear();
	}
	
	/*
	private NamespaceContext namespaceContext =  new NamespaceContext() {
	        @Override
	        public Iterator getPrefixes(String namespaceURI) {
	                return null;
	        }
	        @Override
	        public String getPrefix(String namespaceURI) {
	                return null;
	        }
	        @Override
	        public String getNamespaceURI(String prefix) {
	                if(prefix.equals("sca")) {
	                    return "http://www.osoa.org/xmlns/sca/1.0";
	                }
	                if(prefix.equals("scaext")) {
                        return "http://xsd.tns.tibco.com/amf/models/sca/extensions";
	                }
	                return "";
	        }
	};
	*/
}
