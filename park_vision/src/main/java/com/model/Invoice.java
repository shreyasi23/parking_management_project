package com.model;

import javax.persistence.*;



@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @Column(name = "id")
    private int invoiceID;
    @Column(name = "inv_date")
    private String invDate;
    @Column(name = "parking_id")
    private String parkingID;
    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "payment_method")
    private String paymentMethod;

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getParkingID() {
        return parkingID;
    }

    public void setParkingID(String parkingID) {
        this.parkingID = parkingID;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID=" + invoiceID +
                ", invDate='" + invDate + '\'' +
                ", parkingID='" + parkingID + '\'' +
                ", amountPaid=" + amountPaid +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
