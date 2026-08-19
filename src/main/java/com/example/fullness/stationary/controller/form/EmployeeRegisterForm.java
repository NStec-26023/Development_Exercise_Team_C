package com.example.fullness.stationary.controller.form;

import lombok.Data;

@Data
public class EmployeeRegisterForm {
    private String employeeName; // 選択された社員名（外部キー）
    private String name; // 入力されたアカウント名
    private String password; // 入力されたパスワード
}
