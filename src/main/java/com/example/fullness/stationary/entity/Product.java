package com.example.fullness.stationary.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Product implements Serializable {

    private Integer proId;
    private Integer id;
    private String name;
    private Integer price;
    private String imagePath;
    private String imageUrl;
    private Integer catId;
    private Integer deleteFlg;
    private String categoryName;
    private Integer stock;
    private Integer quantity;

}