package com.example.fullness.stationary.controller.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerLoginForm implements Serializable {
    // ToDo:バリデーション
    @NotNull
    @Email
    private String mailAddress;
    @NotNull
    @Size(min = 5, max = 20)
    private String password;

}
