package org.parkinglot.strategies;

import org.parkinglot.model.Bill;
import org.parkinglot.model.PaymentType;

public interface PaymentStrategy {

    void pay(int amount);

}
