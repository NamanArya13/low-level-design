//package org.example.repository;
//
//import org.example.model.ParkingLot;
//import org.example.model.ParkingSlot;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.TreeMap;
//
//public class ParkingLotRespository {
//
//    private Map<Integer, ParkingLot> parkingLotMap;
//
//    public ParkingLotRespository(){
//        parkingLotMap = new TreeMap<>();
//    }
//
//    public Optional<ParkingLot> findById(int parkingLotId)
//    {
//        return Optional.ofNullable(parkingLotMap.get(parkingLotId));
//    }
//
//    public ParkingLot save(ParkingLot parkingLot){
//        parkingLotMap.put(parkingLot.getId(), parkingLot);
//        return parkingLot;
//    }
//}
