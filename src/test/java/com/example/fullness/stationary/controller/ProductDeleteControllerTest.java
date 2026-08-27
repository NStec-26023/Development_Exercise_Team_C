package com.example.fullness.stationary.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.service.ProductDeleteService;

public class ProductDeleteControllerTest {

    MockMvc mockMvc;

    ProductDeleteController productDeleteController;
    ProductDeleteService productDeleteService; // モックを手動で作る

    @BeforeEach
    public void setUp() {
        // 1. コントローラーとサービスを自分でインスタンス化（またはモック化）する
        productDeleteController = new ProductDeleteController();
        productDeleteService = mock(ProductDeleteService.class); // Mockitoのmock()メソッドを使う方法

        // 2. コントローラーにモックのサービスをセットする
        // ※もしフィールドに直接代入できない場合は、ProductDeleteControllerに `productDeleteService`
        // のセッターを作ると楽です
        productDeleteController.productDeleteService = productDeleteService;

        // 3. MockMvcをビルド
        mockMvc = MockMvcBuilders.standaloneSetup(productDeleteController).build();
    }

    @Test
    public void testShowDeleteConfirm() throws Exception {
        Product mockProduct = new Product();
        mockProduct.setProId(3);
        mockProduct.setName("鉛筆(黒)");

        when(productDeleteService.getProductForDelete(3)).thenReturn(mockProduct);

        mockMvc.perform(get("/admin/product/delete/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/product/delete_confirm"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void testCompleteDirectAccess() throws Exception {
        // セッション検証なしで完了画面に直接アクセスした場合、エラー画面にリダイレクトされること
        mockMvc.perform(get("/admin/product/delete/complete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/error"));
    }
}