package com.taf.sample.framework.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class XmlUtil {

    private Document doc;

    /**
     * Gets node value in XML file by its locator.
     *
     * @param filePath   path to XML file(including fileName and extension).
     * @param xmlLocator locator of the node.
     * @return node value.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     */
    public static String getNodeValue(String filePath, String xmlLocator) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(xmlLocator).evaluate(XmlUtil.setUpDocumentFactory(filePath), XPathConstants.NODE);

        return node.getTextContent();
    }

    /**
     * Sets node value by its locator.
     *
     * @param filePath   path to XML file(including fileName and extension).
     * @param xmlLocator locator of the node.
     * @param newValue   node value which should be set.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     */
    public static void setNodeValue(String filePath, String xmlLocator, String newValue) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {
        Document doc = XmlUtil.setUpDocumentFactory(filePath);

        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(xmlLocator).evaluate(doc, XPathConstants.NODE);

        node.setTextContent(newValue);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }

    /**
     * Prepares XML document for parsing.
     *
     * @param filePath path to XML file.
     * @return XML document.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private static Document setUpDocumentFactory(String filePath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder b = f.newDocumentBuilder();
        return b.parse(new File(filePath));
    }

}
