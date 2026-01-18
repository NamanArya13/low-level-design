package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class Ticket extends BaseModel {

    private Date entryTime;
    private Vehicle vehicle;
    private Gate entryGate;
    private ParkingSlot slot;

    public Ticket(int id) {
        super(id);
    }
}
