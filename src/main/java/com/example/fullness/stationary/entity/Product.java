package com.example.fullness.stationary.entity;

import lombok.Data;

@Data
public class Product {
    private Integer proId; // 商品ID
    private String name; // 商品名
    private Integer price; // 価格
    private String imageUrl; // 画像URL
    private Integer catId; // カテゴリID
    private Integer deleteFlg; // 削除フラグ
    private Integer quantity; // 在庫数(product_stock)
    private String categoryName; // カテゴリ名(product_category)
}