package com.service;

import com.model.VehicleNameCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VNCServiceImpl implements VehicleNameCodeService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<VehicleNameCode> getVNCRecord(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<VehicleNameCode> VNCRecord = session.createQuery("from VehicleNameCode",VehicleNameCode.class).list();
        transaction.commit();
        session.close();
        return VNCRecord;
    }
}
