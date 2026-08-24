package com.example.fullness.stationary.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.fullness.stationary.service.EmployeeLoginAttemptService;

import java.io.IOException;

/**
 * 認証成功時の処理をカスタム制御するハンドラクラスです。
 * ログイン成功時に試行回数（失敗カウント）をクリアし、指定されたターゲットURLや元のリクエスト先へ遷移させます。
 * 
 * @author 陳以勒
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /** ログイン試行回数およびブロック状態を管理するサービス */
    private final EmployeeLoginAttemptService loginAttemptService;

    /**
     * コンストラクタ。
     * 
     * @param EmployeeLoginAttemptService ログイン試行管理サービス
     */
    public CustomAuthenticationSuccessHandler(EmployeeLoginAttemptService employeeLoginAttemptService) {
        this.loginAttemptService = employeeLoginAttemptService;
        setDefaultTargetUrl("/"); // ログイン成功後の遷移先
    }

    /**
     * 認証が成功した際に呼び出されるメソッドです。
     * ログインに成功したユーザーの失敗回数をリセットし、スーパークラスの成功処理（リダイレクト等）を実行します。
     * 
     * @param request        HTTPサーブレットリクエスト
     * @param response       HTTPサーブレットレスポンス
     * @param authentication 認証成功後の認証オブジェクト
     * @throws IOException      入出力例外
     * @throws ServletException サーブレット例外
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        loginAttemptService.loginSucceeded(username);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}