package com.example.fullness.stationary.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeLoginForm {
    @NotBlank(message = "アカウント名を入力してください")
    private String username;
    @NotBlank(message = "パスワードを入力してください")
    private String password;
}
