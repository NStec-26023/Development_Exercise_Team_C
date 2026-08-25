package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

@SpringBootTest
public class EmployeeRegisterServiceTest {

    @Autowired
    EmployeeRegisterService employeeRegisterService;

    @Autowired
    EmployeeAccountRepository employeeAccountRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    /*
     * GetUnregisteredEmployeeList のテスト
     */
    @Test
    public void TestGetUnregisteredEmployeeList() {

        List<Employee> actual = employeeRegisterService.getUnregisteredEmployeeList();

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
    public void testSelectByEmpId() {
        Employee expect = new Employee();
        expect.setEmpId(1001);
        expect.setName("フルネス太郎");
        expect.setKana("フルネスタロウ");
        expect.setDeptId(101);

        Employee actual = employeeRegisterService.selectByEmpId(1001);

        assertEquals(expect, actual);

    }

    @Test
    @Transactional
    public void testRegisterAccount() {
        String hashedPassword = passwordEncoder.encode("fullness");
        when(passwordEncoder.encode(anyString())).thenReturn(hashedPassword);

        EmployeeAccount expected = new EmployeeAccount();
        expected.setAccId(3);
        expected.setEmpId(1001);
        expected.setName("fullness");
        expected.setPassword(hashedPassword);

        EmployeeRegisterForm form = new EmployeeRegisterForm();
        form.setEmpId(1001);
        form.setAccountName("fullness");
        form.setPassword("fullness");
        employeeRegisterService.registerAccount(form);
        EmployeeAccount actual = employeeAccountRepository.findByName("fullness");

        assertEquals(expected.getAccId(), actual.getAccId());// 自動採番
        assertEquals(expected.getEmpId(), actual.getEmpId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPassword(), actual.getPassword());

        // assertTrue(passwordEncoder.matches("fullness", actual.getPassword()),
        // "パスワードが正しくハッシュ化されていません");
    }
}
