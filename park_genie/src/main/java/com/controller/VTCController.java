package com.controller;


import com.model.VehicleTypeCode;
import com.service.VTCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/vehicle-type-code")
public class VTCController {
    @Autowired
    VTCService vtcService;

    private static final Logger logger = LoggerFactory.getLogger(VTCController.class);

    @GetMapping
    public List<VehicleTypeCode> getParkingAreaInfo(){
        logger.info("vehicle type code was invoked");
        return vtcService.getVehicleTypeCode();
    }
}
