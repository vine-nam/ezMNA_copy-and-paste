package com.cspi.booster.file;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class fileTest {

	@Test
	void readTestXml() throws ParserConfigurationException, SAXException, IOException {
		// when
		String pathName = "module.bwm";

		// given
		Document document = (Document) new FileService(pathName).read();

		// then
		assertNotNull(document);
	}

	@Test
	@Disabled
	void readTestString() throws IOException {
		String pathName = "test.txt";
		List<String> list = new FileService(pathName).readText();
		for (String data : list) {
			String[] str = data.split("\\.");
			System.out.println(str[4]);
		}
	}

	@Test
	@Disabled
	void writeTestXml() throws ParserConfigurationException {
		// given
		String pathName = "test.xml";
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		// then
		assertTrue(new FileService(pathName).write(document));
	}

	@Test
	@Disabled
	void wrtieTestSimple() {
		// when

		// given
		String pathName = "test.txt";
		String data = "hello?";

		// then
		assertTrue(new FileService(pathName).write(data));
	}

	@Test
	void fileTypeCheck() {
		String pathName = "module.bwm";
		String mimeType = new MimetypesFileTypeMap().getContentType(new File(pathName));
		System.out.println(mimeType);

		pathName = "test.txt";
		mimeType = new MimetypesFileTypeMap().getContentType(new File(pathName));
		System.out.println(mimeType);
	}
}
