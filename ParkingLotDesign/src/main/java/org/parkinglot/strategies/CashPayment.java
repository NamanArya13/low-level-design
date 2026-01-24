package org.parkinglot.strategies;

import lombok.Setter;
import org.parkinglot.model.PaymentType;
import org.parkinglot.repository.BillRepository;

public class CashPayment implements PaymentStrategy{

    @Override
    public void pay(int amount) {
        System.out.println("Paying "+amount+" through cash");
    }
}
