package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.EmployeeRegisterForm;
import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

import java.util.List;

@Service
public class EmployeeRegisterService {

    @Autowired
    private EmployeeAccountRepository employeeAccountRepository;

    // @Autowired(required = false)
    // private PasswordEncoder passwordEncoder;

    // 担当者未登録社員一覧の取得
    public List<Employee> getUnregisteredEmployeeList() {
        return employeeAccountRepository.selectUnregisteredEmployees();
    }

    // アカウントの登録処理
    @Transactional
    public void registerAccount(EmployeeRegisterForm employeeRegisterForm) {
        // パスワードのハッシュ化（Spring Security導入時）
        // if (passwordEncoder != null) {
        // form.setPassword(passwordEncoder.encode(form.getPassword()));
        // }

        employeeAccountRepository.insertAccount(employeeRegisterForm);
    }
}