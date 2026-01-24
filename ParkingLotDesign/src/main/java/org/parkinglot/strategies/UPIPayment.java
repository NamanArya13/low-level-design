package org.parkinglot.strategies;

public class UPIPayment implements PaymentStrategy{

    @Override
    public void pay(int amount) {
        System.out.println("Paying "+amount+" through UPI");
        throw new RuntimeException("Payment Failed");
    }

}
