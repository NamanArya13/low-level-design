package org.parkinglot.service;

import org.parkinglot.model.*;
import org.parkinglot.repository.GateRepository;
import org.parkinglot.repository.TicketRepository;
import org.parkinglot.repository.VehicleRepository;

import java.util.Optional;

public class TicketService implements ITicketService {

    private final GateRepository gateRepository;
    private final VehicleRepository vehicleRepository;
    private final TicketRepository ticketRepository;
    private static int counter = 1;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(String licensePlate, int entryGateId, int operatorId, VehicleType type) {
        Gate gate = getGate(entryGateId, operatorId);
        Vehicle vehicle = getOrCreateVehicle(licensePlate, type);
        ParkingSlot parkingSlot = allocateParkingSlot(type);

        return createAndSaveTicket(gate, vehicle, parkingSlot);
    }

    private Gate getGate(int entryGateId, int operatorId) {
        Optional<Gate> optionalGate = gateRepository.findById(entryGateId);
        if (optionalGate.isEmpty()) {
            throw new RuntimeException("Gate not found");
        }
        Gate gate = optionalGate.get();
        gate.getOperator().setId(operatorId);
        return gate;
    }

    private Vehicle getOrCreateVehicle(String licensePlate, VehicleType type) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByLicensePlate(licensePlate);
        if (vehicleOptional.isEmpty()) {
            vehicleRepository.save(licensePlate, type);
            return vehicleRepository.findByLicensePlate(licensePlate).orElseThrow(() ->
                    new RuntimeException("Failed to save or retrieve vehicle"));
        } else {
            return vehicleOptional.get();
        }
    }

    private ParkingSlot allocateParkingSlot(VehicleType type) {
        ParkingLot parkingLot = ParkingLot.getParkingLotInstance();
        ParkingSlot parkingSlot = parkingLot.getSlotAllocationStrategy().allocateParkingSlot(type);
        if (parkingSlot == null) {
            throw new RuntimeException("No parking slots available!");
        }
        return parkingSlot;
    }

    private Ticket createAndSaveTicket(Gate gate, Vehicle vehicle, ParkingSlot parkingSlot) {
        Ticket ticket = new Ticket(counter++);
        ticket.setEntryGate(gate);
        ticket.setVehicle(vehicle);
        ticket.setSlot(parkingSlot);
        return ticketRepository.save(ticket);
    }
}