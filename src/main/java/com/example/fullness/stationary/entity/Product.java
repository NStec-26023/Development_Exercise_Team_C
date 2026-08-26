package com.example.fullness.stationary.entity;

import lombok.Data;

@Data
public class Product {

    // 商品ID
    private Integer proId;

    // 商品名
    private String name;

    // 価格
    private Integer price;

    // 画像URL
    private String imageUrl;

    // 商品カテゴリID（外部キー）
    private Integer catId;

    // 削除フラグ（1:削除）
    private Integer deleteFlg;

}
