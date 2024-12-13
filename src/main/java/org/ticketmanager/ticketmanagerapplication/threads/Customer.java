package org.ticketmanager.ticketmanagerapplication.threads;


import org.ticketmanager.ticketmanagerapplication.core.AbstractTicketHandler;
import org.ticketmanager.ticketmanagerapplication.core.TicketPool;
import org.ticketmanager.ticketmanagerapplication.core.TicketRetrievalStrategy;
import org.ticketmanager.ticketmanagerapplication.logging.Logger;

public class Customer extends AbstractTicketHandler implements Runnable {
    private final TicketRetrievalStrategy retrievalStrategy;

    public Customer(TicketRetrievalStrategy retrievalStrategy) {
        super(null); // TicketPool will be handled by the strategy
        this.retrievalStrategy = retrievalStrategy;
    }

    @Override
    public void run() {
        while (true) {
            String ticket = retrievalStrategy.retrieveTicket();
            if (ticket != null) {
                Logger.log("Customer retrieved: " + ticket);
            } else {
                Logger.log("Customer found no tickets available.");
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Logger.log("Customer interrupted.");
            }
        }
    }

    @Override
    public void handleTickets() {
        run();
    }
}