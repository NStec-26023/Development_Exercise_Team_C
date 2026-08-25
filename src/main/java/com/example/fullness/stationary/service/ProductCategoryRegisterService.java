package com.example.fullness.stationary.service;

import com.example.fullness.stationary.entity.ProductCategory;

public interface ProductCategoryRegisterService {

    /** 商品カテゴリ登録 */
    void registerProductCategory(ProductCategory productCategory);

    /* 商品カテゴリ重複チェック */
    boolean existProductCategory(String name);

}