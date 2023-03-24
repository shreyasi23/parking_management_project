package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigInteger;


@Entity
@Table(name = "parking_history")
public class ParkingHistory {
    @Id
    @Column(name = "id")
    private int phID;

//    @Column(name = "entry_date")
//    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
//    private Date entryDate;
    @Column(name = "entry_date")
    private String entryDate;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name = "driver_ph_no")
    private BigInteger driverPhNo;
    @Column(name = "vehicle_type")
    private int vehicleType;
    @Column(name = "license_plate_no", unique = true)
    private String licensePlateNo;
    @Column(name = "slot_no")
    private String slotNo;

    @Column(name = "entry_time")
//    @JsonFormat(pattern="HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private String entryTime;

    @Column(name = "exit_time")
//    @JsonFormat(pattern="HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private String exitTime;

    public int getPhID() {
        return phID;
    }

    public void setPhID(int phID) {
        this.phID = phID;
    }

//    public Date getEntryDate() {
//        return entryDate;
//    }
//
//    public void setEntryDate(Date entryDate) {
//        this.entryDate = entryDate;
//    }


    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public BigInteger getDriverPhNo() {
        return driverPhNo;
    }

    public void setDriverPhNo(BigInteger driverPhNo) {
        this.driverPhNo = driverPhNo;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo;
    }

    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    public String getEntryTime() { return entryTime;}

    public void setEntryTime(String entryTime) { this.entryTime = entryTime;}

    public String getExitTime() { return exitTime;}

    public void setExitTime(String exitTime) { this.exitTime = exitTime; }

    @Override
    public String toString() {
        return "ParkingHistory{" +
                "phID=" + phID +
                ", entryDate='" + entryDate + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverPhNo=" + driverPhNo +
                ", vehicleType=" + vehicleType +
                ", licensePlateNo='" + licensePlateNo + '\'' +
                ", slotNo='" + slotNo + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                '}';
    }
}
