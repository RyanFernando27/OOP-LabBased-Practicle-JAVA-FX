package org.ticketmanager.ticketmanagerapplication;


import org.ticketmanager.ticketmanagerapplication.config.Configuration;
import org.ticketmanager.ticketmanagerapplication.core.TicketPool;
import org.ticketmanager.ticketmanagerapplication.logging.Logger;
import org.ticketmanager.ticketmanagerapplication.threads.Customer;
import org.ticketmanager.ticketmanagerapplication.threads.Vendor;
import org.ticketmanager.ticketmanagerapplication.ui.CommandLineInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Configuration config = CommandLineInterface.configureSystem();
        TicketPool ticketPool = new TicketPool();

        Thread vendor = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
        Thread customer = new Thread(new Customer(ticketPool));

        vendor.start();
        customer.start();

        try {
            vendor.join();
            customer.join();
        } catch (InterruptedException e) {
            Logger.log("Main thread interrupted.");
        }

        Logger.log("System terminated.");
    }
}