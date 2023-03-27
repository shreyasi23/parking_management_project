package com.controller;

import com.model.Invoice;
import com.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping
    public List<Invoice> getInvoiceRecord(){
        logger.info("invoice record");
        return invoiceService.getInvoiceRecord();
    }

    @PostMapping("/create-invoice")
    public Invoice createInvoice(@RequestBody Invoice invoice){
        logger.info("invoice creation invoked {}",invoice);
        return invoiceService.createInvoice(invoice);
    }

//    @GetMapping("/{id}")
//    public Invoice getInvoiceById(@PathVariable("id") String id){
//        logger.info("getInvoiceById is invoked with Invoice ID: {}",id);
//        return invoiceService.getInvoiceById(id);
//    }

    @GetMapping("/{inv_date}")
    public List getRevenue(@PathVariable("inv_date") String inv_date){
        logger.info("getRevenue is invoked with invoice date: {}",inv_date);
        return invoiceService.getRevenue(inv_date);
    }

}
