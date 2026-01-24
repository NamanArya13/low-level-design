package org.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel {

    private PaymentType paymentType;
    private int billId;
    private long transactionId;
    private PaymentStatus paymentStatus;
    private static int counter = 0;

    public Payment(PaymentType paymentType, int billId, long transactionId, PaymentStatus paymentStatus) {
        super(counter++);
        this.paymentType = paymentType;
        this.billId = billId;
        this.transactionId = transactionId;
        this.paymentStatus = paymentStatus;
    }
}
