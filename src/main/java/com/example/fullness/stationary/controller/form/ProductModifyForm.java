package com.example.fullness.stationary.controller.form;


import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductModifyForm implements Serializable {

    private Integer proId;
    private String name;
    private Integer price;
    private Integer stock;
    private Integer catId;
    private String imagePath;
    private MultipartFile image;
}
