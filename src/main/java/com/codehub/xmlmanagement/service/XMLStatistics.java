package com.codehub.xmlmanagement.service;

import com.codehub.xmlmanagement.Model.Statistics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLStatistics {

    private static final String xmlFileInput = "files/book.xml";
    private static Statistics statistics = new Statistics();

    public static Statistics getStatistics() {
        return statistics;
    }

    public static void Create() {
        parseXMLWithSAX();
    }

    public static void PrintStatistics() {
        System.out.println(statistics);
    }

    private static void parseXMLWithSAX() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                // Override the startElement and endElement methods to handle the start and end of an element
                // in the XML file
                int wordCount = 0;
                int sentenceCount = 0;
                int paragraphCount = 0;
                HashSet<String> set = new HashSet<>();
                String author = "";
                String appClassName = "";
                Date creationDate = null;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                StringBuilder currentSentence = new StringBuilder();

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equalsIgnoreCase("sentence")) {
                        sentenceCount++;
                    } else if (qName.equalsIgnoreCase("paragraph")) {
                        paragraphCount++;
                    } else if (qName.equalsIgnoreCase("date")) {
                        try {
                            creationDate = dateFormat.parse(attributes.getValue("creationDate"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else if (qName.equalsIgnoreCase("author")) {
                        author = attributes.getValue("name");
                    } else if (qName.equalsIgnoreCase("app")) {
                        appClassName = attributes.getValue("class");
                    }
                }

                @Override
                public void characters(char ch[], int start, int length) {
                    currentSentence.append(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (qName.equalsIgnoreCase("sentence")) {
                        String[] words = currentSentence.toString().split(" ");
                        wordCount += words.length;
                        set.addAll(Arrays.asList(words));
                        currentSentence.setLength(0);
                    }
                }

                @Override
                public void endDocument() {
                    statistics.setParagraphs(paragraphCount);
                    statistics.setSentences(sentenceCount);
                    statistics.setWords(wordCount);
                    statistics.setDistinctWords(set.size());
                    statistics.setCreationDate(creationDate);
                    statistics.setAuthor(author);
                    statistics.setAppClassName(appClassName);
                }
            };

            saxParser.parse(xmlFileInput, handler);
        
    }   catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(XMLStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
