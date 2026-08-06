package com.example.fullness.stationary.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.fullness.stationary.entity.EmployeeAccount;

public class EmployeeDetails implements UserDetails {
    private final EmployeeAccount employeeAccount;
    private final Collection<GrantedAuthority> authorities;

    public EmployeeDetails(EmployeeAccount employeeAccount, Collection<GrantedAuthority> authorities) {
        this.employeeAccount = employeeAccount;
        this.authorities = authorities;
    }

    public String getPassword() {
        return employeeAccount.getPassword();
    }

    public String getUsername() {
        return employeeAccount.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 権限リストを返す（空）
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // アカウントが期限切れでない（trueで固定）
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // アカウントがロックされていない（trueで固定）
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // パスワードが期限切れでない（trueで固定）
    }

    @Override
    public boolean isEnabled() {
        return true; // アカウントが有効である（trueで固定）
    }
}
