package com.example.fullness.stationary.repository;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.util.TestUtil;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.junit.jupiter.api.Assertions;

//商品修正レポジトリのテスト

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductModifyRepositoryTest {

    @Autowired
    ProductModifyRepository productModifyRepository;

    @Test
    public void testSelectAllProductCategory() {
        List<ProductCategory> productCategory = productModifyRepository.selectAllProductCategory();
        Assertions.assertEquals(TestUtil.getExpectedCategories(), productCategory);
    }

    // @Test
    // public void testSelectByProIdWithProduct() {
    // ProductStock productStock
    // =productModifyRepository.selectByProIdWithProduct();
    // Assertions.assertEquals(TestUtil., productStock);
    // }

    // @Test
    // public void testSelectCatNameByCatId() {
    // String actual =productModifyRepository.selectByProIdWithProduct();
    // Assertions.assertEquals(TestUtil., productStock);
    // }

    // @Test
    // public void testUpdateProductStock() {
    // }

    // @Test
    // public void testUpdateProduct() {
    // }

}
