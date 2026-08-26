package com.example.fullness.stationary.repository;

//import org.junit.jupiter.api.AfterEach;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.jdbc.core.JdbcTemplate;


import com.example.fullness.stationary.entity.ProductCategory;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductCategoryRegisterRepositoryTest {

    @Autowired
    ProductCategoryRegisterRepository productCategoryRegisterRepository;

    //商品カテゴリ名登録
    @Test
    void testInsertProductCategory_OK(){

        // 1. テストデータ（Entity）を作成
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("インテリア");

        // 2. テスト対象メソッドの実行
        productCategoryRegisterRepository.insertProductCategory(productCategory);

        // 3.正常に登録されたのかを確認
        // ProductCategory result = productCategoryRegisterRepository.findByName("インテリア");

        // assertNotNull(result, "インサートしたデータが取得できませんでした");
        // assertEquals("インテリア", result.getName(), "保存された名前が一致しません");
        
        // 3.IDが自動で採番（セット）されたかを確認（期待値104と一致）
        Assertions.assertNotNull(productCategory.getCatId(), "IDが採番されていません");
        Assertions.assertEquals(104, productCategory.getCatId(), "IDが104になっていません");
    }


    // @Test
    // void testFindByName_OK(){

    //     // 1.検索メソッドを動かす（すでに登録済のデータを使用する）
    //     ProductCategory result = productCategoryRegisterRepository.findByName("文房具");

    //     // 2.意図通りのデータが取得できているかチェック
    //     assertNotNull(result, "インサートしたデータが取得できませんでした");
    //     assertEquals("文房具", result.getName(), "保存された名前が一致しません");
    // }


}
