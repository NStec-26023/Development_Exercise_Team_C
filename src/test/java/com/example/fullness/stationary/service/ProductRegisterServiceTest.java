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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.controller.form.ProductRegisterForm;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductRegisterRepository;

@ExtendWith(MockitoExtension.class)
public class ProductRegisterServiceTest {

    @Mock
    private ProductRegisterRepository productRegisterRepository;

    @InjectMocks
    private ProductRegisterService productRegisterService;

    @Test
    @DisplayName("getAllCategories: リポジトリを1回だけ呼び出して、カテゴリ一覧を正常に渡していること")
    void testGetAllCategories() {
        // 1. 【準備】リポジトリが返すダミーデータを作る
        ProductCategory dummyCat = new ProductCategory();
        dummyCat.setCatId(101);
        dummyCat.setName("文房具");
        List<ProductCategory> dummyList = Arrays.asList(dummyCat);

        // リポジトリが呼ばれたら、このダミーを返すように設定
        when(productRegisterRepository.findAllCategories()).thenReturn(dummyList);

        // 2. 【実行】サービスを動かす
        List<ProductCategory> result = productRegisterService.getAllCategories();

        // 3. 【検証】
        // 1回だけリポジトリを呼び出したか？（サービスの機能を検証）
        verify(productRegisterRepository, times(1)).findAllCategories();

        // 届いたデータが、リポジトリから貰ったものと一致しているか？
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("文房具", result.get(0).getName());
    }

    @Test
    @DisplayName("saveProduct: 商品と在庫の登録メソッドがそれぞれ1回ずつ正しく呼び出されること")
    void testSaveProduct_Mock() {
        // 1. 【準備】 テスト用の入力データ（画面からのフォームを想定）
        ProductRegisterForm testForm = new ProductRegisterForm();
        testForm.setCatId(101);
        testForm.setName("色鉛筆");
        testForm.setPrice(150); // 価格
        testForm.setQuantity(45); // 在庫数

        // 2. 【実行】 サービスを実行
        assertDoesNotThrow(() -> productRegisterService.saveProduct(testForm));

        // 3. 【検証】 サービスがサボらずに両方登録したか verify でチェック

        // ① 商品登録のメソッドが「一回だけ」呼び出されたか？
        verify(productRegisterRepository, times(1)).insertProduct(any(Product.class));

        // ② 在庫登録のメソッドが「一回だけ」呼び出されたか？
        verify(productRegisterRepository, times(1)).insertProductStock(any(ProductStock.class));
    }

}
