package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "vehicle_type_code")
public class VehicleTypeCode {

    @Id
    @Column(name = "id")
    private int vtc_id;

    @Column(name = "vehicle_type")
    private String vehicle_Type;

    public int getVtc_id() {
        return vtc_id;
    }

    public void setVtc_id(int vtc_id) {
        this.vtc_id = vtc_id;
    }

    public String getVehicle_Type() {
        return vehicle_Type;
    }

    public void setVehicle_Type(String vehicle_Type) {
        this.vehicle_Type = vehicle_Type;
    }

    @Override
    public String toString() {
        return "vehicle_type_code{" +
                "vtc_id=" + vtc_id +
                ", vehicle_Type='" + vehicle_Type + '\'' +
                '}';
    }
}
