package com.controller;

import com.model.ParkingSlots;
import com.service.ParkingSlotsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/parking-slots")
public class PSController {
    @Autowired
    ParkingSlotsService parkingSlotsService;
    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping
    public List<ParkingSlots> getParkingSlots(){
        logger.info("parking slots record");
        return parkingSlotsService.getParkingSlots();
    }

    @GetMapping("/{vehicle_type}")
    public List<String> getFreeSlots(@PathVariable("vehicle_type") int vehicle_type){
        logger.info("getFreeSlots is invoked with vehicle type: {}",vehicle_type);
        return parkingSlotsService.getFreeSlots(vehicle_type);
    }

    @PutMapping("/{slot}")
    public ParkingSlots updateSlotStatus(@RequestBody ParkingSlots parkingSlots){
        return parkingSlotsService.updateSlotStatus(parkingSlots);
    }
}
