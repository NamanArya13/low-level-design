package org.parkinglot.model;

import lombok.Getter;

@Getter
public enum VehicleType {

    TWO_WHEELER(10),
    FOUR_WHEELER(20),
    HEAVY_VEHICLE(50),
    EV(100);

    private final int baseRatePerHour;

    VehicleType(int baseRatePerHour) {
        this.baseRatePerHour = baseRatePerHour;
    }

}
