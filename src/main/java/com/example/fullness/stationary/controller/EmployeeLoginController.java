package com.example.fullness.stationary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import com.example.fullness.stationary.controller.form.EmployeeLoginForm;

@RequestMapping("/admin")
@Controller
public class EmployeeLoginController {

    // @ModelAttribute
    // public EmployeeLoginForm setUpEmployeeLoginForm() {
    // return new EmployeeLoginForm();
    // }

    @GetMapping("login")
    public String showLogin() {
        return "admin/login"; // templates/admin/login.html
    }

    @GetMapping("")
    public String adminMenu() {
        return "admin/menu";
    }
}
