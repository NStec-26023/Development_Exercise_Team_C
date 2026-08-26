package com.example.fullness.stationary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.fullness.stationary.entity.Product;
import com.example.fullness.stationary.entity.ProductStock;
import com.example.fullness.stationary.repository.ProductDeleteRepository;

@Service
@Transactional(readOnly = true)
public class ProductDeleteService {

    @Autowired
    ProductDeleteRepository delete;

    public Product getProductForDelete(Integer proId) {
        return delete.selectByProIdWithProduct(proId);
    }

    @Transactional
    public boolean deleteProduct(int proId) {
        return delete.deleteById(proId) > 0;
    }
}
