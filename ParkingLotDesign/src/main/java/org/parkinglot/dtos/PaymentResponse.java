package org.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;
import org.parkinglot.model.PaymentStatus;

import java.util.List;

@Getter
@Setter
public class PaymentResponse {

    private ResponseStatus responseStatus;
    private PaymentStatus paymentStatus;
    private List<Long> transactionId;
    private int billId;
    private String failureMessage;
}
