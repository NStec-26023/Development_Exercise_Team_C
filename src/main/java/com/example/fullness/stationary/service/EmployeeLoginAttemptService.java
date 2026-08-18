package com.example.fullness.stationary.service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class EmployeeLoginAttemptService {

    private final ConcurrentHashMap<String, Integer> attemptsCashe = new ConcurrentHashMap<>();
    private static final int MAX_ATTEMPTS = 5;
    private final ConcurrentHashMap<String, LocalDateTime> lastAttemptCache = new ConcurrentHashMap<>();
    private static final int LOCK_TIME_MINUTES = 10;

    public void recordFailedAttempt(String employeeAcount) {
        int attempts = attemptsCashe.getOrDefault(employeeAcount, 0);
        attemptsCashe.put(employeeAcount, attempts + 1);
    }

    public void loginFailed(String username) {
        int attempts = attemptsCashe.getOrDefault(username, 0);
        attempts++;
        attemptsCashe.put(username, attempts);
        System.out.println("ログイン失敗: " + username + " (失敗回数: " + attempts + ")");
        lastAttemptCache.put(username, LocalDateTime.now());
    }

    // ログイン成功時に試行回数をクリア
    public void loginSucceeded(String username) {
        attemptsCashe.remove(username);
    }

    // アカウントがロックされているか判定
    public boolean isBlocked(String username) {
        Integer attempts = attemptsCashe.get(username);
        LocalDateTime lastAttempt = lastAttemptCache.get(username);
        if (attempts == null || attempts < MAX_ATTEMPTS) {
            return false;
        }

        // 最後の失敗から10分経過しているかチェック
        if (lastAttempt != null && lastAttempt.isBefore(LocalDateTime.now().minusMinutes(LOCK_TIME_MINUTES))) {
            // 10分経過していればロックを解除（キャッシュをクリア）
            loginSucceeded(username);
            return false;
        }

        return true;
    }
}
