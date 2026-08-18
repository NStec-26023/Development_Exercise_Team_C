package com.example.fullness.stationary.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ProductStock implements Serializable{

    private Integer stoId;
    private Integer quantity;
    private Integer proId;
    private List<Product> products;
}
