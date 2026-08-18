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
        // フォームから送信されたユーザー名を取得
        String username = request.getParameter("username");

        // ログを出してみる
        System.out.println(">>> 認証失敗ハンドラーが呼ばれました");
        System.out.println(">>> 失敗したユーザー名: " + username);
        System.out.println(">>> 発生した例外: " + exception.getClass().getName() + " : " + exception.getMessage());

        if (username != null && !username.isBlank()) {
            loginAttemptService.loginFailed(username);
        }

        //
        // 2. ユーザーが現在ブロック（ロック）されている状態かどうかをサービス層で直接確認する！
        boolean isLocked = false;
        if (username != null && !username.isBlank()) {
            isLocked = loginAttemptService.isBlocked(username);
        }

        // または例外のチェインに LockedException が含まれているかもチェック
        if (!isLocked) {
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof LockedException
                        || (cause.getMessage() != null && cause.getMessage().contains("ロック"))) {
                    isLocked = true;
                    break;
                }
                cause = cause.getCause();
            }
        }
        // 3. 判定結果に応じてリダイレクト先を分ける
        if (isLocked) {
            response.sendRedirect(request.getContextPath() + "/admin/login?locked");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login?error");
        }
    }
}