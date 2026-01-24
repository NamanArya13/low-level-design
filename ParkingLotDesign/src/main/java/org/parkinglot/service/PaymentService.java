package org.parkinglot.service;

import org.parkinglot.dtos.PaymentResponse;
import org.parkinglot.dtos.ResponseStatus;
import org.parkinglot.model.Bill;
import org.parkinglot.model.Payment;
import org.parkinglot.model.PaymentStatus;
import org.parkinglot.model.PaymentType;
import org.parkinglot.repository.BillRepository;
import org.parkinglot.repository.PaymentRepository;
import org.parkinglot.strategies.CashPayment;
import org.parkinglot.strategies.PaymentStrategy;
import org.parkinglot.strategies.UPIPayment;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PaymentService implements IPaymentService{

    private final Map<PaymentType,PaymentStrategy> paymentStrategyMap;
    private final BillRepository billRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentStrategy paymentStrategy, BillRepository billRepository, PaymentRepository paymentRepository) {
        paymentStrategyMap = new HashMap<>();
        paymentStrategyMap.put(PaymentType.CASH,new CashPayment());
        paymentStrategyMap.put(PaymentType.UPI,new UPIPayment());
        this.billRepository = billRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponse makePayment(int billId, List<String> paymentModes, List<Integer> amounts) {
        validateInputs(paymentModes, amounts);

        Bill bill = getBillFromId(billId);
        PaymentResponse paymentResponse = new PaymentResponse();
        List<Long> transactionIdList = new ArrayList<>();
        List<Payment> paymentList = new ArrayList<>();

        for (int i = 0; i < paymentModes.size(); i++) {
            String paymentMode = paymentModes.get(i);
            int amount = amounts.get(i);
            long transactionId = generateTransactionId();
            Payment payment = new Payment(null, bill.getId(), transactionId, null);
            transactionIdList.add(transactionId);

            try {
                processPayment(paymentMode, amount, payment);
                paymentList.add(payment);
            } catch (Exception e) {
                handlePaymentFailure(paymentResponse, payment, e.getMessage());
                paymentList.add(payment);
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        finalizePayment(bill, paymentList, transactionIdList, paymentResponse);
        return paymentResponse;
    }

    private void validateInputs(List<String> paymentModes, List<Integer> amounts) {
        if (paymentModes == null || amounts == null || paymentModes.size() != amounts.size()) {
            throw new IllegalArgumentException("Payment modes and amounts must be non-null and of the same size.");
        }
    }

    private long generateTransactionId() {
        return ThreadLocalRandom.current().nextLong();
    }

    private void processPayment(String paymentMode, int amount, Payment payment) {
        PaymentType paymentType = PaymentType.valueOf(paymentMode);
        payment.setPaymentType(paymentType);
        PaymentStrategy paymentStrategy = paymentStrategyMap.get(paymentType);
        paymentStrategy.pay(amount);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
    }

    private void handlePaymentFailure(PaymentResponse paymentResponse, Payment payment, String errorMessage) {
        paymentResponse.setPaymentStatus(PaymentStatus.FAILED);
        payment.setPaymentStatus(PaymentStatus.FAILED);
        paymentRepository.save(payment);
    }

    private void finalizePayment(Bill bill, List<Payment> paymentList, List<Long> transactionIdList, PaymentResponse paymentResponse) {
        paymentResponse.setResponseStatus(ResponseStatus.SUCCESS);
        paymentResponse.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentResponse.setTransactionId(transactionIdList);
        bill.setPayment(paymentList);
        billRepository.saveBill(bill);
    }

    private Bill getBillFromId(int billId) {
        Optional<Bill> billOptional = billRepository.findById(billId);
        if (billOptional.isEmpty()) throw new RuntimeException("Bill not found for this");
        return billOptional.get();
    }
}
