package main.crawler;

import main.config.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HTMLParser {

    private Config config = Config.getInstance().init("templates");
    private String postUrl = config.getProperty("postUrl");
    private String imgWrapper = config.getProperty("wrapper");
    private String imgTemplate = config.getProperty("imgTemplate");
    private volatile Boolean complete = false;

    public boolean hasPostLinks(String content) {
        return true;
    }


    public void complete() {
        complete = true;
    }

    public void getPostsUrl( ) {

    }
}
