package org.example.strategies;

import org.example.model.ParkingLot;
import org.example.model.ParkingSlot;
import org.example.model.VehicleType;

public interface SlotAllocationStrategy {

    ParkingSlot allocateParkingSlot(VehicleType vehicleType);
}
