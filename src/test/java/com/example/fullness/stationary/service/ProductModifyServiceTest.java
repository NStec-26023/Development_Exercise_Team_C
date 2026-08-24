package com.example.fullness.stationary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.util.TestUtil;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class ProductModifyServiceTest {

    @Autowired
    private ProductModifyService productModifyService;

    @Test
    public void testFindAllProductCategory() {
        List<ProductCategory> actualCategories = productModifyService.findAllProductCategory();
        assertEquals(TestUtil.getExpectedCategories(), actualCategories);
    }

    @Test
    public void testFindByProIdWithProduct() {
        Integer targetProId = 1;
        ProductStock actualStock = productModifyService.findByProIdWithProduct(targetProId);
        assertEquals(TestUtil.getExpectedProductStock1(), actualStock);
    }

    @Test
    public void testFindCatNameByCatId() {
        Integer targetCatId = 101;
        String actualName = productModifyService.findCatNameByCatId(targetCatId);
        assertEquals(TestUtil.getExpectedCategoryName101(), actualName);
    }

    @Test
    public void testUpdateProductAndStock() {
        Integer targetProId = 1;
        ProductStock originalStock = productModifyService.findByProIdWithProduct(targetProId);

        ProductStock updateStock = new ProductStock();
        updateStock.setStoId(originalStock.getStoId());
        updateStock.setProId(originalStock.getProId());
        updateStock.setQuantity(88);

        Product updateProduct = new Product();
        updateProduct.setProId(TestUtil.pro101.getProId());
        updateProduct.setName("【サービス修正】" + TestUtil.pro101.getName());
        updateProduct.setPrice(180);
        updateProduct.setImageUrl(TestUtil.pro101.getImageUrl());
        updateProduct.setCatId(TestUtil.pro101.getCatId());
        updateProduct.setDeleteFlg(TestUtil.pro101.getDeleteFlg());

        productModifyService.updateProductAndStock(updateProduct, updateStock);

        ProductStock actualStock = productModifyService.findByProIdWithProduct(targetProId);
        assertEquals(updateStock.getQuantity(), actualStock.getQuantity());

        Product actualProduct = actualStock.getProducts().get(0);
        assertEquals(updateProduct.getName(), actualProduct.getName());
        assertEquals(updateProduct.getPrice(), actualProduct.getPrice());
    }

}
