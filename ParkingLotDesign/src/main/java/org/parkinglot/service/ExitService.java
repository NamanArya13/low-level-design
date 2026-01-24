package org.parkinglot.service;

import org.parkinglot.model.Bill;
import org.parkinglot.model.Ticket;
import org.parkinglot.repository.TicketRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class ExitService implements IExitService{

    private static final long BASE_PAY = 50;
    private final TicketRepository ticketRepository;
    private static int billCounter = 1;

    public ExitService( TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Bill generateExitBill(int ticketId,int exitGateId, int operatorId) {
        Ticket ticket = getTicketById(ticketId);
        int amount = (int) calculateAmount(ticket);
        int billId = billCounter++;
        return new Bill(billId, Calendar.getInstance().getTime(),amount,ticketId,exitGateId,operatorId,new ArrayList<>());
    }

    private long calculateAmount(Ticket ticket) {
        Duration duration = Duration.between(ticket.getEntryTime().toInstant(), Calendar.getInstance().getTime().toInstant());
        long totalHours = duration.toHours();
        return BASE_PAY+totalHours*ticket.getVehicle().getVehicleType().getBaseRatePerHour();
    }

    private Ticket getTicketById(int ticketId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (ticketOptional.isEmpty()){
            throw new RuntimeException("No Such ticket exists");
        }
        return ticketOptional.get();
    }
}
