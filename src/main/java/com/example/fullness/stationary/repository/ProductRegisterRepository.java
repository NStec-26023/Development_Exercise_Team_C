package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;

@Mapper
public interface ProductRegisterRepository {

    List<ProductCategory> findAllCategories();

    void insertProduct(Product product);

    void insertProductStock(ProductStock productStock);

}
