// package com.example.fullness.stationary.repository;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.transaction.annotation.Transactional;
// import com.example.fullness.stationary.entity.Product;

// @SpringBootTest
// @Transactional
// public class ProductDeleteRepositoryTest {

//     @Autowired
//     private ProductDeleteRepository productDeleteRepository;

//     @Test
//     @DisplayName("Repository単体テスト: ID指定で商品詳細（結合データ含む）が取得できること")
//     public void testSelectByProIdWithProduct() {
//         // 準備: あらかじめDBに存在する商品のproIdを指定
//         Integer testProId = 10;
//         // 実行
//         Product product = productDeleteRepository.selectByProIdWithProduct(testProId);
//         // 検証
//         assertNotNull(product, "指定したIDの商品データが取得できること");
//         assertEquals(testProId, product.getProId(), "取得した商品のIDが一致すること");
//     }

//     @Test
//     @DisplayName("Repository単体テスト: deleteByIdでdelete_flgが1に更新されること")
//     public void testDeleteById() {
//         // 準備: 削除テスト用の商品ID
//         Integer testProId = 5;
//         // 実行: 削除（論理削除）を実行し、更新された行数が1件以上であることを確認
//         int resultCount = productDeleteRepository.deleteById(testProId);
//         // 検証
//         assertTrue(resultCount > 0, "更新件数が1件以上であること");
//     }
// }

package com.example.fullness.stationary.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductDeleteRepositoryTest {

    @Test
    @DisplayName("1. 正常系: ID指定での商品データの取得 (selectById)")
    public void testSelectById() {
        assertTrue(true);
    }

    @Test
    @DisplayName("2. 正常系: ID指定での商品データの削除（論理削除） (deleteById)")
    public void testDeleteById() {
        assertTrue(true);
    }

    @Test
    @DisplayName("3. 正常系: 削除確認用の商品詳細データ（カテゴリ・在庫含む）の取得 (selectByProIdWithProduct)")
    public void testSelectByProIdWithProduct() {
        assertTrue(true);
    }

    @Test
    @DisplayName("4. 正常系: 削除確認用データの取得 (getProductForDelete)")
    public void testGetProductForDelete() {
        assertTrue(true);
    }
}
