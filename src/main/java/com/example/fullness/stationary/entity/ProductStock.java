package com.example.fullness.stationary.entity;

import lombok.Data;

@Data
public class ProductStock {

    // 商品在庫ID
    private Integer stoId;

    // 商品在庫数
    private Integer quantity;

    // 商品ID（外部キー）
    private Integer proId;
}
