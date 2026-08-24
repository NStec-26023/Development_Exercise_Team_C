package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 社員情報を保持するエンティティクラスです。
 * 
 * @author 陳以勒
 */
@Data
public class Employee implements Serializable {
    /** 社員ID */
    private Integer empId;
    /** 社員名 */
    private String name;
    /** 社員名（カタカナ） */
    private String kana;
    /** 部署ID（外部キー） */
    private Integer deptId;

}
