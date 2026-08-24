package com.example.fullness.stationary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
    public String showRegisterForm(@ModelAttribute("form") EmployeeRegisterForm form, SessionStatus sessionStatus,
            Model model) {
        if (!model.containsAttribute("errorMessages")) {
            sessionStatus.setComplete();
            model.addAttribute("form", new EmployeeRegisterForm());
        }
        model.addAttribute("employees", employeeRegisterService.getUnregisteredEmployeeList());
        return "admin/account/form";
    }

    @PostMapping("/form")
    public String confirm(@Validated @ModelAttribute("form") EmployeeRegisterForm form,
            BindingResult bindingResult, Model model) {
        // 1. 社員IDから名前を検索
        if (form.getEmpId() != null) {
            Employee employee = employeeRegisterService.selectByEmpId(form.getEmpId());
            if (employee != null) {
                // 名前をセット（セッションのformに反映される）
                form.setEmpName(employee.getName());
            }
        }
        // バリデーション
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("employees", employeeRegisterService.getUnregisteredEmployeeList());
            return "admin/account/form";
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
    public String showComplete(Model model) {
        if (!model.containsAttribute("registeredEmpName")) {
            // セッションが切れてデータが消えた場合や、直接URLを入力された場合はFP001へ
            return "admin/menu";
        }
        return "admin/account/complete";
    }
}