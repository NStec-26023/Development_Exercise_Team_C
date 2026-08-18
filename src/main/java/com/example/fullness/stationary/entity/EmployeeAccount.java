package com.example.fullness.stationary.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeAccount implements Serializable {

    private Integer accId; // アカウントID

    private Integer empId;
    @NotBlank
    private String name;// アカウント名、社員名ではない
    @NotBlank
    private String password;

}
