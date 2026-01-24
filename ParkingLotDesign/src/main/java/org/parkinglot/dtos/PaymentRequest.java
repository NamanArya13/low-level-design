package org.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentRequest {

    private int billId;
    private List<String> paymentModes;
    private List<Integer> amounts;

}
