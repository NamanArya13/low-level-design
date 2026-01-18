package org.parkinglot.repository;

import org.parkinglot.model.Gate;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {

    Map<Integer, Gate> gates;
    public GateRepository(){
        gates = new TreeMap<>();
    }

    public Optional<Gate> findById(int gateId){
        return Optional.ofNullable(gates.get(gateId));
    }

    public void save(Gate entryGate) {
        gates.putIfAbsent(entryGate.getId(),entryGate);
    }
}
