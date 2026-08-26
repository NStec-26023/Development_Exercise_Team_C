package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;

//商品修正レポジトリ

@Mapper
public interface ProductModifyRepository {

    List<ProductCategory> selectAllProductCategory();

    ProductStock selectByProIdWithProduct(Integer proId);

    String selectCatNameByCatId(Integer catId);

    Integer updateProductStock(ProductStock productStock);

    Integer updateProduct(Product product);

}