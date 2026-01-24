package org.parkinglot.service;

import org.parkinglot.model.Bill;
import org.parkinglot.model.Ticket;

public interface IExitService {

    Bill generateExitBill(int ticketId,int exitGateId,int operatorId);
}
