package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle_name_code")
public class VehicleNameCode {
    @Id
    @Column(name = "id")
    private String vncID;

    @Column(name = "vehicle_make")
    private String vehicleMake;

    public VehicleNameCode() {
        this.vncID = "MSD";
        this.vehicleMake = "Maruti Suzuki Dzire";
    }
    public String getVncID() {
        return vncID;
    }

    public void setVncID(String vncID) {
        this.vncID = vncID;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    @Override
    public String toString() {
        return "VehicleNameCode{" +
                "vncID='" + vncID + '\'' +
                ", vehicleMake='" + vehicleMake + '\'' +
                '}';
    }

}
