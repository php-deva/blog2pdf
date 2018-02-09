package main.config;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.File;

public class Config {
    private static Config INSTANCE = null;
    private static String CONFIGTYPE = null;
    private static Element CORECONFIG = null;
    private static final String FILENAME = "src/main/resources/config/config.xml";

    public static Config getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    public static Config init(String configType) {
        CONFIGTYPE = configType;
        getConfig();
        return INSTANCE;
    }

    public static void getConfig() {
        try {
            final File xmlFile = new File(FILENAME);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            Node coreNode = doc.getElementsByTagName(CONFIGTYPE).item(0);
            if (coreNode.getNodeType() == Node.ELEMENT_NODE) {
                CORECONFIG = (Element) coreNode;
            }
        } catch (ParserConfigurationException | SAXException
                | IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getProperty(String propertyName) {
        String property = null;
        try {
            property = CORECONFIG.getElementsByTagName(propertyName).item(0).getTextContent();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return property;
    }
}