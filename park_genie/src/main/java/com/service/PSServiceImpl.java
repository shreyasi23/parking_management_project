package com.service;

import com.model.ParkingSlots;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PSServiceImpl implements ParkingSlotsService{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ParkingSlots> getParkingSlots(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<ParkingSlots> PSRecord = session.createQuery("from ParkingSlots",ParkingSlots.class).list();
        transaction.commit();
        session.close();
        return PSRecord;
    }

    @Override
    public List getFreeSlots(int vehicle_type){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("select slot_no from parking_slot where vehicle_type=:wheeler and is_empty=true");
        nativeQuery.setParameter("wheeler",vehicle_type);
        List freeSlots = nativeQuery.list();
        transaction.commit();
        session.close();
        return freeSlots;


    }

    @Override
    public ParkingSlots updateSlotStatus(ParkingSlots parkingSlots){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(parkingSlots);
        transaction.commit();
        session.close();
        return parkingSlots;
    }
}
