package com.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parking_area_info")
public class ParkingAreaInfo {
    @Id
    @Column(name = "id")
    private String PAI_ID;

    @Column(name = "no_of_slots")
    private int noOfSolts;

    @Column(name = "base_price_2")
    private int basePrice2;

    @Column(name = "per_hr_charge_2")
    private int perHrCharge2;

    @Column(name = "base_price_4")
    private int basePrice4;

    @Column(name = "per_hr_charge_4")
    private int perHrCharge4;

    public String getPAI_ID() {
        return PAI_ID;
    }

    public void setPAI_ID(String PAI_ID) {
        this.PAI_ID = PAI_ID;
    }

    public int getNoOfSolts() {
        return noOfSolts;
    }

    public void setNoOfSolts(int noOfSolts) {
        this.noOfSolts = noOfSolts;
    }

    public int getBasePrice2() {
        return basePrice2;
    }

    public void setBasePrice2(int basePrice2) {
        this.basePrice2 = basePrice2;
    }

    public int getPerHrCharge2() {
        return perHrCharge2;
    }

    public void setPerHrCharge2(int perHrCharge2) {
        this.perHrCharge2 = perHrCharge2;
    }

    public int getBasePrice4() {
        return basePrice4;
    }

    public void setBasePrice4(int basePrice4) {
        this.basePrice4 = basePrice4;
    }

    public int getPerHrCharge4() {
        return perHrCharge4;
    }

    public void setPerHrCharge4(int perHrCharge4) {
        this.perHrCharge4 = perHrCharge4;
    }

    @Override
    public String toString() {
        return "ParkingAreaInfo{" +
                "PAI_ID='" + PAI_ID + '\'' +
                ", noOfSolts=" + noOfSolts +
                ", basePrice2=" + basePrice2 +
                ", perHrCharge2=" + perHrCharge2 +
                ", basePrice4=" + basePrice4 +
                ", perHrCharge4=" + perHrCharge4 +
                '}';
    }
}
