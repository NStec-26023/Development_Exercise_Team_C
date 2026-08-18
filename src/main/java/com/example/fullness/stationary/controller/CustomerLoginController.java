package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.RequiredArgsConstructor;

@RequestMapping("/")
@Controller
// @RequiredArgsConstructor
// @SessionAttributes(names = { "Customer", "CustomerLoginForm" })
public class CustomerLoginController {

    /**
     * リクエスト毎に呼ばれる
     */
    // @ModelAttribute
    // public CustomerLoginForm setUpCustomerLoginForm() {
    // return new CustomerLoginForm();
    // }

    @GetMapping("login")
    public String showLogin() {
        return "login"; // templates/login.html などを指す
    }

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html を返す
    }
}
