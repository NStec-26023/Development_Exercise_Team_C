package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.service.EmployeeRegisterService;

@Controller
@RequestMapping("/admin/account")
public class EmployeeAccountController {

    @Autowired
    private EmployeeRegisterService employeeRegisterService;

    // 登録画面の表示
    @GetMapping("/form")
    public String showRegisterForm(Model model) {
        model.addAttribute("employeeRegisterForm", new EmployeeRegisterForm());
        model.addAttribute("unRegisteredEmployeeList", employeeRegisterService.getUnregisteredEmployeeList());
        return "admin/account/register";
    }

    // 登録処理の実行
    @PostMapping("/register")
    public String register(@ModelAttribute EmployeeRegisterForm employeeRegisterForm) {
        employeeRegisterService.registerAccount(employeeRegisterForm);
        return "redirect:/employee/account/register?success";
    }
}
