package com.example.fullness.stationary.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Product {

    // 商品ID
    private Integer proId;
    private Integer id;
    private String name;

    // 価格
    private Integer price;
    private String imagePath;
    private String imageUrl;

    // 商品カテゴリID（外部キー）
    private Integer catId;

    // 削除フラグ（1:削除）
    private Integer deleteFlg;
    private String categoryName;
    private Integer stock;
    private Integer quantity;

}