package com.example.fullness.stationary.entity;

import lombok.Data;

@Data
public class Product {

    private Integer pro_id;

    private String name;

    private Integer price;

    private String image_url;

    private Integer cat_id;

    private Integer delete_flg;

}
