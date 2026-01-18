package org.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingFloor extends BaseModel {

     private String number;
     private Status parkingFloorStatus;
     private List<ParkingSlot> parkingSlotList;
     private List<VehicleType> vehicleTypeList;


     public ParkingFloor(int id) {
          super(id);
     }
}
