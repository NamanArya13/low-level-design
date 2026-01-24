package org.parkinglot.service;

import org.parkinglot.dtos.PaymentResponse;

import java.util.List;

public interface IPaymentService {

    PaymentResponse makePayment(int billId, List<String> paymentModes, List<Integer> amounts);
}
