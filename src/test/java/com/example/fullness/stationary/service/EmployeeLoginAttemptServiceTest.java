package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeLoginAttemptServiceTest {

    private EmployeeLoginAttemptService loginAttemptService;

    @BeforeEach
    void setUp() {
        loginAttemptService = new EmployeeLoginAttemptService();
    }

    @Test
    @DisplayName("ログイン失敗回数が規定回数（5回）に達した場合にブロックされることの正常系")
    void testBlockedAtMaxAttempts() {
        String username = "testuser";

        // 5回連続でログイン失敗を記録
        for (int i = 0; i < 5; i++) {
            loginAttemptService.loginFailed(username);
        }

        // アカウントがロック（ブロック）されていること
        assertTrue(loginAttemptService.isBlocked(username));
    }
}