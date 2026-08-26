package com.example.fullness.stationary.repository;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullness.stationary.entity.Product;
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

    @Test
    public void testSelectByProIdWithProduct() {
        ProductStock productStock = productModifyRepository.selectByProIdWithProduct(1);
        Assertions.assertEquals(TestUtil.getExpectedProductStock1(), productStock);
    }

    @Test
    public void testSelectCatNameByCatId() {
        String actual = productModifyRepository.selectCatNameByCatId(101);
        Assertions.assertEquals(TestUtil.getExpectedCategoryName101(), actual);
    }

    @Test
    public void testUpdateProductStock() {
        Integer targetProId = 1;
        ProductStock originalStock = productModifyRepository.selectByProIdWithProduct(targetProId);

        ProductStock updateStock = new ProductStock();
        updateStock.setStoId(originalStock.getStoId());
        updateStock.setProId(originalStock.getProId());
        updateStock.setQuantity(99);

        Integer result = productModifyRepository.updateProductStock(updateStock);
        Assertions.assertEquals(1, result);

        ProductStock actualStock = productModifyRepository.selectByProIdWithProduct(targetProId);
        Assertions.assertEquals(updateStock.getQuantity(), actualStock.getQuantity());
    }

    @Test
    public void testUpdateProduct() {

        Product updateProduct = new Product();
        updateProduct.setProId(TestUtil.pro101.getProId());
        updateProduct.setName("【修正】" + TestUtil.pro101.getName());
        updateProduct.setPrice(TestUtil.pro101.getPrice() + 50);
        updateProduct.setImageUrl(TestUtil.pro101.getImageUrl());
        updateProduct.setCatId(TestUtil.pro101.getCatId());
        updateProduct.setDeleteFlg(TestUtil.pro101.getDeleteFlg());

        Integer result = productModifyRepository.updateProduct(updateProduct);
        Assertions.assertEquals(1, result);

        ProductStock actualStock = productModifyRepository.selectByProIdWithProduct(updateProduct.getProId());
        Product actualProduct = actualStock.getProducts().get(0);

        Assertions.assertEquals(updateProduct.getName(), actualProduct.getName());
        Assertions.assertEquals(updateProduct.getPrice(), actualProduct.getPrice());
    }

}