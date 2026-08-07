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
@RequestMapping("admin/product")
public class ProductSearchController {
    @Autowired
    private ProductSearchService productSearchService; 


    @GetMapping
    public String searchProducts(
            @RequestParam(name = "catId", required = false) Integer catId,
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {

            List<ProductCategory> categories = productSearchService.findAllProductCategory();
            model.addAttribute("categories", categories);
            model.addAttribute("selectedCategoryId", catId);


            List<Product> list = productSearchService.findByCategory(catId);
            model.addAttribute("products", list);

            // List<Product> products = productSearchService.findByCategoryAndPage(catId, page);
            // int totalPages = productSearchService.getTotalPages(catId); 
        
            // model.addAttribute("products", products);       
            // model.addAttribute("currentPage", page);         
            // model.addAttribute("totalPages", totalPages);   

            return "admin/product/search"; 
        }
    }

    // @ModelAttribute("category")
    // public List<ProductCategory> setUpCategories() {
    //     List<ProductCategory> list =  productSearchService.findAllProductCategory();

    //     return list;
    // }
    // @ModelAttribute("productCategoryForm")
    // public ProductCategoryForm setUpForm() {
    //     return new ProductCategoryForm();
    // }
    // @GetMapping("/search")
    // public String showSearchForm(Model model) {

    //     List<ProductCategory> productCategory = productSearchService.findAllProductCategory();

    //     model.addAttribute("category", productCategory);

    //     List<Product> allProducts = productSearchService.findAll();

    //     model.addAttribute("productList", allProducts);
        
    //     return "admin/product/search"; 
    // }
//     @PostMapping("/search")
//     public String searchProducts(@ModelAttribute ProductCategoryForm productCategoryForm,Model model) {
//     // public String searchProducts(@RequestParam(required = false) Integer catId,Model model) {
        
//         List<Product> list = productSearchService.findByCategory(productCategoryForm.getCatId());
        
//         model.addAttribute("productList", list);
        
//         return "admin/product/search";
//     }
// }
