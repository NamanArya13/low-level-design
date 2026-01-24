package org.parkinglot.strategies;

import org.parkinglot.model.*;

public class FarthestAllocationStrategy implements SlotAllocationStrategy{
    
    @Override
    public ParkingSlot allocateParkingSlot(VehicleType vehicleType) {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        for(int i = parkingLot.getParkingFloorList().size()-1;i>-1;i--){
            ParkingFloor parkingFloor = parkingLot.getParkingFloorList().get(i);
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
