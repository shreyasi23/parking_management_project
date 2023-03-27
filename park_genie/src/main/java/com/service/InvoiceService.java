package com.service;

import com.model.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceService {
    List<Invoice> getInvoiceRecord();
    Invoice createInvoice(Invoice invoice);
//    Invoice getInvoiceById(String id);

    List getRevenue(String inv_date);
}
