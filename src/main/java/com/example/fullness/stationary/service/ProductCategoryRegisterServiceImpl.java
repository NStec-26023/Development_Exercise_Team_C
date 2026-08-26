package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRegisterRepository;

/**
 * UC014商品カテゴリ登録：サービス
 */
@Service
public class ProductCategoryRegisterServiceImpl implements ProductCategoryRegisterService {

    @Autowired
    private ProductCategoryRegisterRepository productCategoryRegisterRepository;

    /** 商品カテゴリ登録 */
    @Override
    public void registerProductCategory(ProductCategory productCategory) {
        productCategoryRegisterRepository.insertProductCategory(productCategory);
    }


    /* 商品カテゴリ重複チェック */
    @Override
    public boolean existProductCategory(String name) {
        return productCategoryRegisterRepository.findByName(name) != null;
    }

}
