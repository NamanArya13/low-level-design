package org.example.controller;

import org.example.dtos.IssueRequestDto;
import org.example.dtos.IssueResponseDto;
import org.example.dtos.ResponseStatus;
import org.example.model.Ticket;
import org.example.service.ITicketService;
import org.example.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketController {

    private ITicketService ticketService;

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
