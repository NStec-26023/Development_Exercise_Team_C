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

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

/**
 * 社員（管理者）のログイン画面表示、入力検証、および管理メニューへの遷移を制御するコントローラークラスです。
 *
 * @author 陳以勒
 */
@RequestMapping("/admin")
@Controller
public class EmployeeLoginController {

    /**
     * ログインフォームのインスタンスを初期化し、モデルの属性として登録します。
     *
     * @return ログイン用フォームオブジェクト
     */
    @ModelAttribute("form")
    public EmployeeLoginForm setUpEmployeeLoginForm() {
        return new EmployeeLoginForm();
    }

    /**
     * ログイン画面を表示します。
     *
     * @param model モデル
     * @return ログイン画面のテンプレートパス ("admin/login")
     */
    @GetMapping("login")
    public String showLogin(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new EmployeeLoginForm());
        }
        return "admin/login"; // templates/admin/login.html
    }

    /**
     * 管理メニュー画面を表示します。
     *
     * @return 管理メニューのテンプレートパス ("admin/menu")
     */
    @GetMapping("")
    public String adminMenu() {
        return "admin/menu";
    }

    /**
     * ログイン処理を実行します。
     * 入力値の検証を行い、エラーがある場合はエラーメッセージを設定してログイン画面へリダイレクトします。
     * 検証を通過した場合は、Spring Securityの認証エンドポイントへフォワードします。
     *
     * @param employeeLoginForm  ログインフォームの入力値
     * @param bindingResult      バリデーション結果
     * @param redirectAttributes リダイレクト時に渡すフラッシュ属性
     * @param request            HTTPサーブレットリクエスト
     * @param response           HTTPサーブレットレスポンス
     * @return リダイレクト先、またはフォワード処理時は null
     * @throws Exception フォワード処理などで発生する例外
     */
    @PostMapping("login")
    public String login(@Valid @ModelAttribute("form") EmployeeLoginForm employeeLoginForm, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> errorMessages.add(e.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errorMessage", String.join(" ",
                    errorMessages));
            redirectAttributes.addFlashAttribute("form", employeeLoginForm);

            return "redirect:/admin/login";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login-process");
        dispatcher.forward(request, response);
        return null;
    }

}
