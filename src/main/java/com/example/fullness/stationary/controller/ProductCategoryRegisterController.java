package com.example.fullness.stationary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fullness.stationary.controller.form.ProductCategoryRegisterForm;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.service.ProductCategoryRegisterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/category")
public class ProductCategoryRegisterController {

    @Autowired
    ProductCategoryRegisterService productCategoryRegisterService;

    // 入力画面表示
    @GetMapping("/add")
    public String showAdd(Model model) {
        model.addAttribute("form", new ProductCategoryRegisterForm());
        return "admin/category/form";
    }


    // 確認画面へpost送信
    @PostMapping("/add/input")
    public String validateAdd(
        @Valid @ModelAttribute("form") ProductCategoryRegisterForm productCategoryRegisterForm, 
        BindingResult bindingResult,
        HttpSession httpSession) {

        //未入力・文字数のバリデーションエラー
        if (bindingResult.hasErrors()){
            return "admin/category/form";
        }

        //重複チェック
        if (productCategoryRegisterService.existProductCategory(productCategoryRegisterForm.getName())){
            bindingResult.rejectValue("name","doubleName","入力されたカテゴリ名は既に登録されています");
            return "admin/category/form";
        }
        
        //入力値をセッションに保存
        httpSession.setAttribute("form",productCategoryRegisterForm);

        //確認画面へリダイレクト
        return "redirect:/admin/category/add/confirm";

    }

    //確認画面表示
    @GetMapping("add/confirm")
    public String showConfirm(HttpSession httpSession,Model model){
        ProductCategoryRegisterForm productCategoryRegisterForm
        = (ProductCategoryRegisterForm)
        httpSession.getAttribute("form");

        model.addAttribute("form",productCategoryRegisterForm);

        return "admin/category/confirm";
    }


    // 登録処理
    @PostMapping("add/confirm/input")
    public String register(HttpSession httpSession){

        ProductCategoryRegisterForm productCategoryRegisterForm
        = (ProductCategoryRegisterForm)
        httpSession.getAttribute("form");

        //Entityに変換
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(productCategoryRegisterForm.getName());

        //DB登録
        productCategoryRegisterService.registerProductCategory(productCategory);

        //完了画面に表示する名前だけ保存
        httpSession.setAttribute("registeredCategoryName",productCategoryRegisterForm.getName());

        //完了画面にリダイレクト
        return "redirect:/admin/category/add/complete";
    }

    //完了画面表示
    @GetMapping("add/complete")
    public String showComplete(HttpSession httpSession,Model model){
        String registeredCategoryName = (String)
        httpSession.getAttribute("registeredCategoryName");

        model.addAttribute("registeredCategoryName",registeredCategoryName);

        //使い終わったら削除
        httpSession.removeAttribute("registeredCategoryName");
        httpSession.removeAttribute("form");

        return "admin/category/complete";

    }





}
