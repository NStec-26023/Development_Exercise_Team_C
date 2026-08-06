package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.service.EmployeeRegisterService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/account")
public class EmployeeRegisterController {
    @Autowired
    private EmployeeRegisterService employeeRegisterService;

    @GetMapping("/form")
    public String showRegisterForm(Model model) {

        model.addAttribute("employeeRegisterForm", new EmployeeRegisterForm());
        model.addAttribute("unregisteredEmployees",
                employeeRegisterService.getUnregisteredEmployees());
        return "admin/account/form";
    }

    /**
     * 実際の登録処理 (POST /register/complete)
     * 確認画面の「登録する」ボタンから呼び出され、DB登録後に完了画面へリダイレクトします。
     */
    @PostMapping("/complete")
    public String registerAccount(
            @Valid @ModelAttribute("employeeRegisterForm") EmployeeRegisterForm employeeRegisterForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("unregisteredEmployees", employeeRegisterService.getUnregisteredEmployees());
            return "redirect:/admin/account/form";
        }
        employeeRegisterService.registerAccount(employeeRegisterForm);
        return "redirect:admin/account/complete";
    }

    /**
     * 2. 確認画面へ遷移 (POST /register/confirm)
     * 入力内容のバリデーション（アカウント名の重複チェック含む）を実行します。
     */
    @PostMapping("/confirm")
    public String showConfirm(
            @Validated @ModelAttribute("employeeRegisterForm") EmployeeRegisterForm employeeRegisterForm,
            BindingResult bindingResult, Model model) {

        // バリデーションエラー（重複チェック含む）がある場合は入力画面に戻す
        if (bindingResult.hasErrors()) {
            return "admin/account/form";
        }
        return "admin/account/confirm";
    }

}
