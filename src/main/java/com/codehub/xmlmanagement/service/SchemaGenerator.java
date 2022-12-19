package com.codehub.xmlmanagement.service;

import com.codehub.xmlmanagement.Model.Statistics;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


public class SchemaGenerator {

    public static void Run() {
     
        try {
            JAXBContext context = JAXBContext.newInstance(Statistics.class);
            context.generateSchema(new SchemaOutputResolver() {
                @Override
                public Result createOutput(String namespaceUri, String suggestedFileName) {
                    try {
                        File file = new File("files/statistics.xsd");
                        StreamResult result = new StreamResult(file);
                        result.setSystemId(file.toURI().toURL().toString());
                        return result;
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(SchemaGenerator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;
                }
            });
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(SchemaGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

