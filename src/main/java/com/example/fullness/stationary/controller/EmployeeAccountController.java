package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

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
        return "admin/account/form";
    }

    @PostMapping("/form")
    public String confirm(@ModelAttribute EmployeeRegisterForm employeeRegisterForm) {
        return "employee/account/confirm";
    }

    // 3. 処理実行して完了画面へ
    @PostMapping("/confirm")
    public String complete(@ModelAttribute EmployeeRegisterForm employeeRegisterForm, SessionStatus sessionStatus) {
        employeeRegisterService.registerAccount(employeeRegisterForm);
        sessionStatus.setComplete(); // セッションを破棄
        return "redirect:/admin/account/complete";
    }

    // 4. 完了画面
    @GetMapping("/complete")
    public String showComplete() {
        return "admin/account/complete";
    }
}
