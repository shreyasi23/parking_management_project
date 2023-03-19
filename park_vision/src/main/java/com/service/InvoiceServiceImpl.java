package com.service;

import com.model.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Invoice> getInvoiceRecord(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Invoice> invoiceRecord = session.createQuery("from Invoice", Invoice.class).list();
        transaction.commit();
        session.close();
        return invoiceRecord;
    }

    @Override
    public Invoice createInvoice(Invoice invoice){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(invoice);
        transaction.commit();
        session.close();
        return invoice;
    }

    @Override
    public Invoice getInvoiceById(String id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Invoice invoice = session.get(Invoice.class, id);
        transaction.commit();
        session.close();
        return invoice;
    }

}
