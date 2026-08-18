package com.example.fullness.stationary.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.fullness.stationary.controller.form.ProductModifyForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.service.ProductModifyService;
import com.example.fullness.stationary.service.ProductSearchService;

@Controller
@RequestMapping("/admin/product")
public class ProductModifyController {

    @Autowired
    private ProductModifyService productModifyService;

    // 画像ファイルを保存するディレクトリパス（環境に合わせて変更してください）
    // private final String UPLOAD_DIR = "src/main/resources/static/images/";

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        List<ProductCategory> categories = productModifyService.findAllProductCategory();
        model.addAttribute("categories", categories);

        ProductStock productStock = productModifyService.findByProIdWithProduct(id);
        
        ProductModifyForm form = new ProductModifyForm();
        if (productStock != null) {
            form.setProId(productStock.getProId());
            form.setStock(productStock.getQuantity());
            
            if (productStock.getProducts() != null && !productStock.getProducts().isEmpty()) {
                Product product = productStock.getProducts().get(0);
                form.setName(product.getName());
                form.setPrice(product.getPrice());
                form.setCatId(product.getCatId());
                // form.setImageUrl(product.getImageUrl());
            }
        }
        
        model.addAttribute("form", form);
        return "admin/product/edit_form"; 
    }

    // @PostMapping("/edit/{id}")
    // public String updateProduct(
    //         @PathVariable("id") Integer id,
    //         @ModelAttribute("form") ProductModifyForm form,
    //         Model model) {
    //             // 1. 在庫エンティティの作成
    //         ProductStock productStock = new ProductStock();
    //         productStock.setProId(id);
    //         productStock.setQuantity(form.getStock());

    //         // 2. 商品エンティティの作成
    //         Product product = new Product();
    //         product.setProId(id);
    //         product.setName(form.getName());
    //         product.setPrice(form.getPrice());
    //         product.setCatId(form.getCatId());
    //         / 3. 画像ファイル（MultipartFile）の保存処理
    //         MultipartFile file = form.getImage();
    //         if (file != null && !file.isEmpty()) {
    //         try {
    //             // ファイル名の重複を防ぐため、UUIDを使用して固有のファイル名を作成
    //             String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
    //             Path uploadPath = Paths.get(UPLOAD_DIR);

    //             // 保存先ディレクトリが存在しない場合は作成
    //             if (!Files.exists(uploadPath)) {
    //                 Files.createDirectories(uploadPath);
    //             }
    //             Path filePath = uploadPath.resolve(fileName);
    //             Files.copy(file.getInputStream(), filePath);

    //             // データベースに保存する用のファイルパス（Thymeleafで表示できる形式）をセット
    //             product.setImageUrl("/images/" + fileName);

    //         } catch (IOException e) {
    //             e.printStackTrace();
    //             // 必要に応じてエラーメッセージを画面に返す処理を追加してください
    //         }
    //     } else {
    //         // 新しい画像がアップロードされなかった場合は、元の画像パスをそのまま維持する
    //         product.setImageUrl(form.getImagePath());
    //     }
    //     productModifyService.updateProductAndStock(product, productStock);

    //     return "redirect:/admin/product";
    // }
// @Controller
// @RequestMapping("admin/product")
// public class ProductModifyController {


//     @Autowired
//     private ProductModifyService productModifyService;

//     @GetMapping("/edit/{id}")
//     public String showEditForm(@PathVariable("id") Integer id, Model model) {

//         List<ProductCategory> categories = productModifyService.findAllProductCategory();
//         model.addAttribute("categories", categories);
//         ProductModifyForm form = productModifyService.findByProIdWithProduct(id);
//         model.addAttribute("form", form);
//         return "admin/product/edit_form"; 
//     }

//     @PostMapping("/edit/{id}")
//     public String updateProduct(
//             @PathVariable("id") Integer id,
//             @ModelAttribute("form") ProductModifyForm form,
//             Model model) {


//         //  enctype="multipart/form-data" からの画像ファイル保存処理

//         ProductStock productStock = new ProductStock();
//         productStock.setProId(id);
//         productStock.setQuantity(form.getStock());

//         productModifyService.updateProductStock(productStock);

//         return "redirect:/admin/product";


//     }

}



