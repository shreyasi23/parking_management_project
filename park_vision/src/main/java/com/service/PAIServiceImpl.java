package com.service;

import com.model.Invoice;
import com.model.ParkingAreaInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PAIServiceImpl implements PAIService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ParkingAreaInfo> getParkingAreaInfo(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<ParkingAreaInfo> parkingAreaInfo = session.createQuery("from ParkingAreaInfo", ParkingAreaInfo.class).list();
        transaction.commit();
        session.close();
        return parkingAreaInfo;
    }
}
