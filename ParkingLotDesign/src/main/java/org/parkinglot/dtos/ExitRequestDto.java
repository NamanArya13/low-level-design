package org.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExitRequestDto {

    private int ticketId;
    private int exitGateId;
    private int operatorId;
    private String payments;
}
