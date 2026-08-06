package com.example.fullness.stationary.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.fullness.stationary.entity.Customer;

public class CustomerDetails implements UserDetails {
    private final Customer customer;
    private final Collection<GrantedAuthority> authorities;

    public CustomerDetails(Customer customer, Collection<GrantedAuthority> authorities) {
        this.customer = customer;
        this.authorities = authorities;
    }

    public String getPassword() {
        return customer.getPassword();
    }

    public String getUsername() {
        return customer.getMailAddress();
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
