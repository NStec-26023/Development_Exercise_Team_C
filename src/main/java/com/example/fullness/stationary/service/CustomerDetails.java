package com.example.fullness.stationary.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.fullness.stationary.entity.Customer;

public class CustomerDetails implements UserDetails {
    private final Customer customer;

    public CustomerDetails(Customer customer) {
        this.customer = customer;
    }

    public String getPassword() {
        return customer.getPassword();
    }

    public String getUsername() {
        return customer.getMailAddress();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 権限リストを返す（空）
        return new ArrayList<>();
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
