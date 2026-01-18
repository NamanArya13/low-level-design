package org.example;

import org.example.controller.TicketController;
import org.example.dtos.IssueRequestDto;
import org.example.dtos.IssueResponseDto;
import org.example.dtos.ResponseStatus;
import org.example.model.ParkingLot;
import org.example.model.ParkingSlot;
import org.example.model.VehicleType;
import org.example.repository.GateRepository;
import org.example.repository.TicketRepository;
import org.example.repository.VehicleRepository;
import org.example.service.ITicketService;
import org.example.service.InitializationService;
import org.example.service.TicketService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Main {
    public static void main(String[] args) {
        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();

        InitializationService initialisationService = new InitializationService(gateRepository);
        ParkingLot parkingLot = initialisationService.initialise();

        ITicketService ticketService = new TicketService(gateRepository,vehicleRepository,ticketRepository);
        TicketController ticketController = new TicketController(ticketService);
        for (int i = 1; i <= 20; i++) {
            IssueRequestDto issueRequestDto = getIssueRequestDto(i, parkingLot);

            IssueResponseDto responseDto = ticketController.issueTicket(issueRequestDto);
            if (responseDto.getStatus() == ResponseStatus.FAILURE) {
                System.out.println("Ticket generation failed for vehicle TG 07 23" + (40 + i)+" Vehicle Type - "+issueRequestDto.getVehicleType());
                System.out.println(responseDto.getFailureMessage());
            } else {
                System.out.println("Ticket generation successful. Slot: " + responseDto.getParkingSlotNumber()+
                        " Vehicle Number - "+issueRequestDto.getLicense_plate()+" Vehicle Type - "+issueRequestDto.getVehicleType());
            }
        }




    }

    private static IssueRequestDto getIssueRequestDto(int i, ParkingLot parkingLot) {
        IssueRequestDto issueRequestDto = new IssueRequestDto();
        issueRequestDto.setEntryGateId((i % parkingLot.getEntryGateList().size()==0?1: i % parkingLot.getEntryGateList().size()));
        if (i %6 == 0 || i %6 == 1) issueRequestDto.setVehicleType(VehicleType.TWO_WHEELER);
        else if (i %6 == 2 || i %6 == 3) issueRequestDto.setVehicleType(VehicleType.FOUR_WHEELER);
        else if (i %6 == 4) issueRequestDto.setVehicleType(VehicleType.EV);
        else issueRequestDto.setVehicleType(VehicleType.HEAVY_VEHICLE);
        issueRequestDto.setOperatorId(3);
        issueRequestDto.setLicense_plate("TG 07 23" + (40 + i));
        return issueRequestDto;
    }
}