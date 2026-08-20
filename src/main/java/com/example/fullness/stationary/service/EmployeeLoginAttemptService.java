package com.example.fullness.stationary.service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 * 社員のログイン試行回数およびアカウントロック状態を管理するサービスです。
 * 一定回数以上のログイン失敗を検知し、一定時間アカウントをロックする機能を提供します。
 * 
 * @author 陳以勒
 */
@Service
public class EmployeeLoginAttemptService {

    /** アカウントごとのログイン失敗回数を保持するキャッシュ */
    private final ConcurrentHashMap<String, Integer> attemptsCashe = new ConcurrentHashMap<>();
    /** アカウントロックとなる最大失敗回数 */
    private static final int MAX_ATTEMPTS = 5;
    /** アカウントごとの最後のログイン失敗時刻を保持するキャッシュ */
    private final ConcurrentHashMap<String, LocalDateTime> lastAttemptCache = new ConcurrentHashMap<>();
    /** アカウントのロック時間（分） */
    private static final int LOCK_TIME_MINUTES = 10;

    /**
     * 失敗試行を記録します（書き込み用）。
     * 
     * @param employeeAcount 社員アカウント名
     */
    public void recordFailedAttempt(String employeeAcount) {
        int attempts = attemptsCashe.getOrDefault(employeeAcount, 0);
        attemptsCashe.put(employeeAcount, attempts + 1);
    }

    /**
     * ログイン失敗時の処理を行います。
     * 失敗回数をインクリメントし、失敗時刻を記録します。
     * 
     * @param username アカウント名
     */
    public void loginFailed(String username) {
        int attempts = attemptsCashe.getOrDefault(username, 0);
        attempts++;
        attemptsCashe.put(username, attempts);
        System.out.println("ログイン失敗: " + username + " (失敗回数: " + attempts + ")");
        lastAttemptCache.put(username, LocalDateTime.now());
    }

    /**
     * ログイン成功時の処理を行います。
     * 失敗試行回数および最終失敗時刻のキャッシュをクリアします。
     * 
     * @param username アカウント名（ユーザー名）
     */
    public void loginSucceeded(String username) {
        attemptsCashe.remove(username);
    }

    /**
     * アカウントが現在ロックされているかどうかを判定します。
     * 最大失敗回数に達している場合でも、ロック時間（10分）が経過していれば自動的にロックを解除します。
     * 
     * @param username アカウント名（ユーザー名）
     * @return ロックされている場合は true、そうでない場合は false
     */
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

    /**
     * 指定されたアカウントの現在の失敗回数を取得する（テスト用、または画面表示用）
     */
    public int getFailedAttempts(String username) {
        return attemptsCashe.getOrDefault(username, 0);
    }
}
