package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parking_slot")
public class ParkingSlots {
    @Id
    @Column(name = "slot_no")
    private String slotNo;

    @Column(name = "vehicle_type")
    private int vehicleType;

    @Column(name = "is_empty")
    private boolean is_empty;


    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isIs_empty() {
        return is_empty;
    }

    public void setIs_empty(boolean is_empty) {
        this.is_empty = is_empty;
    }

    @Override
    public String toString() {
        return "ParkingSlots{" +
                "slotNo='" + slotNo + '\'' +
                ", vehicleType=" + vehicleType +
                ", is_empty=" + is_empty +
                '}';
    }
}
