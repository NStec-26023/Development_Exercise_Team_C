package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeAccount implements Serializable {

    private Integer acc_id; // アカウントID
    private Integer emp_id;
    private String name;// アカウント名、社員名ではない
    private String password;

}
