package org.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;
import org.parkinglot.model.VehicleType;

@Getter
@Setter
public class IssueRequestDto {

    private String license_plate;
    private VehicleType vehicleType;
    private int entryGateId;
    private int operatorId;
}
