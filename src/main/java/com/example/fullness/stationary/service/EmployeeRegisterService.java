package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;
import com.example.fullness.stationary.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeRegisterService {

    @Autowired
    private EmployeeAccountRepository employeeAccountRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    // 担当者未登録社員一覧の取得
    public List<Employee> getUnregisteredEmployeeList() {
        return employeeAccountRepository.selectUnregisteredEmployees();
    }

    // 社員IDで社員情報一件取得
    public Employee selectByEmpId(Integer empId) {
        return employeeRepository.findByEmpId(empId);

    }

    // アカウントの登録処理
    @Transactional
    public void registerAccount(EmployeeRegisterForm employeeRegisterForm) {
        // パスワードのハッシュ化（Spring Security導入時）
        if (passwordEncoder != null) {
            employeeRegisterForm.setPassword(passwordEncoder.encode(employeeRegisterForm.getPassword()));
        }
        employeeAccountRepository.insertAccount(employeeRegisterForm);
    }
}