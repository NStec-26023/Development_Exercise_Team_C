package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeAccount implements Serializable {

    private Integer accId; // アカウントID
    private Integer empId;
    private String name;// アカウント名、社員名ではない
    private String password;

}
