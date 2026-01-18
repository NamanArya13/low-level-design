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
    private int ticketId;
    private int exitGateId;
    private List<Payment> payment;
    private int operatorId;

    public Bill(int id, Date exitTime, int totalAmount, int ticketId, int exitGate, int operatorId,List<Payment> payment) {
        super(id);
        this.exitTime = exitTime;
        this.totalAmount = totalAmount;
        this.ticketId = ticketId;
        this.exitGateId = exitGate;
        this.operatorId = operatorId;
        this.payment = payment;
    }
}
