package org.ticketmanager.ticketmanagerapplication.ui;


import org.ticketmanager.ticketmanagerapplication.config.Configuration;
import org.ticketmanager.ticketmanagerapplication.logging.Logger;

import java.io.IOException;
import java.util.Scanner;

public class CommandLineInterface {
    public static Configuration configureSystem() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Logger.log("Starting system configuration...");

        int totalTickets = getInput(scanner, "Enter Total Tickets: ");
        int ticketReleaseRate = getInput(scanner, "Enter Ticket Release Rate: ");
        int customerRetrievalRate = getInput(scanner, "Enter Customer Retrieval Rate: ");
        int maxTicketCapacity = getInput(scanner, "Enter Max Ticket Capacity: ");
        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        try {
            config.saveToFile("resources/config.json");
            System.out.println("Configuration saved to resources/config.json");
        } catch (IOException e) {
            System.err.println("Failed to save configuration: " + e.getMessage());
        }

        Logger.log("System configured successfully.");
        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }

    private static int getInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}