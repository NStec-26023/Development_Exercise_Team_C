package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.service.EmployeeRegisterService;

@Controller
@RequestMapping("/admin/account")
@SessionAttributes("form")
public class EmployeeAccountController {

    @Autowired
    private EmployeeRegisterService employeeRegisterService;

    @ModelAttribute("form")
    public EmployeeRegisterForm setUpForm() {
        return new EmployeeRegisterForm();
    }

    // 登録画面の表示
    @GetMapping("/form")
    public String showRegisterForm(@ModelAttribute("form") EmployeeRegisterForm form, Model model) {
        model.addAttribute("employees", employeeRegisterService.getUnregisteredEmployeeList());
        return "admin/account/form";
    }

    @PostMapping("/form")
    public String confirm(@ModelAttribute("form") EmployeeRegisterForm form) {

        System.out.println("DEBUG: 社員ID = " + form.getEmpId());
        System.out.println("DEBUG: 社員名 = " + form.getEmpName());
        System.out.println("DEBUG: アカウント名 = " + form.getAccountName());
        // Employee employee = employeeRegisterService.selectByEmpId(form.getEmpId());
        // form.setEmpName(employee.getName());
        // 1. 社員IDから名前を検索
        if (form.getEmpId() != null) {
            Employee employee = employeeRegisterService.selectByEmpId(form.getEmpId());
            if (employee != null) {
                // 名前をセット（これがセッションのformに反映される）
                form.setEmpName(employee.getName());
            }
        }
        return "admin/account/confirm";
    }

    // 3. 処理実行して完了画面へ
    @PostMapping("/confirm")
    public String complete(@ModelAttribute("form") EmployeeRegisterForm form, SessionStatus sessionStatus) {
        employeeRegisterService.registerAccount(form);
        sessionStatus.setComplete(); // セッション破棄
        return "redirect:/admin/account/complete";
    }

    // 4. 完了画面
    @GetMapping("/complete")
    public String showComplete() {
        return "admin/account/complete";
    }
}
