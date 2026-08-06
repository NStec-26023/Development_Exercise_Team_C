package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

@Service
public class EmployeeRegisterService {
    @Autowired
    private EmployeeAccountRepository employeeAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 担当者未登録の社員を取得
    public List<Employee> getUnregisteredEmployees() {
        return employeeAccountRepository.selectUnregisteredEmployees();
    }

    // 担当者アカウント登録
    @Transactional
    public void registerAccount(EmployeeRegisterForm employeeRegisterForm) {
        EmployeeAccount employeeAccount = new EmployeeAccount();
        employeeAccount.setName(employeeRegisterForm.getEmpName());
        employeeAccount.setPassword(passwordEncoder.encode(employeeRegisterForm.getPassword()));

        employeeAccountRepository.insertAccount(employeeAccount);
    }

}
