package com.codehub.xmlmanagement.service;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class ValidateXML {
    public static void Run() {
        ValidateBook();
        ValidateStatistics();
    }
    
    private static void ValidateBook(){
        try {
            // Parse the XML schema file
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("files/book.xsd"));

            // Create a validator based on the schema
            Validator validator = schema.newValidator();

            // Validate the XML file
            validator.validate(new StreamSource(new File("files/book.xml")));
            System.out.println("XML file is valid");
        } catch (Exception e) {
            System.out.println("XML file is invalid: " + e.getMessage());
        }
    }
    
    private static void ValidateStatistics(){
        try {
            // Parse the XML schema file
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("files/statistics.xsd"));

            // Create a validator based on the schema
            Validator validator = schema.newValidator();

            // Validate the XML file
            validator.validate(new StreamSource(new File("files/statistics.xml")));
            System.out.println("XML file is valid");
        } catch (Exception e) {
            System.out.println("XML file is invalid: " + e.getMessage());
        }
    }
}
