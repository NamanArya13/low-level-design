package org.example.repository;

import org.example.model.Gate;
import org.example.model.GateStatus;
import org.example.model.GateType;
import org.example.model.Operator;

import java.util.HashMap;
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
