package com.codehub.xmlmanagement.usecase;

import com.codehub.xmlmanagement.service.XMLStatistics;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UseCase {

    private static final Scanner scanner = new Scanner(System.in);

    public static void Start() {
        // We check how the user want to continue. Handling the user's input if he doesn't give the correct input.

        int userChoice = Integer.MIN_VALUE;
        while (userChoice != 0) {
            System.out.println("\nChoose how you want to continue.");
            System.out.println("\tPress 1: ");
            System.out.println("\tPress 2: ");
            System.out.println("\tPress 3: Create Statistics for the XML");
            System.out.println("\tPress 4: Print Statistics");
            System.out.println("\tPress 0: To exit.");
            try {
                userChoice = scanner.nextInt();
            } catch (InputMismatchException ex) {
                String badInput = scanner.next();
                System.out.println("Bad input: '" + badInput + "' Please try again.\n");
            }

            // Based on the user choose we report back or exit the program.
            switch (userChoice) {

                case 1 ->
                    System.out.println("test");

                case 2 ->
                    System.out.println("test");

                case 3 ->
                    XMLStatistics.Create();

                case 4 ->
                    XMLStatistics.PrintStatistics();

                case 0 ->
                    System.out.println("Exiting...");

            }
        }
    }
}
