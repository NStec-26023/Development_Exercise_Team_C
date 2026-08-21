package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        // System.out.println("DEBUG: 社員ID = " + form.getEmpId());
        // System.out.println("DEBUG: 社員名 = " + form.getEmpName());
        // System.out.println("DEBUG: アカウント名 = " + form.getAccountName());

        // 1. 社員IDから名前を検索
        if (form.getEmpId() != null) {
            Employee employee = employeeRegisterService.selectByEmpId(form.getEmpId());
            if (employee != null) {
                // 名前をセット（セッションのformに反映される）
                form.setEmpName(employee.getName());
            }
        }
        return "admin/account/confirm";
    }

    // 3. 処理実行して完了画面へ
    @PostMapping("/confirm")
    public String handleComfirm(@ModelAttribute("form") EmployeeRegisterForm form,
            @RequestParam(value = "action", required = false) String action, // action パラメータを取得
            Model model, SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {

        if ("back".equals(action)) {
            // 戻るボタンの処理
            model.addAttribute("employees", employeeRegisterService.getUnregisteredEmployeeList());
            return "admin/account/form";
        }

        if ("register".equals(action)) {
            // 登録ボタンの処理
            employeeRegisterService.registerAccount(form);

            redirectAttributes.addFlashAttribute("registeredEmpName", form.getEmpName());
            redirectAttributes.addFlashAttribute("registeredAccountName", form.getAccountName());

            sessionStatus.setComplete();
            return "redirect:/admin/account/complete";

        }

        return "admin/account/confirm";

    }

    // 4. 完了画面
    @GetMapping("/complete")
    public String showComplete() {
        return "admin/account/complete";
    }
}
