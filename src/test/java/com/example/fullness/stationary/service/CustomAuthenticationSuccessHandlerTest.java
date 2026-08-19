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
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class CustomAuthenticationSuccessHandlerTest {

    @Mock
    private EmployeeLoginAttemptService employeeLoginAttemptService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CustomAuthenticationSuccessHandler successHandler;

    @Test
    @DisplayName("認証成功時の正常系：ログイン成功サービスが呼び出され失敗回数がリセットされること")
    void testOnAuthenticationSuccess() {
        // Arrange
        String username = "testuser";
        when(authentication.getName()).thenReturn(username);

        // Act
        try {
            successHandler.onAuthenticationSuccess(request, response, authentication);
        } catch (Exception ignored) {
            // スーパークラスの内部挙動による例外をキャッチ
        }

        // Assert
        verify(employeeLoginAttemptService, times(1)).loginSucceeded(username);
    }
}