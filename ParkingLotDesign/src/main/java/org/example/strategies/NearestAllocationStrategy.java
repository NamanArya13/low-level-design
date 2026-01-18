package org.example.strategies;

import org.example.model.*;

public class NearestAllocationStrategy implements SlotAllocationStrategy{
    @Override
    public ParkingSlot allocateParkingSlot(VehicleType vehicleType) {
        for(ParkingFloor parkingFloor: ParkingLot.getParkingLotInstance().getParkingFloorList()){
            for(ParkingSlot parkingSlot: parkingFloor.getParkingSlotList()){
                if (parkingSlot.getVehicleType().equals(vehicleType)
                && parkingSlot.getStatus() == Status.AVAILABLE){
                    parkingSlot.setStatus(Status.FULL);
                    return parkingSlot;
                }
            }
            parkingFloor.setParkingFloorStatus(Status.FULL);
        }
        return null;
    }

}
