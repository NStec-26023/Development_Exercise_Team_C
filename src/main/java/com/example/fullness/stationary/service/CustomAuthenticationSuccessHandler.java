package com.example.fullness.stationary.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final EmployeeLoginAttemptService loginAttemptService;

    public CustomAuthenticationSuccessHandler(EmployeeLoginAttemptService EmployeeLoginAttemptService) {
        this.loginAttemptService = EmployeeLoginAttemptService;
        setDefaultTargetUrl("/"); // ログイン成功後の遷移先
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        loginAttemptService.loginSucceeded(username);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}