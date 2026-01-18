package org.parkinglot.repository;

import org.parkinglot.model.Ticket;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class TicketRepository {

    Map<Integer, Ticket> ticketMap;
    private static int counter;

    public TicketRepository(){
        ticketMap = new TreeMap<>();
        counter = 1;
    }

    public Ticket save(Ticket ticket){
        ticket.setId(counter++);
        ticket.setEntryTime(Calendar.getInstance().getTime());
        ticketMap.put(ticket.getId(),ticket);
        return ticket;
    }

    public Optional<Ticket> findById(int ticketId){
        return Optional.ofNullable(ticketMap.get(ticketId));
    }
}
