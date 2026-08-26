package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Employee;

@Mapper
public interface EmployeeRepository {
    Employee findByEmpId(Integer empId);// 08/19
}
