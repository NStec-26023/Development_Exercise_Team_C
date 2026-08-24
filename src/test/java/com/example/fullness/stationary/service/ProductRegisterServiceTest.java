package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductRegisterRepository;

public class ProductRegisterServiceTest {

    @Mock
    private ProductRegisterRepository productRegisterRepository;

    @InjectMocks
    private ProductRegisterService productRegisterService;

    private ProductRegisterForm form;

    @BeforeEach
    void setUp() {
        // ★ これを入れることで、@Mock や @InjectMocks が確実に動き出します！
        MockitoAnnotations.openMocks(this);

        // テストごとに使う共通の入力データ（Form）を準備
        form = new ProductRegisterForm();
        form.setCatId(10);
        form.setName("消しゴム");
        form.setPrice(100);
        form.setQuantity(50);
    }

    @Test
    @DisplayName("カテゴリ一覧が正しく取得できること")
    void testGetAllCategories() {
        // 1. 準備（モックの行動を決める）
        List<ProductCategory> mockCategories = Arrays.asList(
                new ProductCategory(),
                new ProductCategory());
        when(productRegisterRepository.findAllCategories()).thenReturn(mockCategories);

        // 2. 実行（テストしたいメソッドを呼ぶ）
        List<ProductCategory> result = productRegisterService.getAllCategories();

        // 3. 検証（結果が正しいかチェック）
        assertEquals(2, result.size());
        verify(productRegisterRepository, times(1)).findAllCategories();
    }

    @Test
    @DisplayName("商品と在庫が順番に正しく登録されること")
    void testSaveProduct() {
        // 1. 準備
        doAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            product.setProId(1); // 自動採番をシミュレート
            return null;
        }).when(productRegisterRepository).insertProduct(any(Product.class));

        // 2. 実行
        productRegisterService.saveProduct(form);

        // 3. 検証
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        ArgumentCaptor<ProductStock> stockCaptor = ArgumentCaptor.forClass(ProductStock.class);

        verify(productRegisterRepository, times(1)).insertProduct(productCaptor.capture());
        verify(productRegisterRepository, times(1)).insertProductStock(stockCaptor.capture());

        Product savedProduct = productCaptor.getValue();
        assertEquals(10, savedProduct.getCatId());
        assertEquals("消しゴム", savedProduct.getName());
        assertEquals(100, savedProduct.getPrice());

        ProductStock savedStock = stockCaptor.getValue();
        assertEquals(1, savedStock.getProId());
        assertEquals(50, savedStock.getQuantity());
    }
}
