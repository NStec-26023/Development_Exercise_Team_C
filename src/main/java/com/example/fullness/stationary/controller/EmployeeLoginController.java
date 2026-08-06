package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.fullness.stationary.controller.form.EmployeeLoginForm;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
@SessionAttributes(names = { "EmployeeAccount", "EmployeeLoginForm" })
public class EmployeeLoginController {

    @ModelAttribute
    public EmployeeLoginForm setUpEmployeeLoginForm() {
        return new EmployeeLoginForm();
    }

    @GetMapping("login")
    public String showLogin() {
        return "admin/login"; // templates/admin/login.html
    }

    @GetMapping("")
    public String admin() {
        return "admin/menu";
    }
}
