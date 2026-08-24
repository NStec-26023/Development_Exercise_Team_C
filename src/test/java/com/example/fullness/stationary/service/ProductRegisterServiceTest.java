package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductRegisterRepository;

@SpringBootTest
@Transactional
public class ProductRegisterServiceTest {

    @Autowired 
    private ProductRegisterService productRegisterService;

    @Autowired 
    private ProductRegisterRepository productRegisterRepository;

    private ProductRegisterForm form;

    @BeforeEach
    void setUp() {
        // データベースに今あるカテゴリの一覧を取得
        List<ProductCategory> categories = productRegisterRepository.findAllCategories();
        
        int testCatId = 101; // 始まりが101なので初期値を設定
        if (categories != null && !categories.isEmpty()) {
            testCatId = categories.get(0).getCatId(); // DBにデータがあればそのIDを使う
        }

        // テスト用の入力データを準備
        form = new ProductRegisterForm();
        form.setCatId(testCatId); 
        form.setName("結合テスト用消しゴム");
        form.setPrice(120);
        form.setQuantity(30);
    }

    @Test
    @DisplayName("getAllCategories: 本物のDBからカテゴリ一覧が取得できること")
    void testGetAllCategories() {
        // 実行
        List<ProductCategory> result = productRegisterService.getAllCategories();

        // 検証（中身がnullでないこと）
        assertNotNull(result);
    }

    @Test
    @DisplayName("saveProduct: 本物のDBに対して商品と在庫がエラーなく連続登録できること")
    void testSaveProduct() {
        // 実行して、エラー（例外）が発生しないことを検証します
        // 本物のDBにデータがインサートされ、最後の在庫登録まで一気に進みます
        assertDoesNotThrow(() -> productRegisterService.saveProduct(form));
        
    }
}
