package org.ticketmanager.ticketmanagerapplication;


import org.ticketmanager.ticketmanagerapplication.config.Configuration;
import org.ticketmanager.ticketmanagerapplication.core.IDRetrieval;
import org.ticketmanager.ticketmanagerapplication.core.PriorityRetrieval;
import org.ticketmanager.ticketmanagerapplication.core.TicketPool;
import org.ticketmanager.ticketmanagerapplication.core.TicketRetrievalStrategy;
import org.ticketmanager.ticketmanagerapplication.logging.Logger;
import org.ticketmanager.ticketmanagerapplication.threads.Customer;
import org.ticketmanager.ticketmanagerapplication.threads.Vendor;
import org.ticketmanager.ticketmanagerapplication.ui.CommandLineInterface;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> tickets = Collections.synchronizedList(new LinkedList<>());
        TicketPool ticketPool = new TicketPool();
        Configuration config = CommandLineInterface.configureSystem();
        Thread vendor = new Thread(new Vendor(ticketPool,
                config.getTicketReleaseRate()));


        // Add initial tickets for testing
        for (int i = 1; i <= 10; i++) {
            tickets.add(i % 2 == 0 ? "VIP-" + i : "TICKET-" + i);
        }

        // Create strategies
        TicketRetrievalStrategy priorityStrategy = new PriorityRetrieval(tickets);
        TicketRetrievalStrategy idStrategy = new IDRetrieval(tickets, "TICKET-3");


        // Create customers with different strategies
        Thread customer1 = new Thread(new Customer(priorityStrategy));
        Thread customer2 = new Thread(new Customer(idStrategy));

        customer1.start();
        customer2.start();
        vendor.start();


        try {
            customer1.join();
            customer2.join();
            vendor.join();
        } catch (InterruptedException e) {
            Logger.log("Main thread interrupted.");
        }

        Logger.log("System terminated.");
    }
}