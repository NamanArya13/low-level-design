package org.parkinglot.service;

import org.parkinglot.model.*;
import org.parkinglot.repository.TicketRepository;
import org.parkinglot.repository.VehicleRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class ExitService implements IExitService{

    private static final long BASE_PAY = 50;
    private final VehicleRepository vehicleRepository;
    private final TicketRepository ticketRepository;
    private static int billCounter = 1;

    public ExitService(VehicleRepository vehicleRepository, TicketRepository ticketRepository) {
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Bill generateExitBill(int ticketId,int exitGateId, int operatorId,String payments) {
        Ticket ticket = getTicketById(ticketId);
        int amount = (int) calculateAmount(ticket);
        List<Payment> paymentsList = getPaymentList(payments);
        Bill bill = new Bill(billCounter++, Calendar.getInstance().getTime(),amount,ticketId,exitGateId,operatorId,paymentsList);
        for(Payment payment: paymentsList) payment.setBill(bill);
        return bill;
    }

    private List<Payment> getPaymentList(String payments) {
        String[] paymentsArray = payments.split(",");
        List<Payment> paymentsList = new ArrayList<>();
        for(String payment: paymentsArray){
            try {
                PaymentType type = PaymentType.valueOf(payment.toUpperCase());
                paymentsList.add(
                        new Payment(1, type, null, "34", PaymentStatus.IN_PROGRESS)
                );
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid Payment type!");
            }
        }
        return paymentsList;
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
