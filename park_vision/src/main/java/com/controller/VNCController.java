package com.controller;

import com.model.VehicleNameCode;
import com.service.VehicleNameCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/vehicle-make")
public class VNCController {
    @Autowired
    VehicleNameCodeService vehicleNameCodeService;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping
    public List<VehicleNameCode> getVNCRecord(){
        logger.info("vehicle make codes record");
        return vehicleNameCodeService.getVNCRecord();
    }
}
