package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.EmployeeAccount;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeAccountRepositoryTest {
    @Autowired
    EmployeeAccountRepository employeeAccountRepository;

    @Test
    // アカウント名で正しいアカウント名とパスワードが取得されること
    public void testFindByName() {

        EmployeeAccount employeeAccountExpected = new EmployeeAccount();
        employeeAccountExpected.setAccId(2);
        employeeAccountExpected.setName("dog1234");
        employeeAccountExpected.setPassword("$2a$12$JKzJsvhJFrDlxaB8mSY.EeaTThHGrF0uwZuNoNr6EhMrvgoyXit3e");
        employeeAccountExpected.setEmpId(1003);
        EmployeeAccount employeeAccountActual = employeeAccountRepository.findByName("dog1234");
        assertEquals(employeeAccountExpected, employeeAccountActual);
    }
}