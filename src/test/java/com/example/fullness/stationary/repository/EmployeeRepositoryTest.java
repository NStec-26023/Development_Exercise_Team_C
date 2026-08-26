package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.Employee;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void findByEmpId() {
        Employee expect = new Employee();
        expect.setEmpId(1001);
        expect.setName("フルネス太郎");
        expect.setKana("フルネスタロウ");
        expect.setDeptId(101);
        Employee actual = employeeRepository.findByEmpId(1001);
        assertEquals(expect, actual);
    }
}
