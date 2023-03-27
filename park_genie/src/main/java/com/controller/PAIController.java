package com.controller;


import com.model.ParkingAreaInfo;
import com.service.PAIService;
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
@RequestMapping(value = "/parking-area-info")
public class PAIController {
    @Autowired
    PAIService paiService;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping
    public List<ParkingAreaInfo> getParkingAreaInfo(){
        logger.info("parking area information");
        return paiService.getParkingAreaInfo();
    }
}
