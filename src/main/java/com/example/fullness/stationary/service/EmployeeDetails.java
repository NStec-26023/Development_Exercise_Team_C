package com.example.fullness.stationary.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.fullness.stationary.entity.EmployeeAccount;

/**
 * Spring Securityの認証・認可において社員アカウント情報を保持するUserDetailsの実装クラスです。
 * 
 * @author 陳以勒
 */
public class EmployeeDetails implements UserDetails {
    /** 社員アカウントエンティティ */
    private final EmployeeAccount employeeAccount;
    /** 付与された権限のコレクション */
    private final Collection<GrantedAuthority> authorities;

    /**
     * コンストラクタ。
     * 
     * @param employeeAccount 社員アカウントエンティティ
     * @param authorities     付与された権限のコレクション
     */
    public EmployeeDetails(EmployeeAccount employeeAccount, Collection<GrantedAuthority> authorities) {
        this.employeeAccount = employeeAccount;
        this.authorities = authorities;
    }

    /**
     * パスワードを取得します。
     * 
     * @return 暗号化されたパスワード
     */
    public String getPassword() {
        return employeeAccount.getPassword();
    }

    /**
     * アカウント名（ユーザー名）を取得します。
     * 
     * @return アカウント名
     */
    public String getUsername() {
        return employeeAccount.getName();
    }

    /**
     * ユーザーに付与されている権限のコレクションを取得します。
     * 
     * @return 権限のコレクション
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * アカウントが期限切れでないかどうかを返します。
     * 
     * @return 常に true（期限切れなし）
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * アカウントがロックされていないかどうかを返します。
     * 
     * @return 常に true（ロックはCustomAuthenticationFaliureHandlerに処理任せる）
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * パスワードが期限切れでないかどうかを返します。
     * 
     * @return 常に true（期限切れなし）
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * アカウントが有効であるかどうかを返します。
     * 
     * @return 常に true（有効）
     */
    public boolean isEnabled() {
        return true;
    }
}
