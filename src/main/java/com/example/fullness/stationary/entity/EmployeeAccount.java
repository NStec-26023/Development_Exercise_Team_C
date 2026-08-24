package com.example.fullness.stationary.entity;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
<<<<<<< HEAD
 * 社員アカウント情報を保持するエンティティクラスです。
=======
 * 社員アカウント（担当者）情報を保持するエンティティクラスです。
>>>>>>> 8791f08308d0b2f5168c6abfec57218a8fede49e
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
