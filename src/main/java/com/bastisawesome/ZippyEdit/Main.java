package com.bastisawesome.ZippyEdit;

// import com.bastisawesome.ZippyEdit.Config.PCFConversion.PcfConfig;
// import com.bastisawesome.ZippyEdit.Config.PCFConversion.PcfHandler;

import java.io.File;
import java.io.IOException;

import javax.swing.text.html.parser.Parser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * TODO: Update all toString methods to
 * use a StringBuilder instead of concatenating
 * strings.
/*

/**
 * @author bast
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SAXException,
		IOException, ParserConfigurationException {
		/*SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			PcfHandler handler = new PcfHandler();
			File cnfFile = new File("template.xml");
			saxParser.parse(cnfFile, handler);
			
			PcfConfig pcfCnf = handler.getPcfCnf();
			
//			System.out.println(pcfCnf.getModExtData());
//			System.out.println(pcfCnf.getBitmaps());
//			System.out.println(pcfCnf.getActions());
//			System.out.println(pcfCnf.getActionLists());
			System.out.println(pcfCnf.getStrings());
		} catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}*/
		File cnfFile = new File("ConfigEditFixed.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(cnfFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("BITMAP");
		
		for(int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String uid = elem.getAttribute("id");
				
				Node node1 = elem.getElementsByTagName("FileName").item(0);
				String fname = node1.getTextContent();
				
				System.out.printf("Bitmap ID: %s%n", uid);
				System.out.printf("Filename: %s%n", fname);
			}
		}
	}
}