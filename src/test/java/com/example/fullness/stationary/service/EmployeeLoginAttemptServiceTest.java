package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeLoginAttemptServiceTest {
    private EmployeeLoginAttemptService employeeLoginAttemptService;
    private String accountName = "testuser";

    @BeforeEach
    void setUp() {
        employeeLoginAttemptService = new EmployeeLoginAttemptService();
    }

    @SuppressWarnings("deprecation")
    @Test
    // 二回ログイン試行失敗させて、ログイン失敗回数が2になること
    void testRecordFailedAttempts() {
        employeeLoginAttemptService.recordFailedAttempt("testuser");
        employeeLoginAttemptService.recordFailedAttempt("testuser");
        // 失敗回数が0から2に増やす
        assertEquals(2, employeeLoginAttemptService.getFailedAttempts(accountName));
    }

    @Test
    // ログイン失敗処理、回数が正しく記録される
    void testLoginFailed() {
        employeeLoginAttemptService.loginFailed(accountName);
        assertEquals(1, employeeLoginAttemptService.getFailedAttempts(accountName), "失敗回数が1回");
    }

    @Test
    // 成功時に失敗回数がクリアされブロックが解除
    void testLoginSecceeded() {
        // 一度5回失敗させてブロック状態にする
        for (int i = 0; i < 5; i++) {
            employeeLoginAttemptService.loginFailed(accountName);
        }
        assertTrue(employeeLoginAttemptService.isBlocked(accountName));
        // ログイン成功処理を実行
        employeeLoginAttemptService.loginSucceeded(accountName);
        // ブロックが解除され、失敗回数が0に戻っていること
        assertFalse(employeeLoginAttemptService.isBlocked(accountName), "ログイン成功後にブロックが解除されること");
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