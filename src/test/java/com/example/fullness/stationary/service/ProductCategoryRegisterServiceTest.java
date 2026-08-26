package com.example.fullness.stationary.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductCategoryRegisterRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductCategoryRegisterServiceTest {

    @InjectMocks
    private ProductCategoryRegisterServiceImpl productCategoryRegisterService;


    @Mock
    private ProductCategoryRegisterRepository productCategoryRegisterRepository;




    /** 商品カテゴリ登録 */
    @Test
    public void testRegisterProductCategory_OK() {

        //テストデータ
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("インテリア");
        
        //テスト対象メソッド実行
        productCategoryRegisterService.registerProductCategory(productCategory);

        //insertが1回呼び出されたことを確認
        verify(productCategoryRegisterRepository,times(1)).insertProductCategory(productCategory);
    }



}
