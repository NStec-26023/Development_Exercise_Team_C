package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Employee;
import com.example.fullness.stationary.entity.EmployeeAccount;

@Mapper
public interface EmployeeAccountRepository {
    //
    EmployeeAccount findByName(String name);

    // 未登録の社員を抽出
    List<Employee> selectUnregisteredEmployees();

    void insertAccount(EmployeeAccount employeeAccount);
}
