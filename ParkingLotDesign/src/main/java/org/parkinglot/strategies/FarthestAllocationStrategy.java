package org.parkinglot.strategies;

import org.parkinglot.model.*;

public class FarthestAllocationStrategy implements SlotAllocationStrategy{


    @Override
    public ParkingSlot allocateParkingSlot(VehicleType vehicleType) {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        for(int i = parkingLot.getParkingFloorList().size()-1;i>-1;i--){
            ParkingFloor parkingFloor = parkingLot.getParkingFloorList().get(i);
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
