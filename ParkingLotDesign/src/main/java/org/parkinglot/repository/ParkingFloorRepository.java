package org.parkinglot.repository;

import org.parkinglot.model.ParkingFloor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingFloorRepository {

    private final Map<String, ParkingFloor> parkingFloorMap;

    public ParkingFloorRepository() {
        this.parkingFloorMap = new HashMap<>();
    }
    public void save(ParkingFloor parkingFloor){
        parkingFloorMap.put(parkingFloor.getNumber(),parkingFloor);
    }

    public Optional<ParkingFloor> findByNumber(String number){
        return Optional.ofNullable(parkingFloorMap.get(number));
    }
}
