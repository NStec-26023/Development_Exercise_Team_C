package com.example.fullness.stationary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
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
    private EmployeeAccountRepository employeeAccountRepository;
    @Autowired
    private EmployeeLoginAttemptService employeeLoginAttemptService;

    @Override
    public EmployeeDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 1. メモリ上でロックされているかチェック
        if (employeeLoginAttemptService.isBlocked(name)) {
            throw new LockedException("アカウントがロックされています。管理者にお問い合わせください。");
        }
        // 2. データベースから社員アカウントを検索
        EmployeeAccount employeeAccount = employeeAccountRepository.findByName(name);
        if (employeeAccount == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません");
        }
        // 3. 権限を取得して EmployeeDetails を返却
        Collection<GrantedAuthority> authorities = getAuthorities(employeeAccount);
        return new EmployeeDetails(employeeAccount, authorities);
    }

    private Collection<GrantedAuthority> getAuthorities(EmployeeAccount employeeAccount) {

        return AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
    }

}
