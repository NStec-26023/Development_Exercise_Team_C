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

/**
 * Spring Securityの認証処理において、データベースから社員アカウント情報をロードするサービスです。
 * ログイン試行制限のブロック状態の確認や、アカウント情報に基づくUserDetailsの構築を行います。
 * 
 * @author 陳以勒
 */
@Service
@Transactional(readOnly = true)
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeAccountRepository employeeAccountRepository;
    @Autowired
    private EmployeeLoginAttemptService employeeLoginAttemptService;

    /**
     * アカウント名をキーにしてユーザー詳細情報をロードします。
     * ログイン試行がブロックされていないか確認した後、データベースからアカウントを検索し、認証用のUserDetailsオブジェクトを返却します。
     * 
     * @param name アカウント名
     * @return 社員の詳細情報オブジェクト（EmployeeDetails）
     * @throws UsernameNotFoundException ユーザーがデータベースに見つからない場合
     * @throws LockedException           アカウントがロック（ブロック）されている場合
     */
    @Override
    public EmployeeDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 1. メモリ上でロックされているかチェック
        if (employeeLoginAttemptService.isBlocked(name)) {
            throw new LockedException("アカウントがロックされています。管理者にお問い合わせください");
        }
        // 2. データベースから社員アカウントを検索
        EmployeeAccount employeeAccount = employeeAccountRepository.findByName(name);
        if (employeeAccount == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません");
        }
        // 3. 権限を取得して EmployeeDetails を返却
        Collection<GrantedAuthority> authorities = getAuthorities();
        return new EmployeeDetails(employeeAccount, authorities);
    }

    /**
     * 社員アカウントに付与する権限（ロール）のコレクションを取得します。
     * 
     * @return 権限のコレクション
     */
    private Collection<GrantedAuthority> getAuthorities() {

        return AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
    }

}
