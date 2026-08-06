package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Customer implements Serializable {

    private Integer id;
    private String name;
    private String nameKana;
    private String address1;
    private String address2;
    private String phoneNumber;
    private String mailAddress;
    private String UserName;
    private String password;
    private String reigisterDate;

    public String getDisplayName() {
        // TODO Auto-generated method stub
        this.getName();
        throw new UnsupportedOperationException("Unimplemented method 'getDisplayName'");
    }

}