package com.controller;

import com.model.ParkingHistory;
import com.service.ParkingHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/parking-history")
public class ParkingHistoryController {
    @Autowired
    ParkingHistoryService parkingHistoryService;

    private static final Logger logger = LoggerFactory.getLogger(ParkingHistoryController.class);

    @GetMapping
    public List<ParkingHistory> getParkingHistory(){
        logger.info("Parking history");
        return parkingHistoryService.getParkingHistory();
    }


    @PostMapping("/record-parking")
    public ParkingHistory recordParkingInfo(@RequestBody ParkingHistory parkingHistory){
        logger.info("record parking info is invoked {}",parkingHistory);
        return parkingHistoryService.recordParkingInfo(parkingHistory);
    }

    @GetMapping("/occupied-5")
    public List<ParkingHistory> getTopFiveOS(){
        logger.info("getTopFiveOS is invoked");
        return parkingHistoryService.getTopFiveOS();
    }

    @GetMapping("/occupied")
    public List<ParkingHistory> getOccupiedSlots(){
        logger.info("getOccupiedSlots is invoked");
        return parkingHistoryService.getOccupiedSlots();
    }

    @GetMapping("/{license_plate_no}")
    public List<ParkingHistory> getHistory(@PathVariable("license_plate_no") String license_plate_no){
        logger.info("getHistory is invoked with license plate number: {}",license_plate_no);
        return parkingHistoryService.getHistory(license_plate_no);
    }

    @PutMapping("/exit-time")
    public ParkingHistory updateParkingInfo(@RequestBody ParkingHistory parkingHistory){
        return parkingHistoryService.updateParkingInfo(parkingHistory) ;
    }
}
