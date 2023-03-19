package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersLoginCreds {
    @Id
    @Column(name = "id")
    private String userID;

    @Column(name = "username")
    private String uName;

    @Column(name = "pswd")
    private String password;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "userID='" + userID + '\'' +
                ", uName='" + uName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
