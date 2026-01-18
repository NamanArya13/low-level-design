package org.example.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Gate;
import org.example.model.Vehicle;
import org.example.model.VehicleType;

import java.util.Date;

@Getter
@Setter
public class IssueRequestDto {

    private String license_plate;
    private VehicleType vehicleType;
    private int entryGateId;
    private int operatorId;
}
