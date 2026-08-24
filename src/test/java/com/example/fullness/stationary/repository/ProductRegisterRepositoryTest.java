package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;

@SpringBootTest
@Transactional
public class ProductRegisterRepositoryTest {

    @Autowired
    private ProductRegisterRepository productRegisterRepository;

    @Test
    @DisplayName("findAllCategories: カテゴリ一覧がデータベースから取得できること")
    void testFindAllCategories() {
        // 1. 実行
        List<ProductCategory> categories = productRegisterRepository.findAllCategories();

        // 2. 検証
        assertNotNull(categories);
        // ※テスト用DBにデータが入っていれば、0より大きいことをチェックできます
        // assertTrue(categories.size() > 0);
    }

    @Test
    @DisplayName("insertProduct と insertProductStock: 商品と在庫が正しくインサートできること")
    void testInsertProductAndStock() {
        // 1. 商品の登録テスト
        Product product = new Product();
        product.setCatId(101); // データベースに存在するカテゴリIDを指定してください
        product.setName("テスト用ボールペン");
        product.setPrice(150);

        // インサートを実行
        productRegisterRepository.insertProduct(product);

        // MyBatisの <selectKey> や useGeneratedKeys が設定されていれば、自動生成されたIDがセットされます
        assertNotNull(product.getProId(), "商品IDが自動生成されていること");

        // 2. 在庫の登録テスト
        ProductStock productStock = new ProductStock();
        productStock.setProId(product.getProId()); // 採番された商品IDをセット
        productStock.setQuantity(100);

        // インサートを実行
        // エラーなく実行できれば、SQLの構文やカラム名が正しいことが証明されます
        assertDoesNotThrow(() -> productRegisterRepository.insertProductStock(productStock));
    }
}
