package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.entity.Employee;
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

    @Test
    public void testSelectUnregisteredEmployees() {
        List<Employee> actual = employeeAccountRepository.selectUnregisteredEmployees();

        List<Employee> expected = new ArrayList<>();

        Employee emp1 = new Employee();
        emp1.setEmpId(1001);
        emp1.setName("フルネス太郎");
        emp1.setKana("フルネスタロウ");
        emp1.setDeptId(101);
        expected.add(emp1);

        Employee emp2 = new Employee();
        emp2.setEmpId(1004);
        emp2.setName("高橋優太");
        emp2.setKana("タカハシユウタ");
        emp2.setDeptId(101);
        expected.add(emp2);

        assertEquals(expected, actual);
    }

    @Test
    public void testInsertAccount() {

        EmployeeAccount expected = new EmployeeAccount();
        expected.setEmpId(1001);
        expected.setName("fullness");
        expected.setPassword("fullness");

        EmployeeRegisterForm form = new EmployeeRegisterForm();
        form.setEmpId(1001);
        form.setAccountName("fullness");
        form.setPassword("fullness");
        employeeAccountRepository.insertAccount(form);
        EmployeeAccount actual = employeeAccountRepository.findByName("fullness");

        assertNotNull(actual.getAccId());// 自動採番
        assertEquals(expected.getEmpId(), actual.getEmpId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPassword(), actual.getPassword());
    }
}
