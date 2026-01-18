package org.parkinglot.strategies;

import org.parkinglot.model.ParkingSlot;
import org.parkinglot.model.VehicleType;

public interface SlotAllocationStrategy {

    ParkingSlot allocateParkingSlot(VehicleType vehicleType);
}
