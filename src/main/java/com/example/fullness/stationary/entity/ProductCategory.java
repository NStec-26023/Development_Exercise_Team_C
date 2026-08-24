package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductCategory implements Serializable {
    /**
     * 商品カテゴリID
     * 
     */
    private Integer catId;

    /**
     * 商品カテゴリ名
     */
    private String name;

}
