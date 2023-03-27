package com.service;

import com.model.ParkingHistory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PHServiceImpl implements ParkingHistoryService{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ParkingHistory> getParkingHistory(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        List<ParkingHistory> PHRecord = session.createQuery("from ParkingHistory",ParkingHistory.class).list();
        SQLQuery query = session.createSQLQuery("select * from parking_history order by id desc");
        List<ParkingHistory> PHRecord = query.list();
        transaction.commit();
        session.close();
        return PHRecord;
    }
    @Override
    public ParkingHistory recordParkingInfo(ParkingHistory parkingHistory){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(parkingHistory);
        transaction.commit();
        session.close();
        return parkingHistory;
    }

    @Override
    public List<ParkingHistory> getHistory(String license_plate_no){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<ParkingHistory> nativeQuery = session.createNativeQuery("select * from parking_history where license_plate_no=:licenseId and exit_time is null", ParkingHistory.class);
        nativeQuery.setParameter("licenseId",license_plate_no);
        List list = nativeQuery.list();
        transaction.commit();
        session.close();
        return list;
    }

//    @Override
//    public List<ParkingHistory> getPRWheelerWise(int vehicle_type){
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        NativeQuery<ParkingHistory> nativeQuery = session.createNativeQuery("select * from parking_history where vehicle_type=:vehicleType", ParkingHistory.class);
//        nativeQuery.setParameter("vehicleType",vehicle_type);
//        List record = nativeQuery.list();
//        transaction.commit();
//        session.close();
//        return record;
//    }

    @Override
    public ParkingHistory updateParkingInfo( ParkingHistory parkingHistory){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(parkingHistory);
        transaction.commit();
        session.close();
        return parkingHistory;
    }

    @Override
    public List<ParkingHistory> getOccupiedSlots(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        NativeQuery<ParkingHistory> nativeQuery = session.createNativeQuery("select driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time from parking_history where exit_time is null", ParkingHistory.class);
//        List occupiedSlots = nativeQuery.list();
        SQLQuery query = session.createSQLQuery("select driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time from parking_history where exit_time is null order by id desc");
        List<ParkingHistory> occupiedSlots = query.list();
        transaction.commit();
        session.close();
        return occupiedSlots;
    }

    @Override
    public List<ParkingHistory> getTopFiveOS(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select driver_name, driver_ph_no, vehicle_type, license_plate_no, slot_no, entry_time from parking_history where exit_time is null order by id desc limit 5");
        List<ParkingHistory> occupiedSlots5 = query.list();
        transaction.commit();
        session.close();
        return occupiedSlots5;
    }

    @Override
    public List<ParkingHistory> getPH_amt(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SQLQuery query =session.createSQLQuery("select parking_history.entry_date, parking_history.driver_name, parking_history.driver_ph_no, parking_history.vehicle_type, parking_history.license_plate_no, parking_history.slot_no, parking_history.entry_time, parking_history.exit_time, invoice.amount_paid  from parking_history left join invoice on parking_history.id=invoice.parking_id order by parking_history.id desc");
        List<ParkingHistory> phAmtRecord = query.list();
        transaction.commit();
        session.close();
        return phAmtRecord;
    }
}
