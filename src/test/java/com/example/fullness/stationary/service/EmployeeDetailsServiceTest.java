package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
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
    void testLoadUserByUsernameSuccess() {

        String accountName = "dog1234";
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");

        EmployeeAccount employeeAccountExpected = new EmployeeAccount();
        employeeAccountExpected.setAccId(2);
        employeeAccountExpected.setName(accountName);
        employeeAccountExpected.setPassword("$2a$12$JKzJsvhJFrDlxaB8mSY.EeaTThHGrF0uwZuNoNr6EhMrvgoyXit3e");
        employeeAccountExpected.setEmpId(1003);
        EmployeeDetails employeeDetailsExpected = new EmployeeDetails(employeeAccountExpected, authorities);
        when(employeeLoginAttemptService.isBlocked(accountName)).thenReturn(false);
        when(employeeAccountRepository.findByName(accountName)).thenReturn(employeeAccountExpected);
        EmployeeDetails employeeDetailsActual = employeeDetailsService.loadUserByUsername("dog1234");

        assertThat(employeeDetailsActual)
                .usingRecursiveComparison()
                .isEqualTo(employeeDetailsExpected);

        assertEquals(employeeDetailsExpected.getAuthorities(), employeeDetailsActual.getAuthorities());
    }
    // @Test
    // アカウント名からEmployeeDetailsを正しく取得
    // void testLoadUserByUsernameSuccess() {

    // String accountName = "testuser";
    // EmployeeAccount employeeAccount = new EmployeeAccount();
    // employeeAccount.setName(accountName);
    // employeeAccount.setPassword("encodedPassword");
    // when(employeeLoginAttemptService.isBlocked(accountName)).thenReturn(false);
    // when(employeeAccountRepository.findByName(accountName)).thenReturn(employeeAccount);

    // EmployeeDetails employeeDetails = (EmployeeDetails)
    // employeeDetailsService.loadUserByUsername(accountName);

    // assertNotNull(employeeDetails, "EmployeeDetailsが返却された");
    // assertEquals(accountName, employeeDetails.getUsername(), "アカウント名が一致している");
    // assertEquals("encodedPassword", employeeDetails.getPassword(),
    // "パスワードが一致している");
    // }
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