package org.parkinglot.strategies;

import org.parkinglot.model.*;

public class NearestAllocationStrategy implements SlotAllocationStrategy{


    @Override
    public ParkingSlot allocateParkingSlot(VehicleType vehicleType) {
        for(ParkingFloor parkingFloor: ParkingLot.getParkingLotInstance().getParkingFloorList()){
            if (parkingFloor.getParkingFloorStatus().equals(Status.AVAILABLE)) {
                for (ParkingSlot parkingSlot : parkingFloor.getParkingSlotList()) {
                    if (parkingSlot.getVehicleType().equals(vehicleType)) {
                        if (parkingSlot.tryOccupy()) return parkingSlot;
                    }
                }
            }
            parkingFloor.setParkingFloorStatus(Status.FULL);
        }
        return null;
    }

}
