package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSlot extends BaseModel {

    private String number;
    private Status status;
    // if a slot can support multiple vehicles, we convert this to a list
    private VehicleType vehicleType;

    public ParkingSlot(int id) {
        super(id);
    }
}
