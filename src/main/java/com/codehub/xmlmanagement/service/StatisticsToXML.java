package com.codehub.xmlmanagement.service;

import com.codehub.xmlmanagement.Model.Statistics;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StatisticsToXML {
    public static void Run() {
        try {
            // Create a JAXBContext object
            JAXBContext jaxbContext = JAXBContext.newInstance(Statistics.class);
            
            // Create a Marshaller object
            Marshaller marshaller = jaxbContext.createMarshaller();
            
            // Set the Marshaller to pretty-print the XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            // Convert the object to XML and write it to a file
            marshaller.marshal(XMLStatistics.getStatistics(), new FileOutputStream("files/statistics.xml"));
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(StatisticsToXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
