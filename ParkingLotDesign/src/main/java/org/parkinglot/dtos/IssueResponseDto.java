package org.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueResponseDto {

    private int ticketId;
    private ResponseStatus status;
    private String parkingSlotNumber;
    private String failureMessage;

}
