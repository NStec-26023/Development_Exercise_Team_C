package com.example.fullness.stationary.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.util.TestUtil;

//商品検索レポジトリのテスト

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductSearchRepositoryTest {

    @Autowired
    ProductSearchRepository productSearchRepository;

    @Test
    public void testSelectAllProductCategory() {
        List<ProductCategory> productCategory = productSearchRepository.selectAllProductCategory();
        Assertions.assertEquals(TestUtil.getExpectedCategories(), productCategory);
    }

    @Test
    public void testSelectAll() {
        List<Product> product = productSearchRepository.selectAll();
        Assertions.assertEquals(TestUtil.getExpectedAllProducts(), product);
    }

    @Test
    public void testselectByCatId() {
        List<Product> product = productSearchRepository.selectByCatId(102);
        Assertions.assertEquals(TestUtil.getExpectedProductsByCatId102(), product);
    }

}
