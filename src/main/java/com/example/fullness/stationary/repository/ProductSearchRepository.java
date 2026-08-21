package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;

//商品検索レポジトリ

@Mapper
public interface ProductSearchRepository {

    List<ProductCategory> selectAllProductCategory();

    List<Product> selectAll();

    List<Product> selectByCatId(Integer catId);

}
