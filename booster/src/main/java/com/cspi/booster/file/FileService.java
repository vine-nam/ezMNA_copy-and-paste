package com.cspi.booster.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FileService {
	
	private File file;

	public FileService(String pathName) {
		this.file = new File(pathName);
	}
	public FileService(File file) {
		this.file = file;
	}
	
	public Object read() throws ParserConfigurationException, SAXException, IOException {
		String mimeType = new MimetypesFileTypeMap().getContentType(file);
		
		if(mimeType.equals("application/octet-stream")) 
			return readXml(); 
		return readText();
	}
	
	public Document readXml() throws ParserConfigurationException, IOException, SAXException  {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document;
		try {
			document = documentBuilder.parse(file);
			document.getDocumentElement().normalize();
			return document;
		} catch (IOException e) {
			throw new IOException(file.getPath() + " 파일을 찾을 수 없습니다.");
		}
	}
	
	public List<String> readText() throws IOException {
		List<String> list = new ArrayList<String>();
		//입력 스트림 생성
        FileReader filereader;
		try {
			filereader = new FileReader(file);
		} catch (IOException e) {
			throw new IOException(file.getPath() + " 파일을 찾을 수 없습니다.");
		}
        //입력 버퍼 생성
        BufferedReader bufReader = new BufferedReader(filereader);
        //.readLine()은 끝에 개행문자를 읽지 않는다.   
        String line;
		while((line = bufReader.readLine()) != null) {
			list.add(line);
		}     
        bufReader.close();
		return list;
	}
	
	public boolean write(Document document) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(file);
			transformer.transform(domSource, streamResult);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean write(String data) {
		try {
			FileWriter output = new FileWriter(file);
			output.write(data);
			output.close();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	
}
