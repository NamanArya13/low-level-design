package org.parkinglot.controller;

import org.parkinglot.dtos.ExitRequestDto;
import org.parkinglot.dtos.ExitResponseDto;
import org.parkinglot.dtos.ResponseStatus;
import org.parkinglot.model.Bill;
import org.parkinglot.service.IExitService;

public class BillController {

    private IExitService exitService;

    public BillController(IExitService exitService){
        this.exitService = exitService;
    }

    public ExitResponseDto requestExit(ExitRequestDto exitRequestDto){
        ExitResponseDto exitResponseDto = new ExitResponseDto();
        try {
            Bill exitBill = exitService.generateExitBill(exitRequestDto.getTicketId(),
                    exitRequestDto.getExitGateId(), exitRequestDto.getOperatorId(),exitRequestDto.getPayments());
            exitResponseDto.setBillId(exitBill.getId());
            exitResponseDto.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            exitResponseDto.setStatus(ResponseStatus.FAILURE);
            exitResponseDto.setFailureMessage(e.getMessage());
        }
        return exitResponseDto;
    }
}
