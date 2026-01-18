package org.example.repository;

import org.example.model.Vehicle;
import org.example.model.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {

    Map<String, Vehicle> vehicleMap;

    public VehicleRepository(){
        vehicleMap = new HashMap<>();
    }

    public Optional<Vehicle> findByLicensePlate(String licensePlate){
        return Optional.ofNullable(vehicleMap.get(licensePlate));
    }

    public Vehicle save(String licensePlate, VehicleType vehicleType){
        Vehicle vehicle = new Vehicle(licensePlate,vehicleType);
        vehicle.setId(5);
        vehicleMap.put(licensePlate,vehicle);
        return vehicle;
    }
}
