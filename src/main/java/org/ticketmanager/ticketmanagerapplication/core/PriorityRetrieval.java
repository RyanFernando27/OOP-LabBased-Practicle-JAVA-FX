package org.ticketmanager.ticketmanagerapplication.core;

import org.ticketmanager.ticketmanagerapplication.logging.Logger;

import java.util.List;

public class PriorityRetrieval implements TicketRetrievalStrategy{
//    Question 2
    private final List<String> ticketPool;

    public PriorityRetrieval(List<String> ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public synchronized String retrieveTicket() {
        for (String ticket : ticketPool) {
            if (ticket.contains("VIP")) {
                ticketPool.remove(ticket);
                Logger.log("Ticket " + ticket + " removed from priority queue");
                return ticket;
            }
        }
        return ticketPool.isEmpty() ? null : ticketPool.remove(0); // Fallback to first ticket
    }
}
