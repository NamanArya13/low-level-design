package org.parkinglot.service;

import org.parkinglot.model.*;
import org.parkinglot.repository.ParkingFloorRepository;
import org.parkinglot.repository.TicketRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class ExitService implements IExitService{

    private static final long BASE_PAY = 50;
    private final TicketRepository ticketRepository;
    private final ParkingFloorRepository parkingFloorRepository;
    private static int billCounter = 1;

    public ExitService(TicketRepository ticketRepository, ParkingFloorRepository parkingFloorRepository) {
        this.ticketRepository = ticketRepository;
        this.parkingFloorRepository = parkingFloorRepository;
    }

    @Override
    public Bill generateExitBill(int ticketId,int exitGateId, int operatorId) {
        Ticket ticket = getTicketById(ticketId);
        ParkingSlot parkingSlot = ticket.getSlot();
        if (!parkingSlot.free()) throw new RuntimeException("Slot already freed / invalid exit");
        Optional<ParkingFloor> parkingFloorOptional = parkingFloorRepository.findByNumber(parkingSlot.getParkingFloorNumber());
        if (parkingFloorOptional.isEmpty()) throw new RuntimeException("Invalid Ticket Id");
        ParkingFloor parkingFloor = parkingFloorOptional.get();
        parkingFloor.setParkingFloorStatus(Status.AVAILABLE);
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
