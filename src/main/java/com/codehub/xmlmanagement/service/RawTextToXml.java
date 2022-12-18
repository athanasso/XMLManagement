package com.codehub.xmlmanagement.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class RawTextToXml {

    private static final String xmlFileOutput = "files/book.xml";
    private static final String textInputFile = "files/raw.txt";

    public static void Run() {
        FileReader();
    }

    private static void FileReader() {
        // Read the raw text file
        List<String> lines = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(textInputFile))) {
            // This method reads all the lines of the file with a Stream
            lines = reader.lines().collect(Collectors.toList());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RawTextToXml.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RawTextToXml.class.getName()).log(Level.SEVERE, null, ex);
        }

        TextToXml(lines);
    }

    private static void TextToXml(List<String> lines) {
        try {
            // Create an XML output factory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

            // Create an XML stream writer
            try ( FileOutputStream input = new FileOutputStream(xmlFileOutput)) {
                XMLStreamWriter writer = outputFactory.createXMLStreamWriter(input, "UTF-8");

                // Write the XML document
                writer.writeStartDocument("utf-8", "1.0");
                writer.writeCharacters("\n");
                writer.writeStartElement("book");
                writer.writeCharacters("\n");

                int chapterCount = 1;
                int paragraphCount = 0;
                int sentenceCount = 0;

                while (!lines.isEmpty()) {
                    // Start a new chapter element
                    writer.writeStartElement("chapter");
                    writer.writeAttribute("number", String.valueOf(chapterCount));
                    writer.writeCharacters("\n");

                    while (paragraphCount < 5 && !lines.isEmpty()) {
                        if (sentenceCount == 0) {
                            // Start a new paragraph element
                            writer.writeStartElement("paragraph");
                            writer.writeCharacters("\n");
                        }

                        // Write a sentence element only if the line is not empty
                        if (!lines.get(0).trim().isEmpty()) {
                            writer.writeCharacters("    ");
                            writer.writeStartElement("sentence");
                            writer.writeCharacters(lines.remove(0));
                            EndElement(writer);
                        } else {
                            lines.remove(0);
                        }

                        sentenceCount++;

                        if (sentenceCount == 8 || lines.isEmpty()) {
                            // End the current paragraph element
                            EndElement(writer);

                            // Reset sentence count for the next paragraph
                            sentenceCount = 0;
                            paragraphCount++;
                        }
                    }

                    // End the current chapter element
                    EndElement(writer);

                    paragraphCount = 0;
                    chapterCount++;
                }

                EndElement(writer);
                writer.writeEndDocument();
                writer.flush();

            } catch (XMLStreamException | IOException ex) {
                Logger.getLogger(RawTextToXml.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FactoryConfigurationError ex) {
            Logger.getLogger(RawTextToXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void EndElement(XMLStreamWriter writer) {
        try {
            // End the book element
            writer.writeEndElement();
            writer.writeCharacters("\n");
        } catch (XMLStreamException ex) {
            Logger.getLogger(RawTextToXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
