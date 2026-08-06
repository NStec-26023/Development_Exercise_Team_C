package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;

@Mapper
public interface ProductSearchRepository {

    List<Product>selectAll();

    List<ProductCategory>selectAllProductCategory();

    List<Product>selectByCatId(Integer catId);

}
