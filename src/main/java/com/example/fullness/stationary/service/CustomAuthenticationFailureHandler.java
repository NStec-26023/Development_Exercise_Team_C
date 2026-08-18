package com.example.fullness.stationary.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

//認証失敗ハンドラー
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final EmployeeLoginAttemptService loginAttemptService;

    public CustomAuthenticationFailureHandler(EmployeeLoginAttemptService employeeLoginAttemptService) {
        this.loginAttemptService = employeeLoginAttemptService;
        setDefaultFailureUrl("/login?error=true");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // フォームから送信されたユーザー名（社員IDやメールアドレスなど）を取得
        String username = request.getParameter("username");

        // ★ ここにログを出してみる
        System.out.println(">>> 認証失敗ハンドラーが呼ばれました！");
        System.out.println(">>> 失敗したユーザー名: " + username);
        //

        if (username != null && !username.isBlank()) {
            loginAttemptService.loginFailed(username);
        }

        // ロック例外による失敗か、通常のパスワード間違いかでリダイレクト先（パラメータ）を分ける
        if (exception instanceof LockedException) {
            response.sendRedirect(request.getContextPath() + "/admin/login?locked");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login?error");

        }
        // super.onAuthenticationFailure(request, response, exception);
    }
}