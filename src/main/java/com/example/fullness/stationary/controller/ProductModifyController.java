package com.example.fullness.stationary.controller;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.controller.form.ProductModifyForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.service.ProductModifyService;

//商品修正コントローラークラス

@Controller
@RequestMapping("/admin/product")
@SessionAttributes("form")
public class ProductModifyController {

    @Autowired
    private ProductModifyService productModifyService;

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
                form.setImageUrl(product.getImageUrl());
            }
        }

        model.addAttribute("form", form);
        return "admin/product/edit_form";
    }

    @PostMapping("/edit/{id}")
    public String confirmUpdate(
            @Validated @ModelAttribute("form") ProductModifyForm form,
            BindingResult bindingResult,
            @RequestParam(value = "image", required = false) MultipartFile imageFile,
            Model model) {

        String catName = productModifyService.findCatNameByCatId(form.getCatId());
        form.setCatName(catName);

        model.addAttribute("form", form);
        return "admin/product/edit_confirm";
    }

    @PostMapping(value = "/edit/confirm", params = "action=complete")
    public String executeUpdate(@ModelAttribute("form") ProductModifyForm form, SessionStatus sessionStatus,
            RedirectAttributes redirectAttributes) {
        Integer id = form.getProId();

        ProductStock productStock = new ProductStock();
        productStock.setProId(id);
        productStock.setQuantity(form.getStock());

        Product product = new Product();
        product.setProId(id);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setCatId(form.getCatId());
        product.setImageUrl(form.getImageUrl());
        productModifyService.updateProductAndStock(product, productStock);

        redirectAttributes.addAttribute("name", form.getName());

        sessionStatus.setComplete();

        return "redirect:/admin/product/edit/complete";
    }

    @PostMapping(value = "/edit/confirm", params = "action=back")
    public String backToEditForm(
            @ModelAttribute("form") ProductModifyForm form,
            Model model) {

        List<ProductCategory> categories = productModifyService.findAllProductCategory();
        model.addAttribute("categories", categories);

        return "admin/product/edit_form";
    }

    @GetMapping("/edit/complete")
    public String showCompletePage(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("productName", name);
        return "admin/product/edit_complete";
    }
}