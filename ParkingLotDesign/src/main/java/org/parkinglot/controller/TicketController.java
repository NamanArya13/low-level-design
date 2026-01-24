package org.parkinglot.controller;

import org.parkinglot.dtos.IssueRequestDto;
import org.parkinglot.dtos.IssueResponseDto;
import org.parkinglot.dtos.ResponseStatus;
import org.parkinglot.model.Ticket;
import org.parkinglot.service.ITicketService;

public class TicketController {

    private final ITicketService ticketService;

    public TicketController(ITicketService ticketService){
        this.ticketService = ticketService;
    }

    public IssueResponseDto issueTicket(IssueRequestDto issueRequestDto){
        IssueResponseDto issueResponseDto = new IssueResponseDto();
        try {
            Ticket ticket = ticketService.issueTicket(issueRequestDto.getLicense_plate(),issueRequestDto.getEntryGateId(),issueRequestDto.getOperatorId(),issueRequestDto.getVehicleType());
            issueResponseDto.setParkingSlotNumber(ticket.getSlot().getNumber());
            issueResponseDto.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            issueResponseDto.setStatus(ResponseStatus.FAILURE);
            issueResponseDto.setFailureMessage(e.getMessage());
        }
        return issueResponseDto;
    }
}
