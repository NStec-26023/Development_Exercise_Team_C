package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Employee implements Serializable {
    private Integer empId;
    private String name;// 社員名
    private String kana;
    private Integer deptId;

}
