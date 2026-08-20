package com.example.fullness.stationary.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 社員アカウント（担当者）情報を保持するエンティティクラスです。
 * 
 * @author 陳以勒
 */
@Data
public class EmployeeAccount implements Serializable {
    /** アカウントID */
    private Integer accId;
    /** 社員ID */
    private Integer empId;
    /** アカウント名 */
    @NotBlank
    private String name;
    /** パスワード */
    @NotBlank
    private String password;

}
