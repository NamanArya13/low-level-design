package org.parkinglot.repository;

import org.parkinglot.model.Bill;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BillRepository {

    private Map<Integer, Bill> billMap;

    public BillRepository(){
        billMap = new HashMap<>();
    }

    public void saveBill(Bill bill){
        billMap.put(bill.getId(),bill);
    }

    public Optional<Bill> findById(int id){
        return Optional.ofNullable(billMap.get(id));
    }


}
