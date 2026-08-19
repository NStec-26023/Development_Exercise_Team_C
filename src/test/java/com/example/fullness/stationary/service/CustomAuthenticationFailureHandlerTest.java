package com.example.fullness.stationary.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class CustomAuthenticationFailureHandlerTest {

    @Mock
    private EmployeeLoginAttemptService employeeLoginAttemptService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException exception;

    @InjectMocks
    private CustomAuthenticationFailureHandler failureHandler;

    @Test
    @DisplayName("認証失敗時の正常系：ログイン失敗回数が記録されること")
    void testOnAuthenticationFailure() {
        // Arrange
        String username = "testuser";
        when(request.getParameter("username")).thenReturn(username);
        when(employeeLoginAttemptService.isBlocked(username)).thenReturn(false);

        // Act
        try {
            failureHandler.onAuthenticationFailure(request, response, exception);
        } catch (Exception ignored) {
            // レスポンスリダイレクトによる例外をキャッチ
        }

        // Assert
        verify(employeeLoginAttemptService, times(1)).loginFailed(username);
    }
}