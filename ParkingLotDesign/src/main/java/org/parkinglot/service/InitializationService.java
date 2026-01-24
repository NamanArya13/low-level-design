package org.parkinglot.service;

import org.parkinglot.model.*;
import org.parkinglot.repository.GateRepository;
import org.parkinglot.strategies.FarthestAllocationStrategy;

import java.util.ArrayList;
import java.util.List;

public class InitializationService {

    private final GateRepository gateRepository;

    public InitializationService(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }


    public ParkingLot initialise() {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        parkingLot.setParkingLotStatus(Status.AVAILABLE);
        parkingLot.setParkingFloorList(new ArrayList<>());
        parkingLot.setEntryGateList(new ArrayList<>()); // Initialise the list of gates
        parkingLot.setExitGates(new ArrayList<>());

        // Create a Gate
        Gate entryGate = new Gate(1, "EN1", GateType.ENTRY, GateStatus.OPERATIONAL, new Operator("Raj"));
        gateRepository.save(entryGate);
        Gate entryGateTwo = new Gate(2, "EN2", GateType.ENTRY, GateStatus.OPERATIONAL, new Operator("Leonard"));
        gateRepository.save(entryGateTwo);
        Gate entryGateThree = new Gate(3, "EN3", GateType.ENTRY, GateStatus.OPERATIONAL, new Operator("Sheldon"));
        gateRepository.save(entryGateThree);
        Gate exitGateOne = new Gate(4, "EX1", GateType.EXIT, GateStatus.OPERATIONAL, new Operator("Howie"));
        gateRepository.save(exitGateOne);
        Gate exitGateTwo = new Gate(5, "EX2", GateType.EXIT, GateStatus.OPERATIONAL, new Operator("Mary"));
        gateRepository.save(exitGateTwo);
        List<Gate> entryList = new ArrayList<>(List.of(entryGate,entryGateTwo,entryGateThree));
        List<Gate> exitList = new ArrayList<>(List.of(exitGateOne,exitGateTwo));
        parkingLot.getEntryGateList().addAll(entryList);// Add the gate to the parking lot
        parkingLot.getEntryGateList().addAll(exitList);

        int parkingLotCapacity = 0;

        for (int i = 0; i < 4; i++) {
            ParkingFloor floor = new ParkingFloor(i);
            floor.setNumber(String.valueOf(i));
            floor.setParkingSlotList(new ArrayList<>());
            floor.setParkingFloorStatus(Status.AVAILABLE);

            for (int j = 0; j < 10; j++) {
                ParkingSlot slot = getParkingSlot(j, i);
                floor.getParkingSlotList().add(slot);
                slot.setParkingFloorNumber(floor.getNumber());
                parkingLotCapacity++;
            }
            parkingLot.getParkingFloorList().add(floor);
        }
        parkingLot.setSlotAllocationStrategy(new FarthestAllocationStrategy());
        parkingLot.setCapacity(parkingLotCapacity);
        return parkingLot;
    }

    private static ParkingSlot getParkingSlot(int j, int i) {
        ParkingSlot slot = new ParkingSlot(j);
        slot.setNumber(String.valueOf((i * 10) + j));
        if (j % 6 == 0 || j % 6 == 1) {
            slot.setVehicleType(VehicleType.TWO_WHEELER);
        } else if (j % 6 == 2 || j %6 == 3) {
            slot.setVehicleType(VehicleType.FOUR_WHEELER);
        } else if (j % 6 == 4) {
            slot.setVehicleType(VehicleType.EV);
        } else {
            slot.setVehicleType(VehicleType.HEAVY_VEHICLE);
        }
        slot.setStatus(Status.AVAILABLE);
        return slot;
    }
}

