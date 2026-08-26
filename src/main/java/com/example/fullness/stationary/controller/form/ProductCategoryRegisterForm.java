package com.example.fullness.stationary.controller.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductCategoryRegisterForm implements Serializable {
    /**
     * 商品カテゴリ名
     */
    @NotBlank(message = "カテゴリ名を入力してください")
    @Size(max = 30, message = "カテゴリ名は1～30文字で入力してください")
    private String name;

}
