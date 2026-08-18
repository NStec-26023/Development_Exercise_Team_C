package com.example.fullness.stationary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.controller.form.EmployeeLoginForm;
import com.example.fullness.stationary.entity.EmployeeAccount;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

// import com.example.fullness.stationary.controller.form.EmployeeLoginForm;

@RequestMapping("/admin")
@Controller
public class EmployeeLoginController {

    @ModelAttribute("form")
    public EmployeeLoginForm setUpEmployeeLoginForm() {
        return new EmployeeLoginForm();
    }

    @GetMapping("login")
    public String showLogin(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new EmployeeLoginForm());
        }
        return "admin/login"; // templates/admin/login.html
    }

    @GetMapping("")
    public String adminMenu() {
        return "admin/menu";
    }

    // 08/18午後に追加
    @PostMapping("login")
    public String login(@Valid @ModelAttribute("form") EmployeeLoginForm employeeLoginForm, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        // 入力チェック
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> errorMessages.add(e.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errorMessage", String.join(" ", errorMessages));
            redirectAttributes.addFlashAttribute("form", employeeLoginForm);

            // ログイン画面にリダイレクト
            return "redirect:/admin/login";
        }

        // バリデーション通過 → Spring Security認証エンドポイントにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login-process");
        dispatcher.forward(request, response);
        return null;
    }

}
