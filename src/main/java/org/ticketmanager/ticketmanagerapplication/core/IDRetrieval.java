package org.ticketmanager.ticketmanagerapplication.core;

import java.util.List;

public class IDRetrieval implements TicketRetrievalStrategy{
//    Question 2
    private final List<String> ticketPool;
    private final String targetID;

    public IDRetrieval(List<String> ticketPool, String targetID) {
        this.ticketPool = ticketPool;
        this.targetID = targetID;
    }

    @Override
    public synchronized String retrieveTicket() {
        for (String ticket : ticketPool) {
            if (ticket.equals(targetID)) {
                ticketPool.remove(ticket);
                return ticket;
            }
        }
        return null; // Return null if the ticket is not found
    }
}
