package com.example.fullness.stationary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.ProductCategory;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductModifyRepository;

@Service
@Transactional
public class ProductModifyService {

    @Autowired
    private ProductModifyRepository productModifyRepository;
    
    public List<ProductCategory> findAllProductCategory(){
        return productModifyRepository.selectAllProductCategory();
    }

    public ProductStock findByProIdWithProduct(Integer proId){
        return productModifyRepository.selectByProIdWithProduct(proId);
    }

    // public void updateProductStock(ProductStock productStock) {
    //     productModifyRepository.updateByProId(productStock);
    // }

}
