package org.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle extends BaseModel {

    private VehicleType vehicleType;
    private String licensePlate;

    public Vehicle(String licensePlate, VehicleType vehicleType) {
        super(-1);
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }
}
