package org.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel {

    private PaymentType paymentType;
    private Bill bill;
    private String transactionId;
    private PaymentStatus paymentStatus;

    public Payment(int id, PaymentType paymentType, Bill bill, String transactionId, PaymentStatus paymentStatus) {
        super(id);
        this.paymentType = paymentType;
        this.bill = bill;
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
    }
}
