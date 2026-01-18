package org.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Bill extends BaseModel {

    private Date exitTime;
    private int totalAmount;
    private Ticket ticket;
    private Gate exitGate;
    private List<Payment> payment;

    public Bill(int id, Date exitTime, int totalAmount, Ticket ticket, Gate exitGate, List<Payment> payment) {
        super(id);
        this.exitTime = exitTime;
        this.totalAmount = totalAmount;
        this.ticket = ticket;
        this.exitGate = exitGate;
        this.payment = payment;
    }
}
