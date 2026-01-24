package org.parkinglot.repository;

import org.parkinglot.model.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {

    private final Map<Integer, Payment> paymentMap;

    public PaymentRepository(){
        paymentMap = new HashMap<>();
    }
    public void save(Payment payment) {
        paymentMap.put(payment.getBillId(),payment);
    }
}
