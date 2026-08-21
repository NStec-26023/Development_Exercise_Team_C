package com.example.fullness.stationary.controller.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//商品修正のフォーム

@Data
public class ProductModifyForm implements Serializable {

    private Integer proId;
    private String name;
    private Integer price;
    private Integer stock;
    private Integer catId;
    private String imageUrl;
    private MultipartFile image;
    private String catName;
}
