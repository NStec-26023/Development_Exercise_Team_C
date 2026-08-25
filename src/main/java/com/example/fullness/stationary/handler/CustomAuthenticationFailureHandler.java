package com.example.fullness.stationary.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.fullness.stationary.service.EmployeeLoginAttemptService;

import java.io.IOException;

/**
 * 認証失敗時の処理をカスタム制御するハンドラクラスです。
 * ログイン失敗回数のカウントアップや、アカウントロック状態に応じたリダイレクト先の振り分けを行います。
 * 
 * @author 陳以勒
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /** ログイン試行回数およびブロック状態を管理するサービス */
    private final EmployeeLoginAttemptService loginAttemptService;

    /**
     * コンストラクタ。
     * 
     * @param employeeLoginAttemptService ログイン試行管理サービス
     */
    public CustomAuthenticationFailureHandler(EmployeeLoginAttemptService employeeLoginAttemptService) {
        this.loginAttemptService = employeeLoginAttemptService;
        setDefaultFailureUrl("/login?error=true");
    }

    /**
     * 認証が失敗した際に呼び出されるメソッドです。
     * 失敗回数の記録や、アカウントがロックされているかどうかの判定を行い、適切なエラーページへリダイレクトします。
     * 
     * @param request   HTTPサーブレットリクエスト
     * @param response  HTTPサーブレットレスポンス
     * @param exception 発生した認証例外
     * @throws IOException      入出力例外
     * @throws ServletException サーブレット例外
     */
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

        // ユーザーが現在ブロック（ロック）されている状態かどうかをサービス層で直接確認する
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
        // 判定結果に応じてリダイレクト先を分ける
        if (isLocked) {
            response.sendRedirect(request.getContextPath() + "/admin/login?locked");
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/login?error");
        }
    }
}