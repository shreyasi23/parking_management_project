package com.service;

import com.model.Invoice;
import com.model.ParkingHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
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

//    @Override
//    public Invoice getInvoiceById(String id){
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        Invoice invoice = session.get(Invoice.class, id);
//        transaction.commit();
//        session.close();
//        return invoice;
//    }

    @Override
    public List getRevenue(String inv_date){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("select sum(amount_paid) from invoice where inv_date=:date");
        nativeQuery.setParameter("date",inv_date);
        List revenue = nativeQuery.list();
        transaction.commit();
        session.close();
        return revenue;
    }
}
