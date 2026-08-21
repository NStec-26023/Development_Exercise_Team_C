package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeLoginAttemptServiceTest {
    private EmployeeLoginAttemptService employeeLoginAttemptService;
    private String accountName = "testuser";

    @BeforeEach
    void setUp() {
        employeeLoginAttemptService = new EmployeeLoginAttemptService();
    }

    @Test
    // ログイン失敗処理、回数が正しく記録される
    void testLoginFailed() {
        employeeLoginAttemptService.loginFailed(accountName);
        employeeLoginAttemptService.loginFailed(accountName);
        assertEquals(2, employeeLoginAttemptService.getFailedAttempts(accountName), "失敗回数が2回");

    }

    @Test
    // 成功時に失敗回数がクリアされる
    void testLoginSecceeded() {
        // 2回連続で失敗させる
        employeeLoginAttemptService.loginFailed(accountName);
        employeeLoginAttemptService.loginFailed(accountName);
        // ログイン成功処理を実行
        employeeLoginAttemptService.loginSucceeded(accountName);
        // 失敗回数が0に戻っていること
        assertEquals(0, employeeLoginAttemptService.getFailedAttempts(accountName), "失敗回数がクリアされていること");
    }

    @Test
    void testIsBlocked() {
        // 5回連続で失敗させる
        for (int i = 0; i < 5; i++) {
            employeeLoginAttemptService.loginFailed(accountName);
        }
        // ブロック状態がtrueになること
        assertTrue(employeeLoginAttemptService.isBlocked(accountName), "5回失敗でアカウントがブロック");
    }

    @Test
    void testGetFailedAttempts() {
        employeeLoginAttemptService.loginFailed(accountName);
        employeeLoginAttemptService.loginFailed(accountName);
        // 取得した失敗回数が2であること
        assertEquals(2, employeeLoginAttemptService.getFailedAttempts(accountName), "現在の失敗回数が正しく取得できること");
    }
}