package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.strategies.SlotAllocationStrategy;

import java.util.List;

@Getter
@Setter
public class ParkingLot extends BaseModel {

    private List<ParkingFloor> parkingFloorList;
    private List<Gate> entryGateList;
    private List<Gate> exitGates;
    private Status parkingLotStatus;
    private int capacity;
    private List<VehicleType> vehicleTypeList;
    private SlotAllocationStrategy slotAllocationStrategy;
    private static ParkingLot parkingLot;
    private final static Object lock = new Object();

    private ParkingLot(int id) {
        super(id);
    }

    public static ParkingLot getParkingLotInstance() {
        if (parkingLot == null) {
            synchronized (lock) {
                if (parkingLot == null) {
                    parkingLot = new ParkingLot(1);
                }
            }
        }
        return parkingLot;
    }
}