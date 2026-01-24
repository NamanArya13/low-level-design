package org.parkinglot.service;

import org.parkinglot.model.Bill;

public interface IExitService {

    Bill generateExitBill(int ticketId,int exitGateId,int operatorId);
}
