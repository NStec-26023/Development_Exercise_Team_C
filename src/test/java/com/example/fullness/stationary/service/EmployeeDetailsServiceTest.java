package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeDetailsServiceTest {

    @Mock
    private EmployeeAccountRepository employeeAccountRepository;

    @Mock
    private EmployeeLoginAttemptService employeeLoginAttemptService;

    @InjectMocks
    private EmployeeDetailsService employeeDetailsService;

    @Test
    // アカウント名からEmployeeDetailsを正しく取得
    void testLoadUserByUsernameSuccess() {

        String accountName = "testuser";
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setName(accountName);
        employeeAccount.setPassword("encodedPassword");
        when(employeeLoginAttemptService.isBlocked(accountName)).thenReturn(false);
        when(employeeAccountRepository.findByName(accountName)).thenReturn(employeeAccount);

        EmployeeDetails employeeDetails = (EmployeeDetails) employeeDetailsService.loadUserByUsername(accountName);

        assertNotNull(employeeDetails, "EmployeeDetailsが返却された");
        assertEquals(accountName, employeeDetails.getUsername(), "ユーザー名が一致している");
        assertEquals("encodedPassword", employeeDetails.getPassword(), "パスワードが一致している");
    }
}

// @Test
// // 担当者取得時にROLE_EMPLOYEE権限が付与されていること
// void testGetAuthorities() {
// // Arrange
// String accountName = "testuser";
// EmployeeAccount mockAccount = new EmployeeAccount();
// when(employeeAccountRepository.findByName(accountName)).thenReturn(mockAccount);
// UserDetails userDetails =
// employeeDetailsService.loadUserByUsername(accountName);

// List<GrantedAuthority> expected =
// AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
// List<GrantedAuthority> actual = employeeDetailsService.ge

// assertNotNull(userDetails);
// boolean hasEmployeeRole = userDetails.getAuthorities().stream()
// .anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"));
// assertTrue(hasEmployeeRole, "権限に ROLE_EMPLOYEE が含まれていません");
// }