package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.EmployeeAccount;

@Mapper
public interface EmployeeAccountRepository {

    EmployeeAccount findByName(String name);
}
