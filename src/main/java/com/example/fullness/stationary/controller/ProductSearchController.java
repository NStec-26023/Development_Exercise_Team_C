package com.example.fullness.stationary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fullness.stationary.controller.form.ProductCategoryForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.service.ProductSearchService;


@Controller
@RequestMapping("products")
public class ProductSearchController {
    @Autowired
    private ProductSearchService productSearchService; 

    // @ModelAttribute("category")
    // public List<ProductCategory> setUpCategories() {
    //     List<ProductCategory> list =  productSearchService.findAllProductCategory();

    //     return list;
    // }
    @ModelAttribute("productCategoryForm")
    public ProductCategoryForm setUpForm() {
        return new ProductCategoryForm();
    }
    @GetMapping("/search")
    public String showSearchForm(Model model) {

        List<ProductCategory> productCategory = productSearchService.findAllProductCategory();

        model.addAttribute("category", productCategory);

        List<Product> allProducts = productSearchService.findAll();

        model.addAttribute("productList", allProducts);
        
        return "products/search"; 
    }
    @PostMapping("/search")
    public String searchProducts(@ModelAttribute ProductCategoryForm productCategoryForm,Model model) {
    // public String searchProducts(@RequestParam(required = false) Integer catId,Model model) {
        
        List<Product> list = productSearchService.findByCategory(productCategoryForm.getCatId());
        
        model.addAttribute("productList", list);
        
        return "products/search";
    }
}
