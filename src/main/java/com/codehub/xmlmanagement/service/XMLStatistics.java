package com.codehub.xmlmanagement.service;

import com.codehub.xmlmanagement.Model.Statistics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLStatistics {

    private static final String xmlFileInput = "files/book.xml";
    private static Statistics statistics = new Statistics();

    public static Statistics getStatistics() {
        return statistics;
    }

    public static void Create() {
        Document doc = XMLParser();
        statistics.setParagraphs(countParagraphs(doc));
        statistics.setSentences(countSentences(doc));
        statistics.setWords(countWords(doc));
        statistics.setDistinctWords(countDistinctWords(doc));
        statistics.setCreationDate(getCreationDate(doc));
        statistics.setAuthor(getAuthor(doc));
        statistics.setAppClassName(getappClassName(doc));
    }

    public static void PrintStatistics() {
        System.out.println(statistics);
    }

    private static Document XMLParser() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new FileInputStream(xmlFileInput));
            return doc;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLStatistics.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SAXException | ParserConfigurationException | IOException ex) {
            Logger.getLogger(XMLStatistics.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static int countWords(Document doc) {
        int wordCount = 0;

        // Iterate through the DOM tree and get the text content of each element
        NodeList elements = doc.getElementsByTagName("sentence");
        for (int i = 0; i < elements.getLength(); i++) {
            String textContent = elements.item(i).getTextContent();

            // Split the text content into words and count them
            String[] words = textContent.split(" ");
            wordCount += words.length;
        }
        return wordCount;
    }

    private static int countDistinctWords(Document doc) {
        HashSet<String> set = new HashSet<>();

        // Iterate through the DOM tree and get the text content of each element
        NodeList elements = doc.getElementsByTagName("sentence");
        for (int i = 0; i < elements.getLength(); i++) {
            String textContent = elements.item(i).getTextContent();

            // Split the text content into words and add them to the set
            String[] words = textContent.split(" ");
            set.addAll(Arrays.asList(words));
        }
        return set.size();
    }

    private static int countParagraphs(Document doc) {
        // Get a list of all the elements with the tag name "paragraph"
        NodeList paragraphElements = doc.getElementsByTagName("paragraph");

        // Count the number of elements in the list
        return paragraphElements.getLength();
    }

    private static int countSentences(Document doc) {
        // Get a list of all the elements with the tag name "sentence"
        NodeList sentenceElements = doc.getElementsByTagName("sentence");

        // Count the number of elements in the list
        return sentenceElements.getLength();
    }

    private static Date getCreationDate(Document doc) {
        // Define the date format that the input string follows
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Get a list of all the elements with the tag name "date"
        NodeList dateElement = doc.getElementsByTagName("date");

        // Get the text content of the first element in the list
        //String stringDate = dateElement.item(0).getTextContent();
        String stringDate = "20-05-1999";

        try {
            Date date = dateFormat.parse(stringDate);
            return date;

        } catch (ParseException e) {
            return null;
        }
    }

    private static String getAuthor(Document doc) {
        // Get a list of all the elements with the tag name "author"
        //NodeList authorElement = doc.getElementsByTagName("author");

        // Get the text content of the first element in the list
        //String author = authorElement.item(0).getTextContent();
        String author = "test";

        return !author.isEmpty() ? author : " ";
    }

    private static String getappClassName(Document doc) {
        // Get a list of all the elements with the tag name "appClass"
        //NodeList appClassElement = doc.getElementsByTagName("appClass");

        // Get the text content of the first element in the list
        //String appClass = appClassElement.item(0).getTextContent();
        String appClass = "test";

        return !appClass.isEmpty() ? appClass : " ";
    }
}