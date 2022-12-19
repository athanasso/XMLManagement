package com.codehub.xmlmanagement.usecase;

import com.codehub.xmlmanagement.service.RawTextToXml;
import com.codehub.xmlmanagement.service.SchemaGenerator;
import com.codehub.xmlmanagement.service.StatisticsToXML;
import com.codehub.xmlmanagement.service.ValidateXML;
import com.codehub.xmlmanagement.service.XMLStatistics;
import com.codehub.xmlmanagement.service.XmlSpecificParagraphs;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UseCase {

    private static final Scanner scanner = new Scanner(System.in);

    public static void StartInterface() {
        // We check how the user want to continue. Handling the user's input if he doesn't give the correct input.

        int userChoice = Integer.MIN_VALUE;
        while (userChoice != 0) {
            System.out.println("\nChoose how you want to continue.");
            System.out.println("\tPress 1: Parse the text file into an XML");
            System.out.println("\tPress 2: Create and Print Statistics for the book");
            System.out.println("\tPress 3: Create an XML of the Statistics");
            System.out.println("\tPress 4: Create XML with selected paragraphs from an existing XML");
            System.out.println("\tPress 5: Generate Xsd");
            System.out.println("\tPress 6: Validate the XML");
            System.out.println("\tPress 0: To exit.");
            try {
                userChoice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                String badInput = scanner.next();
                System.out.println("Bad input: '" + badInput + "' Please try again.\n");
            }

            // Based on the user choose we report back or exit the program.
            switch (userChoice) {
                case 1 -> {
                    System.out.println("Reading Raw text and converting it into XML please wait!");
                    RawTextToXml.Run();
                }
                case 2 -> {
                    XMLStatistics.Create();
                    XMLStatistics.PrintStatistics();
                }
                case 3 -> StatisticsToXML.Run();
                case 4 -> {
                    System.out.println("Input paragraph number to start");
                    int startChoice = scanner.nextInt();
                    System.out.println("Input paragraph number to end");
                    int endChoice = scanner.nextInt();
                    
                    XmlSpecificParagraphs.Run(startChoice, endChoice);
                }
                case 5 -> SchemaGenerator.Run();
                case 6 -> ValidateXML.Run();
                case 0 -> System.out.println("Exiting...");
            }
        }
    }
}
