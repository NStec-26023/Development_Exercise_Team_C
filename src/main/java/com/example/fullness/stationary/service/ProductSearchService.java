package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.repository.ProductSearchRepository;

//商品検索サービスクラス

@Service
@Transactional
public class ProductSearchService {

    @Autowired
    private ProductSearchRepository productSearchRepository;

    public List<ProductCategory> findAllProductCategory() {
        return productSearchRepository.selectAllProductCategory();
    }

    public List<Product> findByCategory(Integer catId) {
        if (catId == null) {
            return productSearchRepository.selectAll();
        }
        return productSearchRepository.selectByCatId(catId);
    }

}
