package business;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InitializeApp {
	
	public static final String xmlFilePath = "/home/student/workspace/Proj/WBGUIL/WebContent/WEB-INF/settings/icon.xml";
	public static final String xmlFilePath1 = "/home/student/workspace/Proj/WBGUIL/WebContent/WEB-INF/settings/extension.xml";
	
	//Language map
	public static Map<String,String> getLanguageList(){
		Map<String, String> langMap = new HashMap<String, String>();
		langMap.put("en", "English");
		langMap.put("hi", "Hindi");
		return langMap;
	}

	public static  ArrayList<String> getIconList() {
		ArrayList<String> al= new ArrayList<String>();

		try {
			File myfile = new File(xmlFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(myfile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("icon");

			for (int itr = 0; itr < nList.getLength(); itr++) {
				Node node = nList.item(itr);
				// System.out.println("\nNode Name :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					al.add(getTagValue("icon-name", eElement));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(al);
		return al;
	}
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nodes = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}

	public static Map<String, String> getViewers() {
		Map<String, String> hm= new HashMap<String, String>();
		try {
			File myfile = new File(xmlFilePath1);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(myfile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("extension");

			for (int itr = 0; itr < nList.getLength(); itr++) {
				Node node = nList.item(itr);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					hm.put(getTagValue("extension-format", eElement), getTagValue("extension-name", eElement));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}
}
