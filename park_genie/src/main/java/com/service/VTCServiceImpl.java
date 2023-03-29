package com.service;

import com.model.VehicleTypeCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VTCServiceImpl {

    @Autowired
    private SessionFactory sessionFactory;
    public List<VehicleTypeCode> getVehicleTypeCode(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<VehicleTypeCode> vTypeCode = session.createQuery("from VehicleTypeCode", VehicleTypeCode.class).list();
        transaction.commit();
        session.close();
        return vTypeCode;
    }
}
