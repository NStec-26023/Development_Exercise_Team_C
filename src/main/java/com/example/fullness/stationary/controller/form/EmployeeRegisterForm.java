package com.example.fullness.stationary.controller.form;

import lombok.Data;

@Data
public class EmployeeRegisterForm {
    private Integer accId; // 自動採番アカウントID
    private Integer empId; // 選択された社員の社員ID
    private String empName; // 選択された社員名
    private String accountName; // 入力されたアカウント名
    private String password; // 入力されたパスワード
}
