package org.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExitResponseDto {

    private int billId;
    private ResponseStatus status;
    private String failureMessage;
}
