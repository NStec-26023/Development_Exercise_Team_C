package com.example.fullness.stationary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Customer;
import com.example.fullness.stationary.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByMailAddress(mailAddress);
        if (customer == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません");
        }
        Collection<GrantedAuthority> authorites = getAuthorities(customer);
        return new CustomerDetails(customer, authorites);
    }

    private Collection<GrantedAuthority> getAuthorities(Customer customer) {
        // 例として全員に "ROLE_CUSTOMER" を付与する場合
        return AuthorityUtils.createAuthorityList("ROLE_CUSTOMER");
    }
}
