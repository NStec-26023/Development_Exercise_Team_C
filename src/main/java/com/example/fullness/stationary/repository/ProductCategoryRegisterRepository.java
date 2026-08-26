package com.example.fullness.stationary.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.fullness.stationary.entity.ProductCategory;

@Mapper
public interface ProductCategoryRegisterRepository {
    //商品カテゴリ名登録
    void insertProductCategory(ProductCategory productCategory); // オブジェクト型（Entityの中身）を受け取るようにする

    //商品カテゴリ名重複チェック
    ProductCategory findByName(String name);

}
