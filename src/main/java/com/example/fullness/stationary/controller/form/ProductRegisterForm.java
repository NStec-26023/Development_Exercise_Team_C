package com.example.fullness.stationary.controller.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRegisterForm implements Serializable {

    // １．商品名
    @NotBlank(message = "商品名を入力してください")
    @Size(max = 20)
    private String name;

    // ２．単価
    @NotNull(message = "価格を入力してください")
    // @Min(value = 0, message = "")
    @Max(value = 1000000, message = "価格は100万円以下で入力してください")
    private Integer price;

    // ３．在庫数
    @NotNull(message = "在庫数を入力してください")
    @Max(value = 1000, message = "在庫数は1000個以下で入力してください")
    private Integer quantity;

    //４．カテゴリID
    @NotNull(message = "カテゴリを選択してください")
    private Integer catId;

    // //５．画像ファイル
    // private MultipartFile image;

    // //６．画像のファイルパス
    // private String imageUrl;

}
