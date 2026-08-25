package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Autowired
    private ProductSearchRepository productSearchRepository;

    @Test
    @DisplayName("findAllCategories: カテゴリ一覧がデータベースから取得でき、中身が正しいこと")
    void testFindAllCategories() {
        // 1. 実行
        List<ProductCategory> categories = productRegisterRepository.findAllCategories();

        // 2. 検証
        assertNotNull(categories);
        // 件数の検証
        assertEquals(3, categories.size());

        // 中身（データ内容）の検証
        // 1件目のデータ検証
        assertEquals(101, categories.get(0).getCatId(), "1件目のカテゴリIDが正しいこと");
        assertEquals("文房具", categories.get(0).getName(), "1件目のカテゴリ名が正しいこと");

        // 2件目のデータ検証
        assertEquals(102, categories.get(1).getCatId(), "2件目のカテゴリIDが正しいこと");
        assertEquals("PC雑貨", categories.get(1).getName(), "2件目のカテゴリ名が正しいこと");

        // 3件目のデータ検証
        assertEquals(103, categories.get(2).getCatId(), "3件目のカテゴリIDが正しいこと");
        assertEquals("ノート・紙雑貨", categories.get(2).getName(), "3件目のカテゴリ名が正しいこと");
    }

    @Test
    @DisplayName("insertProduct と insertProductStock: 商品と在庫が正しくインサートでき、件数が増えること")
    void testInsertProductAndStock() {
        // 【追加】インサート前の現在の件数をそれぞれ数えておく
        int beforeProductCount = productSearchRepository.selectAll().size();
        int beforeStockCount = productRegisterRepository.findAllPuroductStock().size();

        // 1. 商品の登録テスト
        Product product = new Product();
        product.setCatId(101); // データベースに存在するカテゴリIDを指定してください
        product.setName("色鉛筆");
        product.setPrice(150);

        // インサートを実行
        productRegisterRepository.insertProduct(product);

        // MyBatisの <selectKey> や useGeneratedKeys が設定されていれば、自動生成されたIDがセットされます
        assertNotNull(product.getProId(), "商品IDが自動生成されていること");

        // 【追加】商品テーブルの件数が「前の件数 + 1」になっていることを assertEquals で検証
        int afterProductCount = productSearchRepository.selectAll().size();
        assertEquals(beforeProductCount + 1, afterProductCount, "商品テーブルにデータが1件追加されていること");


        // 2. 在庫の登録テスト
        ProductStock productStock = new ProductStock();
        productStock.setProId(product.getProId()); // 採番された商品IDをセット
        productStock.setQuantity(100);

        // インサートを実行
        // エラーなく実行できれば、SQLの構文やカラム名が正しいことが証明されます
        assertDoesNotThrow(() -> productRegisterRepository.insertProductStock(productStock));

        // 【追加】在庫テーブルの件数が「前の件数 + 1」になっていることを assertEquals で検証
        int afterStockCount = productRegisterRepository.findAllPuroductStock().size();
        assertEquals(beforeStockCount + 1, afterStockCount, "在庫テーブルにデータが1件追加されていること");
    }

}
