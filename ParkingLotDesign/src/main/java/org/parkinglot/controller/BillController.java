package org.parkinglot.controller;

import org.parkinglot.dtos.*;
import org.parkinglot.model.Bill;
import org.parkinglot.model.PaymentStatus;
import org.parkinglot.service.IExitService;
import org.parkinglot.service.IPaymentService;

public class BillController {

    private final IExitService exitService;

    private final IPaymentService paymentService;

    public BillController(IExitService exitService, IPaymentService paymentService){
        this.exitService = exitService;
        this.paymentService = paymentService;
    }

    public ExitResponseDto requestExit(ExitRequestDto exitRequestDto){
        ExitResponseDto exitResponseDto = new ExitResponseDto();
        try {
            Bill exitBill = exitService.generateExitBill(exitRequestDto.getTicketId(),
                    exitRequestDto.getExitGateId(), exitRequestDto.getOperatorId());
            exitResponseDto.setBillId(exitBill.getId());
            exitResponseDto.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            exitResponseDto.setStatus(ResponseStatus.FAILURE);
            exitResponseDto.setFailureMessage(e.getMessage());
        }
        return exitResponseDto;
    }

    public PaymentResponse payParkingBill(PaymentRequest paymentRequest){
        PaymentResponse paymentResponse = new PaymentResponse();
        try {
             paymentResponse = paymentService.makePayment(paymentRequest.getBillId(),
                    paymentRequest.getPaymentModes(), paymentRequest.getAmounts());
        }catch (Exception e){
            paymentResponse.setResponseStatus(ResponseStatus.FAILURE);
            paymentResponse.setPaymentStatus(PaymentStatus.FAILED);
            paymentResponse.setFailureMessage(e.getMessage());
        }
        return paymentResponse;
    }
}
