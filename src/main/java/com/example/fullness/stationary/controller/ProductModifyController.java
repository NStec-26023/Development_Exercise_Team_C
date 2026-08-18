package com.example.fullness.stationary.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.fullness.stationary.controller.form.ProductModifyForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.service.ProductModifyService;
import com.example.fullness.stationary.service.ProductSearchService;

@Controller
@RequestMapping("/admin/product")
// @SessionAttributes("form")
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

    @PostMapping("/edit/{id}")
    public String confirmUpdate(
            @Validated @ModelAttribute("form") ProductModifyForm form,
            BindingResult bindingResult,
            Model model) {


        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", productModifyService.findAllProductCategory());
            return "admin/product/edit_form";
        }

        String catName = productModifyService.findCatNameByCatId(form.getCatId());
        form.setCatName(catName);


        model.addAttribute("form", form);
        return "admin/product/edit_confirm"; 
    }

    // @PostMapping("/edit/execute/{id}")
    // public String executeUpdate(
    //         @PathVariable("id") Integer id,
    //         @ModelAttribute("form") ProductModifyForm form) {

    //     ProductStock productStock = new ProductStock();
    //     productStock.setProId(id);
    //     productStock.setQuantity(form.getStock());

    //     Product product = new Product();
    //     product.setProId(id);
    //     product.setName(form.getName());
    //     product.setPrice(form.getPrice());
    //     product.setCatId(form.getCatId());
    //     product.setImageUrl(form.getImageUrl());

        
    //     productModifyService.updateProductAndStock(product, productStock);

    //     return "admin/product/edit_complete"; 

}




