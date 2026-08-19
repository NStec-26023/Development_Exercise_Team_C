package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @DisplayName("ユーザー名による詳細情報ロードの正常系：アカウントが取得できUserDetailsが返却されること")
    void testLoadUserByUsernameSuccess() {
        // Arrange
        String username = "testuser";
        EmployeeAccount account = new EmployeeAccount();
        account.setName(username);
        account.setPassword("encodedPassword");

        when(employeeLoginAttemptService.isBlocked(username)).thenReturn(false);
        when(employeeAccountRepository.findByName(username)).thenReturn(account);

        // Act
        EmployeeDetails userDetails = (EmployeeDetails) employeeDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
    }
}