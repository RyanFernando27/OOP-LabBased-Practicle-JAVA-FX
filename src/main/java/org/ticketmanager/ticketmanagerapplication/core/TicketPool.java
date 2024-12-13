package org.ticketmanager.ticketmanagerapplication.core;


import org.ticketmanager.ticketmanagerapplication.logging.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class TicketPool implements TicketOperation {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());


    @Override
    public synchronized void addTickets(String ticket) {
        tickets.add(ticket);
        Logger.log("Added ticket: " + ticket);

    }

    @Override
    public synchronized String removeTicket() {
        if (tickets.isEmpty()) {
            Logger.log("No ticket to remove.");
            return null;
        } else {
            String removedTicket = tickets.remove(0);
            Logger.log("Removed ticket: " + removedTicket);
            return removedTicket;
        }
    }

    public int getTicketCount() {
        return tickets.size();
    }
}