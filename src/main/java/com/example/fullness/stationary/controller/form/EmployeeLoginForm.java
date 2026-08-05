package com.example.fullness.stationary.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeLoginForm {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
