package org.parkinglot.service;

import org.parkinglot.model.Ticket;
import org.parkinglot.model.VehicleType;

public interface ITicketService{
    Ticket issueTicket(String licensePlate,
                       int entryGateId, int operatorId,
                       VehicleType type);
}
