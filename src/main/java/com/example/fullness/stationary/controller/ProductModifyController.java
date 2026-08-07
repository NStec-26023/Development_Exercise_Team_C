package com.example.fullness.stationary.controller;

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

import com.example.fullness.stationary.controller.form.ProductModifyForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.service.ProductModifyService;
import com.example.fullness.stationary.service.ProductSearchService;



@Controller
@RequestMapping("admin/product")
public class ProductModifyController {


    @Autowired
    private ProductModifyService productModifyService;
    @Autowired
    private ProductSearchService productSearchService; 

    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer proId, Model model) {
        
        Product product = productModifyService.findByProId(proId);

        ProductModifyForm form = new ProductModifyForm();
        //8/18ここをなおすことから
        // form.setProId(product.getProId());
        // form.setName(product.getName());
        // form.setPrice(product.getPrice());
        // form.setStock(product.getStock());
        // form.setCatId(product.getCatId()); 
        // form.setImagePath(product.getImagePath()); 

        
        model.addAttribute("form", form);

        
        List<ProductCategory> categories = productSearchService.findAllProductCategory();
        model.addAttribute("categories", categories);

        return "admin/product/edit"; // 修正入力画面のテンプレート
    }

    
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Integer id,
                                @ModelAttribute("form") ProductModifyForm form,
                                Model model) {
        
        form.setProId(id);

        return "redirect:/admin/product";
    }
}



