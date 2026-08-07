package com.example.fullness.stationary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

@Service
@Transactional(readOnly = true)
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    EmployeeAccountRepository employeeAccountRepository;

    @Override
    public EmployeeDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        EmployeeAccount employeeAccount = employeeAccountRepository.findByName(name);
        if (employeeAccount == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません");
        }
        Collection<GrantedAuthority> authorities = getAuthorities(employeeAccount);
        return new EmployeeDetails(employeeAccount, authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(EmployeeAccount employeeAccount) {

        return AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
    }
}
