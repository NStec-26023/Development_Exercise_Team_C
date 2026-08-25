package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullness.stationary.entity.Employee;

@SpringBootTest
public class EmployeeRegisterServiceTest {

    @Autowired
    EmployeeRegisterService employeeRegisterService;

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
}
