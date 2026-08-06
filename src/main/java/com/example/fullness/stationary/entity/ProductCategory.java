package com.example.fullness.stationary.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductCategory implements Serializable{

    private Integer catId;
    private String name;
}
