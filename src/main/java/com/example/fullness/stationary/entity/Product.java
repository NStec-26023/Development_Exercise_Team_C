package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

//商品

@Data
public class Product implements Serializable {

    private Integer proId;
    private String name;
    private Integer price;
    private String imageUrl;
    private Integer catId;
    private Integer deleteFlg;

}
