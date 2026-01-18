package org.example.service;

import org.example.model.Ticket;
import org.example.model.VehicleType;

public interface ITicketService{
    Ticket issueTicket(String licensePlate,
                       int entryGateId, int operatorId,
                       VehicleType type);
}
