package org.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSlot extends BaseModel {

    private String number;
    private Status status;
    // if a slot can support multiple vehicles, we convert this to a list
    private VehicleType vehicleType;
    private String parkingFloorNumber;

    public ParkingSlot(int id) {
        super(id);
    }

    public synchronized boolean tryOccupy() {
        if (status == Status.AVAILABLE) {
            status = Status.FULL;
            return true;
        }
        return false;
    }

    public boolean free() {
        if (status == Status.FULL) {
            status = Status.AVAILABLE;
            return true;
        }
        return false;
    }
}
