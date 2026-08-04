package com.example.fullness.stationary.controller.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerLoginForm implements Serializable {
    // ToDo:バリデーション
    private String mailAddress;
    private String password;

}
