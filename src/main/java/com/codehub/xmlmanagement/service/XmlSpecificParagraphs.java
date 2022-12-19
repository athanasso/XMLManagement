package com.codehub.xmlmanagement.service;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlSpecificParagraphs {

    private static final String xmlFileInput = "files/book.xml";
    private static final String xmlFileOutput = "files/book2.0.xml";

    public static void Run(int start, int end) {
        extractParagraphs(start, end);
    }

    private static void extractParagraphs(int start, int end) {
        try {
            // Parse the XML file
            File xmlFile = new File(xmlFileInput);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Extract the paragraph elements
            NodeList paragraphs = doc.getElementsByTagName("paragraph");
            int numParagraphs = paragraphs.getLength();

            // Select the desired number of paragraphs
            int numParagraphsToInclude = end - start + 1;
            if (numParagraphsToInclude > numParagraphs) {
                numParagraphsToInclude = numParagraphs;
            }
            Node[] selectedParagraphs = new Node[numParagraphsToInclude];
            for (int i = start; i <= end; i++) {
                selectedParagraphs[i - start] = paragraphs.item(i);
            }

            // Create the new XML file
            Document newDoc = CreateFile(dBuilder, numParagraphsToInclude, selectedParagraphs);

            SaveFile(newDoc);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(XmlSpecificParagraphs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void SaveFile(Document newDoc) {
        try {
            // Save the new XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Set the indentation properties
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File(xmlFileOutput));
            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlSpecificParagraphs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Document CreateFile(DocumentBuilder dBuilder, int numParagraphsToInclude, Node[] selectedParagraphs) {
        Document newDoc = dBuilder.newDocument();
        Element rootElement = newDoc.createElement("book");
        newDoc.appendChild(rootElement);
        Element chapterElement = newDoc.createElement("chapter");
        rootElement.appendChild(chapterElement);
        for (int i = 0; i < numParagraphsToInclude; i++) {
            Node importedNode = newDoc.importNode(selectedParagraphs[i], true);
            chapterElement.appendChild(importedNode);
        }
        return newDoc;
    }
}
