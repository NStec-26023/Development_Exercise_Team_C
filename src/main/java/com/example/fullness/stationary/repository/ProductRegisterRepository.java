package com.example.fullness.stationary.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;

@Mapper
public interface ProductRegisterRepository {

    // カテゴリ情報の取得
    List<ProductCategory> findAllCategories();

    // 商品情報登録
    void insertProduct(Product product);

    //在庫情報の取得
    List<ProductStock> findAllPuroductStock();

    // 在庫情報登録
    void insertProductStock(ProductStock productStock);

}
