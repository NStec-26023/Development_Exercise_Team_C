package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRegisterRepository {

    List<ProductRegisterRepository> selectAll();

}
